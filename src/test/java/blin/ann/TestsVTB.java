package blin.ann;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestsVTB {
    String nameObject1 = "ВТБ Капитал";
    String nameObject2 = "Открытие счёта";
    String nameObject3 = "Условия открытия и ведения счетов";
    String checkedElement1 = "Онлайн-сервисы";
    String checkedElement2 = "Отделения и Банкоматы";
    String checkedElement3 = "ВТБ Капитал предлагает полный спектр инвестиционно-банковских продуктов и услуг как российским";
    String URL = "https://www.vtb.ru/";

    @Test
    @DisplayName("Открываем страницы ВТБ и проверяем контент")
    public void testsForVTBOpenPagesAndCheckedContent() {

        step("Открываем главную страницу ВТБ", () -> {
            open(URL);
            step("Проверяем, что шапка содержит элемент" + checkedElement1, () -> {
                $(".header-bottom-menu").shouldHave(text(checkedElement1));
            });
            step("Переходим на вкладку `Крупный бизнес` и проверяем содержимое вкладки" + nameObject1, () -> {
                $$(".link__text.text.color-white.size-small-medium.weight-medium").find(text("Крупный бизнес")).click();
                $(".tab-panel__image").scrollTo();
                $$(".tab-panel__row li").find(text(nameObject1)).click();
                step("Проверяем вкладку на содержание текста " + checkedElement3, () -> {
                    $(".tab-panel__wrap").shouldHave(text(checkedElement3));
                });
            });
            step("Проверяем содержимое вкладки" + nameObject2, () -> {
                $(".features__item").scrollTo().click();
                $$(".tab-panel__row li").find(text(nameObject3)).click();
                step("Проверяем содержимое страницы" + nameObject2, () ->
                        $(".common-header").shouldHave(text("Открытие счетов")));
                step("Проверяем содержимое вкладки" + nameObject3, () ->
                        $(".padding-slim").shouldHave(text("Заявление о закрытии счета")));

            });
        });
    }

            @Test
            @DisplayName("Открываем главную страницу ВТБ, заполняем конвертер валют, проверяем содержимое при нажатии кнопки `Найти отделение` ")
            public void openPaymentPageAndCheckedCurrencyRate () {

                step("Открываем главную страницу и переходим в `Платежи и Переводы`", () -> {
                    open(URL);
                    $$(".header-bottom-menu-item").findBy(text("Платежи и переводы")).click();
                });
                step("Переходим на страницу `Обмен валюты`", () ->
                        $$(".header-menu-panel-group-item").findBy(text("Обмен валюты")).click());
                step("Выбираем способ обмена", () -> {
                    $("#select2-method-container").click();
                    $$("#select2-method-results li").findBy(text("В офисе (безналично)")).click();
                });
                step("Вводим сколько у меня есть", () -> {
                    $(".combobox").click();
                    $("#method1").setValue("100");
                });
                step("Смотрим сумму, которую мы получим в $", () ->
                        $(".combobox__item.combobox__item_narrow").click());
                step("Нажимаем на кнопку `Найти отделение`", () ->
                        $(".currency-exchange__item.currency-exchange__item_button").click());
                step("Проверяем, что страница содержит элемент" + checkedElement2, () ->
                        $(".ng-scope").shouldHave(text(checkedElement2)));
            }

            @Test
            @DisplayName("Скачиваем PDF документ и проверяем содержимое")
            public void downloadFiles () throws IOException {
                step("Скачиваем PDF документ", () -> {
                    open(URL);
                    $(".link.link--dark-gray").scrollTo();
                    $(By.linkText("Раскрытие информации профессиональным участником рынка ценных бумаг")).click();
                    File pdf = $(".docs-items__doc.docs-items__doc_pdf.docs-items__doc-br").scrollTo().download();
                    PDF parsedPdf = new PDF(pdf);
                    Assertions.assertEquals(2, parsedPdf.numberOfPages);
                });
            }


        }
