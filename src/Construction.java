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
     * class constructor
     * @param schedule
     */
    Construction (List<Job> removedJobs, List<Job> remainingJobs){
        this.removedJobs = new ArrayList<Job>();
        this.remainingJobs = new ArrayList<Job>();
        this.removedJobs = removedJobs;
        this.remainingJobs = remainingJobs;
    }
}
