package cs414.a1.sanjusam;

import java.util.HashSet;
import java.util.Set;

public class Worker {
	private final String name;
	private double salary ;
	private Set<Qualification> qualification = new HashSet<Qualification>();

	public Worker(final String name, final HashSet<Qualification> qualification) {
		this.name = name;
		this.qualification = qualification;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(final double salary) {
		this.salary = salary;
	}
	
	public Set<Qualification> getQualifications() {
		return qualification;
	}
	
	public void addQualification(final Qualification qualification) {
		this.qualification.add(qualification);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Worker)) {
			return false;
		}
		final Worker that = (Worker) obj;
		if (this.name == null) {
			if (that.name != null) {
				return false;
			}
		} else if (!this.name.equals(that.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		/*
		 * Returns a String that includes the nickname, colon, #projects, colon, #qualifications, 
		 * colon, salary. 
		 * For example, a worker named "Nick", working on 2 projects, and having 10 qualifications and 
		 * a salary of 10000 will result in the string Nick:2:10:10000. 
		 */
		return "";
	}
	
	public boolean willOverload(final Project project) {
		/*
		Returns true if a worker will be overloaded if the worker gets assigned to the project "p", '
		false otherwise. A constraint for the entire system is that no worker should ever be overloaded. 
		To determine overloading, consider all the ACTIVE projects the worker is involved in. 
		If adding the new project to the existing projects of the worker 
		makes (3*number_of_big_projects + 2*number_of_medium projects + number_of_small_projects) greater than 12, then the worker will be overloaded.
		*/ 
		return false;
	}
	
	
}
