package com.jembree.practice;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeleniumTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    private ArrayList<String> expectedTitles;
    private ArrayList<String> titles;
    JavascriptExecutor js;


    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver",
                "/home/justin/IdeaProjects/SeleniumPractice/Firefox/geckodriver");
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        titles = new ArrayList<String>();
        expectedTitles = new ArrayList<String>();
        expectedTitles.add("SeleniumHQ Browser Automation");
        expectedTitles.add("SeleniumHQ Browser Automation");
        expectedTitles.add("Selenium Support");
        expectedTitles.add("Selenium Projects");
        expectedTitles.add("Selenium IDE · Open source record and playback test automation for the web");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void main() {
        driver.get("https://www.selenium.dev/");
        driver.manage().window().setSize(new Dimension(1280, 720));
        titles.add(driver.getTitle());
        try {
            driver.findElement(By.id("dropdownButton")).click();
        }
        catch (Exception ElementNotInteractableException) {
            System.out.println("Window is too small for drop down menu");
        }
        WebElement search = driver.findElement(By.id("gsc-i-id1"));
        search.sendKeys("IDE");
        search.sendKeys(Keys.ENTER);
        titles.add(driver.getTitle());
        driver.findElement(By.linkText("Support")).click();
        titles.add(driver.getTitle());
        driver.findElement(By.linkText("Projects")).click();
        titles.add(driver.getTitle());
        driver.findElement(By.cssSelector("div.ide:nth-child(1) > span:nth-child(1)")).click();
        titles.add(driver.getTitle());
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals("URL should be https://www.selenium.dev/selenium-ide/",
                "https://www.selenium.dev/selenium-ide/", actualURL);
        Assert.assertArrayEquals(expectedTitles.toArray(), titles.toArray());
        System.out.println(titles);
    }


    public static void main(String[] args) {
        SeleniumTest test = new SeleniumTest();
        test.main();
    }
}
