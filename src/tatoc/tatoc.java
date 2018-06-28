package tatoc;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.util.Cookie;

public class tatoc {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Chetanagnihotri\\eclipse-workspace\\Tatoc Automation\\chrome\\chromedriver.exe");  // Launch the chrome driver
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.cssSelector("a[href='/tatoc/basic']")).click();     // Click on Basic course
		driver.findElement(By.className("greenbox")).click();                  //Click on the Green box

		// From Dungeon
		driver.switchTo().frame(driver.findElement(By.id("main")));        //Switch on the main frame
		String Box1 = driver.findElement(By.cssSelector("div[id='answer']")).getAttribute("class");       //getting attribte of parent frame
		driver.switchTo().frame(driver.findElement(By.id("child")));        //Switch on the child frame
		String Box2 = driver.findElement(By.cssSelector("div[id='answer']")).getAttribute("child");      //getting attribute of child frame
		while (!(Box1.equals(Box2))) {        //Check if box1!=Box2 Then go into  the loop
			driver.switchTo().defaultContent();   
			driver.switchTo().frame(driver.findElement(By.id("main")));    //Switch on the main frame
			driver.findElement(By.cssSelector("a[href='#']")).click();     //Click on the repaint Box
			driver.switchTo().frame(driver.findElement(By.id("child")));        //Switch on the child frame
			Box2 = driver.findElement(By.cssSelector("div[id='answer']")).getAttribute("class");      //getting attribute of child frame
			// Thread.sleep(1000);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("main")));
		driver.findElement(By.cssSelector("a[onclick*='gonext']")).click();     //Click on proceed 
		// 3. Drag Around
		WebElement From = driver.findElement(By.cssSelector("div[id='dragbox']"));    //Find the path of drag box
		WebElement To = driver.findElement(By.cssSelector("div[id='dropbox']"));    //Find the path of drop box
		Actions drgAndDrp = new Actions(driver);     //Action perform on draganddrop
		drgAndDrp.dragAndDrop(From, To).build().perform();
		driver.findElement(By.cssSelector("a[onclick*='gonext']")).click();        //Click on proceed 

		// 4. Pop-up Windows
		driver.findElement(By.cssSelector("a[onclick*='launchwindow();']")).click();    //find the path of popup window and then click
		// driver.switchTo().window("Popup - Basic Course - T.A.T.O.C");
		Set handles = driver.getWindowHandles();
		System.out.println(handles);
		// String Mainwindow = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.cssSelector("input[id='name']")).sendKeys("Test");
		driver.findElement(By.cssSelector("input[id='submit']")).click();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		driver.findElement(By.cssSelector("a[onclick='gonext();']")).click();    //Click on proceed 

		/*
		 * //Cookie Handling
		 * driver.findElement(By.cssSelector("a[onclick='generateToken();']")).click(
		 * ); String s = driver.findElement(By .xpath("//span[@id='token']")).getText();
		 * System.out.println(s); String[] tokenValue = s.split("\\s");
		 * //System.out.println(tokenValue[1]); String token = tokenValue[1]; Cookie
		 * cookie = new Cookie("Token", token); driver.manage().addCookie(cookie);
		 * driver.findElement(By.cssSelector("a[onclick='gonext();']")).click();
		 * 
		 * } /*}
		 */
	}

}
