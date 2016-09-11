package nl.detesters.taf.web;

import org.joda.time.LocalDateTime;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WebTextBox extends WebElement {
    private static final Logger LOG = LoggerFactory.getLogger(WebTextBox.class);

    /**
     * Enter text.
     *
     * @param text     the text to enter.
     * @throws RuntimeException if text could not be entered.
     */
    public void sendKeys(String text) throws RuntimeException {
        boolean success = false;
        int timeout = getWebDriver().getTimeOut();

        setTimeOut(100, TimeUnit.MILLISECONDS);
        LocalDateTime start = LocalDateTime.now();
        LOG.debug("Entering text [" + text + "].");

        while (LocalDateTime.now().isBefore(start.plusSeconds(timeout))) {
            try {
                sendKeys(text);
                success = true;
                LOG.debug("Entered text [" + text + "].");
                break;
            } catch (Exception e) {
                LOG.trace(e.getMessage());
                LOG.debug("Exception has been thrown. Trying again to enter text.");
            }
        }
        setTimeOut();

        if (!success) {
            throw new RuntimeException("Could not enter text.");
        }
    }

    /**
     * Send OS key strokes like e.g. the enter button.
     *
     * @param key      the OS key stroke.
     */
    public void sendOsKeys(Keys key) {
        LOG.debug("Sending OS key [" + key + "].");
        sendKeys(key);
    }

    public void setText(String text){
        LOG.debug("Set text [" + text + "].");
        clear();
        sendKeys(text);
    }
}
