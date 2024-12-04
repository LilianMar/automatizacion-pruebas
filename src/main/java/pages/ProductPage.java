package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Espera explícita de 10 segundos
    }

    By searchBox = By.name("search");
    By searchButton = By.cssSelector(".btn-default.btn-lg");
    By productLink = By.cssSelector("h4 a");
    By addToCartButton = By.id("button-cart");
    By cartSuccessMessage = By.cssSelector(".alert-success");

    public void searchProduct(String productName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void addProductToCart(String productName) {
        // Verificar que el producto esté visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
        WebElement product = driver.findElement(productLink);
        if (product.getText().equalsIgnoreCase(productName)) {
            product.click();
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            driver.findElement(addToCartButton).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartSuccessMessage));
        } else {
            System.out.println("Producto no encontrado: " + productName);
        }
    }
}
