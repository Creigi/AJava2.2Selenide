package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationUnhappyTest {

    DateMethods date = new DateMethods();
    @ParameterizedTest
    @ValueSource(strings = {"Тольятти", "Пекин", "Berlin", "Samara", ""})
    void inputWrongCity(String city) {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[placeholder=Город]").setValue(city);
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.inputDate(7, "dd.MM.yyyy"));
        $("[data-test-id=name] input").setValue("Виталий");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        assertTrue($("[data-test-id=city].input_invalid").isEnabled());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Alexandre Dumas", "Д'Артаньян", "3 Мушкетера", ""})
    void inputWrongName(String name) {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[placeholder=Город]").setValue("Самара");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.inputDate(7, "dd.MM.yyyy"));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        assertTrue($("[data-test-id=name].input_invalid").isEnabled());
    }

    @ParameterizedTest
    @ValueSource(strings = {"+7901234567", "79012345678", "+7901234567890", ""})
    void inputWrongNumber(String number) {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[placeholder=Город]").setValue("Самара");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.inputDate(7, "dd.MM.yyyy"));
        $("[data-test-id=name] input").setValue("Кот Леопольд");
        $("[data-test-id=phone] input").setValue(number);
        $("[data-test-id=agreement]").click();
        $(".button").click();

        assertTrue($("[data-test-id=phone].input_invalid").isEnabled());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5})
    void inputWrongDate(int days) {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[placeholder=Город]").setValue("Самара");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.inputDate(days, "dd.MM.yyyy"));
        $("[data-test-id=name] input").setValue("Кот Леопольд");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        assertTrue($("[data-test-id=date] .input_invalid").isEnabled());
    }

    @Test
    void shouldNotClickAgreement() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[placeholder=Город]").setValue("Самара");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date.inputDate(7, "dd.MM.yyyy"));
        $("[data-test-id=name] input").setValue("Кот Леопольд");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $(".button").click();

        assertTrue($("[data-test-id=agreement].input_invalid").isEnabled());
    }
}
