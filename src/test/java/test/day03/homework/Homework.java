package test.day03.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homework {
    public static void main(String[] args) throws InterruptedException {
        //打开浏览器
        WebDriver driver = openBrowser("chrome");
        //打开测试派
        driver.get("http://testingpai.com/login");
        //浏览器窗口最大化
        driver.manage().window().maximize();

        //点击登录页面进行登录
        driver.findElement(By.xpath("//input[@placeholder='用户名/邮箱/手机号']")).sendKeys("18813006679");
        driver.findElement(By.id("loginPassword")).sendKeys("0224sxd");
        driver.findElement(By.id("loginBtn")).click();

        //确保登录能够成功-显式等待
        //等待首页的某个特征的出现从而判定是否进入到首页(登录成功)
        WebDriverWait driverWait = new WebDriverWait(driver,10);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() ='欢迎来到 测试派 社区']")));
        driver.get("http://testingpai.com/article/1601464528256");

        //点击回帖
        driver.findElement(By.xpath("//span[text()='请输入回帖内容...']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='vditor-ir']/pre[@placeholder='请输入回帖内容']")).
                sendKeys("测试回帖S");
        Thread.sleep(2000);
        driver.findElement(By.id("commentSubmitBtn")).click();

        Thread.sleep(1000);

        WebElement webElement = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-author='song1922']/p")));
        System.out.println(webElement.getText());

        //返回首页，刷新页面，关闭浏览器
        WebDriver.Navigation navigation = driver.navigate();
        navigation.back();
        Thread.sleep(1000);
        navigation.refresh();
        Thread.sleep(1000);
        driver.quit();

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



