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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		//WebDriverManager for browser driver
		WebDriverManager.chromedriver().setup();

		//disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		//1.Launch the browser
		ChromeDriver driver = new ChromeDriver(options);

		//2.Load the url as "https://www.nykaa.com/"
		driver.get("https://www.nykaa.com/");

		//maximize the window
		driver.manage().window().maximize();

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		
		Actions builder = new Actions(driver);
		Thread.sleep(3000);
		
		//Mouseover on Brands and Search L'Oreal Paris
		WebElement eleBrands = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(eleBrands).perform();
		builder.scrollToElement(eleBrands).perform();
		
		//Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='scroller-container']/div[7]/a[1]")).click();
		
		//Check the title contains L'Oreal Paris(Hint-GetTitle)
		Thread.sleep(3000);
		String brandName = driver.findElement(By.xpath("//ul[@class='css-1uxnb1o']/li[@class='last-list css-vnn8hz']")).getText();
		System.out.println(brandName);
		
		//Click sort By and select customer top rated
		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']")).click();
		driver.findElement(By.xpath("(//div[@class='control-indicator radio '])[3]")).click();
		
		Thread.sleep(3000);
		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		
		//7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//8)check whether the Filter is applied with Shampoo
		String text = driver.findElement(By.xpath("(//div[@class='css-rtde4j']/div)[2]")).getText();
		
		if(text.equals("Color Protection")) {
			System.out.println("Color Protection Filter Applied");
		}
		else {
			System.out.println("Color Protection Filter Not Applied");
		}
		
		//9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains(text(),'Colour Protect Shampoo')]")).click();
		
		//10) GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		
		List<String> lsWindowHandles = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lsWindowHandles.get(1));
		
		WebElement dropDown1 = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select select = new Select(dropDown1);
		
		select.selectByVisibleText("175ml");
		
		//11) Print the MRP of the product
		String price = driver.findElement(By.className("css-1jczs19")).getText();		
		System.out.println(price);
		
		//12) Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		
		//13) Go to Shopping Bag 
		driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']")).click();
		Thread.sleep(3000);
		//14) Print the Grand Total amount
		WebElement frameElement = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(frameElement);
		String grandTotal = driver.findElement(By.xpath("(//div[@class='value medium-strong'])[1]")).getText();
		System.out.println(grandTotal.replace('?', ' '));
		
		//15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.switchTo().window(lsWindowHandles.get(1));
		
		//16) Click on Continue as Guest		
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
		//17) Check if this grand total is the same in step 14
		String total = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']/div[@class='value']")).getText();
		System.out.println(total.replace('?', ' '));		
		
		if(grandTotal.equals(total)) {
			System.out.println("Grand Total Verified");
		}
		else {
			System.out.println("Grand Total Not the same");
		}
		
		//18) Close all windows
		driver.quit();
		

	}

}
