package nl.detesters.taf.web;

import com.google.common.base.Predicate;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebElement extends RemoteWebElement {
    private static final Logger LOG = LoggerFactory.getLogger(WebElement.class);
    private int globalTimeOut;

    public WebElement() {
    }

    /**
     * Waits and Gets the text based on an expected text. Wait has been set to {@link #globalTimeOut}.
     *
     * @param expectedText the expected text.
     * @return the actual text.
     */
    public String getText(String expectedText) {
        LOG.debug("Fetching text [" + expectedText + "]");
        LocalDateTime start = LocalDateTime.now();
        String actualText = "";
        setTimeOut(100, TimeUnit.MILLISECONDS);
        while (LocalDateTime.now().isBefore(start.plusSeconds(globalTimeOut)) && !expectedText.equals(actualText)) {
            actualText = getText();
            LOG.debug("Fetched text [" + expectedText + "].");
        }
        setTimeOut();
        return actualText;
    }

    /**
     * Override of findElement in superclass.
     *
     * @param selector the element to find
     * @return the WebElement
     */
    @Override
    public org.openqa.selenium.WebElement findElement(By selector) {
        waitForPageToLoad();
        org.openqa.selenium.WebElement element = null;

        setTimeOut(100, TimeUnit.MILLISECONDS);
        LocalDateTime start = LocalDateTime.now();
        LOG.debug("Finding element [" + selector.toString() + "].");
        while (LocalDateTime.now().isBefore(start.plusSeconds(globalTimeOut))) {
            try {
                element = super.findElement(selector);
                LOG.debug("Found element [" + selector.toString() + "].");
                break;
            } catch (Exception e) {
                LOG.trace(e.getMessage());
                LOG.debug("Element not found. Trying again to locate element.");

            }
        }
        setTimeOut(globalTimeOut, TimeUnit.SECONDS);

        return element;
    }

    /**
     * Override of findElements in superclass.
     *
     * @param selector the elements to find
     * @return List of WebElements
     */
    @Override
    public List<org.openqa.selenium.WebElement> findElements(By selector) {
        waitForPageToLoad();
        return super.findElements(selector);
    }

    @SuppressWarnings({"unchecked"})
    public <E extends WebElement> E searchElement(By selector) {
        org.openqa.selenium.WebElement webElement = findElement(selector);
        return (E) webElement;
    }

    @SuppressWarnings({"unchecked"})
    public <E extends WebElement> List<E> searchElements(By selector) {
        List<E> webElementList = new ArrayList<E>();
        for (org.openqa.selenium.WebElement webElement : findElements(selector)) {
            webElementList.add((E) webElement);
        }
        return webElementList;
    }

    /**
     * Method to wait for the Page to be completely loaded. The method waits till the injected javascript command
     * 'document.readyState' returns 'complete'. The wait timeout has been set to {@link #globalTimeOut}.
     */
    protected void waitForPageToLoad() {
        int timeOut = getWebDriver().getTimeOut();
        new WebDriverWait(getWebDriver(), timeOut).until(new Predicate<WebDriver>() {
            public boolean apply(WebDriver wd) {
                return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    /**
     * Sets the global webdriver timeout.
     *
     * @param timeout  the timeout period
     * @param timeUnit the time unit
     */
    protected void setTimeOut(long timeout, TimeUnit timeUnit) {
        getWebDriver().setTimeOut(timeout, timeUnit);
    }

    /**
     * Sets the global webdriver timeout with the default value.
     */
    protected void setTimeOut() {
        getWebDriver().setTimeOut();
    }

    protected RemoteWebDriver getWebDriver() {
        return (RemoteWebDriver) this.parent;
    }
}
