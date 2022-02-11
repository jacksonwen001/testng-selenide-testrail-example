package com.chancetop;

import com.chancetop.testrail.TestRail;
import com.chancetop.testrail.TestRailListener;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Listeners({
        TestRailListener.class
})
@TestRail(caseId = 5)
public class GoogleSearchTest {

    @Test
    public void searchAndClickTest(){
        open("https://www.google.com");
        $(By.xpath("//input[@name=\"q\"]")).shouldBe(Condition.visible).sendKeys("TestRail");
        $(By.xpath("//input[@name=\"q\"]")).shouldBe(Condition.visible).sendKeys(Keys.ENTER);
    }

}
