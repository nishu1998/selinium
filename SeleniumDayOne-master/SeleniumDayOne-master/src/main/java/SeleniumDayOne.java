import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDayOne {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.get("https://github.com/");

        WebElement webElement = webDriver.findElement(By.cssSelector("[name='q']"));

        String s_serachitem = "Selenium";
        webElement.sendKeys(s_serachitem);
        webElement.sendKeys(Keys.ENTER);

       webDriver.quit();
       // webDriver.close();

    }
}
