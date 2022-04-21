import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SeleniumDayFour {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.github.com/");

        WebElement webElement = webDriver.findElement(By.cssSelector("[name='q']"));

        String s_serach = "Selenium";
        webElement.sendKeys(s_serach);
        webElement.sendKeys(Keys.ENTER);

        List<String> actuallist = webDriver.findElements(By.cssSelector(".repo-list-item")).stream()
                .map(webElement1 -> webElement1.getText().toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());

        System.out.println("No of list items : "+actuallist.size());
        System.out.println("Actual List : "+actuallist);
        String s_assert="selenium";

        List<String> expected_list = actuallist.stream().filter(s -> s.contains(s_assert)).collect(Collectors.toList());


        System.out.println("No of expected list items : "+expected_list.size());
        System.out.println("Expected List : "+expected_list);
        String s_assert_f="chandan";

        //Now Assertions
        //Our search phrase should capture
        Assert.assertEquals(actuallist,expected_list);

        webDriver.quit();
    }
}