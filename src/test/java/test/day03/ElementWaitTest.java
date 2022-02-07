package test.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementWaitTest {

    public static void main(String[] args) throws InterruptedException {
    WebDriver driver = openBrowser("chrome");
    driver.get("https://movie.douban.com/");
    //设置隐式等待 超时时间10s
    //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    driver.findElement(By.xpath("//a[text()='选电影']")).click();
    //Thread.sleep(2000);
    //选择【不要抬头】
   /* //driver.findElement(By.xpath("//div[@class='list']/a[2]/p")).click();
    driver.get("https://www.baidu.com/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.id("s-usersetting-top")).click();
    driver.findElement(By.xpath("//a[text()='高级搜索']")).click();*/
    //显式等待
        //1.实例化WebDriver 超时时间10s
   /*     WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        By by= By.xpath("//div[@class='list']/a[2]/p");
        //2.通过util方法等到某个条件满足时为止
        WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        webElement.click();*/

    }


    /*
     *显式等待元素可见二次封装
     * @param driver
     * @param by
     *
     */
    public WebElement waitElementVisiable(WebDriver driver,By by){
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