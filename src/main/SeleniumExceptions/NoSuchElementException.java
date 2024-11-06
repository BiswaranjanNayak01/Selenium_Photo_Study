import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class NoSuchElementException {
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

        driver.get("https://www.letskodeit.com/home");
        driver.manage().window().maximize();
        WebElement sign_in = driver.findElement(By.linkText("SIGN IN"));
        sign_in.click();
        WebElement email= driver.findElement(By.id("email"));
        email.sendKeys("Babul");
        WebElement password= driver.findElement(By.id("login-password"));
        password.sendKeys("Nayak");
        WebElement login=driver.findElement(By.xpath("//button[@id=\"login\"]"));
        login.click();
    }

}
