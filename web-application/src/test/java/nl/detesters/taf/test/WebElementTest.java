package nl.detesters.taf.test;

import org.junit.Test;

public class WebElementTest {

    @Test
    public void testLogin() throws Throwable {
        //Arrange
        ExampleWebApplication sut = new ExampleWebApplication();
        ExampleLoginPage loginPage = sut.getLoginPage();

        //Act
        loginPage.getUsernameTextBox().setText("user 1");
        loginPage.getPasswordTextBox().setText("pass1234");
        loginPage.getLoginButton().click();
    }
}
