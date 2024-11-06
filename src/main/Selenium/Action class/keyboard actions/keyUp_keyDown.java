import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class keyUp_keyDown{
    public static void main(String[] args) {
        ChromeOptions o=new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        WebDriver driver=new ChromeDriver(o);
        driver.get("https://text-compare.com");
        driver.manage().window().maximize();
        WebElement input=driver.findElement(By.id("inputText1"));
        driver.findElement(By.id("inputText1")).sendKeys("Welcome to Action class");
//        input.sendKeys(Keys.CONTROL+"a");
//        input.sendKeys(Keys.CONTROL+"c");
//        input.sendKeys(Keys.TAB);
//        input.sendKeys(Keys.CONTROL+"v");
// this is limited to this webelement only
        Actions action=new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        action.sendKeys(Keys.TAB).perform();
        action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
    }
}