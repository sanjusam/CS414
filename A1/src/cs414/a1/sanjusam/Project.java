package cs414.a1.sanjusam;

import java.util.HashSet;
import java.util.Set;

public class Project {
	private final String name;
	private final ProjectSize projectSize;
	private ProjectStatus projectStatus;

	private final Set <Qualification> qualificationRequirements = new HashSet<>();
	private final Set <Worker> workerSet = new HashSet<>();

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

	public void setStatus(final ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Set<Qualification> missingQualifications() {
		final Set<Qualification> workerQualifications = new HashSet<>();
		for(final Worker worker : workerSet) {
			workerQualifications.addAll(worker.getQualifications());
		}
		Set<Qualification> missingQualifications = new HashSet<>();
		for(final Qualification qualification : qualificationRequirements) {
			if(!workerQualifications.contains(qualification)) {
				missingQualifications.add(qualification);
			}
		}
		return missingQualifications;
	}

	public boolean isHelpful(final Worker worker) {
		for (final Qualification qualification : missingQualifications()) {
			if(worker.getQualifications().contains(qualification)) {
				return true;
			}
		}
		//Please refer Overview explicitly added to mark a worker "useful" if he has any of the project requirement.
		for (final Qualification workerQualification : worker.getQualifications()) {
			if(qualificationRequirements.contains(workerQualification)) {
				return true;
			}
		}
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
		return name + ":" + workerSet.size() + ":" + projectStatus;
	}

	/*package */ void addQualificationRequiremnet(final Qualification qualification) {
		qualificationRequirements.add(qualification);
	}

	/*package */ Set <Qualification> getQualificationRequiremnet() {
		return qualificationRequirements;
	}

	/*package */  Set<Worker> getWorkers() {
		return workerSet;
	}

	/*package */  void addWorker(final Worker worker) {
		workerSet.add(worker);
	}

	/*package */  void removeWorker(final Worker worker) {
		workerSet.remove(worker);
	}

	/*package */  void removeAllWorkers() {
		workerSet.clear();
	}


}
