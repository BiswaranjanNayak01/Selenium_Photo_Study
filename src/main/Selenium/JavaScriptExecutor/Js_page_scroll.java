import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Js_page_scroll {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll by pixel
        js.executeScript("window.scrollBy(0,1500)");
        System.out.println(js.executeScript("return window.pageYOffset"));

        // Scroll by Webelement
        WebElement newLunchProduct = driver.findElement(By.xpath("//span[contains(text(),'Today’s Deals')]"));
        js.executeScript("arguments[0].scrollIntoView()", newLunchProduct);
        System.out.println(js.executeScript("return window.pageYOffset"));

        // Scroll to the end of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        System.out.println(js.executeScript("return window.pageYOffset;"));

        // Scroll to the start of the page again
        js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
        System.out.println(js.executeScript("return window.pageYOffset"));
        System.out.println(js.executeScript("return document.activeElement.innerText"));

    }

    public void scrollByPixel(WebDriver driver) {
        long posA = 0;
        long posB = 1;
        long scrollPos = 0;
        while (posA != posB) {
            posA = (long) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
            scrollPos += 300;
            ((JavascriptExecutor) driver).executeScript("window.scrollTo( 0, " + scrollPos + ");");
            posB = (long) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}
