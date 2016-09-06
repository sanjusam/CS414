package cs414.a1.sanjusam;

import java.util.HashSet;
import java.util.Set;

public class Worker {
	private final int OVERLOAD_THRESHOLD = 12;
	private final String name;
	private double salary ;
	private Set<Qualification> qualification = new HashSet<Qualification>();
	private Set<Project> assignedProjects = new HashSet<Project>();

	public Worker(final String name, final Set<Qualification> qualification) {
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
		return name + ":" + assignedProjects.size() + ":" + qualification.size() + ":" + salary;
	}

	public boolean willOverload(final Project project) {
		final int proposedLoad = getCurrentLoad() + getProjectLoad(project);
		return proposedLoad > OVERLOAD_THRESHOLD;
	}

	/*package*/ void assignProject(final Project project) {
		assignedProjects.add(project);
	}

	/*package*/ void unassignProject(final Project project) {
		assignedProjects.remove(project);
	}

	/*package*/ void unassignAllProjects() {
		assignedProjects.clear();
	}

	/*package*/ Set <Project> getAssignedProjects() {
		return assignedProjects;
	}

	private int getCurrentLoad() {
		int overallWeight = 0  ;
		for(final Project project : assignedProjects) {
			overallWeight += getProjectLoad(project);
		}
		return overallWeight;	
	}

	private int getProjectLoad(final Project project) {
		if(project.getSize().equals(ProjectSize.LARGE)) {
			return 3;
		} else if (project.getSize().equals(ProjectSize.MEDIUM)) {
			return 2;
		}
		return 1;
	}
}
