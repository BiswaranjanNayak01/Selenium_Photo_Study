import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class StaleElementException {
    /*
    Element is present in the page ,but it is throwing StaleElementException because page or element is refreshed after
    driver.findElement() step...(it is present in DOM but not able to find because of refreshment.)
    --> Good part is,it never happens due to ajax or timing issues.
    --> Can be easily handled by simpy finding Element after Page or Element refresh.
     */
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.setHeadless(false);
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @Test
    public void unsupportedActionPositive1() {

        driver.get("https://www.letskodeit.com/login");
        driver.manage().window().maximize();
        WebElement loginform=driver.findElement(By.name("loginform"));
        loginform.click();
//        driver.navigate().refresh();
        loginform.click();
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("Babul");
    }

    @Test
    public void unsupportedActionPositive2() {

        driver.get("https://www.letskodeit.com/login");
        driver.manage().window().maximize();
        WebElement loginform=driver.findElement(By.name("loginform"));
        loginform.click();
        driver.navigate().refresh();
        WebElement Sameloginform=driver.findElement(By.name("loginform"));
        Sameloginform.click();
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("Babul");
    }

    @Test
    public void unsupportedActionNegative() {
        driver.get("https://www.letskodeit.com/login");
        driver.manage().window().maximize();
        WebElement loginform=driver.findElement(By.name("loginform"));
        loginform.click();
        driver.navigate().refresh();
        loginform.click();
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("Babul");
    }
}
