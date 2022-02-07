package framework.Pages;

import framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By goodsNameBy = By.xpath("//a[@class=\"name\"]");
    private By goodsPriceBy = By.xpath("//div[@class='unit-price']");

    private By goodsNumberBy = By.xpath("//input[@class=\"number\"]");
    private By deleteCartBy = By.xpath("//a[text()='删除']");
    private By confirmBy = By.cssSelector(".btn-r");

    private WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getGoodsName(){

        return waitElementVisiable(driver,goodsNameBy).getText();
    }

    public String getGoodsPrice(){

        return waitElementVisiable(driver,goodsPriceBy).getText();
}
    public String getGoodsNumber(){
        return waitElementVisiable(driver,goodsNumberBy).getText();

}
    public void deleteCart(){
        //waitElementClickable(driver,deleteCartBy).click();
        click(driver,deleteCartBy,"删除购物车");
        //waitElementClickable(driver,confirmBy).click();
        click(driver,confirmBy,"二次弹窗确定按钮");
    }

}
