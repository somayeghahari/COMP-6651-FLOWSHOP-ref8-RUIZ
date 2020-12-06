import java.util.ArrayList;
import java.util.List;

public class LocalSearch {
    /**
     * list of scheduled jobs
     */
    private List<Job> schedule;
    /**
     * makespan of the scheduled jobs
     */
    private int makespan;
    
    public LocalSearch ()
    {
    	schedule = null;
    	makespan = 0;
    }

    /**
     * This method can be applied to both local and total search, to approximate 
     * more from the optimal result
     * @param partialSolution list with the partial solution before this step
     * @return the improved partial solution
     */
    public List<Job> IterativeImprovementInsert (List<Job> partialSolution)
    {
    	schedule = partialSolution;
    	NEH neh = new NEH(schedule);
    	
    	boolean improve = true;
    	while (improve)
    	{
    		// checker to see if the selected variable was already selected
    		boolean[] checked = new boolean[schedule.size()];
    		for (int i = 0; i < checked.length; i++)
    			checked[i] = false;
    		improve = false;
    		for (int i = 0; i < checked.length; i++)
    		{
    			int id = (int) (Math.random() * checked.length);
    			while (checked[id])
    				id = (int) (Math.random() * checked.length);
    			checked[id] = true;
    			
    			List<Job> partialSchedule = BestPermutation(schedule, id);
    			
    			if (neh.calculateMakespan(partialSchedule) < neh.calculateMakespan(schedule))
    			{
    				System.out.println("FOUND DA SHITZ");
    				schedule = CopyList(partialSchedule);
    				improve = true;
    			}
    		}
    		
    	}
    	makespan = neh.calculateMakespan(schedule);
    	return schedule;
    }
    
    /**
     * Method to calculate all possible permutations,
     * changing the position of the job with the selected id
     * @param jobs list of jobs
     * @param id id of the job to do the permutation
     * @return the permutation with lowest makespan
     */
    public List<Job> BestPermutation(List<Job> jobs, int id)
    {
    	List<Job> permutation = CopyList(jobs);
    	Job job = permutation.remove(id);
    	permutation.add(0, job);
    	
    	NEH neh = new NEH(permutation);
    	int makespan = neh.calculateMakespan(permutation);
    	int opt = 0; // store the optimal position for the randomly selected job j
    	
    	for (int i = 1; i < permutation.size(); i++)
    	{
    		permutation.remove(i-1);
        	permutation.add(i, job);
        	int partialMakespan = neh.calculateMakespan(permutation);
        	
        	
        	if (partialMakespan < makespan)
        	{
        		opt = i;
        		makespan = partialMakespan;
        	}
    	}

    	permutation.remove(permutation.size()-1);
    	permutation.add(opt, job);
    	
    	return permutation;
    }
    
    /**
     * Method to check the acceptance criteria,
     * and  attribute the correct values to the temporary solutions
     * @param pi original list
     * @param piPrime list derived from the original
     * @param piB list created by the destruction operation
     */
    public void CheckAcceptanceCriteria(List<Job> pi, List<Job> piPrime, List<Job> piB)
    {
    	NEH neh = new NEH(pi);
    	int cpi = neh.calculateMakespan(pi);
    	int cpiPrime = neh.calculateMakespan(piPrime);
    	int cpiB = neh.calculateMakespan(piB);
    	
    	// TODO: fix this value and test this method (I'll do it tomorrow probably)
    	// temperature = T * sum(1, m, (sum(1, n, p_ij)))/(n * m * 10)
    	float temperature = 1;
    	
    	if (cpiPrime < cpi)
    	{
    		pi = CopyList(piPrime);
    		cpi = cpiPrime;
    		if (cpi < cpiB)
    		{
    			piB = CopyList(pi);
    			cpiB = cpi;
    		}
    	}
    	else if (Math.random() <= Math.exp(-(cpiPrime-cpi)/temperature))
    	{
    		pi = CopyList(piPrime);
    	}
    }
    
    /**
     * Method to copy the list into a new one (different pointers needed)
     * @param copy the list you want to copy
     * @return the list copied to another pointer
     */
    public List<Job> CopyList (List<Job> copy)
    {
    	List<Job> result = new ArrayList<Job>();
    	for (int i = 0; i < copy.size(); i++)
    		result.add(copy.get(i));
    	
    	return result;
    }
}
