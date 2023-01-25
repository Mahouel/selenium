import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.quit();
        //commande pour lancer les tests sur Firefox;
        //driver = new FirefoxDriver
    }

    @Test
    public void firstTest (){
        driver = new ChromeDriver();
        System.out.println("Je suis à mon premier test");
        driver.get("https://chromedriver.chromium.org/downloads");

    }
}
