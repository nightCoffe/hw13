package blin.tests;

import com.codeborne.selenide.Configuration;
import blin.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    String nameObject1 = "ВТБ Капитал";
    String nameObject2 = "ВТБ Страхование";
    String nameObject3 = "ВТБ Факторинг";
    String checkedElement1 = "Онлайн-сервисы";
    String checkedElement2 = "Отделения и Банкоматы";
    String checkedText1 = "ВТБ Капитал предлагает полный спектр инвестиционно-банковских продуктов и услуг как российским";
    String checkedText2 = "Почему именно ВТБ Страхование";
    String checkedText3 = "ВТБ Факторинг";

    String URL = "https://www.vtb.ru/";

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}

