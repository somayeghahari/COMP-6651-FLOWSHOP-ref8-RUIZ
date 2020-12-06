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
		List<Job> pi = neh.getSchedule();
		
		LocalSearch ls = new LocalSearch();
		pi = ls.IterativeImprovementInsert(pi);
		List<Job> piB = LocalSearch.CopyList(pi);
		
		// This value must be determined by the execution time
		int terminationCriteria = 1000;
		
		while (terminationCriteria > 1)
		{
			Destruction destruction = new Destruction(LocalSearch.CopyList(pi));
			destruction.destructSolution();
			
			System.out.println();
			System.out.println("Phase 2:");
			
			Construction construction = new Construction(destruction.getRemovedJobs(), destruction.getRemainingJobs());
			construction.constructSolution();
			
			System.out.println();
			System.out.println("Phase 3:");
			
			List<Job> piPrime = construction.getNewSchedule();
			piPrime = ls.IterativeImprovementInsert(piPrime);
			
			ls.CheckAcceptanceCriteria(pi, piPrime, piB);
			
			terminationCriteria--;
		}
		  
		return piB;
	}
	
}
