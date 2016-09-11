package nl.detesters.taf.test;

import nl.detesters.taf.web.WebButton;
import nl.detesters.taf.web.WebPage;
import nl.detesters.taf.web.WebTextBox;
import org.openqa.selenium.By;

public class ExampleLoginPage extends WebPage {

    private static final String LOGIN_CONTAINER = "//div[@id='login-container']";
    private static final String USERNAME_TEXTBOX = LOGIN_CONTAINER + "//input[@id='username']";
    private static final String PASSWORD_TEXTBOX = LOGIN_CONTAINER + "//input[@id='password']";
    private static final String LOGIN_BUTTON = LOGIN_CONTAINER + "//input[@id='login']";

    public WebTextBox getUsernameTextBox() {
        return searchElement(By.xpath(USERNAME_TEXTBOX));
    }

    public WebTextBox getPasswordTextBox() {
        return searchElement(By.xpath(PASSWORD_TEXTBOX));
    }

    public WebButton getLoginButton() {
        return searchElement(By.xpath(LOGIN_BUTTON));
    }
}
