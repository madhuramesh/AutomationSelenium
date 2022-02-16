package com.framework.uitests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OverviewTabTests extends BaseTestClass{

    String user = "andrejs-ps";

    @BeforeEach
    void overviewTabSetup() {
        driver.get(BASE_URL + user);
    }

    @AfterEach
    void localCleanup(){
       // driver.close();
    }

    @Test
    void userNameIsCorrectOnOverviewTab() {
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();
        Assertions.assertEquals(user, actualUserName);

    }

    @Test
    void repoLinkGoesToCorrectRepo() {
        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selemium";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(BASE_URL + "andrejs-ps/" + repo, actualUrl);
    }

}
