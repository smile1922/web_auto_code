package framework.testcases;

import framework.Pages.CartPage;
import framework.Pages.GoodsDetailPage;
import framework.Pages.GoodsListPage;
import framework.Pages.HomePage;
import framework.businesslogic.AddToCart;
import framework.businesslogic.LoginFlow;
import framework.common.BaseTest;
import framework.config.GlobalDatas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.util.HashMap;

public class TestCart extends BaseTest{
    WebDriver driver=null;
    @BeforeTest
    public void setup(){
        //用例前置
        //1.打开浏览器-->登录
        driver =openBrowser(GlobalDatas.BROWSER_NAME);
        driver.manage().window().maximize();
        driver.get(GlobalDatas.INDEX_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterLoginPage();
        LoginFlow loginFlow =new LoginFlow(driver);
        loginFlow.login(GlobalDatas.USER_NAME,GlobalDatas.USER_PASSWORD);

    }

    @Test
    public void test_addtocart_success() throws InterruptedException {
       //测试步骤
        //1.添加商品到购物车
        Thread.sleep(3000);
        AddToCart addToCart = new AddToCart(driver);
        HashMap<String,Object> datas = addToCart.doAction();
        //断言
        //进入购物车
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.enterCartPage();
        //1.商品的名称
        //Assert.assertEquals(cartPage.getGoodsName(),datas.get("goodsTitle"));
       myAssertEquals(cartPage.getGoodsName(),datas.get("goodsTitle"),"商品名称断言");
        //2.商品的价格
        Assert.assertEquals(cartPage.getGoodsPrice(),datas.get("goodsPrice"));
        //3.商品的数量
        Assert.assertEquals(cartPage.getGoodsNumber(),datas.get("goodsAmount"));
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
       //测试后置
        //删除购物车商品
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteCart();
        Thread.sleep(2000);
        //1.退出登录
        HomePage homePage = new HomePage(driver);
        homePage.quitLogin();
        //2.关闭浏览器
        closeBrowser(driver);
    }
}
