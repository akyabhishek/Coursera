package pageClasses;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import baseClasses.BaseClass;

public class HomePage extends BaseClass {

	public ExtentTest logger;

	public HomePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	@FindBy(className = "react-autosuggest__input")
	WebElement searchBox;

	@FindBy(xpath = "//a[@data-track-component='navigation_meta_nav_Business']")
	WebElement topForBusinessBtn;

	@FindAll (value = @FindBy(id = "enterprise-link"))
	List<WebElement> navForBusinessList;

	// Search query in Homepage
	public SearchPage search(String query) {
		enterText(searchBox, query);
		clickEnter(searchBox);
		SearchPage searchPage = new SearchPage(driver, logger);
		PageFactory.initElements(driver, searchPage);
		return searchPage;
	}

	// Click of "For Businesses"
	public ForBusinessPage clickForBusinessBtn() {
		if(navForBusinessList.size()!=0) {
			elementClick(navForBusinessList.get(0));
		} else{
			elementClick(topForBusinessBtn);
		}
		ForBusinessPage forBusinessPage = new ForBusinessPage(driver, logger);
		PageFactory.initElements(driver, forBusinessPage);
		return forBusinessPage;

	}

}
