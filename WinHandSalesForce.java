package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class WinHandSalesForce {

	public static void main(String[] args) {
		/*.Assignment for WindowHandles  
		Salesforce Customer service:
		1.Launch the browser
		2.Load the url as " https://login.salesforce.com/ "
		3.Enter the username as " ramkumar.ramaiah@testleaf.com "
		4. Enter the password as " Password$123 "
		5.click on the login button
		6.click on the learn more option in the Mobile publisher
		7.Switch to the next window using Windowhandles.
		8.click on the confirm button in the redirecting page
		9.Get the title
		10.Get back to the parent window
		11.close the browser
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

		//disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");		
		
		//3.Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");

		//4. Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");

		//5.click on the login button
		driver.findElement(By.id("Login")).click();

		//6.click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//Get all open indow handles into set
		Set<String> windowHandles = driver.getWindowHandles();

		//convert to list
		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);

		//7.Switch to the next window using Windowhandles.
		driver.switchTo().window(lsWindowHandles.get(1));

		//8.click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//9. Get the title		
		System.out.println(driver.getTitle());

		//10.Get back to the parent window
		driver.switchTo().window(lsWindowHandles.get(0));
		
		//11.close the browser
		driver.quit();

	}

}
