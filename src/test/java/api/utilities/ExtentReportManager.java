package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext testContext) {
		String timeStamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";

		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repName);//Specify location of the report

		sparkReporter.config().setDocumentTitle("RestAssuredAutomatioinProject");
		sparkReporter.config().setReportName("Pet stor User Api");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		//extent=new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet stor User Api");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","A S Chinnabba");
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test Pased");
	}
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL,"Test Failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP,"Test Skipped");
		test.log(Status.SKIP,result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext) 
	{
		extent.flush();
	}

}
