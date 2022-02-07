package test.day03;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class WebdriverApiTest {
    public static void main(String[] args) throws InterruptedException {
       WebDriver driver = openBrowser("chrome");
       //get方法会等到当前网页加载结束才会返回
        driver.get("http://testingpai.com/");
        driver.findElement(By.id("navLogin")).click();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());
       // System.out.println(driver.getPageSource());

        //quit与close区别
        //截图 getScreenshotAs
        //转换为TakeScreenshot类型来得到截图能力
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        //outputType-->截图类型
        //OutputType.FILE-->文件对象
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //把文件对象保存到本地文件
        try {
            //工具类的调用--commons.io 依赖包里面的工具类
            FileUtils.copyFile(file,new File("D:\\test\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






    /*
     *打开所有浏览器通用方法封装
     *@param browserName
     */
    public static WebDriver openBrowser(String browserName){
        WebDriver webDriver = null;
        if("chrome".equalsIgnoreCase(browserName)){
            System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
            webDriver = new ChromeDriver();
        }else if("firefox".equalsIgnoreCase(browserName)){
            //1.firefox浏览器地址Cannot find firefox binary in PATH. Make sure firefox is installed. OS appears to be: WIN10
            System.setProperty("webdriver.firefox.bin","D:\\program file\\firefox.exe");
            //2.驱动地址
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            //3.打开浏览器
            webDriver = new FirefoxDriver();
        }else if("ie".equalsIgnoreCase(browserName)){
            //1.驱动位置 The path to the driver executable must be set by the webdriver.ie.driver system property;
            System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");

            //取消IE安全设置（忽略IE的Protected Mode的设置）
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

            //Unexpected error launching Internet Explorer. Browser zoom level was set to 150%. It should be set to 100%
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            webDriver = new InternetExplorerDriver(capabilities);

        }
        return webDriver;

    }}

