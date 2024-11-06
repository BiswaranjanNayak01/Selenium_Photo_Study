import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class JavaScriptExe {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement NameInput = driver.findElement(By.id("name"));
        WebElement phone = driver.findElement(By.id("phone"));
        List<WebElement> Input = driver.findElements(By.className("form-control"));

        // use instead of sendkeys(), during there is exception or selenium sendkeys is not working. ----------------------
        js.executeScript("arguments[0].value='puspza';", NameInput);
        js.executeScript("arguments[0].setAttribute('value','Tufani')", phone);
        js.executeScript("document.getElementById('name').value='kadu'");
        js.executeScript("document.getElementsByClassName('form-control')[1].value='kuta'");
        js.executeScript("arguments[0].value='puspza63763637995';", Input.get(2));

        //click on something---------------------------------------------------------

        WebElement radio_male = driver.findElement(By.id("male"));
        js.executeScript("arguments[0].click()", radio_male);
       // click on check box
        js.executeScript("document.getElementById('tuesday').click()");

        js.executeScript("document.getElementsByClassName('form-check-input')[3].click()");

        // change any type of any element
        js.executeScript("document.getElementById('friday').type='text'");
        WebElement saturday = driver.findElement(By.id("saturday"));
        js.executeScript("arguments[0].type='text'", saturday);




    }
}
