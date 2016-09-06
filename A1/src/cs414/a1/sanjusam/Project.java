package cs414.a1.sanjusam;

import java.util.HashSet;
import java.util.Set;

public class Project {
	private final String name;
	private final ProjectSize projectSize;
	private ProjectStatus projectStatus;
	
	//*Added as per design doc!
	private final Set <Qualification> qualificationRequirements = new HashSet<>();
	
	public Project(final String name, final ProjectSize projectSize, final ProjectStatus projectStatus) {
		this.name = name;
		this.projectSize = projectSize;
		this.projectStatus = projectStatus;
	}

	public String getName() {
		return name;
	}

	public ProjectSize getSize() {
		return projectSize;
	}

	public ProjectStatus getStatus() {
		return projectStatus;
	}
	
	public void setStatus (final ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	public Set<Qualification> missingQualifications() {
		//TODO -- Compare the qualifications required by the project and those that 
//		are met by the workers who are assigned to the project. 
//		Return the qualifications that are not met. An empty set (not null set)
//		 is returned when all the qualification requirements are met. 
//		//The order of qualifications is not important.
		return new HashSet<Qualification>();
	}
	
	public boolean isHelpful() {
		//isHelpful(w : Worker) :
//		If at least one of the missing qualification 
//		requirements of a project is satisfied by the worker, then return true, else return false. 
		return false;
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
		if (!(obj instanceof Project)) {
			return false;
		}
		final Project that = (Project) obj;
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
		//TOD ::  name, colon, number of assigned workers, colon, status
		//CS5Anniv:10:PLANNED
		return "";
	}
	
	//*Added as per design doc!
	public void addQualificationRequiremnet(final Qualification qualification) {
		qualificationRequirements.add(qualification);
	}

}
