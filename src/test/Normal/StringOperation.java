import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StringOperation {
    protected static String viewUserTable="//%s/table/tbody";

    protected static void l() {
        String x=String.format(String.valueOf(viewUserTable), "Babul");
        System.out.println(x);
    }

    public static void main(String[] args) throws InterruptedException {
        l();
    }
}
