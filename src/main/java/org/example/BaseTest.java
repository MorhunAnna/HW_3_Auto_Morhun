package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseTest
{

    public WebDriver webDriver;

    @BeforeMethod
    public void initDriver()
    {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void quitDriver()
    {
        webDriver.quit();
    }

 protected String extractDigits(String input)
    {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        StringBuilder digits = new StringBuilder();

        while (matcher.find())
        {
            digits.append(matcher.group());
        }

        return digits.toString();
    }
}

