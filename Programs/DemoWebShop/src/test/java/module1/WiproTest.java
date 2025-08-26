package module1;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class WiproTest {
	@Test(groups = "smoke")
	public void wiproTest()
	{
		Reporter.log("wipro company",true);
	}
	

}
