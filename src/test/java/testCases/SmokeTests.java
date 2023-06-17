package testCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import baseClasses.BaseClass;

public class SmokeTests extends BaseClass {

	@Test(enabled = true)
	public void openCourseraSite() {
		try {
			invokeBrowser(prop.getProperty("browserName"));
			logger = report.createTest("Homepage title test");
			openApplication(prop.getProperty("URL"));
			verifyTitle("Coursera | Degrees, Certificates, & Free Online Courses");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
