package test.day07;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ElementUnableLocateTest {
    public static void main(String [] args) throws MalformedURLException, InterruptedException {
    //启动被测APP
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","127.0.0.1:62001");
        caps.setCapability("platformName","Android");
        caps.setCapability("appPackage","tv.danmaku.bili");
        caps.setCapability("appActivity",".MainActivityV2");
        // 在通过代码启动app的时候不清除app的数据
        //caps.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url,caps);
        Thread.sleep(3000);
        //点击[同意并继续]
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/agree")).click();
        //点击【登录导航栏按钮】
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/drawer_handler")).click();
        //点击【登录】按钮
        driver.findElement(MobileBy.id("tv.danmaku.bili:id/login")).click();
        Thread.sleep(2000);
        System.out.println(driver.getPageSource());

        //点击密码登录
        driver.findElement(MobileBy.id("android:id/button1")).click();
       // text定位
       driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"我知道了\") "));




    }
}
