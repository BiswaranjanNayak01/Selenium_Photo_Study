import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Driver;
import java.time.Duration;

public class Assignment1 {
    public static void main(String[] args) {
        WebDriver driver1;
        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("debuggerAddress", "localhost:55327");
        System.out.println(options.getCapabilityNames());
        System.out.println(options.getBrowserName());
        System.out.println(options.getPlatformName());
//        1. Launch browser window(Chrome)
        ChromeDriver driver = new ChromeDriver(options);
        Capabilities capabilities = driver.getCapabilities();
        System.out.println(capabilities);
//        2. Maximize the browser window
        driver.manage().window().maximize();
//        3. Delete all the cookies
        driver.manage().deleteAllCookies();
//        4. Enter URL and Launch the application (https://parabank.parasoft.com/parabank/index.htm)
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
//        5. Verify application title (ParaBank | Welcome | Online Banking)
        System.out.println(driver.getTitle());
//        6. Verify application logo
        System.out.println(driver.findElement(By.className("logo")).getAttribute("src"));
        Assert.assertTrue(driver.findElement(By.className("logo")).getAttribute("src").contains("/parabank/images/logo.gif"));
//        7. Verify application caption (Experience the difference)
        System.out.println(driver.findElement(By.xpath("//div[@id='topPanel']/p")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='topPanel']/p")).getText().equals("Experience the difference"));
//        8. Enter Invalid credentials in Username and Password textboxes
//        driver.findElement(By.name("username")).sendKeys("Biswa");
//        driver.findElement(By.name("password")).sendKeys("Nayak");
        driver.findElement(By.name("username")).sendKeys("Soap");
        driver.findElement(By.name("password")).sendKeys("Soap");
//        9. Verify button label (LOG IN) and Click on Login Button
        System.out.println(driver.findElement(By.xpath("//input[@class='button']")).getAttribute("value"));
        Assert.assertEquals(driver.findElement(By.xpath("//input[@class='button']")).getAttribute("value"), "Log In");
//        driver.findElement(By.xpath("//input[@class='button']")).submit();
//        10. Verify error message is coming

//        11. Click on Adminpage link
        WebElement AdminPage = driver.findElement(By.xpath("//a[text()='Admin Page']"));
        AdminPage.click();
//        12. Wait for admin page


//        13. Select Data access mode as ' SOAP'
        driver.findElement(By.id("accessMode1")).click();
//        14. Scrolldown till Loan provider
        WebElement LoanProvider = driver.findElement(By.xpath("//b[text()='Loan Provider:']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(LoanProvider).build().perform();
//        15. Select Loanprovider as 'Web Service'
        Select select = new Select(driver.findElement(By.id("loanProvider")));
        select.selectByVisibleText("Web Service");

//        16. Click on Submit button
        WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));
        submit.submit();
//        17.wait for Successful submission message

        (new WebDriverWait(driver, Duration.ofSeconds(5000)))
                .until(d -> d.findElement(By.xpath("//b[text()='Settings saved successfully.']")).isDisplayed());

        //      18.Click on Services Link
        driver.findElement(By.xpath("//ul[@class='leftmenu']/li[3]/a")).click();
//        19.Wait for Services page

//        20.Scrolldown till Bookstore services
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Bookstore services:']")));
//        21.Get total rows, columns in the bookstore service table

//        22.Get Column headers of book store services table

//        23.Get all the data from book store service table

//        24.Close browser window
    }
}
