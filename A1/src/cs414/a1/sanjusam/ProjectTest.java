package cs414.a1.sanjusam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	private Project projectNetworkConfigurtion ;
	private Project projectNetworkConfigurtionLarge ;
	private Project projectNetworkGui;
	
	@Before() 
	public void testSetup() {
		projectNetworkConfigurtion = new Project("Network Config", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		projectNetworkConfigurtionLarge = new Project("Network Config", ProjectSize.LARGE, ProjectStatus.ACTIVE);
		projectNetworkGui = new Project("Network Config GUI", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		
	}
	
	@Test
	public void testEquality() throws Exception {
		Assert.assertFalse(projectNetworkConfigurtion.equals(projectNetworkGui));
		Assert.assertTrue(projectNetworkConfigurtion.equals(projectNetworkConfigurtionLarge));
	}
	
	@Test
	public void testGetters() throws Exception {
		Assert.assertEquals("Network Config", projectNetworkConfigurtionLarge.getName());
		Assert.assertEquals(ProjectSize.LARGE, projectNetworkConfigurtionLarge.getSize());	
		Assert.assertEquals(ProjectStatus.ACTIVE, projectNetworkConfigurtionLarge.getStatus());
	}
	
	@Test
	public void testSetStatusAndGet() throws Exception {
		//**Given
		projectNetworkConfigurtionLarge.setStatus(ProjectStatus.FINISHED);
		
		//**Then
		Assert.assertEquals(ProjectStatus.FINISHED, projectNetworkConfigurtionLarge.getStatus());
	}
	
	
}
