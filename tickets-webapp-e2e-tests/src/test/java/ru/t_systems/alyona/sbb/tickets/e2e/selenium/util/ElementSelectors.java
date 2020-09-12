package ru.t_systems.alyona.sbb.tickets.e2e.selenium.util;

import org.openqa.selenium.By;

public final class ElementSelectors {

    private static final String TEST_ID_ATTRIBUTE_NAME = "data-test-id";

    public static By byTestId(String testId) {
        return byAttribute(TEST_ID_ATTRIBUTE_NAME, testId);
    }

    public static By byAttribute(String attributeName, String attributeValue) {
        return By.xpath("//*[@" + attributeName + "='" + attributeValue + "']");
    }
}
