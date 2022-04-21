import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelneiumDayTwo {
    public static void main(String[] args) {
//Homework for DayOne
         WebDriverManager.chromedriver().setup();
        WebDriver driver =  new ChromeDriver();
        driver.get("https://www.amazon.com/");

        WebElement webElement = driver.findElement(By.cssSelector("[name='field-keywords']"));

        String s_serach = "iPhone 13";
        webElement.sendKeys(s_serach);
        webElement.sendKeys(Keys.ENTER);
        
        driver.quit();


    }
}
