package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class gmailLogin {
    public WebDriver driver;

    public gmailLogin(WebDriver driver){
        this.driver = driver;
    }
    public void setUserName(String strUserName){
        driver.findElement(By.id("identifierId")).sendKeys(strUserName);
    }
    public void clickUserNameNext(){
        driver.findElement(By.id("identifierNext")).click();
    }


    public void setPassword(String strPassword){
        driver.findElement(By.name("password")).sendKeys(strPassword);
    }
    public void clickPasswordNext(){
        driver.findElement(By.id("passwordNext")).click();
    }

    public void loginToGmail(String strUserName,String strPasword){
        this.setUserName(strUserName);
        this.clickUserNameNext();

        this.setPassword(strPasword);
        this.clickPasswordNext();
    }

}
