import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Robot_keyAction {

    //SELENIUM Code to demonstrate an example of Mouse movement and Keyboard operation

    public static void m() throws InterruptedException, AWTException {
        WebDriver driver=new ChromeDriver();
        Robot r = new Robot();
        WebElement unTB = driver.findElement(By.id("username"));
        unTB.sendKeys("admin");
        Thread.sleep(3000);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_A);
        r.keyPress(KeyEvent.VK_DELETE);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_DELETE);
    }
    public static void main(String[] args) throws InterruptedException, AWTException {
        //System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
        ChromeOptions o=new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        WebDriver driver=new ChromeDriver(o);
        driver.manage().window().maximize();
        driver.get("https://text-compare.com");
        WebElement input=driver.findElement(By.id("inputText1"));
        driver.findElement(By.id("inputText1")).sendKeys("Welcome to Action class");
        Actions action=new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Robot r = new Robot(); //Creating an object of Robot Class
//        r.mouseMove(300, 500); //move the mouse by x and y coordinate
//        r.keyPress(KeyEvent.VK_TAB);
//        action.sendKeys(Keys.TAB).perform();
        action.sendKeys(Keys.TAB).perform();
        action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        action.keyDown(Keys.CONTROL).sendKeys("q").perform();
        Thread.sleep(5000); // Explicitly waiting for the page to be loaded
       // Robot r = new Robot(); //Creating an object of Robot Class
        r.mouseMove(300, 500); //move the mouse by x and y coordinate
        r.keyPress(KeyEvent.VK_ALT); //press ALT key from keyboard
        r.keyPress(KeyEvent.VK_F); //press F key from keyboard
        r.keyRelease(KeyEvent.VK_F); //Release F key from keyboard
        r.keyRelease(KeyEvent.VK_ALT); //Release Alt key from keyboard
        Thread.sleep(3000);// Explicitly waiting for the action to be performed
        r.keyPress(KeyEvent.VK_W); //Press W key from keyboard to open a new private window
        r.keyRelease(KeyEvent.VK_W); //Release W key from keyboard
        Thread.sleep(3000); //Explicitly waiting for the action to be performed
        r.keyPress(KeyEvent.VK_F);
        r.keyRelease(KeyEvent.VK_F);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_W);
        //driver.close(); // It will close only the current browser window
//driver.quit(); // It will close all the browser windows opened by SELENIUM
    }

}
