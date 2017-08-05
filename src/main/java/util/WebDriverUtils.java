package util;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    public enum WebDriverOptions {
        CHROME,
        FIREFOX,
        PHANTOM_JS
    }

    public WebDriver getWebDriver(WebDriverOptions webDriverOptions) {
        switch (webDriverOptions) {
            case FIREFOX:
            case PHANTOM_JS:
                throw new UnsupportedOperationException("This WebDriver is not supported yet");
            case CHROME:
            default:
                return this.getDefaultWebDriver();
        }
    }

    private WebDriver getDefaultWebDriver() {
        System.setProperty("webdriver.chrome.driver",
                new File("out/production/resources/chromedriver_win32/chromedriver.exe").getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return this.configureWebDriverOptions(new ChromeDriver(capabilities));
    }

    private WebDriver configureWebDriverOptions(WebDriver webDriver) {
        webDriver.manage().timeouts().pageLoadTimeout(Timeouts.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        return webDriver;
    }

}
