import java.util.*;
/**
 * Phase 2 of Flowshop Scheduling Problem
 * Destruction Class destructs partial solution
 * @author Mehrnoosh Amjadi
 *
 */

public class Destruction {
	
    /**
     * list of scheduled jobs
     */
    private List<Job> schedule;
    
    /**
     * list of removed jobs
     */
    private List<Job> removedJobs;
    
    /**
     * list of remaining jobs
     */
    private List<Job> remainingJobs;
    
    /**
     * class constructor
     * @param schedule
     */
    Destruction (List<Job> schedule){
        this.schedule = schedule;
        this.removedJobs = new ArrayList<Job>();
        this.remainingJobs = new ArrayList<Job>();
        this.remainingJobs = schedule;
    }
    
    /**
     * This function chooses d jobs randomly 
     * and remove them from the schedule list.
     * Then make two subsequences: one contains removed jobs 
     * and another contains the rest jobs.  
     */
    public void destructSolution(){

    	Random rand = new Random(); 
    	int randomJobsCount = rand.nextInt(schedule.size());
    	ArrayList<Integer> indexRemovedList = new ArrayList<Integer>();
    	
    	if (randomJobsCount == 0)
    		randomJobsCount++;
    	
    	// choose random jobs and remove them.
    	for(int i=0; i< randomJobsCount; i++) {
    		
    		int randJob = rand.nextInt(schedule.size());
    		
    		// check if the generated random number is repetitive or not
    		while (indexRemovedList.contains(randJob))
    			randJob = rand.nextInt(schedule.size());
    		
    		// keep track of removed jobs indexes.
    		indexRemovedList.add(randJob); 
    		
    		// add random jobs to the list of removed.
    		this.removedJobs.add(schedule.get(randJob));		
    	} 
    	
    	Collections.sort(indexRemovedList,Collections.reverseOrder());
    	
    	// add remaining jobs to the remainingJobs List.
    	for(Integer itr:indexRemovedList)  {
    		remainingJobs.remove(remainingJobs.get(itr));
    	}

    	
    /*	System.out.println("********************* Destruction *****************");
    	System.out.println("Random:" + randomJobsCount);
    	System.out.println("removedJobs:");
    	System.out.println(this.removedJobs);
    	System.out.println("indexRemovedList:");
    	System.out.println(indexRemovedList);
    	System.out.println("remainingJobs:");
    	System.out.println(this.remainingJobs); 
    	System.out.println("***************************************************"); */
    }
    
    public List<Job> getRemovedJobs()
    {
    	return removedJobs;
    }
    
    public List<Job> getRemainingJobs ()
    {
    	return remainingJobs;
    }
    
}
