package nl.detesters.taf.web;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

/**
 * Supported Operating Systems for the test execution.
 * Also see {@link Browser} for supported browsers.
 */
public enum OperatingSystem {
    OSX,
    WINDOWS,
    LINUX;

    private static final Logger LOG = LoggerFactory.getLogger(OperatingSystem.class);

    private EnumSet<Browser> WINDOWS_BROWSERS = EnumSet.of(
            Browser.INTERNET_EXPLORER,
            Browser.INTERNET_EXPLORER_8,
            Browser.INTERNET_EXPLORER_11,
            Browser.CHROME,
            Browser.FIREFOX
    );

    private EnumSet<Browser> LINUX_BROWSERS = EnumSet.of(
            Browser.CHROME,
            Browser.FIREFOX
    );

    private EnumSet<Browser> OSX_BROWSERS = EnumSet.of(
            Browser.SAFARI,
            Browser.CHROME,
            Browser.FIREFOX
    );

    /**
     * Method to return the Selenium capabilities for a given Browser.
     *
     * @return Selenium capabilties for given Browser
     * @throws UnsupportedOperationException if the used operating system and browser combination is not supported
     */
    public Capabilities getCapabilities(Browser browser) {
        DesiredCapabilities capabilities;
        EnumSet<Browser> supportedBrowsers;

        switch (this) {
            case WINDOWS: {
                supportedBrowsers = WINDOWS_BROWSERS;
                break;
            }
            case LINUX: {
                supportedBrowsers = LINUX_BROWSERS;
                break;
            }
            case OSX: {
                supportedBrowsers = OSX_BROWSERS;
                break;
            }
            default: {
                LOG.error(String.format("Operating system '%s' is not supported", this));
                throw new UnsupportedOperationException(String.format("Operating system '%s' is not supported", this));
            }
        }

        if (!supportedBrowsers.contains(browser)) {
            LOG.error(String.format("Browser '%s' is not supported on operating system '%s'",browser, this));
            throw new UnsupportedOperationException(String.format("Browser '%s' is not supported on operating system '%s'",browser, this));
        }

        capabilities = browser.getCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        return capabilities;
    }
}
