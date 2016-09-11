package nl.detesters.taf.test;

import nl.detesters.taf.web.AbstractWebApplication;
import nl.detesters.taf.web.Browser;
import nl.detesters.taf.web.OperatingSystem;

import java.net.MalformedURLException;
import java.net.URL;

public class ExampleWebApplication extends AbstractWebApplication {
    private static final String GRID_URL = "";
    private static final String APPLICATION_URL = "http://localhost/";
    private static final OperatingSystem os = OperatingSystem.WINDOWS;
    private static final Browser browser = Browser.CHROME;

    private ExampleLoginPage loginPage;

    public ExampleWebApplication() throws MalformedURLException {
        super(new URL(GRID_URL), os, browser, APPLICATION_URL);
    }

    public ExampleLoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = getPage();
        }
        return loginPage;
    }
}
