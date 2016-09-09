package cs414.a1.sanjusam;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	private Project projectNetworkConfigurtion ;
	private Project projectNetworkConfigurtionLarge ;
	private Project projectNetworkGui;
	private HashSet<Qualification> qualificationSetHasJava = new HashSet<>();
	private HashSet<Qualification> qualificationSetTwo = new HashSet<>();

	@Before() 
	public void testSetup() {
		projectNetworkConfigurtion = new Project("Network Config", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		projectNetworkConfigurtionLarge = new Project("Network Config", ProjectSize.LARGE, ProjectStatus.ACTIVE);
		projectNetworkGui = new Project("Network Config GUI", ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		qualificationSetHasJava.add(new Qualification("database"));
		qualificationSetHasJava.add(new Qualification("python"));

		qualificationSetTwo.add(new Qualification("c++"));
		qualificationSetTwo.add(new Qualification("oracle"));
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

	@Test
	public void testToString() throws Exception {
		//**Given
		projectNetworkConfigurtionLarge.setStatus(ProjectStatus.FINISHED);

		//*Then
		Assert.assertEquals("Network Config:0:FINISHED", projectNetworkConfigurtionLarge.toString());
	}

	/*  Testing a support method
	@Test
	public void testmissingQualifications() throws Exception {
		//**Given
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("database"));
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("java"));
		final Worker workerOne = new Worker("Sanju", qualificationSetHasJava);
		final Qualification missingQualification = new Qualification("java");

		//**When
		projectNetworkConfigurtionLarge.addWorker(workerOne);

		//**Then
		Assert.assertEquals(1, projectNetworkConfigurtionLarge.missingQualifications().size());
		Assert.assertEquals(missingQualification, projectNetworkConfigurtionLarge.missingQualifications().iterator().next());
	}



	@Test
	public void testmissingQualificationsMulitpleWorkers() throws Exception {
		//**Given
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("database"));
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("java"));
		final Worker workerOne = new Worker("Sanju", qualificationSetHasJava);
		final Worker workerTwo = new Worker("Saju", qualificationSetTwo);
		final Qualification missingQualification = new Qualification("java");

		//**When
		projectNetworkConfigurtionLarge.addWorker(workerOne);
		projectNetworkConfigurtionLarge.addWorker(workerTwo);

		//**Then
		Assert.assertEquals(1, projectNetworkConfigurtionLarge.missingQualifications().size());
		Assert.assertEquals(missingQualification, projectNetworkConfigurtionLarge.missingQualifications().iterator().next());
	}

	@Test
	public void testIsHelpful() throws Exception {
		//**Given
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("database"));
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("java"));
		final Worker workerOne = new Worker("Sanju", qualificationSetTwo);
		final Worker workerTwo = new Worker("Saju", qualificationSetTwo);
		projectNetworkConfigurtionLarge.addWorker(workerOne);
		projectNetworkConfigurtionLarge.addWorker(workerTwo);

		final Worker usefulWorker = new Worker("Fred", qualificationSetHasJava);

		//**Then
		Assert.assertTrue(projectNetworkConfigurtionLarge.isHelpful(usefulWorker));
	}

	@Test
	public void testIsHelpfulFailureCase() throws Exception {
		//**Given
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("database"));
		projectNetworkConfigurtionLarge.addQualificationRequiremnet(new Qualification("java"));
		final Worker workerOne = new Worker("Sanju", qualificationSetHasJava);
		final Worker workerTwo = new Worker("Saju", qualificationSetHasJava);
		projectNetworkConfigurtionLarge.addWorker(workerOne);
		projectNetworkConfigurtionLarge.addWorker(workerTwo);

		final Worker workerNotUseful = new Worker("Fred", qualificationSetTwo);

		//**Then
		Assert.assertFalse(projectNetworkConfigurtionLarge.isHelpful(workerNotUseful));
	}

*/
}
