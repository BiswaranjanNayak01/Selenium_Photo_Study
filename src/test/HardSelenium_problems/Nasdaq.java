import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Nasdaq {
    public static void main(String[] args) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("disable-popup-blocking", "true");
        options.addArguments("--disable-web-security");
        WebDriver driver = new EdgeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.nasdaq.com/market-activity/stocks");
        List<WebElement> names = driver.findElements(By.xpath("//div[@class=\"jupiter22-c-expert-name\"]/ancestor::div[contains(@class,\"jupiter22-c-expert-image-container\")]/descendant::div[@class=\"jupiter22-c-expert-name\"]"));
        List<WebElement> photos = driver.findElements(By.xpath("//div[@class=\"jupiter22-c-expert-name\"]/ancestor::div[contains(@class,\"jupiter22-c-expert-image-container\")]/descendant::div[@class=\"jupiter22-c-expert-media\"]"));

        System.out.println(names.size());
        System.out.println(photos.size());
        int photocount = photos.size();
        int namesCount = names.size();

        for (int i = 0; i < namesCount; i++) {
            String name = names.get(i).getText();
            System.out.println(name);
            if (i < photocount) {
                String photo =photos.get(i).getAttribute("style");
                System.out.println(photo);
                System.out.println("inside pic");
            }
        }
    }
}
