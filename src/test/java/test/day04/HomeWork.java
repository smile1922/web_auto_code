package test.day04;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class HomeWork {
    public static void main(String[] args) throws InterruptedException {
        //打开豌豆荚应用排行版页面，找到【虎牙直播】并进行点击  https://www.wandoujia.com/top/app
        //JavaScript页面滚动-->点击-->将剩余的元素加载出来
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.wandoujia.com/top/app");
        //怎么判断一个元素是否在当前页面中？getPageSource
        //一直不断的点击【查看更多】
        while(true){
        if(driver.getPageSource().contains("title=\"虎牙直播\"")){
            driver.findElement(By.xpath("//a[text()='虎牙直播']")).click();
            break;
        };
        //点击【查看更多】
            //怎么判断到了末尾呢？数据全部加装出来？？
            WebElement webElement= driver.findElement(By.id("j-refresh-btn"));
            JavascriptExecutor javascriptExecutor =(JavascriptExecutor)driver;
            javascriptExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true)",webElement);
            webElement.click();
            Thread.sleep(2000);
        }
    }


    /*
     * 封装通用切换窗口的方法--根据对应窗口的标题来切换
     * @param driver 驱动对象
     * @param title 窗口标题
     *
     * */

    public static void switchWindow(WebDriver driver, String title){
        Set<String> allWindowHandles = driver.getWindowHandles();

        for(String windowHandle:allWindowHandles){
            //根据窗口的url地址或者标题来进行判断
            if(("title").equals(driver.getTitle())){
                break;
            }else{
                driver.switchTo().window(windowHandle);
            }
        }
    }




    /*
     *显式等待元素可见二次封装
     * @param driver
     * @param by
     *
     */
    public WebElement waitElementVisiable(WebDriver driver, By by){
        //实例化WebDriverWait 超时时间10s
        WebDriverWait webDriverWait =new WebDriverWait(driver,10);
        //2.通过until方法等到某个条件满足时为止
        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webElement;
    }


    /*
     *显式等待元素可被点击二次封装
     * @param driver
     * @param by
     *
     */
    public WebElement waitElementClickable(WebDriver driver,By by){
        //实例化WebDriverWait 超时时间10s
        WebDriverWait webDriverWait =new WebDriverWait(driver,10);
        //2.通过until方法等到某个条件满足时为止
        WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return webElement;
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

