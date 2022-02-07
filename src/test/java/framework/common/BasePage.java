package framework.common;

import framework.businesslogic.LoginFlow;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//页面
public class BasePage {

    private static Logger logger = Logger.getLogger(BasePage.class);
    /*
     *显式等待元素可见二次封装
     * @param driver
     * @param by
     *
     */
    public WebElement waitElementVisiable(WebDriver driver, By by){
        WebElement webElement = null;
        //实例化WebDriverWait 超时时间10s
       try {
           WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
           //2.通过until方法等到某个条件满足时为止
           webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
       }catch(Exception e){
           logger.info("定位元素【" + by +"】异常");
       }
       return webElement;
    }


    /*
     *显式等待元素可被点击二次封装
     * @param driver
     * @param by
     *
     */
    public WebElement waitElementClickable(WebDriver driver,By by){
        WebElement webElement = null;
        //实例化WebDriverWait 超时时间10s
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
            //2.通过until方法等到某个条件满足时为止
            webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        }catch(Exception e){
            logger.info("定位元素【" + by +"】异常");
        }
        return webElement;
    }
    /*
    *输入框输入数据通用方法
    * @param driver 驱动对象
    * @param by 元素定位信息
    * @para data 输入的数据
    */
    public void type(WebDriver driver,By by,String data,String elementName){
        logger.info("往元素【"+elementName+"】输入数据【"+data+"】");
        waitElementVisiable(driver,by).sendKeys(data);

    }

    public void click(WebDriver driver ,By by,String elementName){

        logger.info("对元素【"+elementName+"】进行点击");
        waitElementClickable(driver,by).click();
    }

    /*
    * 获取元素文本的通用方法
    * @para driver 驱动对象
    * @para by 元素定位信息
    * @para elementName 元素的描述信息
    * @return
    * */


    public String getText(WebDriver driver ,By by,String elementName){
        String text = waitElementVisiable(driver,by).getText();
        logger.info("获取元素【" + elementName + "】文本【" + text + "】");
        return text;
    }

}
