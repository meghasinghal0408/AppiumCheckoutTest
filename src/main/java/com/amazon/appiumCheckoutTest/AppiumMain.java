package com.amazon.appiumCheckoutTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumMain {

	private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	private static final int INSTALL_TIMEOUT_VALUE = 60000;
	private static final String ANDROID_INSTALL_TIMEOUT = "androidInstallTimeout";
	private static final String IGNORE_HIDDEN_API_POLICY_ERROR = "ignoreHiddenApiPolicyError";
	private static final String APP_ACTIVITY = "appActivity";
	private static final String APP_PACKAGE = "appPackage";
	private static final String APP_ACTIVITY_NAME = "com.amazon.mShop.home.HomeActivity";
	private static final String APP_PACKAGE_NAME = "com.amazon.mShop.android.shopping";
	private static final String DEVICE_NAME = "13c45e0e";
	private static final String APK_NAME = "Amazon_shopping.apk";
	
	
	
	/**
	 * We will set up the application using main method
	 * 
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {

		File fs = new File(APK_NAME);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		cap.setCapability("enforceAppInstall", false);
		cap.setCapability(APP_PACKAGE, APP_PACKAGE_NAME);
		cap.setCapability(APP_ACTIVITY, APP_ACTIVITY_NAME);
		cap.setCapability(IGNORE_HIDDEN_API_POLICY_ERROR, true);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(ANDROID_INSTALL_TIMEOUT, INSTALL_TIMEOUT_VALUE);
		System.out.println(fs.getAbsolutePath());
		AndroidDriver<AndroidElement> androidDriver = new AndroidDriver<AndroidElement>(new URL(APPIUM_URL),
				cap);
		TestCheckout testCheckout = new TestCheckout(androidDriver);
		testCheckout.testCheckoutFlowWithoutLogin();
	
	}

}
