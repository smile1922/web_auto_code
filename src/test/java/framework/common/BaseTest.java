package framework.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


//用例的共性操作
public class BaseTest {

    private static Logger logger = Logger.getLogger(BaseTest.class);
    public WebDriver driver;

    /*
     *打开所有浏览器通用方法封装
     *@param browserName
     */
   

    public static WebDriver openBrowser(String browserName){
        WebDriver webDriver = null;
        if("chrome".equalsIgnoreCase(browserName)){
            System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
            webDriver = new ChromeDriver();
            logger.info("====打开了chrome浏览器======");
        }else if("firefox".equalsIgnoreCase(browserName)){
            //1.firefox浏览器地址Cannot find firefox binary in PATH. Make sure firefox is installed. OS appears to be: WIN10
            System.setProperty("webdriver.firefox.bin","D:\\program file\\firefox.exe");
            //2.驱动地址
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            //3.打开浏览器
            webDriver = new FirefoxDriver();
            logger.info("====打开了firefox浏览器======");
        }else if("ie".equalsIgnoreCase(browserName)){
            //1.驱动位置 The path to the driver executable must be set by the webdriver.ie.driver system property;
            System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");

            //取消IE安全设置（忽略IE的Protected Mode的设置）
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

            //Unexpected error launching Internet Explorer. Browser zoom level was set to 150%. It should be set to 100%
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            webDriver = new InternetExplorerDriver(capabilities);
            logger.info("====打开了IE浏览器======");

        }
        return webDriver;
    }

    public void closeBrowser(WebDriver driver){
        logger.info("====关闭浏览器======");
        driver.close();
    }

    public void myAssertTrue(boolean condition,String assertDescription){
        logger.info("断言：【" + assertDescription + "】条件表达式【" +condition+"】");
        Assert.assertTrue(condition);
    }

    public void myAssertEquals(String actural,Object expected,String assertDescription ){
        logger.info("断言：【" + assertDescription + "】实际值【" +actural+"】" +"期望值【" +expected+"】");
        Assert.assertEquals(actural,expected);
    }
}
