package ru.t_systems.alyona.sbb.tickets.e2e.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.fixtures.User;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.util.TicketsWebApplication;

public class AuthorizationPage {

    private WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    public static AuthorizationPage open(WebDriver driver) {
        driver.get(TicketsWebApplication.getBaseUrl() + "/sign_in");
        return new AuthorizationPage(driver);
    }

    public MainPage loginAs(User user) {
        AuthorizationPage page = AuthorizationPage.open(driver);
        page.typeLogin(user.getLogin());
        page.typePassword(user.getPassword());
        return page.submitUserData();
    }

    private void typePassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    private void typeLogin(String login) {
        WebElement loginField = driver.findElement(By.xpath("//input[@name='username']"));
        loginField.clear();
        loginField.sendKeys(login);
    }

    public MainPage submitUserData() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@name='sign-btn']"));
        submitButton.click();
        return MainPage.assumeOpen(driver);
    }
}
