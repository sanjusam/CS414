package cs414.a1.sanjusam;

import java.util.HashSet;
import java.util.Set;

public class Company {
	private final String name;
	private final Set<Project> projectsCarriedout = new HashSet<Project>();
	private final Set<Worker> assignedWorkers = new HashSet<Worker>();
	private final Set<Worker> availableWorkers = new HashSet<Worker>();

	public Company(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Worker> getAvailableWorkers() {
		return availableWorkers;
	}

	public Set<Worker> getAssignedWorkers() {
		return assignedWorkers;
	}

	public Set<Worker> getUnassignedWorkers() {
		final Set<Worker> unassignedWorkers = availableWorkers;
		unassignedWorkers.removeAll(assignedWorkers);
		return unassignedWorkers;
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
		if (!(obj instanceof Company)) {
			return false;
		}
		final Company that = (Company) obj;
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
		return name + ":" + availableWorkers.size() + ":" + projectsCarriedout.size() ;
	}


	public void addToAvailableWorkerPool(final Worker worker) {
		if(!availableWorkers.contains(worker)) {
			availableWorkers.add(worker);
		} else {
			//"DEBUG :: Worker already preset"
		}

	}

	public void assign(final Worker worker, final Project project) {
		if( !availableWorkers.contains(worker) || project.getWorkers().contains(worker)) {
			//"DEBUG :: Cannot assign worker"
			return;
		}

		if(project.getStatus().equals(ProjectStatus.ACTIVE) || project.getStatus().equals(ProjectStatus.FINISHED) ) {
			//"DEBUG :: Cannot assign worker Project status invalid"
			return;
		}

		if(worker.willOverload(project)) {
			//"DEBUG :: Cannot add worker will be overloaded"
			return;
		}

		if(!project.isHelpful(worker)) {
			//"DEBUG :: Cannot add worker is not helpful for this project"
			return;
		}

		assignedWorkers.add(worker);
		assignWorkerToProject(worker, project);
	}

	public void unassign(final Worker worker, final Project project) {
		if(project.getWorkers().contains(worker)) {
			project.removeWorker(worker);
			worker.unassignProject(project);
			if(worker.getAssignedProjects().isEmpty()) {
				assignedWorkers.remove(worker);
			}
			if(project.missingQualifications().size() > 0 && project.getStatus().equals(ProjectStatus.ACTIVE)) {
				project.setStatus(ProjectStatus.SUSPENDED);
			}
		} else {
			//"DEBUG :: Worker not assigned to project"
		}
	}

	public void unassignAll(final Worker worker) {
		for(final Project project : projectsCarriedout) {
			if(project.getWorkers().contains(worker)) {
				project.removeWorker(worker);
				if(project.missingQualifications().size() > 0 && project.getStatus().equals(ProjectStatus.ACTIVE)) {
					project.setStatus(ProjectStatus.SUSPENDED);
				}
			}
		}
		worker.unassignAllProjects();
		assignedWorkers.remove(worker);
	}

	public void start(final Project project) {
		if(!(project.getStatus().equals(ProjectStatus.PLANNED) || project.getStatus().equals(ProjectStatus.SUSPENDED))) {
			//"DEBUG :: Project cannot be started, Invalid status to start"
			return;
		} 

		if(checkWorkerMeetsProjectRequirements(project)) {
			project.setStatus(ProjectStatus.ACTIVE);
		} else {
			//"DEBUG :: Project cannot be started, does not meet requiremnets to start"
		}

	}

	public void finish(final Project project) {
		if(project.getStatus().equals(ProjectStatus.ACTIVE)) {
			project.setStatus(ProjectStatus.FINISHED);
			for(final Worker worker : project.getWorkers()) {
				final Set <Project> assignedProjects = worker.getAssignedProjects();
				if(assignedProjects.size() == 1) {
					if(assignedProjects.iterator().next().getName().equals(project.getName())) {
						assignedWorkers.remove(worker);
					}
				}
				worker.unassignProject(project);
			}
			project.removeAllWorkers();
		}

	}

	public Project createProject(final String projectName, final Set<Qualification> qualificationSet, final ProjectSize size,
			final ProjectStatus status) {
		final Project newProject = createNewProjectAndUpdateStatus(projectName, size, qualificationSet);

		if(!projectsCarriedout.contains(newProject)) {
			projectsCarriedout.add(newProject);
		}
		return newProject;
	}


	private Project createNewProjectAndUpdateStatus(final String projectName, final ProjectSize projectSize, final Set<Qualification> qualificationSet) {
		final Project newProject = new Project(projectName, projectSize, ProjectStatus.PLANNED);

		for(final Qualification qualifcation : qualificationSet ) {
			newProject.addQualificationRequiremnet(qualifcation);
		}
		return newProject;
	}

	private boolean checkWorkerMeetsProjectRequirements(final Project project) {
		final Set<Qualification> projectRequiremnets =  project.getQualificationRequiremnet();
		final Set<Qualification> workerQualifications = new HashSet<>();
		for(final Worker worker : project.getWorkers()) {
			workerQualifications.addAll(worker.getQualifications());
		}

		for(final Qualification projectRequiremnet : projectRequiremnets) {
			if(!workerQualifications.contains(projectRequiremnet)) {
				return false;
			}
		}
		return true;
	}

	private void assignWorkerToProject(final Worker worker, final Project project) {
		worker.assignProject(project);
		project.addWorker(worker);
	}


}
