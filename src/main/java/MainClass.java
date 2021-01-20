import org.openqa.selenium.By;
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
        driver.get("http://the-internet.herokuapp.com/");

        //Disappearing Elements

        //Drag and Drop
        driver.findElement(By.xpath("//a[@href='/drag_and_drop']")).click();
        WebElement dragElement1 = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement dragElement2 = driver.findElement(By.xpath("//div[@id='column-b']"));
        actions.dragAndDrop(dragElement1, dragElement2).build().perform();
        driver.navigate().back();

        //Dropdown List
        driver.findElement(By.xpath("//a[@href='/dropdown']")).click();
        WebElement dropDownElement = driver.findElement(By.xpath("//select[@id='dropdown']"));
        WebElement value1 = driver.findElement(By.xpath("//option[@value='1']"));
        WebElement value2 = driver.findElement(By.xpath("//option[@value='2']"));

        if (!value1.isSelected()) {
            value1.click();
            value2.click();
            System.out.println("Value 1 is selected and the Value 2 was selected");
        }else System.out.println("Value was not selected");
        driver.navigate().back();

        //Dynamic Content

        //Dynamic Controls

        //Dynamically Loaded Page Elements

        //Entry Ad. FLAKY
        //driver.findElement(By.xpath("//a[@href='/entry_ad']")).click();
        //driver.findElement(By.xpath("//p[contains(text(), 'Close')]")).click(); //finish closing this alert. FLAKY!
        //driver.get("http://the-internet.herokuapp.com/");

        //Exit Intent
        //driver.findElement(By.xpath("//a[@href='/exit_intent']")).click();

        //File Download
        //driver.findElement(By.xpath("//a[@href='/download']")).click();

        //File Upload
        driver.findElement(By.xpath("//a[@href='/upload']")).click();
        //driver.findElement(By.xpath("//input[@id='file-upload']")).click();
        driver.findElement(By.xpath("//input[@id='file-upload']")).sendKeys("C:\\Users\\Dell\\Desktop\\file\\pic1.jpg");
        driver.findElement(By.xpath("//input[@class='button']")).click();

        if (driver.getPageSource().contains("File Uploaded!")) {
            System.out.println("FILE IS UPLOADED!");
        }else System.out.println("Whoops something went wrong while uploading the file");
        driver.get("http://the-internet.herokuapp.com/");

        //Floating Menu

        //Forgot Password

        //Form Authentication
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("", "tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("", "SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        WebElement flashAlert = driver.findElement(By.xpath("//div[@class='flash success']"));
        if (flashAlert.isDisplayed()) {
            System.out.println("Successful login, alert is displayed");
        }else System.out.println("Unsuccessful login");
        driver.get("http://the-internet.herokuapp.com/");

        //Frames

        //Geolocation

        //Horizontal Slider

        //Hovers
        driver.findElement(By.xpath("//a[@href='/hovers']")).click();
        WebElement avatar1 = driver.findElement(By.xpath("//img[@src='/img/avatar-blank.jpg']"));
        //WebElement avatar2 = driver.findElement(By.xpath("//img[@src='/img/avatar-blank.jpg']"));
        //WebElement avatar3 = driver.findElement(By.xpath("//img[@src='/img/avatar-blank.jpg']"));
        actions.moveToElement(avatar1).perform();
        driver.findElement(By.xpath("//a[@href='/users/1']")).click(); //View profile
        driver.get("http://the-internet.herokuapp.com/");



        //Infinite Scroll

        //Inputs

        //JQuery UI Menus

        //JavaScript Alerts

        //JavaScript onload event error

        //Key Presses

        //Large & Deep DOM

        //Multiple Windows

        //Nested Frames

        //Notification Messages

        //Redirect Link

        //Secure File Download

        //Shadow DOM

        //Shifting Content

        //Slow Resources

        //Sortable Data Tables

        //Status Codes

        //Typos

        //WYSIWYG Editor




        driver.quit();

    }
}
