package cs414.a1.sanjusam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	private Company companyOne;
	private Company companyTwo;
	
	@Before
	public void setupTest() {
		companyOne = new Company("Test  Company One");
		companyTwo = new Company("Test  Company Two");
	}

	@Test
	public void testCompanyEqualityDiffernetNames() throws Exception {
		Assert.assertFalse(companyOne.equals(companyTwo));	
	}
	
	@Test
	public void testCompanyEqualitySameNames() throws Exception {
		final Company copyOfCompanyOne = new Company(companyOne.getName());
		Assert.assertTrue(companyOne.equals(copyOfCompanyOne));	
		
		Assert.assertEquals(companyOne.getName(), copyOfCompanyOne.getName());
	}
	
}
