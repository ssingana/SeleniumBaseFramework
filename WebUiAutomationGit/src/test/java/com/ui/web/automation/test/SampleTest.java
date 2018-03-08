package com.ui.web.automation.test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.web.ui.automation.custom.annotations.Email;
import com.web.ui.automation.util.ExcelUtil;

public class SampleTest {

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase1() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase2() {
		Assert.assertTrue(false);
	}

	/*@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase3() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase4() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase5() {
		Assert.assertTrue(false);
	}
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase6() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase7() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase8() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase9() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase10() {
		Assert.assertTrue(false);
	}
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase11() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase12() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase13() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase14() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase15() {
		Assert.assertTrue(false);
	}
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase16() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase17() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase18() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase19() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase20() {
		Assert.assertTrue(false);
	}
	
	
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase1111() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase21() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase31() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase41() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase51() {
		Assert.assertTrue(false);
	}
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase61() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase71() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase81() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase91() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase101() {
		Assert.assertTrue(false);
	}
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase111() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase121() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase131() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase141() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase151() {
		Assert.assertTrue(false);
	}
	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase161() {
		throw new SkipException("Not yet Implemented.");
	}

	@Test(enabled = false)
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase171() {
		Assert.assertTrue(false);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase181() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase191() {
		Assert.assertTrue(true);
	}

	@Test
	@Email(id = "Sreenivasulu.Singana@in.bosch.com")
	public void TestCase201() {
		Assert.assertTrue(false);
	}*/

}
