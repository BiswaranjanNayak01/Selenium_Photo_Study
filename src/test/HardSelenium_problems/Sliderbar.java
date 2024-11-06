import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sliderbar {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.fitpeo.com/revenue-calculator");
        Actions act=new Actions(driver);
        act.scrollToElement(driver.findElement(By.xpath("//input[@type='number']"))).build().perform();
        act.clickAndHold(driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMe')]"))).build().perform();
//        act.dragAndDropBy(slider,0,0).build().perform();
        System.out.println(driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMe')]/input")).getAttribute("value"));

        while (!driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMe')]/input")).getAttribute("value").equals("820"))
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMe')]/input")));
            System.out.println(driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMe')]/input")).getAttribute("value"));
            act.keyDown(Keys.ARROW_RIGHT).build().perform();
            act.keyUp(Keys.ARROW_RIGHT).build().perform();
        }
    }
}
