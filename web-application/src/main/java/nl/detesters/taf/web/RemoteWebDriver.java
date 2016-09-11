package nl.detesters.taf.web;

import com.google.common.base.Predicate;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RemoteWebDriver extends org.openqa.selenium.remote.RemoteWebDriver {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteWebDriver.class);
    private final int globalTimeOut;
    private final TimeUnit timeUnit;

    /**
     * Returns a new instance of RemoteWebDriver
     *
     * @param remoteAddress       the (remote/local) SeleniumGrid url
     * @param desiredCapabilities the driver capabilities
     * @param timeout             the driver timeout
     * @param timeUnit            the time unit for the driver timeout
     */
    public RemoteWebDriver(URL remoteAddress, Capabilities desiredCapabilities, int timeout, TimeUnit timeUnit) {
        super(remoteAddress, desiredCapabilities);
        globalTimeOut = timeout;
        this.timeUnit = timeUnit;
        setTimeOut();
    }

    /**
     * Override of findElement in superclass.
     *
     * @param selector the element to find
     * @return the WebElement
     */
    @Override
    public org.openqa.selenium.WebElement findElement(By selector) {
        return getCurrentPage().findElement(selector);
    }

    /**
     * Override of findElements in superclass.
     *
     * @param selector the elements to find
     * @return List of WebElements
     */
    @Override
    public List<org.openqa.selenium.WebElement> findElements(By selector) {
        return getCurrentPage().findElements(selector);
    }

    /**
     * Check whether the browser throws an alert window. The wait period has been set to {@link #globalTimeOut}.
     * If the alert window is present the focus will also switch to the same.
     *
     * @return boolean
     */
    public boolean isAlertPresent() {
        boolean isPresent = false;

        LOG.debug("Checking if Alert Window is present.");
        try {
            WebDriverWait wait = new WebDriverWait(this, globalTimeOut);
            wait.until(ExpectedConditions.alertIsPresent());
            LOG.debug("Detected Alert Window.");
            isPresent = true;
        } catch (NoAlertPresentException e) {
            LOG.trace(e.getMessage());
            LOG.debug("Could not detect presence of Alert Window.");
        }
        return isPresent;
    }

    /**
     * Accept and dismiss an alert window displayed by the browser.
     */
    public void acceptAlert() {
        LOG.debug("Accepting alert Window...");
        switchTo().alert().accept();
        switchTo().defaultContent();
        LOG.debug("Accepted Alert Window and switched back to default content.");
    }

    public WebPage getCurrentPage(){
        return (WebPage) findElement(By.xpath("/"));
    }

    /**
     * Sets the global webdriver timeout.
     *
     * @param timeout  the timeout period
     * @param timeUnit the time unit
     */
    public void setTimeOut(long timeout, TimeUnit timeUnit) {
        this.manage().timeouts().implicitlyWait(timeout, timeUnit);
    }

    /**
     * Sets the global webdriver timeout with the default value.
     *
     */
    public void setTimeOut() {
        this.manage().timeouts().implicitlyWait(globalTimeOut, timeUnit);
    }

    /**
     * Gets the global webdriver timeout.
     *
     */
    public int getTimeOut() {
        return this.globalTimeOut;
    }
}
