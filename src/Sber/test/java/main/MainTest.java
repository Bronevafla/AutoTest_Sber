package main;
//1. Перейти на страницу http://www.sberbank.ru/ru/person
//        2. Нажать на меню – Карты
//        3. Выбрать подменю – «Дебетовые карты»
//        4. Проверить наличие на странице заголовка – «Дебетовые карты»
//        5. Под заголовком из представленных карт найти “Молодёжная карта” и кликнуть на кнопку данной карты “Заказать онлайн”
//        6. Проверить наличие на странице заголовка – «Молодёжная карта»
//        7. Кликнуть на кнопку «Оформить онлайн» под заголовком
//        8. В представленной форме заполнить поля:
//        • Фамилию, Имя, Отчетво, Имя и фамилия на карте, Дату рождения, E-mail, Мобильный телефон
//        • Основной документ - не заполняем
//        9. Проверить, что все поля заполнены правильно
//        10. Нажать «Далее»
//        11. Проверить, что появилось сообщение именно у незаполненных полях – «Обязательное поле»


import base.BaseTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class MainTest extends BaseTests {

    @Parameterized.Parameters
    public static Collection data() {

        return Arrays.asList(new String[][]{
                {"Иванов", "Николай", "Петрович"},
                {"Лебедев", "Михаил", "Сергеевич"},
                {"Королёв", "Дмитрий", "Павлович"},


        });
    }

    @Parameterized.Parameter(0)
    public String firstName;
    @Parameterized.Parameter(1)
    public String lastName;
    @Parameterized.Parameter(2)
    public String midName;


    @Test
    public void test() throws InterruptedException {
        //клик подменю "карты"
        String cartButton = "//ul//li//a[@aria-label='Меню  Карты']";
        WebElement cartButtonElement = driver.findElement(By.xpath(cartButton));
        scrollToElementJs(cartButtonElement);
        waitUtilElementToBeVisible(cartButtonElement);
        waitUtilElementToBeClickable(cartButtonElement);
        Thread.sleep(1000);
        cartButtonElement.click();

        //выбираю "дебетовые карты"
        String debetCatrButton = "//div/ul/li[@class = 'kitt-top-menu__item']/a[text() = 'Дебетовые карты']";
        WebElement debetCartElement = driver.findElement(By.xpath(debetCatrButton));
        Thread.sleep(1000);
        debetCartElement.click();

        //проверяю наличие заголовка "дебетовые карты" на открывшейся странице (также можно проверить через title html-документа)
        String checkLabel = "//div/h1[text() = 'Дебетовые карты']";
        WebElement checkLabelElement = driver.findElement(By.xpath(checkLabel));
        Assert.assertEquals("Не найден заголовок страницы", "Дебетовые карты", checkLabelElement.getText());

        //ищу "молодёжную карту" и нажимаю "заказать онлайн"
        String youthCartButton = "//div//a[@data-product = 'Молодёжная карта']//span[text() = 'Заказать онлайн']";
        WebElement youthCartButtonElement = driver.findElement(By.xpath(youthCartButton));
        Thread.sleep(1000);
        youthCartButtonElement.click();

        //проверяю нахождение на странице с молодежной картой
        Assert.assertEquals("Неверный тайтл страницы", "Молодёжная карта СберБанка - СберБанк", driver.getTitle());

        //выпадает ElementClickInterceptedException.........
//        //клик по кнопке "оформить онлайн"
//        String arrOnlineButton = "//div[@class = 'page-teaser-dict__button']//a/span[text() = 'Оформить онлайн']";
//        WebElement arrOnlineButtonElement = driver.findElement(By.xpath(arrOnlineButton));
//        Thread.sleep(1000);
//        arrOnlineButtonElement.click();

        //заполняю поле фамилия
        String lastNameInput = "//input[@data-name = 'lastName']";
        WebElement lastnameInputElement = driver.findElement(By.xpath(lastNameInput));
        Thread.sleep(1000);
        lastnameInputElement.clear();
        lastnameInputElement.sendKeys(lastName);

        //заполняю поле имя
        String firstNameInput = "//input[@data-name = 'firstName']";
        WebElement firstnameInputElement = driver.findElement(By.xpath(firstNameInput));
        Thread.sleep(1000);
        firstnameInputElement.clear();
        firstnameInputElement.sendKeys(firstName); //input[@data-name = 'middleName']

        //заполняю поле отчества
        String midNameInput = "//input[@data-name = 'middleName']";
        WebElement midNameInputElement = driver.findElement(By.xpath(midNameInput));
        Thread.sleep(1000);
        midNameInputElement.clear();
        midNameInputElement.sendKeys(midName);

        //заполняю дату рождения
        String birthdayInput = "//input[@data-name = 'birthDate']";
        WebElement birthdayInputElement = driver.findElement(By.xpath(birthdayInput));
        Thread.sleep(1000);
        birthdayInputElement.clear();
        birthdayInputElement.sendKeys("11");
        birthdayInputElement.sendKeys("11");
        birthdayInputElement.sendKeys("1998");

        //заполняю email
        String emailInput = "//input[@data-name = 'email']";
        WebElement emailInputElement = driver.findElement(By.xpath(emailInput));
        Thread.sleep(1000);
        emailInputElement.click();
        emailInputElement.clear();
        emailInputElement.click();
        emailInputElement.sendKeys("email@mail.ru");

        //заполняю телефон
        String phoneInput = "//input[@data-name = 'phone']";
        WebElement phoneInputElement = driver.findElement(By.xpath(phoneInput));
        scrollToElementJs(phoneInputElement);
        Thread.sleep(1000);
        phoneInputElement.click();
        phoneInputElement.clear();
        phoneInputElement.click();
        phoneInputElement.sendKeys("9265051147");

        //проверка, что поля заполнены верно
        //если число полей с предупреждением о незаполненности
        //больше нуля -> assert.fail()
        String invalidStr = "//div[@class = 'odcui-error__text']";
        List<WebElement> invalidElementsList = driver.findElements(By.xpath(invalidStr));
        List<String> errorListStr = new ArrayList<>();
       for (WebElement e: invalidElementsList){
           if(!e.getText().isEmpty()){
               errorListStr.add(e.getText());
           }
       }
        if(errorListStr.size() > 0){
            Assert.fail();
        }

        //нажимаю кнопку "далее"
        String nextButton = "//span[text() = 'Далее']";
        WebElement nextButtonElement = driver.findElement(By.xpath(nextButton));
        Thread.sleep(1000);
        nextButtonElement.click();

        //проверка на выпадение сообщения о незаполненных полях
        String areaErrorStr = "//div[text() = 'Обязательное поле']";
        List<WebElement> areaErrorList = driver.findElements(By.xpath(areaErrorStr));
        Assert.assertTrue(areaErrorList.size() == 3);

    }


    private void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
