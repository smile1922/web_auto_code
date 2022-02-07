package framework.Pages;

import framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    //1.登录链接
    private By loginBy = By.xpath("//a[text()='登录']");
    //2.购物车
    private By cartBy = By.xpath("//span[@data-route='cart']");
    //3.登录成功的提示语
    private By tipsBy =By.xpath("//span[text() = '欢迎来到柠檬班']");
    //4.用户名
    private By nicknameBy = By.xpath("//a[text()='waiwai']");

    //5.退出登录
    private By quitBy = By.xpath("//a[text()='退出登录']");

    private WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public LoginPage enterLoginPage(){
        //waitElementClickable(driver,loginBy).click();
        click(driver,loginBy,"进入链接");
        return new LoginPage(driver);
    }

    public CartPage enterCartPage(){
       // waitElementClickable(driver,cartBy).click();
        click(driver,cartBy,"进入购物车页面");
        return new CartPage(driver);
        //可以返回购物车页面
    }

    public void quitLogin(){
        Actions actions = new Actions(driver);
        WebElement webElement = waitElementClickable(driver,nicknameBy);
        actions.moveToElement(webElement).perform();
        click(driver,quitBy,"退出登录");
    }

    public boolean isTipsExist(){

        return waitElementVisiable(driver,tipsBy).isDisplayed();
        }

    public boolean isNicknameExist(){

        return waitElementVisiable(driver,nicknameBy).isDisplayed();
    }
}
