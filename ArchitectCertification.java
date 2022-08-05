package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertification {

	public static void main(String[] args) throws InterruptedException {
		/*	1. Launch Salesforce application https://login.salesforce.com/
			2. Login with Provided Credentials
			3. Click on Learn More link in Mobile Publisher
			4. Click  On Resources
			5. Select SalesForce Certification Under Learnings
			6. Choose Your Role as Salesforce Architect
			7. Get the Text(Summary) of Salesforce Architect 
			8. Get the List of Salesforce Architect Certifications Available
			9. Click on Application Architect 
			10.Get the List of Certifications available
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
		//Get all open window handles into set
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		Thread.sleep(3000);
		//convert to list
		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);		

		//4.Switch to the next window using Windowhandles.
		driver.switchTo().window(lsWindowHandles.get(1));		

		//4.click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//Click  On Learning
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

		//Choose Your Role as Salesforce Architect
		driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();

		//Get the Text(Summary) of Salesforce Architect 
		String salesForceText = driver.findElement(By.xpath("//h1[text()='Salesforce Architect']/following-sibling::div")).getText();
		System.out.println(salesForceText);

		//Get the List of Salesforce Architect Certifications Available
		List<WebElement> lsArchiCerti = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));

		for(WebElement webelement : lsArchiCerti) {
			String certifiName = webelement.getText();
			System.out.println(certifiName);
		}

		//Click on Application Architect 
		driver.findElement(By.xpath("//div[@class='credentials-card_title']/a")).click();

		List<WebElement> lsAppArchiCerti = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));

		for(WebElement webelement : lsAppArchiCerti) {
			String appCertifiName = webelement.getText();
			System.out.println(appCertifiName);
		}
	}

}
