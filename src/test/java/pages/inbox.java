package pages;

import io.qameta.allure.*;
import org.openqa.selenium.*;

public class inbox {
    WebDriver driver;

    public inbox(WebDriver driver){
        this.driver = driver;
    }


    public String searchLetters() throws InterruptedException {
        String nameSender="Фарит";
        logToAllure("Поиск писем");
        driver.findElement(By.xpath("//*[@id=\"gs_lc50\"]/input[1]")).sendKeys(nameSender);
        driver.findElement(By.xpath("//*[@id=\"gs_lc50\"]/input[1]")).sendKeys(Keys.RETURN);
        driver.findElement(By.xpath("//*[@id=\"gs_lc50\"]/input[1]")).click();
        String sumLetter = driver.findElement(By.xpath("//*[@class=\"aeH\"]/div[2]")).findElement(By.xpath(".//*[@aria-label=\"Показать другие сообщения\"]/span/span[2]")).getText();
        logToAllure("Количество писем от "+nameSender+": "+sumLetter);
        return sumLetter;
    }

    public String writeLetters(String sumLetter) throws InterruptedException {
        driver.findElement(By.xpath("//*[@class=\"aic\"]/div/div")).click();
        logToAllure("Написать письмо");
        driver.findElement(By.xpath("//*[@name=\"to\"]")).sendKeys("farit.valiahmetov@simbirsoft.com");
        //driver.findElement(By.xpath("//*[@name=\"to\"]")).sendKeys("sadiulinn@bk.ru"); //для тестирования
        logToAllure("Заполняем email");
        driver.findElement(By.xpath("//*[@name=\"subjectbox\"]")).sendKeys("Тестовое задание. Садиулин");
        logToAllure("Заполняем тему");
        driver.findElement(By.xpath("//*[@class=\"Am Al editable LW-avf tS-tW\"]")).sendKeys(sumLetter);
        logToAllure("Заполняем текст письма - кол-во найденных писем");
        driver.findElement(By.xpath("//*[@class=\"T-I J-J5-Ji aoO v7 T-I-atl L3\"]")).click();
        logToAllure("Отправляем письмо");
        Thread.sleep(3000);
        String statusSend = driver.findElement(By.xpath("//*[@class=\"bAq\"]")).getText();
        logToAllure("Получаем статус отправки - "+statusSend);
        return statusSend;
    }


    @Step("{log}")
    public void logToAllure(String log) {
        System.out.println("log: "+log);
        saveAllureScreenshot();
    }

    @Step("Page screenshot")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}