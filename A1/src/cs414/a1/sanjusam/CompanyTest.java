package cs414.a1.sanjusam;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	private Company companyOne;
	private Company companyTwo;
	private Set<Qualification> qualificationNeeded = new HashSet<Qualification>();

	private Worker workerOne ;
	private Worker workerTwo ;
	private Worker workerThree ;
	private Worker workerFour ;
	private Worker workerFive;

	private Set<Qualification> qualificationSetOne = new HashSet<Qualification>();
	private Set<Qualification> qualificationSetTwo = new HashSet<Qualification>();
	private Set<Qualification> qualificationSetThree = new HashSet<Qualification>();
	private Set<Qualification> qualificationSetFour = new HashSet<Qualification>();
	private Set<Qualification> qualificationSetFive = new HashSet<Qualification>();
	private Set<Qualification> qualificationNotWorthForCurrentProj = new HashSet<Qualification>();

	@Before
	public void setupTest() {
		companyOne = new Company("Test  Company One");
		companyTwo = new Company("Test  Company Two");
		qualificationNeeded.add(new Qualification("Java"));
		qualificationNeeded.add(new Qualification("C++"));
		qualificationNeeded.add(new Qualification("Python"));
		qualificationNeeded.add(new Qualification("Database - Oracle"));
		qualificationNeeded.add(new Qualification("Database - Sybase"));		

		qualificationSetOne.add(new Qualification("Java"));
		qualificationSetTwo.add(new Qualification("C++"));
		qualificationSetThree.add(new Qualification("Python"));
		qualificationSetFour.add(new Qualification("Database - Oracle"));
		qualificationSetFive.add(new Qualification("Database - Sybase"));
		qualificationNotWorthForCurrentProj.add(new Qualification("Perl"));
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

	@Test
	public void testCreateProject() throws Exception {
		//**Given
		final Project companyOneProject = companyOne.createProject("Network-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);

		//**Then
		Assert.assertEquals("Test  Company One:0:1", companyOne.toString());
		Assert.assertEquals("Network-Configs", companyOneProject.getName());
		Assert.assertEquals(ProjectSize.MEDIUM, companyOneProject.getSize());
		Assert.assertEquals(ProjectStatus.PLANNED, companyOneProject.getStatus());
	}

	@Test
	public void testCreateProjectAndAssignWorkers() throws Exception {
		//**Given
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);

		//**When
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);

		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);

		//**Then
		Assert.assertEquals(2, companyOne.getAssignedWorkers().size());
		Assert.assertTrue(companyOne.getAssignedWorkers().contains(workerOne));
		Assert.assertEquals(5, companyOne.getAvailableWorkers().size());
		Assert.assertTrue(companyOne.getAvailableWorkers().contains(workerOne));
		Assert.assertEquals(3, companyOne.getUnassignedWorkers().size());
		Assert.assertTrue(companyOne.getUnassignedWorkers().contains(workerFive));
	}

	@Test
	public void testCreateProjectAndAssignWorkersFailure() throws Exception {
		//**Given
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOneProject.setStatus(ProjectStatus.FINISHED);

		//**When
		companyOne.assign(workerOne, companyOneProject);

		//**Then
		Assert.assertEquals(0, companyOne.getAssignedWorkers().size());
		Assert.assertEquals(1, companyOne.getAvailableWorkers().size());
	}

	@Test
	public void testAssignWorkerNotPresetnInAvailableWorker() throws Exception {
		//**Given
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationNeeded);

		//**When
		companyOne.assign(workerOne, companyOneProject);

		//**Then
		Assert.assertEquals(0, companyOne.getAssignedWorkers().size());
		Assert.assertEquals(0, companyOne.getAvailableWorkers().size());
	}

	@Test
	public void testOverLoadWorkerAndThenAssignFailure() throws Exception {
		//**Given  -- Overloaded worker
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationNeeded);
		companyOne.addToAvailableWorkerPool(workerOne);
		workerOne.assignProject(new Project("Project1", ProjectSize.LARGE, ProjectStatus.PLANNED));
		workerOne.assignProject(new Project("Project2", ProjectSize.LARGE, ProjectStatus.PLANNED));
		workerOne.assignProject(new Project("Project3", ProjectSize.LARGE, ProjectStatus.PLANNED));
		workerOne.assignProject(new Project("Project4", ProjectSize.LARGE, ProjectStatus.PLANNED));
		workerOne.assignProject(new Project("Project5", ProjectSize.LARGE, ProjectStatus.PLANNED));

		//**When
		companyOne.assign(workerOne, companyOneProject);

		//**Then -- Worker not assigned
		Assert.assertEquals(0, companyOne.getAssignedWorkers().size());
		Assert.assertEquals(1, companyOne.getAvailableWorkers().size());		
	}

	@Test
	public void testCreateProjectAndAssignWorkersInvalidQualificationFailure() throws Exception {
		//**Given
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationNotWorthForCurrentProj);  
		companyOne.addToAvailableWorkerPool(workerOne);

		//**When
		companyOne.assign(workerOne, companyOneProject);

		//**Then
		Assert.assertEquals(0, companyOne.getAssignedWorkers().size());
		Assert.assertEquals(1, companyOne.getAvailableWorkers().size());
	}

	@Test
	public void testCreateProjectAndUnAssignWorkers() throws Exception {
		//**Given
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);
		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);

		//**When
		companyOne.unassign(workerTwo, companyOneProject);

		//*Then
		Assert.assertEquals(1, companyOne.getAssignedWorkers().size());
		Assert.assertTrue(companyOne.getAssignedWorkers().contains(workerOne));
		Assert.assertFalse(companyOne.getAssignedWorkers().contains(workerTwo));
		Assert.assertEquals(5, companyOne.getAvailableWorkers().size());
		Assert.assertTrue(companyOne.getAvailableWorkers().contains(workerOne));
		Assert.assertEquals(4, companyOne.getUnassignedWorkers().size());
	}

	@Test
	public void testUnassignWorkerAndCheckProjectStatus() throws Exception {

		//**Given
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);

		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);
		companyOne.assign(workerThree, companyOneProject);
		companyOne.assign(workerFour, companyOneProject);
		companyOne.assign(workerFive, companyOneProject);
		companyOneProject.setStatus(ProjectStatus.ACTIVE);


		//**When
		companyOne.unassign(workerTwo, companyOneProject);

		//**Then
		Assert.assertEquals(ProjectStatus.SUSPENDED, companyOneProject.getStatus());
	}

	@Test
	public void testCreateProjectAndUnAssignAllWorkers() throws Exception {
		//**Given -- Company, workers and Status ACTIVE
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);
		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);
		companyOneProject.setStatus(ProjectStatus.ACTIVE);

		//**When
		companyOne.unassignAll(workerOne);

		//*Then  -- Project Status SUSPENDED
		Assert.assertEquals(1, companyOne.getAssignedWorkers().size());
		Assert.assertFalse(companyOne.getAssignedWorkers().contains(workerOne));
		Assert.assertTrue(companyOne.getAssignedWorkers().contains(workerTwo));
		Assert.assertEquals(5, companyOne.getAvailableWorkers().size());
		Assert.assertTrue(companyOne.getAvailableWorkers().contains(workerOne));
		Assert.assertEquals(4, companyOne.getUnassignedWorkers().size());
		Assert.assertEquals(ProjectStatus.SUSPENDED, companyOneProject.getStatus());
	}


	@Test
	public void testStartProjectFailureOnProjectStatus() throws Exception {
		//**Given  -- Company, workers and Status ACTIVE
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);
		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);
		companyOneProject.setStatus(ProjectStatus.ACTIVE);

		//**When
		companyOne.start(companyOneProject);

		//**Then  -- Status does not change.
		Assert.assertEquals(ProjectStatus.ACTIVE, companyOneProject.getStatus());
	}

	@Test
	public void testStartProjectFailureMissingRequiremnt() throws Exception {
		//**Given  -- Company, workers and Status Planned, but missing one requirement "Database - Oracle" 
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFive);
		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);
		companyOne.assign(workerThree, companyOneProject);
		companyOne.assign(workerFour, companyOneProject);
		companyOne.assign(workerFive, companyOneProject);

		//**When
		companyOne.start(companyOneProject);

		//**Then  -- Status does not change.
		Assert.assertEquals(ProjectStatus.PLANNED, companyOneProject.getStatus());
	}

	@Test
	public void testStartProjectSuccess() throws Exception {
		//**Given  -- Company, workers and Status Planned
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);
		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);
		companyOne.assign(workerThree, companyOneProject);
		companyOne.assign(workerFour, companyOneProject);
		companyOne.assign(workerFive, companyOneProject);

		//**When
		companyOne.start(companyOneProject);

		//**Then  -- Status does not change.
		Assert.assertEquals(ProjectStatus.ACTIVE, companyOneProject.getStatus());
	}

	@Test
	public void testFinishSuccess() throws Exception {
		//**Given  -- Company, workers and Status Planned
		final Project companyOneProject = companyOne.createProject("Netowork-Configs", qualificationNeeded, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		final Worker workerOne = new Worker("Sanju1", qualificationSetOne);  
		final Worker workerTwo = new Worker("Sanju2", qualificationSetTwo); 
		final Worker workerThree = new Worker("Sanju3", qualificationSetThree); 
		final Worker workerFour = new Worker("Sanju4", qualificationSetFour);
		final Worker workerFive = new Worker("Sanju5", qualificationSetFive);
		companyOne.addToAvailableWorkerPool(workerOne);
		companyOne.addToAvailableWorkerPool(workerTwo);
		companyOne.addToAvailableWorkerPool(workerThree);
		companyOne.addToAvailableWorkerPool(workerFour);
		companyOne.addToAvailableWorkerPool(workerFive);
		companyOne.assign(workerOne, companyOneProject);
		companyOne.assign(workerTwo, companyOneProject);
		companyOne.assign(workerThree, companyOneProject);
		companyOne.assign(workerFour, companyOneProject);
		companyOne.assign(workerFive, companyOneProject);
		companyOneProject.setStatus(ProjectStatus.ACTIVE);

		//**When
		companyOne.finish(companyOneProject);

		//**Then  -- Status does not change.
		Assert.assertEquals(ProjectStatus.FINISHED, companyOneProject.getStatus());
		Assert.assertEquals(0, companyOneProject.getWorkers().size());
		Assert.assertEquals(0,  companyOne.getAssignedWorkers().size());
	}

}
