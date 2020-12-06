package base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver","webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 1000);

        String baseUrl = "http://www.sberbank.ru/ru/person";
        driver.get(baseUrl);
    }

    @AfterClass
    public static void after(){
        driver.quit();
    }

}
