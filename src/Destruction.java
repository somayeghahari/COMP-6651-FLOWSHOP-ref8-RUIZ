import java.util.*;
/**
 * Step 2 of Flowshop Scheduling Problem
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
    	
    	for(int i=0; i< randomJobsCount; i++) {
    		
    		int randJob = rand.nextInt(schedule.size());
    		
    		while (indexRemovedList.contains(randJob))
    			randJob = rand.nextInt(schedule.size());
    		
    		indexRemovedList.add(randJob); 
    		System.out.println("rand: " + randJob);
    		this.removedJobs.add(schedule.get(randJob));		
    	} 
    	
    	remainingJobs = schedule;
    	Collections.sort(indexRemovedList,Collections.reverseOrder());
    	
    	for(Integer itr:indexRemovedList)  {
    		remainingJobs.remove(remainingJobs.get(itr));
    	}

    	
    	System.out.println("********************* Destruction *****************");
    	System.out.println(schedule);
    	System.out.println("Size:" + schedule.size());
    	System.out.println("Random:" + randomJobsCount);
    	System.out.println("removedJobs:");
    	System.out.println(removedJobs);
    	System.out.println("indexRemovedList:");
    	System.out.println(indexRemovedList);
    	System.out.println("remainingJobs:");
    	System.out.println(remainingJobs); 
    	System.out.println("***************************************************");
    }
    
}
