import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ElementClickInterceptedException {
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
        System.out.println("bredrttyhju==============");
    }

    @AfterMethod
    public void tearDown() {
        // driver.quit();
    }

    @Test
    public void duringClickAnotherUIElementIsOPeningForGettingInputNegative() {
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        WebElement checkbox = driver.findElement(By.cssSelector("input[name=\"add-flight-switch\"]"));
        driver.findElement(By.xpath("//div[@class=\"uitk-field has-floatedLabel-label has-icon\"]")).click();
        WebElement SecGoingToElement = driver.findElement(By.xpath("//input[@data-stid=\"destination_form_field-menu-input\"]"));
        SecGoingToElement.sendKeys("India");
        checkbox.click(); //---------------checkbox is not visible here.-----------------
    }

    @Test
    public void duringClickAnotherUIElementIsOPeningForGettingInputPositive1() {
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        WebElement checkbox = driver.findElement(By.cssSelector("input[name=\"add-flight-switch\"]"));
        driver.findElement(By.xpath("//div[@class=\"uitk-field has-floatedLabel-label has-icon\"]")).click();
        WebElement SecGoingToElement = driver.findElement(By.xpath("//input[@data-stid=\"destination_form_field-menu-input\"]"));
        SecGoingToElement.sendKeys("India");
        js.executeScript("arguments[0].click()",checkbox);
    }

    @Test
    public void duringClickAnotherUIElementIsOPeningForGettingInputPositive2() {

        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        WebElement checkbox = driver.findElement(By.cssSelector("input[name=\"add-flight-switch\"]"));
        driver.findElement(By.xpath("//div[@class=\"uitk-field has-floatedLabel-label has-icon\"]")).click();
        WebElement SecGoingToElement = driver.findElement(By.xpath("//input[@data-stid=\"destination_form_field-menu-input\"]"));
        SecGoingToElement.sendKeys("India");
        SecGoingToElement.sendKeys(Keys.ENTER);
        checkbox.click();
    }
}
