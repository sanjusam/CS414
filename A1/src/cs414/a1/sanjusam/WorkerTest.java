package cs414.a1.sanjusam;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkerTest {
	private Worker workerOne ;
	private Worker workerOneCopy;
	private Worker workerTwo;

	private final HashSet<Qualification> qualificationForWorkerOne = new HashSet<Qualification>();
	private final HashSet<Qualification> qualificationForWorkerTwo = new HashSet<Qualification>();

	@Before() 
	public void setupTest() {
		final Qualification qualificationOne = new Qualification("database");
		final Qualification qualificationTwo = new Qualification("python");
		final Qualification qualificationThree = new Qualification("java");


		qualificationForWorkerOne.add(qualificationOne);
		qualificationForWorkerOne.add(qualificationTwo);
		workerOne = new Worker("Worker-1", qualificationForWorkerOne);


		qualificationForWorkerTwo.add(qualificationOne);
		qualificationForWorkerTwo.add(qualificationThree);
		workerTwo = new Worker("Worker-2", qualificationForWorkerTwo);
	}

	@Test
	public void testEquality() throws Exception {
		Assert.assertFalse(workerOne.equals(workerTwo));
	}

	@Test
	public void testEqualityWhenWorkerNamesAreSame() throws Exception {
		workerOneCopy = new Worker("Worker-1", qualificationForWorkerTwo);
		Assert.assertTrue(workerOne.equals(workerOneCopy));
	}

	@Test
	public void testGetters() throws Exception {
		Assert.assertEquals("Worker-1", workerOne.getName());
		Assert.assertTrue(workerOne.getSalary() == 0.0);
		Assert.assertEquals(qualificationForWorkerOne, workerOne.getQualifications());
	}

	@Test
	public void testSetSalary() throws Exception {
		//**Given
		final double salaryToSet = 9999.00;
		Assert.assertTrue(workerOne.getSalary() == 0.0);

		//**When
		workerOne.setSalary(salaryToSet);

		//**Then
		Assert.assertTrue(workerOne.getSalary() == salaryToSet);
	}

	@Test
	public void testAddQualification() throws Exception {
		//**Given
		final Qualification qualificationToAdd = new Qualification("Oracle"); 
		Assert.assertEquals(2,  workerOne.getQualifications().size());

		//**When
		workerOne.addQualification(qualificationToAdd);

		//**Then
		Assert.assertFalse(workerOne.getQualifications() != qualificationForWorkerOne);
		Assert.assertTrue(workerOne.getQualifications().contains(qualificationToAdd));
		Assert.assertEquals(3,  workerOne.getQualifications().size());
	}

	@Test
	public void testToString() throws Exception {
		Assert.assertEquals("Worker-1:0:2:0.0", workerOne.toString());
	}

	/*  Just testing a helper method
	@Test
	public void testWillOverload() throws Exception {
		//**Given
		final Project project1 = new Project("Project1",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project2 = new Project("Project2",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project3 = new Project("Project3",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project4 = new Project("Project4",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project5 = new Project("Project5",ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Project project6= new Project("Project6",ProjectSize.MEDIUM, ProjectStatus.PLANNED);

		workerOne.assignProject(project1);
		workerOne.assignProject(project2);
		workerOne.assignProject(project3);
		workerOne.assignProject(project4);
		workerOne.assignProject(project5);

		//**Then
		Assert.assertTrue(workerOne.willOverload(project6));
	}
	
	
	@Test
	public void testGetAssignedProjects() throws Exception {
		//**Given
		final Project project1 = new Project("Project1",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project2 = new Project("Project2",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project3 = new Project("Project3",ProjectSize.LARGE, ProjectStatus.PLANNED);

		workerOne.assignProject(project1);
		workerOne.assignProject(project2);
		workerOne.assignProject(project3);
		workerOne.assignProject(project3);

		final Project project6 = new Project("Project6",ProjectSize.MEDIUM, ProjectStatus.PLANNED);

		//**Then
		Assert.assertEquals(3, workerOne.getAssignedProjects().size());
		Assert.assertTrue(workerOne.getAssignedProjects().contains(project1));
		Assert.assertFalse(workerOne.getAssignedProjects().contains(project6));
	}


	
	@Test
	public void testUnassignAllProjects() throws Exception {
		//**Given
		final Project project1 = new Project("Project1",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project2 = new Project("Project2",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project3 = new Project("Project3",ProjectSize.LARGE, ProjectStatus.PLANNED);

		workerOne.assignProject(project1);
		workerOne.assignProject(project2);
		workerOne.assignProject(project3);

		//**When
		workerOne.unassignAllProjects();

		//**Then
		Assert.assertTrue(workerOne.getAssignedProjects().isEmpty());
	}
	

	@Test
	public void testUnassignProject() throws Exception {
		//**Given
		final Project project1 = new Project("Project1",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project2 = new Project("Project2",ProjectSize.LARGE, ProjectStatus.PLANNED);
		final Project project3 = new Project("Project3",ProjectSize.LARGE, ProjectStatus.PLANNED);

		workerOne.assignProject(project1);
		workerOne.assignProject(project2);
		workerOne.assignProject(project3);

		//**When
		workerOne.unassignProject(project3);
		Assert.assertTrue(workerOne.getAssignedProjects().contains(project1));

		//**Then
		Assert.assertEquals(2, workerOne.getAssignedProjects().size());
	}
*/

}
