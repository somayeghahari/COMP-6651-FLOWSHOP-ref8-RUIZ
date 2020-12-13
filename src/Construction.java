import java.util.*;
/**
 * Phase 2 of Flowshop Scheduling Problem
 * Construction Class constructs job schedule again from
 * the two lists obtained from Destruction phase.
 * @author Mehrnoosh Amjadi
 *
 */

public class Construction {
	
    /**
     * list of removed jobs
     */
    private List<Job> removedJobs;
    
    /**
     * list of remaining jobs
     */
    private List<Job> remainingJobs;
    
    /**
     * makespan of the scheduled jobs
     */
    private int makespan;
    
    /**
     * class constructor
     * @param removedJobs list of removed jobs
     * @param remainingJobs list of remaining jobs
     */
    Construction (List<Job> removedJobs, List<Job> remainingJobs){
    	
        this.removedJobs = new ArrayList<Job>();
        this.remainingJobs = new ArrayList<Job>();
        this.removedJobs = removedJobs;
        this.remainingJobs = remainingJobs;
        
    }
    
    public void constructSolution() {
    	
        for (int j = 0; j < this.removedJobs.size(); j++) {
            int temp = Integer.MAX_VALUE;
    		List<Job> minMakespanSchedule = new ArrayList<Job>();


            // each new job iterates through all positions
            for (int i = 0; i <= this.remainingJobs.size(); i++) {
        		List<Job> tempSchedule = new ArrayList<Job>();

            	for (int k = 0; k < this.remainingJobs.size(); k++) {
            		 if(k!=i) {
            			tempSchedule.add(this.remainingJobs.get(k));
            		}
            		else {
            			tempSchedule.add(removedJobs.get(j));
            			tempSchedule.add(remainingJobs.get(k));
            		}
            	}
            	if(tempSchedule.size()==remainingJobs.size())
        			tempSchedule.add(removedJobs.get(j));

            	// the makespan of sequence is calculated
            	int tempScheduleMakespan = NEH.calculateMakespan(tempSchedule);
            	if(tempScheduleMakespan < temp ) {
            		temp =tempScheduleMakespan;
            		minMakespanSchedule = tempSchedule;
            	}
            }
            
            // the sequence with lowest makespan is set as scheduled 
            if(!minMakespanSchedule.isEmpty()) {
            	remainingJobs = minMakespanSchedule;
            	makespan = temp;

            }
            
        }
        
//    	System.out.print("   Flow-shop New Schedule: ");
//    	for(Job d: this.remainingJobs){
//    		System.out.print(d.getJobID()+", ");	
//    		}
//    	System.out.println();
//    	System.out.println("   Flow-shop New Makespan: " + makespan);
    	
    }
    
    public List<Job> getNewSchedule ()
    {
    	return remainingJobs;
    }
    
    public int getMakeSpan ()
    {
    	return makespan;
    }
}
