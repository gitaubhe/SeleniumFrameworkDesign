package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.IFactoryAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;

public class BaseTest {

	protected WebDriver driver;
	protected LandingPage landingpage;
	static public ExtentReports extent;

	public WebDriver InitializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Gita\\Drivers for Selenium\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if (browser.contains("headless")) {
				options.addArguments("headless");
				driver.manage().window().setSize(new Dimension(1440, 900));
			}
			driver = new ChromeDriver(options);

		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Gita\\Drivers for Selenium\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath));

		// String to Hashmap using Jackson data bind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + testcaseName + ".png";
	}

	@BeforeTest()
	public void Config() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Tests");
		reporter.config().setDocumentTitle(path);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Gita");
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException {
		driver = InitializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.GoToApplication();
        return landingpage; 
	}

	@AfterMethod
	public void closeApplication() {
		driver.close();
	}
}
