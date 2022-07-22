import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestFirst {
    public static final String BASIC_URL = "https://pl.wikipedia.org/";
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(BASIC_URL);
    }

    @DataProvider(name = "search")
    public Object[][] getSearch(){
        return new Object[][] {
                {"fox"},
                {"dog"}
        };
    }

    @Test(dataProvider = "search")
    public void searchForWiki(String searchValue) {
        searchForText(driver, searchValue);
        String actualTitle = driver.getCurrentUrl();
        Assert.assertTrue(actualTitle.toLowerCase().contains(searchValue.toLowerCase()));
    }

    private void searchForText(WebDriver driver, String valueForSearch) {
        By pathToSearchInput = By.className("vector-search-box-input");
        WebElement searchInput = driver.findElement(pathToSearchInput);
        searchInput.click();
        searchInput.sendKeys(valueForSearch);
        searchInput.sendKeys(Keys.ENTER);
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
