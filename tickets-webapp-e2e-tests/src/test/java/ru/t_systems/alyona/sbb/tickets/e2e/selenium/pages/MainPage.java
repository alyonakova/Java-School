package ru.t_systems.alyona.sbb.tickets.e2e.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.util.TicketsWebApplication;

import static ru.t_systems.alyona.sbb.tickets.e2e.selenium.util.ElementSelectors.byTestId;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static MainPage open(WebDriver driver) {
        driver.get(TicketsWebApplication.getBaseUrl());
        return new MainPage(driver);
    }

    static MainPage assumeOpen(WebDriver driver) {
        return new MainPage(driver);
    }

    public String getTextOnSignInButton() {
        return findSignInButton().getText();
    }

    private WebElement findSignInButton() {
        return driver.findElement(byTestId("sign-in-button"));
    }

    public String getErrorMessage() {
        return driver.findElement(byTestId("alert-message")).getText();
    }
}
