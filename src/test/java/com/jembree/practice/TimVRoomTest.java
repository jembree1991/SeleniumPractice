package com.jembree.practice;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TimVRoomTest {


    private static WebDriver driver;


    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver",
                "/home/justin/IdeaProjects/SeleniumPractice/Firefox/geckodriver");
        driver = new FirefoxDriver();
        String baseURL = "http://timvroom.com/selenium/playground/";
        driver.navigate().to(baseURL);
        driver.manage().window().setSize(new Dimension(1280, 720));
    }



    private String findTestPass(int testNumber) {
        String testOk = String.format("ok_%s", testNumber);
        return driver.findElement(By.id(testOk)).getText();
    }


    private void checkResults() {
        driver.findElement(By.id("checkresults")).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }


    // Test 1
    @Test
    public void fillTitleTest() {
        String currentTitle = driver.getTitle();
        WebElement titleInput = driver.findElement(By.id("answer1"));
        titleInput.sendKeys(currentTitle);
        checkResults();
        String testPass = findTestPass(1);
        Assert.assertEquals("OK",testPass);
    }

    // Test 2
    @Test
    public void fillNameTest() {
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys("Kilgore Trout");
        checkResults();
        String testPass = findTestPass(2);
        Assert.assertEquals("OK", testPass);
    }

    // Test 3
    @Test
    public void setOccupationTest() {
        WebElement dropDown = driver.findElement(By.id("occupation"));
        Select selectDropDown = new Select(dropDown);
        selectDropDown.selectByValue("scifiauthor");
        checkResults();
        String testPass = findTestPass(3);
        Assert.assertEquals("OK", testPass);
    }

    // Test 4
    @Test
    public void countBlueBoxesTest() {
        int blueBoxCount = driver.findElements(By.className("bluebox")).size();
        WebElement blueCountInput = driver.findElement(By.id("answer4"));
        blueCountInput.sendKeys(Integer.toString(blueBoxCount));
        checkResults();
        String testPass = findTestPass(4);
        Assert.assertEquals("OK", testPass);
    }

    // Test 5
    @Test
    public void clickMeTest() {
        driver.findElement(By.linkText("click me")).click();
        checkResults();
        String testPass = findTestPass(5);
        Assert.assertEquals("OK", testPass);
    }


    // Test 6
    @Test
    public void findBoxClassTest() {
        String boxClass = driver.findElement(By.id("redbox")).getAttribute("class");
        WebElement boxClassInput = driver.findElement(By.id("answer6"));
        boxClassInput.sendKeys(boxClass);
        checkResults();
        String testPass = findTestPass(6);
        Assert.assertEquals("OK", testPass);
    }


    // Test 7
    @Test
    public void runJavaScriptTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("ran_this_js_function()");
        checkResults();
        String testPass = findTestPass(7);
        Assert.assertEquals("OK", testPass);
    }


    // Test 8
    @Test
    public void javaScriptReturnTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object result = js.executeScript("return got_return_from_js_function()");
        WebElement resultInput = driver.findElement(By.id("answer8"));
        resultInput.sendKeys(result.toString());
        checkResults();
        String testPass = findTestPass(8);
        Assert.assertEquals("OK", testPass);
    }


    // Test 9
    @Test
    public void markWrittenBookTest() {
        driver.findElement(By.name("wrotebook")).click();
        checkResults();
        String testPass = findTestPass(9);
        Assert.assertEquals("OK", testPass);
    }


    // Test 10
    @Test
    public void redBoxTextTest() {
        String text = driver.findElement(By.id("redbox")).getText();
        WebElement textInput = driver.findElement(By.id("answer10"));
        textInput.sendKeys(text);
        checkResults();
        String testPass = findTestPass(10);
        Assert.assertEquals("OK", testPass);
    }


    // Test 11
    @Test
    public void boxOnTopTest() {
        WebElement greenBox = driver.findElement(By.id("greenbox"));
        WebElement orangeBox = driver.findElement(By.id("orangebox"));
        int greenBoxLocation = greenBox.getLocation().getY();
        int orangeBoxLocation = orangeBox.getLocation().getY();
        WebElement boxInput = driver.findElement(By.id("answer11"));
        if (greenBoxLocation < orangeBoxLocation) {
            boxInput.sendKeys("green");
        }
        else {
            boxInput.sendKeys("orange");
        }
        checkResults();
        String testPass = findTestPass(11);
        Assert.assertEquals("OK", testPass);
    }


    // Test 12
    @Test
    public void setSizeTest() {
        driver.manage().window().setSize(new Dimension(850, 650));
    }


    // Test 13
    @Test
    public void checkIsHereTest() {
        boolean isHere = !driver.findElements(By.id("ishere")).isEmpty();
        WebElement isHereInput = driver.findElement(By.id("answer13"));
        if (!isHere) {
            isHereInput.sendKeys("no");
        }
        else {
            isHereInput.sendKeys("yes");
        }
        checkResults();
        String testPass = findTestPass(13);
        Assert.assertEquals("OK", testPass);

    }


    // Test 14
    @Test
    public void checkIsVisibleTest() {
        boolean isVisible = driver.findElement(By.id("purplebox")).isDisplayed();
        WebElement isVisibleInput = driver.findElement(By.id("answer14"));
        if (isVisible) {
            isVisibleInput.sendKeys("yes");
        }
        else {
            isVisibleInput.sendKeys("no");
        }
        checkResults();
        String testPass = findTestPass(14);
        Assert.assertEquals("OK", testPass);
    }


    // Test 15
    @Test
    public void clickThenWaitTest() {
        driver.findElement(By.linkText("click then wait")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("click after wait"))));
        driver.findElement(By.linkText("click after wait")).click();
        driver.switchTo().alert().accept();
        checkResults();
        String testPass = findTestPass(15);
        Assert.assertEquals("OK", testPass);
    }


    // Test 16
    @Test
    public void popUpConfirmTest() {
        checkResults();
        String testPass = findTestPass(16);
        Assert.assertEquals("OK", testPass);
    }


    // Test 17
    @Test
    public void clickSubmitTest() {
        driver.findElement(By.id("submitbutton")).click();
        checkResults();
        String testPass = findTestPass(17);
        Assert.assertEquals("OK", testPass);
    }
}
