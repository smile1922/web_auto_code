package framework.businesslogic;

import framework.Pages.HomePage;
import framework.Pages.LoginPage;
import framework.config.GlobalDatas;
import framework.testcases.TestLogin02;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;



public class LoginFlow {

    private WebDriver driver;
    public LoginFlow(WebDriver driver){
        this.driver=driver;
    }

    public HomePage login(String phone,String password){
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.inputPhone(GlobalDatas.USER_NAME);
       // loginPage.inputPassword(GlobalDatas.USER_PASSWORD);
        loginPage.inputPhone(phone);
        loginPage.inputPassword(password);
        HomePage homePage = loginPage.clickLogin();
        return homePage;
    }
}
