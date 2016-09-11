package nl.detesters.taf.web;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Supported Browsers for the test execution. Also see
 * {@link OperatingSystem} for supported Operating Systems.
 */
public enum Browser {
    INTERNET_EXPLORER,
    INTERNET_EXPLORER_8,
    INTERNET_EXPLORER_11,
    CHROME,
    FIREFOX,
    SAFARI;

    private static final Logger LOG = LoggerFactory.getLogger(Browser.class);

    /**
     *
     * @return selenium capabilities for the browser
     */
    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities;
        switch (this) {
            case INTERNET_EXPLORER: {
                capabilities = getInternetExplorerCapabilities();
                break;
            }
            case INTERNET_EXPLORER_8: {
                capabilities = getInternetExplorerCapabilities();
                capabilities.setCapability("version", "8");
                break;
            }
            case INTERNET_EXPLORER_11: {
                capabilities = getInternetExplorerCapabilities();
                capabilities.setCapability("version", "11");
                break;
            }
            case CHROME: {
                capabilities = getChromeCapabilities();
                break;
            }
            case FIREFOX: {
                capabilities = getFirefoxCapabilities();
                break;
            }
            case SAFARI: {
                capabilities = getSafariCapabilities();
                break;
            }
            default: {
                LOG.error(String.format("Browser '%s' is not supported", this));
                throw new UnsupportedOperationException(String.format("Browser '%s' is not supported", this));
            }
        }
        return capabilities;
    }

    /**
     * Method to return the Selenium capabilities for Internet Explorer.
     *
     * @return Selenium capabilties for Internet Explorer
     */
    private DesiredCapabilities getInternetExplorerCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        return capabilities;
    }

    /**
     * Method to return the Selenium capabilities for Chrome.
     *
     * @return Selenium capabilities for Chrome
     */
    private DesiredCapabilities getChromeCapabilities() {
        return DesiredCapabilities.chrome();
    }

    /**
     * Method to return the Selenium capabilities for Safari.
     *
     * @return Selenium capabilities for Safari
     */
    private DesiredCapabilities getSafariCapabilities() {
        return DesiredCapabilities.safari();
    }

    /**
     * Method to return the Selenium capabilities for Firefox.
     *
     * @return Selenium capabilities for Firefox
     */
    private DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, getFirefoxProfile());
        return capabilities;
    }

    /**
     * Method to return a Selenium profile for Firefox. This method is implemented
     * in {@link #getFirefoxCapabilities()}. The profile has the following preferences:
     * <ul>
     * <li>security.ssl3.dhe_rsa_aes_128_sha, false</li>
     * <li>security.ssl3.dhe_rsa_aes_256_sha, false</li>
     * <li>xpinstall.signatures.required, false</li>
     * <li>browser.startup.page, 0</li>
     * <li>browser.startup.homepage_override.mstone, ignore</li>
     * </ul>
     *
     * @return Selenium profile for Firefox
     */
    private FirefoxProfile getFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("security.ssl3.dhe_rsa_aes_128_sha", false);
        profile.setPreference("security.ssl3.dhe_rsa_aes_256_sha", false);
        profile.setPreference("xpinstall.signatures.required", false);
        profile.setPreference("browser.startup.page", 0);
        profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
        return profile;
    }
}
