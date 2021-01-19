import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Documents\\GitHub\\selenium\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/");

        //simple click on element
        driver.findElement(By.xpath("//a[@href='/abtest']")).click();
        driver.navigate().back();

        //click on different buttons present on page
        driver.findElement(By.xpath("//a[@href='/add_remove_elements/']")).click();
        WebElement button1 = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        button1.click();
        WebElement button2 = driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        button2.click();
        driver.navigate().back();

        //basic authentication
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        driver.getPageSource().contains("Congratulations! You must have the proper credentials.");
        driver.navigate().back();

        //broken image test

        //Challenging DOM

        //Checkboxes
        driver.findElement(By.xpath("//a[@href='/checkboxes']")).click();
        WebElement checkbox1 = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));

        if (!checkbox1.isSelected()) {
            checkbox1.click();
            checkbox2.click();
            System.out.println("Selected checkbox1 and unselected checkbox2");
        }
        else System.out.println("This checkbox was already selected");
        driver.navigate().back();

        //Context Menu. Right-click menu will trigger a JavaScript alert
        driver.findElement(By.xpath("//a[@href='/context_menu']")).click();
        WebElement contextMenu = driver.findElement(By.xpath("//div[@id='hot-spot']"));
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenu).release().build().perform();
        driver.switchTo().alert().accept();


        //Digest Authentication
        driver.get("https://admin:admin@the-internet.herokuapp.com/digest_auth");
        driver.getPageSource().contains("Congratulations! You must have the proper credentials.");
        driver.navigate().back();

        
        driver.quit();

    }
}