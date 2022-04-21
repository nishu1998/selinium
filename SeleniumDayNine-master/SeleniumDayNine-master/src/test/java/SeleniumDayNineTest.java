import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.*;

public class SeleniumDayNineTest {
    static ChromeDriver chromeDriver;
    static WebDriverWait wait;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        //Its implicit wait for execution
        chromeDriver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

    @BeforeAll
    public static void setUpWait() {
        wait = new WebDriverWait(chromeDriver, 15);
    }

    private static void switchOffImplicitWait() {
        chromeDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
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
        switchOffImplicitWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".repo-list-item")));

        Assertions.assertEquals(expectedItems, actualItems);
    }

    @AfterAll
    public static void stopdpwnDriver() {

        System.out.println(LocalDateTime.now());
        chromeDriver.quit();
    }
}
