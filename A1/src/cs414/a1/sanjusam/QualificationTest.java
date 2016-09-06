package cs414.a1.sanjusam;

import org.junit.Assert;
import org.junit.Test;

public class QualificationTest {

	@Test
	public void testEqual() throws Exception {
		//**Given
		final Qualification graduateDegreeOne = new Qualification("graduate");
		final Qualification graduateDegreeTwo = new Qualification("graduate");
		
		//**Then
		Assert.assertTrue(graduateDegreeOne.equals(graduateDegreeTwo));
	}
	
	@Test
	public void testEqualForSameObject() throws Exception {
		//**Given
		final Qualification graduateDegree = new Qualification("graduate");
				
		//**Then
		Assert.assertTrue(graduateDegree.equals(graduateDegree));
	}
	@Test
	public void testNotEqual() throws Exception {
		//**Given
		final Qualification graduate  = new Qualification("graduate");
		final Qualification underGrad = new Qualification("under-grad");
		
		//**Then
		Assert.assertFalse(graduate.equals(underGrad));
	}
	
	@Test
	public void testNotEqualWhenNullObject() throws Exception {
		//**Given
		final Qualification graduateDegreeOne = new Qualification("graduate");
		final Qualification graduateDegreeTwo = null;
		
		//**Then
		Assert.assertFalse(graduateDegreeOne.equals(graduateDegreeTwo));
	}

	@Test
	public void testNotEqualWhenDifferntObject() throws Exception {
		//**Given
		final Qualification graduateDegreeOne = new Qualification("graduate");
		final Object graduateDegreeTwo = new Object();
		
		//**Then
		Assert.assertFalse(graduateDegreeOne.equals(graduateDegreeTwo));
	}
}
