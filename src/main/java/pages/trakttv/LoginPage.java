package pages.trakttv;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import util.Timeouts;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    public static final String URL = "https://trakt.tv/auth/signin";
    private static final By LOGIN_INPUT = By.id("user_login");
    private static final By PASSWORD_INPUT = By.id("user_password");
    private static final By SIGN_IN_BUTTON = By.xpath("//input[@value='Sign in']");

    private WebDriver webDriver;
    private Wait<WebDriver> wait;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new FluentWait<>(this.webDriver)
                .withTimeout(Timeouts.BASE_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(Timeouts.POLLING_TIME, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        this.webDriver.get(URL);
    }

    public DashboardPage signInAs(String user, String password) {
        WebElement loginInput = wait.until(webDriver -> webDriver.findElement(LOGIN_INPUT));
        WebElement passwordInput = wait.until(webDriver -> webDriver.findElement(PASSWORD_INPUT));
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_BUTTON));

        loginInput.sendKeys(user);
        passwordInput.sendKeys(password);
        signInButton.click();

        return new DashboardPage(this.webDriver);
    }
}
