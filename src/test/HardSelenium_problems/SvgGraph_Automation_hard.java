import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SvgGraph_Automation_hard {
    @FindBy(xpath = "//*[local-name()=\"svg\" and @class=\"uch-psvg\"]")
    WebElement svgGraph;
    @FindBy(xpath = "//div[@class=\"knowledge-finance-wholepage-chart__hover-card\"]")
    WebElement value;
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        //driver.quit();
    }
    @Test
    public void testCase1() {
        driver.get("https://www.google.com/search?q=tcs+share+price&rlz=1C1RXQR_enIN1052IN1052&oq=&gs_lcrp=EgZjaHJvbWUqCQgAECMYJxjqAjIJCAAQIxgnGOoCMgkIARAjGCcY6gIyCQgCECMYJxjqAjIJCAMQIxgnGOoCMgkIBBAjGCcY6gIyCQgFECMYJxjqAjIJCAYQIxgnGOoCMgkIBxAjGCcY6gLSAQ0xOTc2OTg4MjhqMGo3qAIIsAIB&sourceid=chrome&ie=UTF-8");
        driver.manage().window().setSize(new Dimension(1066, 812));
        driver.manage().window().maximize();
        {
            WebElement element = driver.findElement(By.cssSelector(".V30e3e"));
            Actions builder = new Actions(driver);
           // builder.moveToElement(element).clickAndHold().perform();
        }
        int height=svgGraph.getRect().getHeight();
        System.out.println("height :: "+height);
        int width=svgGraph.getRect().width;
        System.out.println("width :: "+width);
        int gwid=svgGraph.getRect().getWidth();
        System.out.println("gwid :: "+gwid);
        int x=svgGraph.getRect().x;
        System.out.println("x :: "+x);
        int y=svgGraph.getRect().y;
        System.out.println("y :: "+y);
        int x1=svgGraph.getRect().getX();
        System.out.println("x1 :: "+x1);
        int y1=svgGraph.getRect().getY();
        System.out.println("y1 :: "+y1);
        Dimension dimr=svgGraph.getRect().getDimension();
        System.out.println("dimr :: "+dimr);
        Point point=svgGraph.getRect().getPoint();
        System.out.println("point :: "+point);
        int hashCode=svgGraph.getRect().hashCode();
        System.out.println("hashCode ::"+hashCode);


        int height2=((svgGraph.getSize().getHeight())/2)-svgGraph.getSize().height;
        System.out.println("height1 :: "+height2);
        int width2=((svgGraph.getSize().getWidth())/2)-svgGraph.getSize().getWidth();
        System.out.println("width2 :: "+width2);

        Actions act=new Actions(driver);
        for (int i=0;i<=svgGraph.getSize().getWidth();i++) {
            act.moveToElement(svgGraph, width2+i, 0).perform();
            System.out.println("value :: "+value.getText());
        }
      //  act.moveByOffset(width2,height2).perform();---------------it will fail
       // act.moveByOffset(x1,y1).build().perform();------------it will pass bdecause it is a x and y co-ordinate.
        System.out.println("value :: "+value.getText());
    }
    @Test
    public void explicitWithOptions() {
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
        WebElement revealed = driver.findElement(By.id("revealed"));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

        driver.findElement(By.id("reveal")).click();
        wait.until(d -> {
            revealed.sendKeys("Displayed");
            return true;
        });
        System.out.println(revealed.getDomProperty("value"));
        Assertions.assertEquals("Displayed", revealed.getDomProperty("value"));
    }
}
