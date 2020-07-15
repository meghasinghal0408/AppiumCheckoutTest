package com.amazon.appiumCheckoutTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppTest {

	public static URL url;
	public static DesiredCapabilities capabilities;
	public static AndroidDriver<AndroidElement> driver;

	@BeforeSuite
	public void setupAppium() throws MalformedURLException {
		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
		final String APK_NAME = "Amazon_shopping.apk";
		final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
		final int INSTALL_TIMEOUT_VALUE = 60000;
		final String ANDROID_INSTALL_TIMEOUT = "androidInstallTimeout";
		final String IGNORE_HIDDEN_API_POLICY_ERROR = "ignoreHiddenApiPolicyError";
		final String APP_ACTIVITY = "appActivity";
		final String APP_PACKAGE = "appPackage";
		final String APP_ACTIVITY_NAME = "com.amazon.mShop.home.HomeActivity";
		final String APP_PACKAGE_NAME = "com.amazon.mShop.android.shopping";
		url = new URL(URL_STRING);
		File fs = new File(APK_NAME);
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		capabilities.setCapability("enforceAppInstall", false);
		capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(APP_PACKAGE, APP_PACKAGE_NAME);
		capabilities.setCapability(APP_ACTIVITY, APP_ACTIVITY_NAME);
		capabilities.setCapability(IGNORE_HIDDEN_API_POLICY_ERROR, true);
		capabilities.setCapability(ANDROID_INSTALL_TIMEOUT, INSTALL_TIMEOUT_VALUE);
		driver = new AndroidDriver<AndroidElement>(url, capabilities);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@AfterSuite
	public void uninstallApp() throws InterruptedException {
		//driver.removeApp("com.amazon.mShop.android.shopping");
	}

	@Test(enabled = true)
	public void myFirstTest() throws InterruptedException {
		driver.resetApp();
	}
	
	@Test(enabled = true)
	public void testCheckoutFlow( ) {
		final String COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT = "com.amazon.mShop.android.shopping:id/rs_search_src_text";
		final String COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_SKIP_SIGN_IN_BUTTON = "com.amazon.mShop.android.shopping:id/skip_sign_in_button";
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		if(waitForPresence(driver, 1,COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_SKIP_SIGN_IN_BUTTON )) {
			driver.findElementById(COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_SKIP_SIGN_IN_BUTTON).click();
		}
		driver.findElementById(COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT).click();
		if(waitForPresence(driver, 2,COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT )) {
			driver.findElementById(COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT)
			.sendKeys("65-inch TV" + "\\n");
		}
		
		List<AndroidElement> elementList = driver.findElements(By.className("android.widget.LinearLayout"));
		int size = elementList.size();
		Random rand = new Random(1);
		int index = rand.nextInt(size - 1); // -1 because index will start from 0
		elementList.get(index).click();
		scrollAndClick("Add to Cart", driver);
		
		
		
	}
	
	
	/**
	 * Helper method for scroll functionality
	 * 
	 * @param visibleText
	 * @param driver
	 */
	private void scrollAndClick(String visibleText, AndroidDriver driver) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))")
				.click();
	}	

	private boolean waitForPresence(AndroidDriver driver, int timeLimitInSeconds, String targetResourceId){
	boolean isElementPresent;
	try{
		MobileElement mobileElement = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\""+targetResourceId+"\")");
		WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
		wait.until(ExpectedConditions.visibilityOf(mobileElement));
		isElementPresent = mobileElement.isDisplayed();
		return isElementPresent;	
	}catch(Exception e){
		isElementPresent = false;
		System.out.println(e.getMessage());
		return isElementPresent;
	} }
		
		
		
	}

