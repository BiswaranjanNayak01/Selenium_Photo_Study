import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TwitterScroll {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
//        chromeOptions.setExperimentalOption("debuggerAddress","localhost:57520");
        WebDriver driver=new ChromeDriver(chromeOptions);
//        driver.manage().window().maximize();
//        driver.get("https://babulily50@gmail.com:Biswa@1999 @www.x.com/messages/145054677-1726183692727463937?text=");
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//span[text()='Phone or username']")).click();
//        driver.findElement(By.xpath("//input[@name='text']")).click();
////        driver.findElement(By.xpath("//span[text()='Phone or username']")).sendKeys("babulily50@gmail.com");
//        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("babulily50@gmail.com");
//        driver.findElement(By.xpath("//span[text()='Next']")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.name("password")).sendKeys("Biswa@1999");
//        driver.findElement(By.xpath("//span[text()='Log in']")).click();
//        driver.findElement(By.xpath("//a[@aria-label='Direct Messages']")).click();
//        driver.findElement(By.xpath("//section[@aria-label='Section navigation']"));
        Actions act=new Actions(driver);
//        act.moveToElement(driver.findElement(By.xpath("//section[@aria-label='Section navigation']"))).build().perform();
//        act.scrollByAmount(0,1200).build().perform();
//        Thread.sleep(3000);
//        act.scrollToElement(driver.findElement(By.xpath("(//div[@data-testid=\"cellInnerDiv\"]/div[@data-testid=\"activeRoute\"])[18]"))).build().perform();
        int messageElement=driver.findElements(By.xpath("//div[@data-testid='cellInnerDiv']/div[@data-testid='activeRoute']")).size();
        for (int i=(messageElement/5);i<=messageElement;i+=5){
            messageElement=driver.findElements(By.xpath("//div[@data-testid='cellInnerDiv']/div[@data-testid='activeRoute']")).size();
            act.scrollToElement(driver.findElement(By.xpath("(//div[@data-testid=\"cellInnerDiv\"]/div[@data-testid=\"activeRoute\"])["+i+"]"))).build().perform();
        }

    }
}
