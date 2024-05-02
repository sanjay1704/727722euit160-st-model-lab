package com.example;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    WebDriver driver;
    @BeforeMethod
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.abhibus.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void Testcase1() throws Exception {
        driver.findElement(By.xpath("//*[@id=\"train-link\"]/span[2]")).click();
        Thread.sleep(3000);

        String url = driver.getCurrentUrl();
        if (url.contains("trains")) {
            System.out.println("Train keyword present in page");
        } else {
            System.out.println("Train keyword does not present in page");
        }
        driver.findElement(By.xpath("//*[@id=\"login-link\"]/span[2]")).click();
            Thread.sleep(3000);
    
            String value1 = driver.findElement(By.xpath("//*[@id=\"login-heading\"]/div[1]/h4")).getText();
           
            if (value1.equals("Login to AbhiBus")) {
                System.out.println("The given value equals");
            } else {
                System.out.println("The given value does not equals");
            }
    
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("screenshot1.png"));

        // driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/a/img")).click();
        // Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void Testcase2() throws Exception {

        FileInputStream fs = new FileInputStream("C:\\Users\\Admin\\Music\\Model lab.xlsx");
        XSSFWorkbook w = new XSSFWorkbook(fs);
        XSSFSheet sheet = w.getSheet("Sheet1");
        XSSFRow row = sheet.getRow(0);
        String a = row.getCell(0).getStringCellValue();
        String b = row.getCell(1).getStringCellValue();

        driver.findElement(By.xpath("//*[@id=\"search-from\"]/div/div/div/div/div[2]/input")).sendKeys(a);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"search-to\"]/div/div/div/div/div[2]/input")).sendKeys(b);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[2]/div/div/div/div[11]/a")).click();
        Thread.sleep(3000);

        String u = driver.getCurrentUrl();
        if ((u.contains("Mumbai") && (u.contains("Pune")))) {
            System.out.println("It contains the keyword");
        } else {
            System.out.println("It does not contains the keyword");
        }
    }

    @Test(priority = 3)
    public void Testcase3() throws Exception {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[1]/div/div/div/div/button[5]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[2]/div/div/div/div[7]/a")).click();
        Thread.sleep(3000);

        String keyword = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/h2")).getText();

        if (keyword.contains("Media")) {
            System.out.println("Test is present");
        } else {
            System.out.println("Test does not present");
        }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
    }

    @AfterMethod
    public void af() throws Exception {
        driver.quit();
    }
}