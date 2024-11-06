// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TestCase1Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testCase1() {
    driver.get("https://www.google.com/search?q=tcs+share+price&sca_esv=562779362&sxsrf=AB5stBjxf_8mh1P3nNSy3XdjtxEGj7mXWg%3A1693936647043&source=hp&ei=B2z3ZPZJ1q3j4Q-Mnpww&iflsig=AD69kcEAAAAAZPd6FyVH_nTKhR4nABkHdxxTtrvLcoxP&ved=0ahUKEwi26ffghZSBAxXW1jgGHQwPBwYQ4dUDCAk&uact=5&oq=tcs+share+price&gs_lp=Egdnd3Mtd2l6Ig90Y3Mgc2hhcmUgcHJpY2UyDxAjGIoFGCcYnQIYRhj6ATILEAAYgAQYsQMYgwEyCxAAGIAEGLEDGIMBMhAQABiABBgUGIcCGLEDGIMBMgsQABiABBixAxiDATIFEAAYgAQyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAESOUWUABYAHAAeACQAQCYAboDoAG6A6oBAzQtMbgBA8gBAPgBAvgBAQ&sclient=gws-wiz");
    driver.manage().window().setSize(new Dimension(1066, 812));
    {
      WebElement element = driver.findElement(By.cssSelector(".V30e3e"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".V30e3e"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".V30e3e"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.cssSelector(".V30e3e")).click();
    driver.close();
  }
}
