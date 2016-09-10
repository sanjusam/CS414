package cs414.a1.sanjusam;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.JUnit4TestAdapter;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	QualificationTest.class,
	WorkerTest.class,
	ProjectTest.class,
	CompanyTest.class
	})

public class TestAll {
	public static void main(String [] args) {
		junit.textui.TestRunner.run(suit());
	}
	
	public static junit.framework.Test suit() {
		return new JUnit4TestAdapter(TestAll.class);
	}
}
