package test.day08;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class SmallProgramTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //启动被测APP---微信
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.tencent.mm");
        caps.setCapability("appActivity",".Ui.LauncherUI");
        // 一定要切记！！！！ 要加这个配置，否则微信数据全部清除 在通过代码启动app的时候不清除app的数据
        caps.setCapability("noReset",true);

        // 支持X5内核应用自动化配置
        caps.setCapability("recreateChromeDriverSessions", true);
        // ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候,
        // 把com.tencent.mm:appbrand0的webview识别成com.tencent.mm的webview.
        // 所以为了避免这个问题，加上androidProcess: com.tencent.mm:appbrand0
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        // 初始化会默认将chrome浏览器打开，需要将Browser置为空
        caps.setBrowserName("");



        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        Thread.sleep(4000);

        //向下滑动--小程序列表加载
        swipeDown(driver,2000);
        Thread.sleep(2000);
        //定位小程序列表中【柠檬班软件测试】
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬版软件...\")")).click();
        Thread.sleep(10000);
        //进入到小程序页面
        //System.out.println(driver.getContextHandles());

        //切换到小程序的状态中(WEBVIEW_COM.tencent.mm:appbrand0)
        driver.context("WEBVIEW_COM.tencent.mm:appbrand0");
        //当你的微信小程序打开之后是会有三个web页面(窗口)-->窗口切换
        switchWindow(driver,"腾讯课堂柠檬班软件测试");
        //定位元素[课程]
        driver.findElement(By.xpath("//a[contains(text(),'课程')]")).click();


    }

/*
 *向下滑动的通用封装
 * @param driver
 * @param swipeTIme 滑动间隔时间
 */

//=========================单次滑动====================================
        public static void swipeDown(AndroidDriver driver,int swipeTime){

            //1.确定滑动起始点(屏幕宽度1/2,屏幕高度1/4)，滑动终止点(屏幕宽度1/2,屏幕高度3/4) 坐标系统
            int width = driver.manage().window().getSize().getWidth();
            int height = driver.manage().window().getSize().getHeight();

            PointOption pointOption1 = PointOption.point(width/2,height/4);
            PointOption pointOption2 = PointOption.point(width/2,height*3/4);

            //2.通过TouchAction类来模拟滑动过程
            TouchAction touchAction = new TouchAction(driver);
            //先按下-->移动-->手指释放
            //waitAction() 设置滑动的间隔时间
            Duration duration = Duration.ofMillis(swipeTime);
            WaitOptions waitOptions = WaitOptions.waitOptions(duration);
            touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();

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


