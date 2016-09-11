package nl.detesters.taf.web;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebPage extends WebElement {
    private static final Logger LOG = LoggerFactory.getLogger(WebPage.class);

    private static final String HEAD = "/head";
    private static final String BODY = "/body";

    public WebPage(){
        getWebDriver().findElement(By.xpath("/"));
    }

    public WebElement getHead() {
        return searchElement(By.xpath(HEAD));
    }

    public WebElement getBody() {
        return searchElement(By.xpath(BODY));
    }
}
