

import java.util.*;
/**
 * NEH Class performs NEH heuristic
 * @author somayeghahari
 *
 */
public class NEH {

	/**
	 * list of jobs
	 */
    private List<Job> jobs;
    /**
     * list of scheduled jobs
     */
    private List<Job> schedule;
    /**
     * makespan of the scheduled jobs
     */
    private int makespan;

    /**
     * class constructor
     * @param jobs
     */
    NEH(List<Job> jobs){
        this.jobs = jobs;
        this.schedule = new ArrayList<>();
        this.makespan = 0;
    }

    /**
     * function gets initial solution
     * 	it sorts the jobs in descending order 
     * 	it sequences all the jobs according to NEH heuristic
     */
    public void getInitialSolution(){
        Collections.sort(jobs, new JobComparator());
        
        // step 2 of NEH heuristic
        Job p1 = jobs.get(0);
        Job p2 = jobs.get(1);
        assignTwoJobsOrder(p1, p2);

        // step3 of NEH heuristic
        for (int j = 2; j < jobs.size(); j++) {
            int temp = Integer.MAX_VALUE;
    		List<Job> minMakespanSchedule =new ArrayList<Job>();


            // each new job iterates through all positions
            for (int i = 0; i <= schedule.size(); i++) {
        		List<Job> tempSchedule = new ArrayList<Job>();

            	for (int k = 0; k < schedule.size(); k++) {
            		 if(k!=i) {
            			tempSchedule.add(schedule.get(k));
            		}
            		else {
            			tempSchedule.add(jobs.get(j));
            			tempSchedule.add(schedule.get(k));
            		}
            	}
            	if(tempSchedule.size()==schedule.size())
        			tempSchedule.add(jobs.get(j));

//            	System.out.println("TTTTTTTTemp Schedule:");
//                for(Job d: tempSchedule){
//                    System.out.print(d.getJobID()+",");
//                }
//                System.out.println();
            	// the makespan of sequence is calculated
            	if(calculateMakespan(tempSchedule) < temp ) {
            		temp = calculateMakespan(tempSchedule);
            		minMakespanSchedule = tempSchedule;
            	}
            }
            // the sequence with lowest makespan is set as scheduled 
            if(!minMakespanSchedule.isEmpty()) {
            	schedule = minMakespanSchedule;
            	makespan = temp;
//            	System.out.println("flow-shop Schedule:");
//                for(Job d: this.schedule){
//                    System.out.print(d.getJobID()+",");
//                }
//                System.out.println();
//            	System.out.println("Flow-shop Makespan: " + makespan);
            }
        }
//    	System.out.print("   Flow-shop Schedule: ");
//    	for(Job d: this.schedule){
//    		System.out.print(d.getJobID()+", ");	
//    		}
//    	System.out.println();
//    	System.out.println("   Flow-shop Makespan: " + makespan);
            
    }

    /**
     * function to assign two jobs to schedule
     * @param p1 first job
     * @param p2 second job
     */
    private void assignTwoJobsOrder(Job p1, Job p2){
        /**
         * Find correct position relative to job 1 and 2 to minimize makespan.
         */
        // if sequence p1-p2
        int p1Sum = 0;
        int p2Sum = 0;
        for(int i=0; i<p1.getProcessingTimes().size(); i++){
            p1Sum += p1.getProcessingTimes().get(i);
            p2Sum = Math.max(p1Sum, p2Sum) + p2.getProcessingTimes().get(i);
        }
        int makeSpan1 = p2Sum;

        // if sequence p2-p1
        p1Sum = 0;
        p2Sum = 0;
        for(int i=0; i<p1.getProcessingTimes().size(); i++){
            p1Sum += p2.getProcessingTimes().get(i);
            p2Sum = Math.max(p1Sum, p2Sum) + p1.getProcessingTimes().get(i);
        }

        if(makeSpan1<=p2Sum){
            this.schedule.add(p1);
            this.schedule.add(p2);
            makespan = makeSpan1;
            
        } else {
            this.schedule.add(p2);
            this.schedule.add(p1);
            makespan = p2Sum;
        }
    }
    
    /**
     * function calculates makespan of list of jobs in sequence
     * @param jobs list of jobs
     * @return makespan of list of jobs 
     */
    public static int calculateMakespan(List<Job> jobs) {
        int totalMakespan = 0;

        ArrayList<int[]> sumLst = new ArrayList<int[]>();

        sumLst.add(new int[jobs.get(0).getSizePT()]);
        sumLst.get(0)[0] = jobs.get(0).getProcessingTimes().get(0);
        for (int i = 1; i < jobs.get(0).getSizePT(); i++) {
        	sumLst.get(0)[i] = sumLst.get(0)[i - 1] + jobs.get(0).getProcessingTimes().get(i);
        }


        for (int i = 1; i < jobs.size(); i++) {
        	sumLst.add(new int[jobs.get(i).getSizePT()]);

        }
        for (int i = 1; i < sumLst.size(); i++) {
        	sumLst.get(i)[0] = sumLst.get(i - 1)[0] + jobs.get(i).getProcessingTimes().get(0);
        }

        // go through all jobs
        for (int i = 1; i < jobs.size(); i++) {
          for (int j = 1; j < sumLst.get(i).length; j++) {
            // find start point of next job
            if (sumLst.get(i - 1)[j] > sumLst.get(i)[j - 1]) {
            	sumLst.get(i)[j] = sumLst.get(i - 1)[j] + jobs.get(i).getProcessingTimes().get(j);

            }
            else {
            	sumLst.get(i)[j] = sumLst.get(i)[j - 1] + jobs.get(i).getProcessingTimes().get(j);

            }
          }
        }

        totalMakespan = sumLst.get(sumLst.size() - 1)[sumLst.get(sumLst.size() - 1).length - 1];

        return totalMakespan;
      }
    
    public List<Job> getSchedule ()
    {
    	return schedule;
    }
}
