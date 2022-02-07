package test.day05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ActionTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
        driver.get("http://treejs.cn/v3/demo/cn/exedit/drag.html");
        Thread.sleep(2000);
        //actions类-->鼠标操作
        Actions action = new Actions(driver);
        WebElement webElement1= driver.findElement(By.id("treeDemo_2_span"));
        WebElement webElement2= driver.findElement(By.id("treeDemo_3_span"));
        //按住--》拖拽-->鼠标释放
        action.clickAndHold(webElement1).moveToElement(webElement2).release().perform();
        //双击
        action.doubleClick(webElement1).perform();



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


