package test.day06;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverApiTest {
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
/*        //获取首页的页面名
        System.out.println(driver.currentActivity());
        //获取设备时间信息
        System.out.println(driver.getDeviceTime());
        //获取设备DPI，注意不是分辨率
        System.out.println(driver.getDisplayDensity());
        //获取automation name，默认为null，如果有指定automation name为uiautomator2就为对应值
        System.out.println(driver.getAutomationName());
        //获取设置横竖屏屏状态，有PORTRAIT(竖屏)与LANDSCAPE（横屏）
        System.out.println(driver.getOrientation());*/

        //3.定位【首页】的题库元素
        // driver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(1000);
        //4.定位去登录元素
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"去登录\")")).click();

        //发送按键事件
        //返回
     /*   //1.实例化KeyEvent
        KeyEvent keyEvent = new KeyEvent();
      //2.设置按键操作：返回
        //keyEvent.withKey(AndroidKey.BACK);
        keyEvent.withKey(AndroidKey.VOLUME_DOWN);
        //3.执行对应的事件
        driver.pressKey(keyEvent);*/

        //截图
        File file = driver.getScreenshotAs(OutputType.FILE);
        //保存到本地
        File file2 = new File("D:\\test\\screenshot_app.png");
        try {
            FileUtils.copyFile(file,file2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
