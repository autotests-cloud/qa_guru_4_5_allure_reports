package io.qaguru.eroshenkoam;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BaseSteps {

    private static final String BASE_URL = "http://github.com";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("Ищем репоизторий ${repository}")
    public void searchForRepository(final String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("Переходим в репозиторий ${repository}")
    public void goToRepositoryFromSearch(final String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Переходим в раздел Issues")
    public void openRepositoryIssues() {
        $(withText("Issues")).click();
    }

    @Step("Создаем Issue с ${title}")
    public void createIssueWithTitle(final String title) {
        //todo: Implement me
    }

    @Step("Проверяем что Issue с тайтлом ${number} существует")
    public void shouldSeeIssueWithTitle(final String title) {
        $(withText(title)).should(Condition.exist);
    }

    @Step("Проверяем что Issue с номером ${number} существует")
    public void shouldSeeIssueWithNumber(final String number) {
        $(withText(number)).should(Condition.exist);
    }


}
