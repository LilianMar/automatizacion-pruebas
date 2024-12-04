package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    By cartButton = By.cssSelector(".btn-inverse");
    By viewCartLink = By.cssSelector("div#cart.btn-group.btn-block");
    By checkoutButton = By.linkText("Checkout");
    By confirmBillingButton = By.cssSelector("input#button-payment-address.btn.btn-primary");
    By confirmDeliveryDetailButton = By.cssSelector("input#button-shipping-address.btn.btn-primary");
    By confirmDeliveryMethodButton = By.cssSelector("input#button-shipping-method.btn.btn-primary");
    By confirmTermsButton = By.cssSelector("input[type='checkbox'][name='agree']");
    By confirmPaymentMethodButton = By.cssSelector("input#button-payment-method.btn.btn-primary");
    By confirmOrderButton = By.cssSelector("input#button-confirm.btn.btn-primary");


    By confirmationMessage = By.cssSelector("div#common-success.container div.row div#content.col-sm-12 h1");


    public void proceedToCheckout() {
        driver.findElement(cartButton).click();
        driver.findElement(viewCartLink).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmBillingButton));
        driver.findElement(confirmBillingButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmDeliveryDetailButton));
        driver.findElement(confirmDeliveryDetailButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmDeliveryMethodButton));
        driver.findElement(confirmDeliveryMethodButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmTermsButton));
        driver.findElement(confirmTermsButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmPaymentMethodButton));
        driver.findElement(confirmPaymentMethodButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}
