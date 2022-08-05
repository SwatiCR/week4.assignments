package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class AdminCertifications {

	public static void main(String[] args) throws InterruptedException {
		/*1. Launch Salesforce application https://login.salesforce.com/
			2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
			3. Click on Learn More link in Mobile Publisher
			4. Click confirm on Confirm redirect
			5. Click Resources and mouse hover on Learning On Trailhead
			6. Clilck on Salesforce Certifications
			6. Click on Ceritification Administrator
			7. Navigate to Certification - Administrator Overview window
			8. Verify the Certifications available for Administrator Certifications should be displayed
		 */

		//WebDriverManager for browser driver
		WebDriverManager.chromedriver().setup();

		//1.Launch the browser
		ChromeDriver driver = new ChromeDriver();

		//2.Load the url as " https://login.salesforce.com/ "
		driver.get("https://login.salesforce.com/");

		//maximize the window
		driver.manage().window().maximize();

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		//explicit wait
		WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(5));

		//disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");		

		//2.Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//2. Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");

		//2.click on the login button
		driver.findElement(By.id("Login")).click();

		//3. Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//4. Click confirm on Confirm redirect
		//Get all open indow handles into set
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		Thread.sleep(3000);
		//convert to list
		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);		
		
		//4.Switch to the next window using Windowhandles.
		driver.switchTo().window(lsWindowHandles.get(1));
		//wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		//ele.click();
		
		//4.click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//5. Click Resources and mouse hover on Learning On Trailhead
		//handle shadow dom
		Shadow dom=new Shadow(driver);
		dom.findElementByXPath("//span[text()='Learning']").click();
		
		Thread.sleep(3000);
		//Learning on Trailhead
		Actions builder = new Actions(driver);	
		WebElement trailHead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		builder.moveToElement(trailHead).perform();
		
		builder.scrollToElement(trailHead).perform();		
		
		//6. Clilck on Salesforce Certifications
		WebElement salesCertifi = dom.findElementByXPath("//a[text()='Salesforce Certification']");		
		driver.executeScript("arguments[0].click();", salesCertifi);		
		
		//Click on Ceritification Administrator
		driver.findElement(By.linkText("Administrator")).click();
		
		//Navigate to Certification - Administrator Overview window
		List<WebElement> certifications = driver.findElements(By.xpath("(//div[@class='trailMix-card-body']/div[2])/a"));
		System.out.println(certifications.size());
		for (WebElement webElement : certifications) {
			String text = webElement.getText();
			System.out.println(text);
			}
		
	}

}
