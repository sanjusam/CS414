package cs414.a1.sanjusam;


import org.junit.Assert;
import org.junit.Test;

public class ProjectStatusTest {
	
	@Test
	public void ProjectSizeEnums() throws Exception {
		Assert.assertEquals("ACTIVE", ProjectStatus.ACTIVE.toString());
		Assert.assertEquals("PLANNED", ProjectStatus.PLANNED.toString());
		Assert.assertEquals("SUSPENDED", ProjectStatus.SUSPENDED.toString());
		Assert.assertEquals("FINISHED", ProjectStatus.FINISHED.toString());
	}
	
}
