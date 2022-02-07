package test.day06;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ElementLocateTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //一、启动被测的app
        //1.初始化配置对象，用来存放启动app的四个配置
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","com.lemon.lemonban");
        caps.setCapability("appActivity",".activity.WelcomeActivity");
        //与appium服务建立连接，把我们的配置传给Appium
        //第一个参数：URL appium的工作地址
        // 第二个参数：caps配置
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver= new AndroidDriver(url,caps);
        Thread.sleep(4000);

        //3.定位【首页】的题库元素
       // driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(1000);
        //4.定位去登录元素
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"去登录\")")).click();
        //5.输入手机号码
        driver.findElement(MobileBy.xpath("//android.widget.EditText[@text='手机号码']")).sendKeys("18813006679");
        //driver.findElement(MobileBy.xpath("//*[@text='手机号码']")).sendKeys("18813006679");
        //6.输入密码
        driver.findElement(MobileBy.xpath("//android.widget.EditText[@text='密码']")).sendKeys("006679");
        //7.点击登录按钮
        driver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
    }
}
