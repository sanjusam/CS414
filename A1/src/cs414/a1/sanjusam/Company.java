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
		/*
		 * Returns a String that includes the company name, colon, number of available workers, colon,
		 *  and number of projects carried out. For example, a company called ABC that has 20 available 
		 *  workers and 10 projects will result in the string ABC:20:10.
		 */
		return name + ":" + availableWorkers.size() + ":" + projectsCarriedout.size() ;
	}


	public void addToAvailableWorkerPool(final Worker worker) {
		//TODO ::
		//A worker "w" who is currently not in the pool of available workers gets added to the 
		//pool of available workers.
		if(!availableWorkers.contains(worker)) {
			availableWorkers.add(worker);
		}

	}

	public void assign(final Worker worker, final Project project) {
		//TODO ::
		/*Only workers from the pool of available workers can be assigned as long as they are not
		 *  already assiged to the same project. The project must not be in the ACTIVE or FINISHED state. 
		 *  The worker should not get overloaded by adding to this project. The worker can be added only 
		 *  if the worker is helpful to the project. If the conditions are satisfied, (1) the assigned 
		 *  worker is added to the pool of assiged workers of the company unless they were already 
		 *  present in that pool, and (2) the worker is also added to the project. This results in at 
		 *  least one previously unmet required qualification of the project being met.
		 *  Note that the same worker can be in both the available pool and assigned pool of wokers 
		 *  at the same time. However, the worker cannot be in the assigned pool if they are not in the available pool. 
		 *  Think of the available pool as the pool of employed workers.
		 */

		if( !availableWorkers.contains(worker) || project.getWorkers().contains(worker)) {
			//Cannot Assign worker
			return;
		}

		if(project.getStatus().equals(ProjectStatus.ACTIVE) || project.getStatus().equals(ProjectStatus.FINISHED) ) {
			return;
		}

		if(worker.willOverload(project)) {
			return;
		}

		if(!project.isHelpful(worker)) {
			return;
		}

		assignedWorkers.add(worker);
		assignWorkerToProject(worker, project);
	}

	public void unassign(final Worker worker, final Project project) {
		//TODO ::
		/*
		 * The worker must have been assiged to the project to be unassigned. If this was the only 
		 * project for the worker, then delete this worker from the pool of assigned workers of 
		 * the company. If the qualification requirements of an ACTIVE project are no longer met, 
		 * that project is marked SUSPENDED. A PLANNED OR SUSPENDED project remains in that state.
		 */

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
			//Worker not assigned!!!
		}
	}

	public void unassignAll(final Worker worker) {
		//TODO ::
		/*Remove the worker from all the projects that were assigned to the worker. Also remove the 
		 * worker from the pool of assigned workers of the company. Change the state of the affected 
		 * projects as needed.
		 * 
		 */

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
		//TODO ::
		/*A PLANNED or SUSPENDED project may be started as long as the project's qualification 
		 * requirements are all satisfied. This project is now in ACTIVE status. Otherwise, 
		 * the project remains PLANNED or SUSPENDED (i.e., as it was before the method was called).
		 * 
		 */
		if(!(project.getStatus().equals(ProjectStatus.PLANNED) || project.getStatus().equals(ProjectStatus.SUSPENDED))) {
			return;
		} else {
			//Project not ready for start
		}

		if(checkWorkerMeetsProjectRequirements(project)) {
			project.setStatus(ProjectStatus.ACTIVE);
		} else {
			//Project not ready for start
		}

	}

	public void finish(final Project project) {
		//TOOD ::
		/*An ACTIVE project is marked FINISHED. The project no longer has any assigned workers, 
		 * so if a worker was only involved
		 *  in this project, remove them from the pool of assigned workers of the company. 
		 *  A SUSPENDED or PLANNED project remains as it was.
		 * 
		 */
		if(project.getStatus().equals(ProjectStatus.ACTIVE)) {
			project.setStatus(ProjectStatus.FINISHED);
			for(final Worker worker : project.getWorkers()) {
				final Set <Project> assignedProjects = worker.getAssignedProjects();
				if(assignedProjects.size() == 1) {
					if(assignedProjects.iterator().next().getName().equals(project.getName())) {
						assignedWorkers.add(worker);
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
