package test.day08;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class HybridTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //一、启动被测的app
        //1.初始化配置对象，用来存放启动app的四个配置
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "127.0.0.1:62001");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.lemon.lemonban");
        caps.setCapability("appActivity", ".activity.WelcomeActivity");
        //与appium服务建立连接，把我们的配置传给Appium
        //第一个参数：URL appium的工作地址
        // 第二个参数：caps配置
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, caps);
        Thread.sleep(4000);

        //点击【柠檬社区】
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬社区\")")).click();
        Thread.sleep(4000);
        //切换状态，从原生页面元素状态--》web页面元素状态
        //[NATIVE_APP, WEBVIEW_com.lemon.lemonban]
        //System.out.println(driver.getContextHandles());
        //通过context来进行切换
        driver.context("WEBVIEW_com.lemon.lemonban");
        //定位web元素信息
        driver.findElement(By.xpath("//a[@href='http://testingpai.com/register']")).click();



    }
}
