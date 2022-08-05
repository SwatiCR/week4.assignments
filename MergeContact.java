package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) {
		/*
		 * //Pseudo Code
		 * 
		 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		 * 
		 * 2. Enter UserName and Password Using Id Locator
		 * 
		 * 3. Click on Login Button using Class Locator
		 * 
		 * 4. Click on CRM/SFA Link
		 * 
		 * 5. Click on contacts Button
		 * 	
		 * 6. Click on Merge Contacts using Xpath Locator
		 * 
		 * 7. Click on Widget of From Contact
		 * 
		 * 8. Click on First Resulting Contact
		 * 
		 * 9. Click on Widget of To Contact
		 * 
		 * 10. Click on Second Resulting Contact
		 * 
		 * 11. Click on Merge button using Xpath Locator
		 * 
		 * 12. Accept the Alert
		 * 
		 * 13. Verify the title of the page
		 */

		//WebDriverManager for browser driver
		WebDriverManager.chromedriver().setup();

		//Launch the browser
		ChromeDriver driver = new ChromeDriver();

		//1. Load the URL
		driver.get("http://leaftaps.com/opentaps/control/login");

		//maximize the screen
		driver.manage().window().maximize();

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));

		//2.Enter the username
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");

		//2.Enter the password
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		//3.click login
		driver.findElement(By.className("decorativeSubmit")).click();

		//4	Click crm/sfa link
		driver.findElement(By.linkText("CRM/SFA")).click();

		//5. Click on contacts Button
		driver.findElement(By.xpath("//div[@class='x-panel-header']/a[text()='Contacts']")).click();

		//6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//ul[@class='shortcuts']/li/a[text()='Merge Contacts']")).click();

		//7. Click on Widget of From Contact
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();

		//get the list of open windows
		Set<String> windowHandles1 = driver.getWindowHandles();

		//convert to list
		List<String> lsWindowHandles1 = new ArrayList<String>(windowHandles1);

		//switch control to new window
		driver.switchTo().window(lsWindowHandles1.get(1));				

		//8. Click on First Resulting Contact
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();

		//shift control back to window1
		driver.switchTo().window(lsWindowHandles1.get(0));

		//9. Click on Widget of To Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();

		//get the list of open windows
		Set<String> windowHandles2 = driver.getWindowHandles();

		//convert to list
		List<String> lsWindowHandles2 = new ArrayList<String>(windowHandles2);

		//switch control to new window
		driver.switchTo().window(lsWindowHandles2.get(1));				

		//10. Click on Second Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		
		//switch control back to window1
		driver.switchTo().window(lsWindowHandles2.get(0));
		
		//11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		//12. Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		//13. Verify the title of the page
		String title = driver.getTitle();
		
		System.out.println(title);

	}

}
