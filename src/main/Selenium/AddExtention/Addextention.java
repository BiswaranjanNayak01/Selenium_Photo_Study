package AddExtention;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Addextention {
    public static void main(String[] args) {
        ChromeOptions opt=new ChromeOptions();
        opt.addExtensions(new File("src/main/resources/SearchGPT-for-Chrome.crx"));
        opt.addExtensions(new File("src/main/resources/AdBlock-—-best-ad-blocker.crx"));
        WebDriver driver=new ChromeDriver(opt);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        String parent=driver.getWindowHandle();
        System.out.println(parent);
        Set<String> windows=driver.getWindowHandles();
        System.out.println(windows);
        for (String win:windows){
            System.out.println(win);
            driver.switchTo().window(win);
            System.out.println(driver.getTitle());
            if (!win.equals(parent)){
                driver.close();
            }
        }
//        driver.switchTo().window(parent);
        List list=new ArrayList<>(windows);
        System.out.println(list);

    }


}
