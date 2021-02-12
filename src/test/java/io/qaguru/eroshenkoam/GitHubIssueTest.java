package io.qaguru.eroshenkoam;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

public class GitHubIssueTest {

    private static final String BASE_URL = "http://github.com";

    private static final String ISSUES = "Issues";

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";

    @Test
    @Owner("eroshenkoam")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("critical")})
    @Link(name = "Base URL", value = BASE_URL)

    @Feature("Issues")
    @Story("Поск Issue")
    @DisplayName("Поиск Issue по номеру в репозитории")
    public void testIssueSearch() {
        open(BASE_URL);
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(By.linkText(REPOSITORY + "as")).click();
        $(withText(ISSUES)).click();
        $(withText(ISSUE_NUMBER)).should(Condition.exist);
    }


    @Test
    @Disabled
    @Owner("eroshenkoam")
    @Tags({@Tag("web"), @Tag("critical")})
    @Link(name = "Base URL", value = BASE_URL)

    @Feature("Issues")
    @Story("Создание Issue")
    @DisplayName("Создание Issue в новом репоизтории")
    public void testIssueCreate() {
        parameter("Repository", REPOSITORY);
        parameter("Issue Number", ISSUE_NUMBER);

        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });
        step("Ищем репоизторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY + "a")).click();
        });
        step("Переходим в раздел " + ISSUES, () -> {
            $(withText(ISSUES)).click();
        });
        step("Проверяем что Issue с номером " + ISSUE_NUMBER + " существует", () -> {
            $(withText(ISSUE_NUMBER)).should(Condition.exist);
        });
    }

}
