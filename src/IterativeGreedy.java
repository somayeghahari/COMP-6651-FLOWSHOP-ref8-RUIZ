import java.util.List;

public class IterativeGreedy {
	
	private List<Job> input;
	
	public IterativeGreedy (List<Job> jobs)
	{
		input = jobs;
	}
	
	public List<Job> CalculateSolution()
	{
		System.out.println();
		System.out.println("Phase 1:");
		// phase1: find an initial solution based on NEH heuristic
		NEH neh = new NEH(input);
		neh.getInitialSolution();
		List<Job> partialSolution = neh.getSchedule();
		
		Destruction destruction = new Destruction(partialSolution);
		destruction.destructSolution();
		
		Construction construction = new Construction(destruction.getRemovedJobs(), destruction.getRemainingJobs());
		construction.constructSolution();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("New Schedule: ");
		System.out.println(construction.getNewSchedule());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
	//	LocalSearch ls = new LocalSearch();
	//	partialSolution = ls.IterativeImprovementInsert(partialSolution);
		List<Job> solution = partialSolution;
		  
		// while (termination criteria not satisfied)
			//phase2: Destruction 
			// phase 3: local search
			// phase 4: acceptance criterion
		  
		return solution;
	}
	
}
