package SwitchTo.Blank_window_or_Tab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewWindow {
    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://text-compare.com");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.geeksforgeeks.org/dsa-sheet-by-love-babbar/");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.flipkart.com/");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.amazon.com/");
    }
}
