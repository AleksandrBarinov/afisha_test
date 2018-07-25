package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.RunListener;

import java.util.ArrayList;
import java.util.List;

import static steps.BaseSteps.driver;

public class MainPage {

    @FindBy(xpath = "//*[text()='В кино']")
    WebElement goToCinema;

    @FindBy(xpath = "//div[@class='tab__content js-tab__content tab__content_active']//*[text()='Сегодня']/parent::div/parent::div/parent::div")
    WebElement whenList;

    @FindBy(xpath = "//*[@class='input-group__item']")
    WebElement when;

    @FindBy(xpath = "//*[@class='input-group input-group_fixed']//*[@class='input js-suggest__input-wrap']//*[@placeholder]")
    WebElement subwayList;

    @FindBy(xpath = "//div[@class='input-group__item']/parent::div//parent::div//parent::div//parent::form[@class='js-module']//*[@placeholder='Все жанры']")
    WebElement genreList;

    @FindBy(xpath = "//*[@class='checkbox checkbox_block checkbox_colored checkbox_small suggest__item js-select__item']")
    WebElement genre;

    @FindBy(xpath = "//*[@class='tbl tbl_width_full margin_top_20']//*[@class='checkbox__inner']")
    WebElement threeDCheckbox;

    @FindBy(name = "clb6796813")
    WebElement chooseButton;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public String getWhenTyped() {
        return whenTyped;
    }

    public void setWhenTyped(String whenTyped) {
        this.whenTyped = whenTyped;
    }

    public String whenTyped;

    public ArrayList<String> getSubwaysSelected() {
        return subwaysSelected;
    }

    ArrayList<String> subwaysSelected = new ArrayList<String>();

    public ArrayList<String> getGenresSelected() {
        return genresSelected;
    }

    ArrayList<String> genresSelected = new ArrayList<String>();

    public String getThreeDCheckboxSelected() {
        return threeDCheckboxSelected;
    }

    String threeDCheckboxSelected;

    RunListener runListener = new RunListener();

    public void selectGoToCinema() throws InterruptedException {
        Thread.sleep(500);
        goToCinema.click();

    }

    public void selectWhenList() throws InterruptedException {
        Thread.sleep(500);
        whenList.click();

    }

    public void selectWhen(String menuItem){
        setWhenTyped(menuItem);
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(when.findElement(By.xpath("//*[@data-title='"+menuItem+"']//parent::label//parent::div")))).click();

    }

    public void selectSubwayList(){
        subwayList.click();

    }

    public void selectSubway(String menuItem,int suggestionNumber) throws InterruptedException {
        subwayList.sendKeys(menuItem);
        Thread.sleep(1000);
        runListener.runListener();
        List<WebElement> subways = runListener.getEventDriver().findElements(By.xpath("//*[@data-title='"+menuItem+"']"));
        suggestionNumber = suggestionNumber - 1;
        WebElement currentSubway = subways.get(suggestionNumber);
        currentSubway.click();

        subwaysSelected.add(subways.get(suggestionNumber).getAttribute("data-id"));
        //subwaysSelected.add(menuItem);

    }

    public void selectGenreList() throws InterruptedException {
        genreList.click();
        Thread.sleep(1000);
    }

    public void selectGenre(String menuItem) throws InterruptedException {
        runListener.runListener();
        Wait<WebDriver> wait = new WebDriverWait(runListener.getEventDriver(), 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(genre.findElement(By.xpath(".//*[text()='"+menuItem+"']//parent::div/parent::div/child::label")))).click();
        Thread.sleep(800);

        genresSelected.add(menuItem);

    }

    public void selectThreeDCheckbox(String menuItem) throws InterruptedException {
        Thread.sleep(1000);
        threeDCheckbox.findElement(By.xpath("//*[contains(text(),'в "+menuItem+"')]//parent::div/parent::div/child::label")).click();

        threeDCheckboxSelected = menuItem;
    }

    public void selectChoose(){
        chooseButton.click();

    }


}
