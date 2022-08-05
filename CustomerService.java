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

public class CustomerService {

	public static void main(String[] args) throws InterruptedException {
		/*1. Launch Salesforce application https://login.salesforce.com/
			2. Login with Provided Credentials
			3. Click on Learn More link in Mobile Publisher
			4. Clilck on Products and Mousehover on Service 
			5. Click on Customer Services
			6. Get the names Of Services Available 
			7. Verify the title*/

		//WebDriverManager for browser driver
		WebDriverManager.chromedriver().setup();

		//disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		//1.Launch the browser
		ChromeDriver driver = new ChromeDriver(options);

		//2.Load the url as " https://login.salesforce.com/ "
		driver.get("https://login.salesforce.com/");

		//maximize the window
		driver.manage().window().maximize();

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		
		//explicit wait
		//WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(5));

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

		//shadow dom
		Shadow dom = new Shadow(driver);

		//Clilck on Products and Mousehover on Service
		dom.findElementByXPath("//span[text()='Products']").click();

		//Actions Class
		Actions builder = new Actions(driver);
		Thread.sleep(3000);
		WebElement eleService = dom.findElementByXPath("//span[text()='Service']");
		builder.moveToElement(eleService).perform();
		builder.scrollToElement(eleService).perform();

		//6. Get the names Of Services Available 
		List<WebElement> availableService = dom.findElementsByXPath("//li[@class='c360-panel-linkedlist__listitem']/h4");		

		for(WebElement webelement : availableService) {
			String serviceName = webelement.getText();
			System.out.println(serviceName);

		}

	}

}
