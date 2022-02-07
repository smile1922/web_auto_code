package test.day07;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverApiTest {
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


        //swipeDown(driver,500);

        //模拟下拉刷新(从上到下滑动)
        //1.确定滑动起始点(426,508)，滑动终止点(426,1192) 坐标系统
        /*PointOption pointOption1 = PointOption.point(426, 508);
        PointOption pointOption2 = PointOption.point(426, 1192);

        //2.通过TouchAction类来模拟滑动过程
        TouchAction touchAction = new TouchAction(driver);
        //先按下-->移动-->手指释放
        //waitAction() 设置滑动的间隔时间
        Duration duration = Duration.ofMillis(300);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
        // touchAction.press(pointOption1).moveTo(pointOption2).release().perform();*/



        //=多次联系滑动==================
        /*driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(2000);

        PointOption pointOption1=PointOption.point(178,465);
        PointOption pointOption2  = PointOption.point(706,465);
        PointOption pointOption3= PointOption.point(178,1000);
        PointOption pointOption4=PointOption.point(706,1000);

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(pointOption1).moveTo(pointOption2).
                moveTo(pointOption3).moveTo(pointOption4).release().perform();
*/
        //多点手势操作 放大+缩小
        //核心点：一根手指代表一个TouchAction
        //放大的效果模拟
        driver.findElement(MobileBy.AccessibilityId("题库")).click();
        Thread.sleep(2000);
        PointOption pointOptionA = PointOption.point(368,565);
        PointOption pointOptionB = PointOption.point(561,1102);
        PointOption pointOptionC = PointOption.point(722,1307);
        PointOption pointOptionD = PointOption.point(173,330);
        //通过touchAction1模拟A-->D
         TouchAction touchAction1= new TouchAction(driver);
         touchAction1.press(pointOptionA).moveTo(pointOptionD).release();
         //通过touchAction2模拟B-->C
        TouchAction touchAction2=new TouchAction(driver);
         touchAction2.press(pointOptionB).moveTo(pointOptionC).release();
         //同时执行两根手指的操作呢？MultiTouchAction
        MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
        multiTouchAction.add(touchAction1);
        multiTouchAction.add(touchAction2);
        multiTouchAction.perform();






    }
    /*
    *向下滑动的通用封装
    * @param driver
    * @param swipeTIme 滑动间隔时间
    */

//=========================单次滑动====================================
        /*public static void swipeDown(AndroidDriver driver,int swipeTime){

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

    *//*
     *向上滑动的通用封装
     * @param driver
     * @param swipeTIme 滑动间隔时间
     *//*


    public static void swipeUp(AndroidDriver driver,int swipeTime){

        //1.确定滑动起始点(屏幕宽度1/2,屏幕高度3/4)，滑动终止点(屏幕宽度1/2,屏幕高度1/4) 坐标系统
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        PointOption pointOption1 = PointOption.point(width/2,height*3/4);
        PointOption pointOption2 = PointOption.point(width/2,height/4);

        //2.通过TouchAction类来模拟滑动过程
        TouchAction touchAction = new TouchAction(driver);
        //先按下-->移动-->手指释放
        //waitAction() 设置滑动的间隔时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();

    }

    *//*
     *向左滑动的通用封装
     * @param driver
     * @param swipeTIme 滑动间隔时间
     *//*


    public static void swipeLeft(AndroidDriver driver,int swipeTime){

        //1.确定滑动起始点(屏幕宽度3/4,屏幕高度1/2)，滑动终止点(屏幕宽度1/4,屏幕高度1/2） 坐标系统
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        PointOption pointOption1 = PointOption.point(width*3/4,height/2);
        PointOption pointOption2 = PointOption.point(width/4,height/2);

        //2.通过TouchAction类来模拟滑动过程
        TouchAction touchAction = new TouchAction(driver);
        //先按下-->移动-->手指释放
        //waitAction() 设置滑动的间隔时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();

    }

    *//*
     *向右滑动的通用封装
     * @param driver
     * @param swipeTIme 滑动间隔时间
     *//*


    public static void swipeRight(AndroidDriver driver,int swipeTime){

        //1.确定滑动起始点(屏幕宽度1/4,屏幕高度1/2)，滑动终止点(屏幕宽度3/4,屏幕高度1/2) 坐标系统
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();

        PointOption pointOption1 = PointOption.point(width/4,height/2);
        PointOption pointOption2 = PointOption.point(width*3/4,height/2);

        //2.通过TouchAction类来模拟滑动过程
        TouchAction touchAction = new TouchAction(driver);
        //先按下-->移动-->手指释放
        //waitAction() 设置滑动的间隔时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();

    }*/







}
