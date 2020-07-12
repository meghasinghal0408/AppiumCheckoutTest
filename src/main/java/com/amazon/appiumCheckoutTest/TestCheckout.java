package com.amazon.appiumCheckoutTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestCheckout {
	
	private static final String COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT = "com.amazon.mShop.android.shopping:id/rs_search_src_text";
	AndroidDriver<AndroidElement> androidDriver;
	private static final String COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_SKIP_SIGN_IN_BUTTON = "com.amazon.mShop.android.shopping:id/skip_sign_in_button";

	

	public TestCheckout(AndroidDriver<AndroidElement> androidDriver) {

		this.androidDriver=androidDriver;
	
	
	}
	
	
	public void testCheckoutFlowWithoutLogin() {
		
		androidDriver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		// HomePage h=new HomePage(driver);

		androidDriver.findElementById(COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_SKIP_SIGN_IN_BUTTON).click();
		androidDriver.findElementById(COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT).click();
		androidDriver.findElementById(COM_AMAZON_M_SHOP_ANDROID_SHOPPING_ID_RS_SEARCH_SRC_TEXT)
				.sendKeys("65-inch TV" + "\\n");

		List<AndroidElement> elementList = androidDriver.findElements(By.className("android.widget.LinearLayout"));
		int size = elementList.size();
		Random rand = new Random(1);
		int index = rand.nextInt(size - 1); // -1 because index will start from 0
		elementList.get(index).click();
		scrollAndClick("Add to Cart", androidDriver);
	}

	public static void scrollAndClick(String visibleText, AndroidDriver driver) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))")
				.click();
	}	

}
