package framework.testcases;

import framework.common.BaseTest;
import framework.config.GlobalDatas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {
    WebDriver driver = null;
    @BeforeTest
    public void setup(){
        //用例前置
        //1.打开浏览器
        driver =openBrowser(GlobalDatas.BROWSER_NAME);
        driver.manage().window().maximize();
        //2.进入登录页面
        driver.get(GlobalDatas.INDEX_URL);
        driver.findElement(By.xpath("//a[text()='登录']")).click();
    }

    @Test
    public void test_login_success() throws InterruptedException {
        //用例步骤
      /*  driver.findElement(By.xpath("//input[@placeholder='请输入手机号/用户名']")).sendKeys(GlobalDatas.USER_NAME);
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys(GlobalDatas.USER_PASSWORD);*/
        driver.findElement(By.className("login-button")).click();
        Thread.sleep(3000);
        //用例断言，测试结果是否符合预期
        //1.根据主页的提示【欢迎来到柠檬班】，根据它是否有显示？？
      //  WebElement webElement1 = waitElementVisiable(driver,By.xpath("//span[text() = '欢迎来到柠檬班']"));
     //  Assert.assertTrue(webElement1.isDisplayed());
        //2.根据主页的用户名
      //  WebElement webElement2 = waitElementVisiable(driver,By.xpath("//a[text()='waiwai']"));
       // Assert.assertTrue(webElement2.isDisplayed());


    }
    @AfterTest
    public void teardown() {
        //用例后置
        //1.退出登录
        //1-1  鼠标移动到用户名上
        Actions actions = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath("//a[text()='waiwai']"));
        actions.moveToElement(webElement).perform();
        //1-2 退出登录
        driver.findElement(By.xpath("//a[text()='退出登录']")).click();
        //2.关闭浏览器
        driver.quit();
    }
}

