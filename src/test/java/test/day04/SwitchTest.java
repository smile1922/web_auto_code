package test.day04;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SwitchTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = openBrowser("chrome");
       // driver.get("https://www.baidu.com");
       // System.out.println("点击前当前窗口的标识"+driver.getWindowHandle());


        /*driver.findElement(By.xpath("//a[text()='hao123']")).click();
        switchWindow(driver,"hao123_上网从这里开始");*/
      //  System.out.println("点击后当前窗口的标识"+driver.getWindowHandle());

        /*Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("点击后当前窗口的标识"+driver.getWindowHandles());
        //思路：遍历窗口句柄的集合，判断当前的窗口标识是不是对的，不是的话进行切换
        for(String windowHandle:allWindowHandles){
            //根据窗口的url地址或者标题来进行判断
            if(("https://www.hao123.com").equals(driver.getCurrentUrl())){
                break;
            }else{
                driver.switchTo().window(windowHandle);
            }

        }*/
      //  driver.findElement(By.xpath("//a[text()='星座运势']")).click();

        //2.iframe切换
       /* driver.get("https://ke.qq.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("js_login")).click();
        Thread.sleep(2000);
        //iframe元素定位
        WebElement iframeElement = driver.findElement(By.xpath("//iframe[contains(@src,'xlogin')]"));
        driver.switchTo().frame(iframeElement);

        //账号密码登录
        driver.findElement(By.id("switcher_plogin")).click();
        //输入QQ号码
        driver.findElement(By.id("u")).sendKeys("849361161");
        Thread.sleep(2000);
        //关闭登录弹窗
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//a[@title='关闭']")).click();*/

        //例子二.多层iframe切换
      /*  driver.get("file:///E:/test/iframe/a.html");
        Thread.sleep(2000);
       */ //1.切换到b.html中
     /*   WebElement bframeElement = driver.findElement(By.id("bframe"));
        driver.switchTo().frame(bframeElement);
        driver.findElement(By.id("bb")).sendKeys("b输入框");
*/
        //2.切换搭配c.html中
   /*     WebElement cframeElement = driver.findElement(By.id("cframe"));
        driver.switchTo().frame(cframeElement);
        driver.findElement(By.id("cc")).sendKeys("c输入框");
        Thread.sleep(2000);*/
        //3.返回到默认页面-切换到a.html中
        //3-1 parentFrame() 一级一级第返回
        /*driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.findElement(By.id("aa")).sendKeys("a输入框");*/
        //3-2 defaultContent() 一次性回到默认页面
       /* driver.switchTo().defaultContent();
        driver.findElement(By.id("aa")).sendKeys("a输入框");
*/

        //3.alter弹框处理
     /*   driver.get("E:\\test\\alert.html");
        Thread.sleep(2000);
        driver.findElement(By.id("abtn")).click();
        Thread.sleep(2000);
        //切换alter
        Alert alert = driver.switchTo().alert();
      //  alert.accept();

        Thread.sleep(2000);
        alert.dismiss();*/

        //JavaScript 操作
        //1.移除元素的属性
      /*  driver.get("https://www.12306.cn/index/");
        Thread.sleep(2000);*/
        //在当前去调用JavaScript的代码
        //强制转换，得到JavaScript的执行能力
        //JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        //设置元素的属性值
        //javascriptExecutor.executeScript("document.getElementById('train_date').setAttribute('value','2020-1-1');");
        //移除掉元素的属性值
        //javascriptExecutor.executeScript("document.getElementById('train_date').removeAttribute('value','2020-1-1');");
        //2.页面滚动
        //2-1 滚动到页面的最低部
       /* driver.get("https://www.12306.cn/index/");
        Thread.sleep(2000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;*/
        //javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        //2-2 滚动到指定的元素上去
        //javascriptExecutor.executeScript("document.getElementById('index_ads').scrollIntoViewIfNeeded(true);");

        //3.特殊元素无法被点击的情况
        driver.get("https://www.ketangpai.com/#/login");
        driver.findElement(By.xpath("//input[@placeholder='请输入邮箱/手机号/账号']")).sendKeys("18813006679");
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys("0224sxd");
       // driver.findElement(By.xpath("//span[text()='登录']")).click();

        //      元素不可被点击
 /*       Exception in thread "main" org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
        Element <span>...</span> is not clickable at point (867, 364). Other element would receive the
        click: <div data-v-6de82106="" class="bottom-box">...</div>*/
        //通过JavaScript来点击元素
        //传参的使用，Java中的变量传递给JavaScript
        WebElement webElement = driver.findElement(By.xpath("//span[text() = '登录']"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("arguments[0].click()",webElement);




    }


    /*
    * 封装通用切换窗口的方法--根据对应窗口的标题来切换
    * @param driver 驱动对象
    * @param title 窗口标题
    *
    * */

    public static void switchWindow(WebDriver driver,String title){
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
