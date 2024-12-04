package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opencart.abstracta.us/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.navigateToLogin();
        String[] credentials = ExcelUtils.getLoginData("src/test/resources/testdata.xlsx");
        loginPage.login(credentials[0], credentials[1]);
        Assert.assertEquals(loginPage.getAccountName(), "Expected Account Name");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
