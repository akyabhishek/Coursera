package testCases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import pageClasses.ContactSalesPage;
import pageClasses.ForBusinessPage;
import pageClasses.ForCampusPage;
import pageClasses.HomePage;
import pageClasses.SearchPage;
import utilities.TestDataProvider;

public class AllTestCases extends BaseClass {

	HomePage homePage;
	SearchPage searchPage;
	ForBusinessPage forBusinessPage;
	ForCampusPage forCampusPage;
	ContactSalesPage contactSalesPage;

	@Test(enabled = true)
	public void verifyCourses() {
		invokeBrowser(prop.getProperty("browserName"));
		logger = report.createTest("Verify web development courses");
		homePage = openApplication(prop.getProperty("URL"));
		verifyTitle("Coursera | Degrees, Certificates, & Free Online Courses");
		searchPage = homePage.search("web development");
		searchPage.filterLevel("Beginner");
		searchPage.selectEnglishLanguage();
		searchPage.printFirstTwoCourse();
		reportPass("Test case - Verify web development courses, passed succesfully");
	}

	@Test(enabled = true ,dataProvider = "getContactFormData", dataProviderClass=utilities.DataProviders.class,priority = 1)
	public void campusContact(Hashtable<String, String> testData) {
		invokeBrowser(prop.getProperty("browserName"));
		logger = report.createTest(testData.get("testType")+" campus contact form for - "+testData.get("firstname"));
		homePage = openApplication(prop.getProperty("URL"));
		verifyTitle("Coursera | Degrees, Certificates, & Free Online Courses");
		forBusinessPage = homePage.clickForBusinessBtn();
		forCampusPage = forBusinessPage.clickForCampusBtn();
		contactSalesPage = forCampusPage.clickContactUs();
		contactSalesPage.submitForm(testData.get("firstname"), testData.get("lastname"), testData.get("title"), testData.get("discipline"), testData.get("email"), testData.get("phone"),
				testData.get("company"), testData.get("institution"), testData.get("country"), testData.get("state"), testData.get("needs"),testData.get("nextPageTitle"),testData.get("testType"));
		reportPass("Test case -"+testData.get("testType")+" campus contact form for - "+testData.get("firstname")+", passed succesfully");
	}

	@Test(enabled = true ,dataProvider = "getLanguageLearningData", dataProviderClass=utilities.DataProviders.class,priority = 2)
	public void verifyLanguageLearning(Hashtable<String, String> testData) {
		invokeBrowser(prop.getProperty("browserName"));
		logger = report.createTest("Language Learning courses for "+testData.get("level")+ " level");
		homePage = openApplication(prop.getProperty("URL"));
		verifyTitle("Coursera | Degrees, Certificates, & Free Online Courses");
		searchPage = homePage.search(testData.get("searchQuery"));
		searchPage.filterLevel(testData.get("level"));
		searchPage.printCourses(testData.get("level")+" Courses");
		System.out.println("\nTotal courses for "+testData.get("level")+" level - " + searchPage.getNumberOfResult() + "\n");
		reportPass("Test case - Language Learning courses for "+testData.get("level")+ " level, passed succesfully");
	}



}
