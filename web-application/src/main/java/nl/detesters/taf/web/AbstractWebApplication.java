package nl.detesters.taf.web;

import org.openqa.selenium.Capabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class AbstractWebApplication {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractWebApplication.class);

    private final OperatingSystem os;
    private final Browser browser;
    private final URL seleniumGridUrl;
    private RemoteWebDriver webDriver;

    public AbstractWebApplication(URL seleniumGridUrl, OperatingSystem os, Browser browser, String applicationUrl) {
        this.os = os;
        this.browser = browser;
        this.seleniumGridUrl = seleniumGridUrl;
        getDriver().get(applicationUrl);
    }

    @SuppressWarnings({"unchecked"})
    protected <E extends WebPage> E getPage(){
        return (E) getDriver().getCurrentPage();
    }

    /**
     * @return {@link RemoteWebDriver}
     */
    protected RemoteWebDriver getDriver() {
        if (webDriver == null) {
            LOG.info(String.format("Fetching Selenium driver for os '%s' and browser '%s'", os, browser));
            Capabilities capabilities = os.getCapabilities(browser);
            webDriver = new RemoteWebDriver(seleniumGridUrl, capabilities, 10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    /**
     * Closes the {@link RemoteWebDriver}
     */
    @PreDestroy
    protected void releaseDriver() {
        if (webDriver != null) {
            LOG.info(String.format("Cleanup Selenium driver"));
            webDriver.quit();
            webDriver = null;
        }
    }

}
