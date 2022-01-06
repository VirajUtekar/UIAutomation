package Test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PractiseTest {

	public WebDriver driver;
	
	@BeforeTest
	public void navigateUrl() {
		
		
		
		String ch = System.getProperty("user.dir")+"/driver/chromedriver";
		
		System.setProperty("webdriver.chrome.driver", ch);
		
		driver = new ChromeDriver();
		
		driver.get("https://demoqa.com/text-box");
	}
	
	@Test(dataProvider="login")
	public void t1(String username, String email, String currentAddr, String permanentAddr) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	    // username field 
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(1) > div:nth-Child(2) > input")).clear();
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(1) > div:nth-Child(2) > input")).sendKeys(username);
    
		//email field	
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(2) > div:nth-Child(2) > input")).clear();
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(2) > div:nth-Child(2) > input")).sendKeys(email);
		
		//current Address field	
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(3)  > div:nth-Child(2) > textarea")).clear();
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(3)  > div:nth-Child(2) > textarea")).sendKeys(currentAddr);
 
		//permanent Address field	
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(4)  > div:nth-Child(2) > textarea")).clear();
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(4)  > div:nth-Child(2) > textarea")).sendKeys(permanentAddr);
	
		driver.findElement(By.cssSelector("div#app > div > div > div:nth-Child(2) > div:nth-Child(2)> div:nth-Child(1) > form > div:nth-Child(5) > div > button")).click();
				
	}
	
	@DataProvider(name="login")
	public Object[][] getData() throws IOException {
		
		
		  String path = System.getProperty("user.dir")+"/utilities/TestData.xlsx";
		  XLUtility xlutility = new XLUtility(path); 
		  int totalRow = xlutility.getRowCount("Sheet1"); 
		  int totalCol = xlutility.getCellCount("Sheet1",1);
		  
		  System.out.println(totalRow); System.out.println(totalCol);
		  
		  Object loginData[][] = new Object[totalRow][totalCol]; 
		  for(int i=0;i<totalRow;i++)
		  {
			  for(int j=0; j<totalCol;j++)
			  {
				  loginData[i][j] = xlutility.getCellData("Sheet1", i, j); 
				  }
			  }
		 		return loginData;				
	}
	
	@AfterTest
	public void terminate() {
		driver.quit();
	}	
}
