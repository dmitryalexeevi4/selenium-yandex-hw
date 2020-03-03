package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestClass {
    private static final Logger LOG = LoggerFactory.getLogger(TestClass.class);
    private WebDriver webDriver;


    @BeforeClass
    public void initDriver() {
        LOG.info("Инициализация ChromeDriver");
        String chromedriverExtension = System.getProperty("os.name").equals("Windows") ? ".exe" : "";
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver" + chromedriverExtension);
        webDriver = new ChromeDriver();
    }

    @Test
    public void helloWorldCheck() {
        LOG.info("Выполнение теста");
        webDriver.get("https://yandex.ru/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement input = webDriver.findElement(By.name("text"));
        input.sendKeys("руддщ цщкдв");
        WebElement button = webDriver.findElement(By.className("search2__button"));
        button.click();
        WebElement expectedInput = webDriver.findElement(By.name("text"));
        Assert.assertEquals(expectedInput.getAttribute("value"), "hello world");
        assertTrue(webDriver.getTitle().contains("hello world"));
    }

    @AfterClass
    public void closeDriver() {
        LOG.info("Закрытие ChromeDriver");
        webDriver.quit();
    }
}
