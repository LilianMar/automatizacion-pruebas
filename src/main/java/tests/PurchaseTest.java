package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ProductPage;
import pages.LoginPage;
import utils.ExcelUtils;

import java.util.List;

public class PurchaseTest {
    WebDriver driver;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opencart.abstracta.us/");
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);  // Inicializamos la página de Login
    }

    @Test
    public void testPurchaseFlow() {
        // Paso 1: Iniciar sesión
        String[] credentials = ExcelUtils.getLoginData("src/test/resources/testdata.xlsx");
        loginPage.navigateToLogin();
        loginPage.login(credentials[0], credentials[1]);

        // Esperar que el nombre del usuario esté visible después del login


        // Paso 2: Procesar la compra
        List<String[]> products = ExcelUtils.getProducts("src/test/resources/testdata.xlsx");

        System.out.println("=== Reporte de compra ===");
        for (String[] product : products) {
            String productName = product[0];
            int quantity = Integer.parseInt(product[1]);
            System.out.println("Procesando producto: " + productName + " | Cantidad: " + quantity);

            try {
                productPage.searchProduct(productName);
                for (int i = 0; i < quantity; i++) {
                    productPage.addProductToCart(productName);
                }
                System.out.println("Producto agregado exitosamente: " + productName);
            } catch (Exception e) {
                System.out.println("Error al procesar el producto: " + productName + " | Detalles: " + e.getMessage());
            }
        }

        // Paso 3: Proceder al checkout
        try {
            checkoutPage.proceedToCheckout();
            checkoutPage.confirmOrder();

            String confirmation = checkoutPage.getConfirmationMessage();
            Assert.assertEquals(confirmation, "Your order has been placed!", "Mensaje de confirmación incorrecto");
            System.out.println("Compra completada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error durante el proceso de checkout: " + e.getMessage());
        }
        System.out.println("===========================");
    }



    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
