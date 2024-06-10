import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideLesson1Test {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com/selenide/selenide";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1366x768";
    }

    /**
     * - Откройте страницу Selenide в Github
     * - Перейдите в раздел Wiki проекта
     * - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
     * - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
     */
    @Test
    void checkInWikiForTextAvailability() {
        open("");
        System.out.println("Real size: " + WebDriverRunner.getWebDriver().manage().window().getSize());
        // move to wiki page
        $("#wiki-tab").click();
        // find the SoftAssertions page in the list of Pages and open it
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box a[href*='Soft']")
                .shouldBe(visible).click();

        //check that there is an example code for JUnit5 inside the page
        // check for header
        $(withText("Using JUnit5 extend test class"))
                .shouldBe(visible);
        // check for example text
        $x("//*[@id='wiki-body']//*[.//*[contains(@id, 'using-junit5')]]/following-sibling::div[1]")
                .shouldBe(visible);
        $x("//*[@id='wiki-body']//*[.//*[contains(@id, 'using-junit5')]]/following-sibling::div[2]")
                .shouldBe(visible);
    }
}
