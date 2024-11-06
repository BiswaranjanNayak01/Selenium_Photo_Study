import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ElementNotInteractable {
    /*
    ==============================================ElementNotInteractable====================================================================
     1) Element is present in the Dom but it may be in hidden state.
     2) During performing an Unsupported action on an Element.
     */

    @Test
    public void duringClickAnotherUIElementIsOPeningForGettingInputPositive() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(o);
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        WebElement checkbox = driver.findElement(By.cssSelector("input[name=\"add-flight-switch\"]"));
        checkbox.sendKeys("1");
        driver.findElement(By.xpath("//div[@class=\"uitk-field has-floatedLabel-label has-icon\"]")).click();
        WebElement SecGoingToElement = driver.findElement(By.xpath("//input[@data-stid=\"destination_form_field-menu-input\"]"));
        SecGoingToElement.sendKeys("India");
    }
    @Test
    public void duringClickAnotherUIElementIsOPeningForGettingInputNegative() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(o);
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
        WebElement checkbox = driver.findElement(By.cssSelector("input[name=\"add-flight-switch\"]"));
        checkbox.sendKeys("1");
        driver.findElement(By.xpath("//div[@class=\"uitk-field has-floatedLabel-label has-icon\"]")).sendKeys("Odisha");
        WebElement SecGoingToElement = driver.findElement(By.xpath("//input[@data-stid=\"destination_form_field-menu-input\"]"));
        SecGoingToElement.sendKeys("India");
    }

    @Test
    public void unsupportedActionPositive() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(o);
        driver.get("https://www.letskodeit.com/login");
        driver.manage().window().maximize();
        driver.findElement(By.name("loginform")).click();
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("Babul");
    }

    @Test
    public void unsupportedActionNegative() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(o);
        driver.get("https://www.letskodeit.com/login");
        driver.manage().window().maximize();
        driver.findElement(By.name("loginform")).click();
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("Babul");
        driver.findElement(By.name("loginform")).sendKeys("Nayak");//---------------org.openqa.selenium.ElementNotInteractableException: element not interactable
    }
}
