package xyz.screenplay.questions;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BrowserAlert implements Question<String> {

    private WebDriver driver;

    public BrowserAlert(WebDriver driver) {
        this.driver = driver;
    }

    public static Question<String> says(WebDriver driver) {
        return new BrowserAlert(driver);
    }

    @Override
    public String answeredBy(Actor actor) {
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        log.info("Alert says: {}", alertMessage);
        alert.accept();
        return alertMessage;
    }
}
