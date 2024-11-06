import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ClassNameDotIssue {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/sortable");
//        WebElement element=driver.findElement(By.xpath("//div[@class='fade tab-pane active show']")); //----working
        WebElement element=driver.findElement(By.className("nav.nav-tabs"));  //----not working
        System.out.println(element.getText());
    }
}
