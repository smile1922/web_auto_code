package framework.businesslogic;

import framework.Pages.GoodsDetailPage;
import framework.Pages.GoodsListPage;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class AddToCart {

    private WebDriver driver;

    public AddToCart(WebDriver driver) {
        this.driver = driver;

    }

    /*
     * 添加到购物测业务逻辑
     * @return hashmap数据，包含了商品名称，数量，价格
     *
     * */
    public HashMap<String, Object> doAction() {
        GoodsListPage goodsListPage = new GoodsListPage(driver);
        //点击商品列表
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goodsListPage.clickGoodsList();
        //点击第一个商品
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GoodsDetailPage goodsDetailPage = goodsListPage.clickFirstGoods();
        //点击添加到购物车
        goodsDetailPage.addTOCart();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取商品的名称
        String goodsTitle = goodsDetailPage.getGoodsName();

        //获取商品的价格
        String goodsPrice = goodsDetailPage.getGoodsPrice();

        //获取商品的数量
        String goodsAmount = goodsDetailPage.getGoodsNumber();
        //int amount
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("goodsTitle",goodsTitle);
        hashMap.put("goodsPrice",goodsPrice);
        hashMap.put("goodsAmount",goodsAmount);
        return hashMap;













    }

}