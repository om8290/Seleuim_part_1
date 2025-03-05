import org.example.Dashboard;
import org.example.Logout;
import org.example.SignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpTest {
    private WebDriver driver;
    private SignupPage signUpPage;
    private Dashboard dashboard;
    private Logout logoutit;

    @BeforeClass
    public void setUp() {
        // Set up the ChromeDriver (ensure the chromedriver executable is in your PATH)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Replace with the actual sign-up URL

        // Initialize Page Objects
        signUpPage = new SignupPage(driver);
        dashboard = new Dashboard(driver);
        logoutit = new Logout(driver);
    }

    @Test(priority = 0)
    public void testSignUp() throws Exception {
        // Perform sign-up with the specified credentials
        signUpPage.signUp("Admin", "admin123");

        // Assert successful login by checking dashboard header visibility
        Thread.sleep(5000L);
        Assert.assertTrue(driver.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")).isDisplayed(),
                "Dashboard header is not displayed, login might have failed.");
    }

    @Test(priority = 1)
    public void claim() {
        dashboard.navigateToClaimPage();
    }

    @Test(priority = 2)
    public void logoutIt() {
        // Perform logout and capture the login page title
        String loginText = logoutit.performLogout();
        // Assert that logout was successful and user is redirected to the login page
        Assert.assertEquals(loginText, "Login", "Logout failed! Expected Login page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
