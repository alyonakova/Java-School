package ru.t_systems.alyona.sbb.tickets.e2e.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.fixtures.User;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.pages.AuthorizationPage;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.pages.MainPage;
import ru.t_systems.alyona.sbb.tickets.e2e.selenium.util.WebDriverFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationTest {

    private WebDriver driver;
    private AuthorizationPage page;

    @BeforeEach
    public void openAuthorizationPage() {
        driver = WebDriverFactory.createChromeDriver();
        page = AuthorizationPage.open(driver);
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void signInSuccessful() {
        User user = User.existingUserWithCorrectPassword();
        MainPage mainPage = page.loginAs(user);
        assertThat(mainPage.getTextOnSignInButton()).isEqualTo("Sign out");
    }

    @Test
    public void unknownUserSignIn() {
        User user = User.nonExistingUser();
        MainPage mainPage = page.loginAs(user);
        String errorMessage = mainPage.getErrorMessage();
        assertThat(errorMessage).isEqualTo("Wrong login or password!");
    }

    @Test
    public void wrongPassword() {
        User user = User.existingUserWithWrongPassword();
        MainPage mainPage = page.loginAs(user);
        String errorMessage = mainPage.getErrorMessage();
        assertThat(errorMessage).isEqualTo("Wrong login or password!");
    }
}
