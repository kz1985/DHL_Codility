package com.DHL.CodilityAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FunctionsOfUI {

	static ChromeDriver driver;
	public static String path = "C:\\MY_WORKS\\Framework\\Drivers\\chromedriver.exe";
	static long Time_count = 12;
	public static WebDriverWait wait;
	static String totalSales = "$3,500";
	static String converTotalSale = String.valueOf(totalSales);

	@BeforeMethod
	public static void tearUp() {
	
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.navigate().refresh();
		driver.get("\r\n" + "\r\n"
				+ "https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html\r\n"
				+ "");
		// driver.navigate("https://robotsparebinindustries.com/#/").switchTo()

		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void validatePageTitle() {
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "ARR", "Title of ARR is Not Available");
	}

	@Test(priority = 2)
	public void ValidateHompage() {
		validatePageTitle();
		// Total number of element in the form is
		List<WebElement> totalFields = driver
				.findElements(By.xpath("//body/div[@id='container']/div[@id='login-form']/div[1]"));
		System.out.println("Total Number of Form is : " + totalFields.size());
		System.out.print("Total number of Elements are availabel include hidden message area is : ");
		for (WebElement e : totalFields) {
			System.out.print(e.getText().length() + "\n");
		}

		// Validate Login Form
		boolean lForm = driver.findElement(By.xpath("//div[@id='login-form']")).isDisplayed();
		Assert.assertTrue(lForm);
		System.out.println("Entire Login form is properly Displayed!!");
		// validate Email Input available
		boolean eInput = driver.findElement(By.cssSelector("#email-input")).isDisplayed();
		Assert.assertTrue(eInput);
		System.out.println("Email Input text box is available and Displayed!!");
		// validate Password Input available
		boolean pInput = driver.findElement(By.cssSelector("#password-input")).isDisplayed();
		Assert.assertTrue(eInput);
		System.out.println("Password Input text box is Available and Displayed!");

		// Valida on Login button
		boolean lButton = driver.findElement(By.id("login-button")).isDisplayed();
		Assert.assertTrue(lButton);
		System.out.println("Login Button is Available and Displayed!");

	}

	@Test(priority = 3)
	public void loginWithValidCredentials() {
		// Enter email id
		driver.findElement(By.xpath("//*[@id='email-input']")).sendKeys("login@codility.com");
		// Enter password
		driver.findElement(By.cssSelector("#password-input")).sendKeys("password");
		// Click on Login button
		driver.findElement(By.id("login-button")).click();
		String welcomeMessage = driver.findElement(By.tagName("div")).getText();
		Assert.assertEquals(welcomeMessage, "Welcome to Codility", "Unsuccessful Login");
		System.out.println("Successfully Login Message as : "+welcomeMessage);
		validatePageTitle();
	}

	@Test(priority = 4)
	public void loginWithInVaidID() {
		// Enter Invalid email id
		driver.findElement(By.xpath("//*[@id='email-input']")).sendKeys("colility.com");
		// Enter Invalid password
		driver.findElement(By.cssSelector("#password-input")).sendKeys("password");
		// Click on Login button
		driver.findElement(By.id("login-button")).click();
		String inValidID = driver.findElement(By.xpath("//*[@id='messages']/div")).getText();
		Assert.assertEquals(inValidID, "Enter a valid email", "Your email ID is unkown, Plese try with the Correct email id."); 
		System.out.println("Incorrect Email : "+inValidID);
	}

	@Test(priority = 5)
	public void loginWithInVaidPassword() {
		driver.findElement(By.xpath("//*[@id='email-input']")).sendKeys("login@colility.com");
		driver.findElement(By.cssSelector("#password-input")).sendKeys("32145");
		// Click on Login button
		driver.findElement(By.id("login-button")).click();
		String inValidPass = driver.findElement(By.xpath("//div[contains(text(),'You shall not pass! Arr!')]"))
				.getText();
		Assert.assertEquals(inValidPass, "You shall not pass! Arr!", "Your Password is not belongs to this user.");
		System.out.println("Incorrect Password : "+inValidPass);
	}

	@Test(priority = 2)
	public void loginWithEmptyCredentials() {
		// Click on Login button
		driver.findElement(By.id("login-button")).click();
		// Validate Empty Email input field
		String emptyEmailError = driver.findElement(By.xpath("//div[contains(text(),'Email is required')]")).getText();
		Assert.assertEquals(emptyEmailError, "Email is required", "Email Field must not be empty!");
		System.out.println("Email Field is Empty with this error message: " + emptyEmailError.toString());
		// Validate Empty Password input field
		String emptyPassError = driver.findElement(By.xpath("//div[contains(text(),'Password is required')]"))
				.getText();
		Assert.assertEquals(emptyPassError, "Password is required", "Password Field must not be empty!");
		System.out.println("Password Field is Empty with this error message: " + emptyPassError.toString());

	}

	@AfterMethod
	public static void tearDown() {
		driver.close();
	}

}
