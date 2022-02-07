package test.day02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ElementLocate {
    public static void main(String[] args){
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.baidu.com");
        //===============六大基本元素定位======================
        //1.id定位
       /* driver.findElement(By.id("kw")).sendKeys("柠檬班");
        driver.findElement(By.id("su")).click();*/

        //2.name 属性
       // driver.findElement(By.name("wd")).sendKeys("柠檬班");

        //3.tagName标签名定位
      /* List<WebElement> allElements = driver.findElements(By.tagName("a"));
       System.out.println(allElements.size());*/

       //4.className 样式名定位
        //坑：通过自动化代码打开的浏览器四一个全新的浏览器
        //注意：定位元素的时候保证页面跟自动化代码跑的页面是一样的，如：登录状态和非登录状态
      //  driver.findElement(By.className("s_ipt")).sendKeys("柠檬班");
        //5.linkText定位，根据超链接元素的文本值(完整)定位
       // driver.findElement(By.linkText("更多")).click();

        //6.partialLinkText定位，根据超链接元素的文本值(部分)定位
      //  driver.findElement(By.partialLinkText("hao123")).click();

        //======================css选择器定位===============
        //1.根据标签名
      /*  List<WebElement> allElements = driver.findElements(By.cssSelector("a"));
        System.out.println(allElements.size());*/

        //2.根据ID #id
       // driver.findElement(By.cssSelector("#kw")).sendKeys("柠檬班");

        //3.class定位(样式名)  .class样式
       // driver.findElement(By.cssSelector(".s_ipt")).sendKeys("柠檬班");

        //4.单属性选择
      //  driver.findElement(By.cssSelector("input[autocomplete='off']")).sendKeys("柠檬班");
      //    driver.findElement(By.cssSelector("input[value='百度一下']")).click();
        //5.多属性选择
     //   driver.findElement(By.cssSelector("input[autocomplete='off'][class='s_ipt']")).sendKeys("柠檬班");
        

        //===============xpath 定位

    }
  /*
  *打开浏览器封装
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
