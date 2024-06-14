package org.example;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prozorro extends BaseTest {
    @Test
    public void comparison() {
        webDriver.get("https://prozorro.gov.ua/uk");

        WebElement searchInput = webDriver.findElement(By.className("search-text__input"));
        searchInput.sendKeys("ділянка");
        searchInput.sendKeys(Keys.ENTER);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement name = webDriver.findElement(By.xpath("//a[@class='item-title__title']"));
        String textName = name.getText();

        WebElement status = webDriver.findElement(By.xpath("//span[@class='getter _setter __v_isRef __v_isReadonly effect _cacheable']"));
        String textStatus = status.getText();

        WebElement cost = webDriver.findElement(By.xpath("//p[@class='text-color--green app-price__amount']"));
        String textCost = cost.getText();
        String costDigits = extractDigits(textCost);

        WebElement enterprise = webDriver.findElement(By.xpath("//div[@class='search-result-card__description']"));
        String textEnterprise = enterprise.getText();

        name.click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement detailName = webDriver.findElement(By.xpath("//div[@class='tender--head--title col-sm-9']"));
        String textDetailName = detailName.getText();

        WebElement detailStatus = webDriver.findElement(By.xpath("//span[@class='marked']"));
        String textDetailStatus = detailStatus.getText();

        WebElement detailCost = webDriver.findElement(By.xpath("//div[@class='green tender--description--cost--number']"));
        String textDetailCost = detailCost.getText();
        String costDetailDigits = extractDigits(textDetailCost);

        WebElement detailEnterprise = webDriver.findElement(By.xpath("//td[@class='col-sm-6']"));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", detailEnterprise);

        String textDetailEnterprise = detailEnterprise.getText();

        Assert.assertEquals(textDetailName, textName, "Name does not match");
        Assert.assertEquals(textDetailStatus, textStatus, "Status does not match");
        Assert.assertEquals(costDetailDigits, costDigits, "Cost does not match");
        Assert.assertEquals(textDetailEnterprise, textEnterprise, "The name of the enterprise does not match");
    }
}

