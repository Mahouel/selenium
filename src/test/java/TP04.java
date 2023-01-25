import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TP04 {
    WebDriver driver;
    WebDriverWait wait;
    private By login = By.id("email_register");
    private By loginConnec = By.id("email_login");
    private String loginValue = "kmahouel@zenity.fr";
    private By password = By.id("password_register");
    private By passwordConnec = By.id("password_login");
    private By password1 = By.id("confirm_password_register");
    private String passwordValue = "123456789";
    private By btn = By.id("btn_register");
    private By btnConnec = By.id("btn_login");
    private By logoHomePage = By.xpath("//div[@id='style_content_logo__pkvMP']/h1");
    private By panier = By.xpath("//div[@id='style_content_cart_wrapper__mqNbf']");
    private By compte = By.id("style_avatar_wrapper__pEGIQ");
    private By recherche = By.cssSelector("input#style_input_navbar_search__Scaxy");
    private By resultats= By.xpath("//div[@id='style_popular_product_wrapper__z6J0h']/div[@class='style_card__gNEqX']");

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://ztrain-web.vercel.app/auth/register");

    }


    @Test
    public void registration() {
        System.out.println("Creation compte");
        WebElement email_register = driver.findElement(login);
        email_register.sendKeys(loginValue);
        //driver.findElement(login).sendKeys(loginValue);
        WebElement password_register = driver.findElement(password);
        password_register.sendKeys(passwordValue);

        WebElement confirm_password_register = driver.findElement(password1);
        confirm_password_register.sendKeys(passwordValue);

        WebElement btn_register = driver.findElement(btn);
        btn_register.click();
        driver.manage().window().maximize();
        WebElement authLogin = driver.findElement(By.className("style_link__unbWN"));
        authLogin.click();

        System.out.println("Connection");
        WebElement email_connec = driver.findElement(loginConnec);
        email_connec.sendKeys(loginValue);
        //driver.findElement(login).sendKeys(loginValue);
        WebElement passwordConnection = driver.findElement(passwordConnec);
        passwordConnection.sendKeys(passwordValue);

        WebElement btnConnection = driver.findElement(btnConnec);
        btnConnection.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(logoHomePage));
        String ActualTitle = driver.getCurrentUrl();
        String ExpectedTitle = "https://ztrain-web.vercel.app/home";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
        Assert.assertTrue(driver.findElement(panier).isEnabled(), "Le panier n'est actif");
        Assert.assertTrue(driver.findElement(compte).isEnabled(), "Le compte n'est actif");

        int panierLocator = driver.findElement(panier).getLocation().x;
        int compteLocator = driver.findElement(compte).getLocation().x;

        Assert.assertTrue(panierLocator < compteLocator);

        System.out.println("Chercher des chaussures sur le site");
        WebElement rechercheChausseurs = driver.findElement(recherche);
        rechercheChausseurs.click();
        rechercheChausseurs.sendKeys("chaussures");

        int sizeResults = driver.findElements(resultats).size();
        System.out.println(sizeResults);
        Assert.assertTrue(sizeResults>=2);

    }

    @AfterMethod
    public void teardown() {
        //driver.quit();
    }

}
