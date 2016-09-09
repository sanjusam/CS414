package cs414.a1.sanjusam;

import org.junit.Assert;
import org.junit.Test;


public class ProjectSizeTest {
	
	@Test
	public void ProjectSizeEnums() throws Exception {
		Assert.assertEquals("LARGE", ProjectSize.LARGE.toString());
		Assert.assertEquals("MEDIUM", ProjectSize.MEDIUM.toString());
		Assert.assertEquals("SMALL", ProjectSize.SMALL.toString());
	}
	
}
