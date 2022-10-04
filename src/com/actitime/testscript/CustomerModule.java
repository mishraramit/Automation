package com.actitime.testscript;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseCLass;
import com.actitime.generic.FileLib;
import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)
public class CustomerModule extends BaseCLass{

	@Test
	public void testCreateCustomer() throws InterruptedException,EncryptedDocumentException, IOException {
		
		String customerName = f.getExcelData("Sheet1",1,2);
		String customerDescription = f.getExcelData("Sheet1",1,2);
		Reporter.log("CreateCustomer",true);
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.setTaskTab();
		TaskListPage t=new TaskListPage(driver);
		t.getAddNewBtn().click();
		t.getNewCustOptions().click();
		t.getEnterCustNameTbx().sendKeys(customerName);
		t.getEnterCustDescTbx().sendKeys(customerDescription);
		t.getSelectCustDD().click();
		t.getOurCompany().click();
		t.getCreateCustBtn().click();
		//WebDriverWait wait=new WebDriverWait(driver, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBePresentInElement(t.getActualCustomer(), customerName));
		String actualCustText = t.getActualCustomer().getText();
		Assert.assertEquals(actualCustText, customerName);
	}
}
