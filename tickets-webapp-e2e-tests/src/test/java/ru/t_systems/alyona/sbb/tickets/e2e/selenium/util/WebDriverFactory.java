package ru.t_systems.alyona.sbb.tickets.e2e.selenium.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    static {
        WebDriverManager.chromedriver().setup();
    }

    public static ChromeDriver createChromeDriver() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(TicketsWebApplication.getBaseUrl());
        return driver;
    }
}
