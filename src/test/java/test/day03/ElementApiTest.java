package test.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ElementApiTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver= openBrowser("chrome");
        driver.get("https://www.baidu.com");
        String Title = driver.getTitle();
        System.out.println(Title);

       // driver.findElement(By.id("kw")).sendKeys("柠檬班");
        //按键操作组合
      /*  //1.ctrl+a
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"a");
        //2.ctrl+c
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"c");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //3.ctrl+v
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"v");
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"v");
        driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL,"v");*/


        WebElement webElement =driver.findElement(By.xpath("//a[text()='地图']"));
        //获取标签名 getTagName
        String tagName = webElement.getTagName();
        System.out.println(tagName);
        //获取元素对应的属性值
        String hrefValue=webElement.getAttribute("href");
        System.out.println(hrefValue);

        //获取元素文本值
        String text =webElement.getText();
        System.out.println(text);

        //导航栏操作
        Navigation navigation = driver.navigate();
        navigation.to("https://www.hao123.com/");
        navigation.refresh();
        navigation.back();
        Thread.sleep(5);
        navigation.forward();

    }

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



