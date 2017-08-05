package pages.trakttv;

import model.TvShow;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import util.Timeouts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardPage {

    public static final String URL = "https://trakt.tv/dashboard";
    private static final By POSTERS = By.xpath("//div[@class='row posters']");
    private static final By TITLES = By.xpath("//section[@id='ondeck-wrapper']//div[@class='titles']");

    private WebDriver webDriver;
    private Wait<WebDriver> wait;

    public DashboardPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new FluentWait<>(this.webDriver)
                .withTimeout(Timeouts.BASE_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(Timeouts.POLLING_TIME, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        if (!this.webDriver.findElement(POSTERS).isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(POSTERS));
        }
    }

    public List<TvShow> getWatchList() {
        List<TvShow> result = new ArrayList<>();
        List<WebElement> titles = this.webDriver.findElements(TITLES);

        for (WebElement title : titles) {
            // Example: '3x10 Built, Not Born\nDark Matter'
            String elementText = title.getText();
            Pattern seasonAndEpisodeNumberPattern = Pattern.compile("\\d+x\\d+");
            Pattern episodeNamePattern = Pattern.compile("[^\\d+x\\d+\\s].+\\n");
            Pattern tvShowNamePattern = Pattern.compile("\\n.+");

            String seasonNumber = "s";
            String episodeNumber = "e";
            String episodeName = null;
            String tvShowName = null;

            Matcher matcher = seasonAndEpisodeNumberPattern.matcher(elementText);
            if (matcher.find()) {
                String match = matcher.group();
                // Replace 1 to 01 otherwise it won't be found in TPB
                if (match.split("x")[0].length() == 1) {
                    seasonNumber += "0" + match.split("x")[0];
                } else {
                    seasonNumber += match.split("x")[0];
                }

                episodeNumber += match.split("x")[1];
            }
            matcher = episodeNamePattern.matcher(elementText);
            if (matcher.find()) {
                episodeName = matcher.group().trim();
            }
            matcher = tvShowNamePattern.matcher(elementText);
            if (matcher.find()) {
                tvShowName = matcher.group().trim();
            }
            result.add(
                    new TvShow(
                            seasonNumber,
                            episodeNumber,
                            episodeName,
                            tvShowName));
        }

        return result;
    }

    public void signOut() {
        this.webDriver.get("https://trakt.tv/logout");
    }
}
