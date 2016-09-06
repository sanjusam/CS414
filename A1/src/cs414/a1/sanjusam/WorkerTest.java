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
}
