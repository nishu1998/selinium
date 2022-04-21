import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.*;

public class SeleniumDayEightTest {
    static ChromeDriver chromeDriver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        //Its implicit wait for execution
        chromeDriver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

    @Test
    public void checkgitsearch() {
        chromeDriver.get("https://github.com/");

        WebElement searchInput = chromeDriver.findElement(By.cssSelector("[name='q']"));

        String searchPhrase = "selenium";

        searchInput.sendKeys(searchPhrase);
        searchInput.sendKeys(Keys.ENTER);

        List<String> actualItems = chromeDriver.findElements(By.cssSelector(".repo-list-item")).stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList());

        List<String> expectedItems = actualItems.stream()
                .filter(item -> item.contains(searchPhrase))
                .collect(Collectors.toList());

        System.out.println(LocalDateTime.now());
        Assertions.assertTrue(chromeDriver.findElement(By.cssSelector("[title='invalid title']")).isDisplayed());
        Assertions.assertEquals(expectedItems, actualItems);
    }

    @AfterAll
    public static void stopdpwnDriver() {

        System.out.println(LocalDateTime.now());
        chromeDriver.quit();
    }
}
