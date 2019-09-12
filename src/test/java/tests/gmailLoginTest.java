package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import pages.gmailLogin;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.inbox;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class gmailLoginTest {
    WebDriver driver;


    @BeforeTest
    public void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver76.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver = new RemoteWebDriver(new URL("http://192.168.54.233:4444/wb/hub"), new ChromeOptions()); //если используем selenium grid, отключено для тестирования локально
    }

    @Test(priority=1)
    public void gmailLoginTest() throws InterruptedException {
        driver.get("https://gmail.com");
        gmailLogin objLogin = new gmailLogin(driver);
        objLogin.loginToGmail("nsadiulin@gmail.com", ""); /** EMAIL и ПАРОЛЬ для авторизации на GMAIL.COM **/
        inbox objInbox = new inbox(driver);
        String sumLetter = objInbox.searchLetters();//получаем кол-во писем
        Assert.assertTrue(objInbox.writeLetters(sumLetter).contains("Письмо отправлено."));
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
