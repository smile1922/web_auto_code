package framework.testcases;

import framework.Pages.HomePage;
import framework.Pages.LoginPage;
import framework.businesslogic.LoginFlow;
import framework.common.BaseTest;
import framework.config.GlobalDatas;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class TestLogin02 extends BaseTest {
    public WebDriver driver = null;

    @BeforeMethod
    @Parameters({"browserName"})
    public void setup(String browserName) {
        //用例前置
        //1.打开浏览器
        driver = openBrowser(browserName);
        driver.manage().window().maximize();
        //2.进入登录页面
        driver.get(GlobalDatas.INDEX_URL);
        HomePage homePage = new HomePage(driver);
        homePage.enterLoginPage();
    }

    @Test
    public void test_login_success() throws InterruptedException {
        //用例步骤
       /* driver.findElement(By.xpath("//input[@placeholder='请输入手机号/用户名']")).sendKeys(GlobalDatas.USER_NAME);
        driver.findElement(By.xpath("//input[@placeholder='请输入密码']")).sendKeys(GlobalDatas.USER_PASSWORD);
        driver.findElement(By.className("login-button")).click();
        Thread.sleep(3000);*/
        //2层PO
        //通过调用页面层已经封装好的操作组成业务逻辑--登录
     /*   LoginPage loginPage = new LoginPage(driver);
        loginPage.inputPhone(GlobalDatas.USER_NAME);
        loginPage.inputPassword(GlobalDatas.USER_PASSWORD);
        HomePage homePage = loginPage.clickLogin();*/


        //三层PO
        LoginFlow loginFlow = new LoginFlow(driver);
        HomePage homePage = loginFlow.login(GlobalDatas.USER_NAME, GlobalDatas.USER_PASSWORD);

        //用例断言，测试结果是否符合预期
        //1.根据主页的提示【欢迎来到柠檬班】，根据它是否有显示？？
        myAssertTrue(homePage.isTipsExist(), "欢迎来到柠檬班提示");

        //2.根据主页的用户名
       myAssertTrue(homePage.isNicknameExist(), "登录成功用户名");
       takeScreenshot(driver,"test");
       //退出登录
        homePage.quitLogin();


   }

    @Test(dataProvider = "getLoginFailureDatas")
    @Description("手机号码10位/手机号码12位/手机号码未注册")
    public void test_login_failure(String phone,String pwd){
        LoginFlow loginFlow = new LoginFlow(driver);
        HomePage homePage = loginFlow.login(phone,pwd);
        //断言？？
        LoginPage loginPage = new LoginPage(driver);
        String actual = loginPage.getErrorAccountPwdText();
        myAssertEquals(actual,"账号或密码不正确","账号密码不正确提示信息");
    }


    @DataProvider
    public Object[][] getLoginFailureDatas(){
        Object[][] datas={
                {"13323234541","123456"},
                {"133232345451","123456"},
                {"1332323455","123456"},
        };
        return datas;
    }


    @AfterMethod
    public void teardown() {
        //用例后置
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       /* HomePage homePage = new HomePage(driver);
        homePage.quitLogin();*/
       //关闭浏览器
        closeBrowser(driver);
    }

    public void takeScreenshot(WebDriver driver,String filename){
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        //源文件目录
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //本地路径
        File destFile = new File(System.getProperty("user.dir") + "\\screenshot"+filename+".png");

        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    public static void main(String[] args){
        System.out.println(System.getProperty("user.dir"));

    }
}

