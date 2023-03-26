package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    DateMethods date = new DateMethods();

    @ParameterizedTest
    @CsvSource(
            value = {
                    "Самара, 3, Виктор Пузо, +79012345678",
                    "Магас, 5, Виктор Пузо-Крузо, +79012345678",
                    "Йошкар-Ола, 10, мал буквов, +79999999999"
            })
    void shouldBookCardCorrectInput(String city,int days, String name, String phoneNumber) {
        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999");
        $("[placeholder=Город]").setValue(city);
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.Date(days));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phoneNumber);
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15));
        $x("//div[@data-test-id='notification']").shouldHave(Condition.matchText(date.Date(days)));
    }

    @ParameterizedTest
    @ValueSource(ints = 7)
    void shouldBookCardCorrectInputAutocomplete(int days) {
        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999");
        $("[placeholder=Город]").sendKeys("С" + "а");
        $$(".menu-item").find(exactText("Самара")).click();
        $(".icon_name_calendar").click();
        if (!$$("table .calendar__day").find(exactText(date.Date1(days))).has(cssClass("calendar__day_type_off"))) {
            $$("table .calendar__day").find(exactText(date.Date1(days))).click();
        } else {
            $("[data-step='1'].calendar__arrow_direction_right").click();
            $$("table .calendar__day").find(exactText(date.Date1(days))).click();
        }
        $("[data-test-id=name] input").setValue("Макар");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15));
        $x("//div[@data-test-id='notification']").shouldHave(Condition.matchText(date.Date(days)));
    }
}