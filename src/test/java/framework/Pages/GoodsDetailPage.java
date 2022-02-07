package framework.Pages;

import framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.day03.WebdriverApiTest;

public class GoodsDetailPage extends BasePage {
    //商品名称
    private By goodsNameBy = By.xpath("//div[@class='name-box']/div[@class='name']");
    //商品价格
    private By goodsPriceBy =By.xpath("//div[@class=\"item goods-price\"]//span[@class=\"big\"]");
    //商品数量
    private  By goodsNumberBy=By.xpath("//input[@class=\"number\"]");
    //加入购物车
    private By addToCartBy = By.xpath("//a[@class='add-cart']");
    private WebDriver driver;

    public GoodsDetailPage(WebDriver driver){
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

    public void addTOCart(){

        //waitElementClickable(driver,addToCartBy).click();
        click(driver,addToCartBy,"添加到购物车");
    }



}
