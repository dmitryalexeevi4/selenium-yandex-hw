package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestClass {
    private static final Logger LOG = LoggerFactory.getLogger(TestClass.class);
    private WebDriver webDriver;

    @BeforeClass
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        LOG.info("Инициализируем ChromeDriver");
    }

    @Test
    public void helloWorldCheck() {
        webDriver.get("https://yandex.ru/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement input = webDriver.findElement(By.name("text"));
        input.sendKeys("руддщ цщкдв");
        WebElement button = webDriver.findElement(By.className("search2__button"));
        button.click();
        WebElement expectedInput = webDriver.findElement(By.name("text"));
        Assert.assertEquals(expectedInput.getAttribute("value"), "hello world");
        Assert.assertEquals(webDriver.getTitle().contains("hello world"), true);
        LOG.info("Выполнение теста");
    }

    @AfterClass
    public void closeDriver() {
        webDriver.quit();
        LOG.info("Закрываем ChromeDriver");
    }
}
