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
    	NEH neh = new NEH(schedule);
    	schedule = partialSolution;
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
    				schedule = partialSchedule;
    				improve = true;
    			}
    		}
    		
    	}
    	makespan = neh.calculateMakespan(schedule);
    	return schedule;
    }
    
    public List<Job> BestPermutation(List<Job> jobs, int id)
    {
    	return jobs;
    }
}
