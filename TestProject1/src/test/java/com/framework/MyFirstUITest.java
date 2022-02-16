package com.framework;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
import java.util.List;
public class MyFirstUITest {

    public static final String BASE_URL = "https://github.com/";
    static WebDriver driver;

    @BeforeAll
    static void setup(){
        //driver = new ChromeDriver(options);
    }

    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver","/Users/madhumathiramesh/development/source/chromedriver");
        ChromeOptions  options = new ChromeOptions().addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);
        return driver;
    }

    @AfterAll
    static void cleanup(){
        driver.close();
    }

    @Test
    void userNameIsCorrectOnOverviewTab(){
        String user = "andrejs-ps";
        driver.get(BASE_URL + user);

        String actualUserName = driver.findElement(By.className("p-nickname")).getText();
        Assertions.assertEquals(user, actualUserName);
        //driver.close();
    }

    @Test
    void repoLinkGoesToCorrectRepo(){
        String user = "andrejs-ps";
        driver.get(BASE_URL + user);

        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selemium";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = BASE_URL +  "andrejs-ps/" + repo;
        Assertions.assertEquals(BASE_URL + "andrejs-ps/" + repo, actualUrl);
    }

    @Test
    void repositoryCountIsCorrect(){
        driver.get(BASE_URL + "andrejs-ps/" + "?tab=repositories");
        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));
        Assertions.assertEquals(6, repos.size());
    }

}
