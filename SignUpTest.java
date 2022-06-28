import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest {
    private  WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigate() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }



    @Test
    public void positiveZipCodeTest() throws InterruptedException {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(), "");
    }
   @Test
    public void negativeZipCodeTestSymbol() throws InterruptedException {
        driver.findElement(By.name("zip_code")).sendKeys("qqqqq");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), " ");
    }
    @Test
    public void negativeZipCodeTestOneNumber() throws InterruptedException {
        driver.findElement(By.name("zip_code")).sendKeys("1");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), " ");
    }
    @Test
    public void positiveSignUpTestRegistration () throws InterruptedException {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("Yarosalv");
        driver.findElement(By.name("last_name")).sendKeys("");
        driver.findElement(By.name("email")).sendKeys("yasharko@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(), "");
    }
    @Test
    public void negativeSignUpTestInvalidEmail () throws InterruptedException {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("Yarosalv");
        driver.findElement(By.name("last_name")).sendKeys("Sharko");
        driver.findElement(By.name("email")).sendKeys("yasharko");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        WebElement registerButton3 = driver.findElement(By.cssSelector("[value='Register']"));
        registerButton3.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "");
    }
    @Test
    public void negativeSignUpTestMissingField () throws InterruptedException {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.name("last_name")).sendKeys("Sharko");
        driver.findElement(By.name("email")).sendKeys("yasharko@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        WebElement registerButton= driver.findElement(By.cssSelector("[value='Register']"));
        registerButton.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "");
    }
}