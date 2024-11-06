import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClickBoxesPopupType {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Special add to cart pop ups");
        driver.findElement(By.name("q")).submit();
        driver.findElement(By.xpath("//div[@aria-label=\"Settings\"]//*[local-name()='svg']")).click();
        System.out.println(driver.findElement(By.xpath("(//span[@class=\"ZI7elf\"])[7]")).getText());
    }
}
