import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class DragAndDrop {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    Actions actions;

    @FindBy(xpath = "//div[@id='dragarea']/descendant::img[@id='angular']")
    private WebElement firstImg;
    @FindBy(xpath = "//div[@id='dragarea']/descendant::img[@id='mongo']")
    private WebElement secondImg;
    @FindBy(xpath = "//div[@id='dragarea']/descendant::img[@id='node']")
    private WebElement thirdImg;
    @FindBy(xpath = "//div[@id='droparea']")
    private WebElement dropArea;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        actions=new Actions(driver);
    }

    @Test
    public void testCase1() throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Static.html");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        actions.dragAndDrop(firstImg,dropArea).build().perform();
        actions.dragAndDrop(secondImg,dropArea).build().perform();
        actions.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(thirdImg),0,200).build().perform();
        actions.dragAndDrop(thirdImg,dropArea).build().perform();

//        actions.moveToElement(dropArea).build().perform();
    }
}
