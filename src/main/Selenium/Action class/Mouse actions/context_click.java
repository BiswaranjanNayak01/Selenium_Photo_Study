import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class context_click {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = "//span[.='Copy']")
    WebElement CopyButton;
    @FindBy(how = How.XPATH, using = "//span[text()='right click me']")
    public WebElement RightClickMe;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Paste']")
    public WebElement PasteButton;
    @FindBy(how = How.XPATH, using = "//span[normalize-space(.)='Delete']")
    public WebElement DeleteButton;
    @FindBy(how = How.XPATH, using = "//span[normalize-space(text())='Cut']")
    public WebElement CutButton;
    @FindBy(how = How.XPATH, using = "(//span[contains(text(),'Edit')])[2]")
    WebElement EditButton;



    @Test
    private void call() throws InterruptedException {
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        driver.manage().window().maximize();
        Actions act = new Actions(driver);
        act.contextClick(RightClickMe).perform();
        act.click(CopyButton).perform();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();

        act.contextClick(RightClickMe).perform();
        Thread.sleep(3000);
        act.click(driver.findElement(By.xpath("//span[normalize-space(text())='Cut']"))).perform();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();

        act.contextClick(RightClickMe).perform();
        Thread.sleep(3000);
        DeleteButton.click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();


    }


}
