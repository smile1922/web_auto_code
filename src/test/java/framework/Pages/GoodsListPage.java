package framework.Pages;

import framework.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoodsListPage extends BasePage {
    //商品列表
    private By goodsListBy = By.xpath("//a[text()='商品列表']");
    //第一个商品
    private By firstGoodsBy = By.xpath("//div[@class='goods-img']");
    //第三个商品
   // private By thirdGoodsBy = By.xpath("//div[@class='list-con normalList']/div[3]/div[1]");

    private WebDriver driver;
    public GoodsListPage(WebDriver driver){
        this.driver=driver;
    }

    public void clickGoodsList(){
       // waitElementClickable(driver,goodsListBy).click();
        click(driver,goodsListBy,"点击商品列表");
    }

    public GoodsDetailPage clickFirstGoods(){
        //waitElementClickable(driver,firstGoodsBy).click();
        click(driver,firstGoodsBy,"点击第一个商品");
       // click(driver,thirdGoodsBy,"点击第三个商品");
        return new GoodsDetailPage(driver);
    }
}
