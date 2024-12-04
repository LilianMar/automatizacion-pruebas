package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By myAccount = By.linkText("My Account");
    By loginLink = By.linkText("Login");
    By emailField = By.id("input-email");
    By passwordField = By.id("input-password");
    By loginButton = By.cssSelector("input[value='Login']");
    By accountName = By.cssSelector("input#input-firstname.form-control");
    By editInformationLink = By.linkText("Edit Account");  // Localizamos el enlace "Edit Information"

    public void navigateToLogin() {
        driver.findElement(myAccount).click();
        driver.findElement(loginLink).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getAccountName() {
        // Hacer clic en el enlace "Edit Information"
        driver.findElement(editInformationLink).click();

        // Esperar hasta que el campo esté visible (asegúrate de que el campo sea accesible después de hacer clic)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountName));

        // Obtener el texto del campo de nombre de cuenta
        return driver.findElement(accountName).getAttribute("value");  // Usamos getAttribute("value") para obtener el texto del campo input
    }
}
