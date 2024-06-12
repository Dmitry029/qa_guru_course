import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideLesson2Tests {
    @BeforeAll
    static void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1366x768";
    }

    /**
     * На главной странице GitHub выберите: Меню -> Solutions -> Enterprize (с помощью команды hover для Solutions).
     * Убедитесь, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").
     */
    @Test
    void navigateToEnterprisePageTest() {
        open("https://github.com");
        //найти вкладку Solutions, методом hover открыть ее (выпадающий список)
        $x("//button[contains(text(), 'Solutions')]").hover();
        //выбрать из списка вкладку Enterprise и перейти на страницу Enterprise
        $x("//*[@aria-labelledby='solutions-for-heading']//*[contains(text(), 'Enterprise')]")
                .click();
        //проверка того, что загрузилась нужная страница
        $(byTagAndText("h1", "The AI-powered")).shouldBe(visible, ofSeconds(10));
        assertTrue(url().contains("/enterprise"));
    }

    /**
     * Запрограммируйте Drag&Drop с помощью Selenide.actions()
     * - Откройте https://the-internet.herokuapp.com/drag_and_drop
     * - Перенесите прямоугольник А на место В
     * - Проверьте, что прямоугольники действительно поменялись
     * - В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест,
     * если использовать её вместо actions()
     */
    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        SelenideElement source = $("#column-a");
        SelenideElement target = $("#column-b");
        //проверка headers элементов до action
        source.shouldHave(text("A"));
        target.shouldHave(text("B"));

        //перенесос прямоугольник А на место В
        actions().clickAndHold(source).moveToElement(target).release().build().perform();
        //проверка того, что headers поменялись
        source.shouldHave(text("B"));
        target.shouldHave(text("A"));

        //проверка работает ли тест, с командой dragAndDrop
        source.dragAndDrop(to("#column-b"));
        //проверка того, что headers элементов в первоначальном состоянии
        source.shouldHave(text("A"));
        target.shouldHave(text("B"));
    }
}