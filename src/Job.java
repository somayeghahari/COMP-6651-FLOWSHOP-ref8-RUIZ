import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * a class to hold the data related to job
 * @author somayeghahari
 *
 */
public class Job {
    /**
     * Class to hold job details.
     * Stores three items as follows:
     *  1) Job id
     *  2) List of processing times on each machine
     *  3) Total time taken by the job on all machines
     */
	/**
	 * Job id
	 */
    private int jobID;
    /**
     * List of processing times on each machine
     */
	private List<Integer> processingTimes;
	/**
	 * Total time taken by the job on all machines
	 */
    private int totalTime;
    /**
     * number of processing times
     */
    private int sizePT;

    /**
     * Job constructor
     * @param jobID
     * @param processingTimes
     */
	Job(int jobID, ArrayList<Integer> processingTimes){
        this.jobID = jobID;
        this.processingTimes = processingTimes;
        this.totalTime = 0;
        sizePT=0;
        for(Integer i: processingTimes){
            totalTime += i;
            sizePT++;
        }
    }

	/**
	 * function gets the job id 
	 * @return job id
	 */
    public int getJobID() {
        return jobID;
    }
    
    /**
     * function gets the processing times
     * @return the processing times
     */
    public List<Integer> getProcessingTimes() {
        return processingTimes;
    }
    
    /**
     * function sets the processing times
     * @param processingTimes
     */
    public void setProcessingTimes(List<Integer> processingTimes) {
		this.processingTimes = processingTimes;
		for(Integer i: processingTimes){
            totalTime += i;
            sizePT++;
        }
	}

    /**
     * function gets the total time on all machines
     * @return the total time on all machines
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     * function gets number of processing times
     * @return number of processing times
     */
    public int getSizePT() {
		return sizePT;
	}
}

class JobComparator implements Comparator<Job>{
    
	/**
     * Comparator to compare total time taken on all machines.
     * Essential for first step of NEH.
     * @param o1 Data object 1
     * @param o2 Data object 2
     * @return Number to result in descending order of the two objects
     */
    @Override
    public int compare(Job o1, Job o2) {
        if(o1.getTotalTime()==o2.getTotalTime()){
          return 0;
        } else if(o1.getTotalTime()<o2.getTotalTime()){
            return 1;
        } else{
            return -1;
        }
    }
}
