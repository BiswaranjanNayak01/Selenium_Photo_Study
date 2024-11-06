import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class WindowSize {
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

    @Test
    public void testCase1() {
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        System.out.println(driver.manage().window().getSize());
        System.out.println(driver.manage().window().getPosition());
        driver.manage().window().setSize(new Dimension(1066, 812));
        System.out.println(driver.manage().window().getSize());
        System.out.println(driver.manage().window().getPosition());
        driver.manage().window().setPosition(new Point(666,666));
        System.out.println(driver.manage().window().getSize());
        System.out.println(driver.manage().window().getPosition());
        driver.manage().window().maximize();
        System.out.println(driver.manage().window().getSize());
        System.out.println(driver.manage().window().getPosition());
        driver.manage().window().setPosition(new Point(1200,8));

    }
}
