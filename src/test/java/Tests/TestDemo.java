package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDemo {
	
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Gita\\Drivers for Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("gita123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sangamner_1");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> elements = driver.findElements(By.cssSelector(".mb-3"));
		WebElement el = elements.stream().filter(element->element.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	    el.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	    Thread.sleep(50);
	    Actions a = new Actions(driver);
	    a.moveToElement(driver.findElement(By.cssSelector("[routerlink*='cart']")));
	    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
	    List<WebElement> products =  driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean flag = products.stream().anyMatch(element->element.getText().equalsIgnoreCase("ZARA COAT 3"));
	    System.out.println(flag);
	    Assert.isTrue(flag,"Not added");
	    driver.close();
	  
	   
	    
	}

}
