import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Documents\\GitHub\\selenium\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://the-internet.herokuapp.com/");

        //Simple click on element. A/B Testing
        driver.findElement(By.xpath("//a[@href='/abtest']")).click();
        driver.navigate().back();

        //Add/Remove Elements
        driver.findElement(By.xpath("//a[@href='/add_remove_elements/']")).click();
        WebElement button1 = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        button1.click();
        WebElement button2 = driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        button2.click();
        driver.navigate().back();

        //Basic authentication
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        driver.getPageSource().contains("Congratulations! You must have the proper credentials.");
        driver.navigate().back();

        //Challenging DOM
        driver.findElement(By.xpath("//a[@href='/challenging_dom']")).click();
        WebElement changingNameButton1 = driver.findElement(By.xpath("//div[@class=\"large-2 columns\"]/a[1]"));
        WebElement changingNameButton2 = driver.findElement(By.xpath("//div[@class=\"large-2 columns\"]/a[2]"));
        WebElement changingNameButton3 = driver.findElement(By.xpath("//div[@class=\"large-2 columns\"]/a[3]"));
        changingNameButton1.isDisplayed();
        changingNameButton2.isDisplayed();
        changingNameButton3.isDisplayed();
        driver.navigate().back();

        //Checkboxes
        driver.findElement(By.xpath("//a[@href='/checkboxes']")).click();
        WebElement checkbox1 = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));

        if (!checkbox1.isSelected()) {
            checkbox1.click();
            checkbox2.click();
            System.out.println("Selected checkbox1 and unselected checkbox2");
        } else System.out.println("This checkbox was already selected");
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
        } else System.out.println("Value was not selected");
        driver.navigate().back();

        //Entry Ad. FLAKY
        driver.findElement(By.xpath("//a[@href='/entry_ad']")).click();
        driver.findElement(By.xpath("//p[contains(text(), 'Close')]")).click(); //finish closing this alert. FLAKY!
        driver.get("http://the-internet.herokuapp.com/");

        //File Upload
        driver.findElement(By.xpath("//a[@href='/upload']")).click();
        //driver.findElement(By.xpath("//input[@id='file-upload']")).click();
        driver.findElement(By.xpath("//input[@id='file-upload']")).sendKeys("C:\\Users\\Dell\\Desktop\\file\\pic1.jpg");
        driver.findElement(By.xpath("//input[@class='button']")).click();

        if (driver.getPageSource().contains("File Uploaded!")) {
            System.out.println("FILE IS UPLOADED!");
        } else System.out.println("Whoops something went wrong while uploading the file");
        driver.get("http://the-internet.herokuapp.com/");

        //Form Authentication
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("", "tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("", "SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        WebElement flashAlert = driver.findElement(By.xpath("//div[@class='flash success']"));
        if (flashAlert.isDisplayed()) {
            System.out.println("Successful login, alert is displayed");
        } else System.out.println("Unsuccessful login");
        driver.get("http://the-internet.herokuapp.com/");

        //Hovers
        driver.findElement(By.xpath("//a[@href='/hovers']")).click();
        WebElement avatar1 = driver.findElement(By.xpath("//img[@src='/img/avatar-blank.jpg']"));
        actions.moveToElement(avatar1).perform();
        driver.findElement(By.xpath("//a[@href='/users/1']")).click(); //View profile
        driver.get("http://the-internet.herokuapp.com/");

        //Inputs
        driver.findElement(By.xpath("//a[@href='/inputs']")).click();
        WebElement inputWindow = driver.findElement(By.xpath("//input[@type='number']"));

        inputWindow.sendKeys("Some String value");
        String inputValue = inputWindow.getAttribute("value");
        int size = inputValue.length();

        if (size == 0) {
            System.out.println("No words are allowed. Enter a number");
            inputWindow.clear();
            inputWindow.sendKeys("1", "");

        } else System.out.println("Something went wrong");
        driver.get("http://the-internet.herokuapp.com/");

        //JavaScript Alerts
        driver.findElement(By.xpath("//a[@href='/javascript_alerts']")).click();
        WebElement clickForJsAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        WebElement clickForJsConfirm = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        WebElement clickForPrompt = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        clickForJsAlert.click();
        driver.switchTo().alert().accept();
        driver.getPageSource().contains("You successfuly clicked an alert");

        clickForJsConfirm.click();
        driver.switchTo().alert().accept();
        driver.getPageSource().contains("You clicked: Ok");

        clickForPrompt.click();
        driver.switchTo().alert().accept();
        driver.getPageSource().contains("You entered: "); //add changes

        driver.get("http://the-internet.herokuapp.com/");

        //JavaScript onload event error
        driver.findElement(By.xpath("//a[@href='/javascript_error']")).click();
        driver.getPageSource().contains("This page has a JavaScript error in the onload event. " +
                "This is often a problem to using normal Javascript injection techniques.");
        driver.get("http://the-internet.herokuapp.com/");

        //Key Presses
        driver.findElement(By.xpath("//a[@href='/key_presses']")).click();
        WebElement inputTab = driver.findElement(By.xpath("//input[@id='target']"));
        inputTab.sendKeys(Keys.ENTER);
        driver.getPageSource().contains("You entered: ENTER");
        driver.get("http://the-internet.herokuapp.com/");

        //Multiple Windows
        driver.findElement(By.xpath("//a[@href='/windows']")).click();
        String firstPage = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@href='/windows/new']")).click();

        for (String windowHandler : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandler);
        }
        driver.switchTo().window(firstPage);
        driver.get("http://the-internet.herokuapp.com/");

        //Notification Messages.
        driver.findElement(By.xpath("//a[@href='/notification_message']")).click();
        driver.getPageSource().contains("Action successful");
        driver.findElement(By.xpath("//a[@href='/notification_message']")).click();
        driver.getPageSource().contains(" Action unsuccesful, please try again");
        driver.get("http://the-internet.herokuapp.com/");

        driver.quit();
    }
}