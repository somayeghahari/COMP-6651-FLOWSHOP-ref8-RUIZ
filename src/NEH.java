

import java.util.*;

public class NEH {
    /**
     * Performs NEH.
     * NOTE: NOT WORKING YET. PUTTING IT UP FOR EVERYONE's REFERENCE.
     *
     */
//    private List<Data> processingTimes;
    private List<Job> jobs;
    private List<Job> schedule;
    private int makespan;

    NEH(List<Job> jobs){
        this.jobs = jobs;
        this.schedule = new ArrayList<>();
        this.makespan = 0;
    }

    public void getFeasibleSolution(){
        Collections.sort(jobs, new JobComparator());
        Job p1 = jobs.get(0);
        Job p2 = jobs.get(1);
        assignRelativeOrder(p1, p2);

        // go through each data point and add to schedule
        for (int j = 2; j < jobs.size(); j++) {
            int temp = Integer.MAX_VALUE;
    		List<Job> minMakespanSchedule =new ArrayList<Job>(); ;


            // iterate through all positons
            for (int i = 0; i <= schedule.size(); i++) {
        		List<Job> tempSchedule = new ArrayList<Job>();

            	for (int k = 0; k < schedule.size(); k++) {
//            		 System.out.println("j"+j+",i"+i+",k"+k);
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
            	if(calculateMakespan(tempSchedule) < temp ) {
            		temp = calculateMakespan(tempSchedule);
//            		System.out.println("TTTTTTTTemp makespan:"+temp);
            		minMakespanSchedule = tempSchedule;
            	}
            }
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
    	System.out.println();
    	System.out.print("flow-shop Schedule: ");
    	for(Job d: this.schedule){
    		System.out.print(d.getJobID()+", ");	
    		}
    	System.out.println();
    	System.out.println("Flow-shop Makespan: " + makespan);
            
        
//        for(int i=2; i<this.jobs.size(); i++){
//            assignPosition(this.jobs.get(i));
//        }

    }

    private void assignRelativeOrder(Job p1, Job p2){
        /**
         * Find correct position relative to Data point 1 and 2 to minimize makespan.
         */
        // if sequence p1-p2
        int p1Sum = 0;
        int p2Sum = 0;
        for(int i=0; i<p1.getProcessingTimes().size(); i++){
            p1Sum += p1.getProcessingTimes().get(i);
            p2Sum = Math.max(p1Sum, p2Sum) + p2.getProcessingTimes().get(i);
//            System.out.println("p1sum: " + p1Sum + " | p2sum: " + p2Sum);
        }
        int makeSpan1 = p2Sum;
//        System.out.println("Makespan for sequence p1-p2 is: " + p2Sum);

        // if sequence p2-p1
        p1Sum = 0;
        p2Sum = 0;
        for(int i=0; i<p1.getProcessingTimes().size(); i++){
            p1Sum += p2.getProcessingTimes().get(i);
            p2Sum = Math.max(p1Sum, p2Sum) + p1.getProcessingTimes().get(i);
//            System.out.println("p1sum: " + p1Sum + " | p2sum: " + p2Sum);
        }
//        System.out.println("Makespan for sequence p2-p1 is: " + p2Sum);

        if(makeSpan1<=p2Sum){
            this.schedule.add(p1);
            this.schedule.add(p2);
            makespan = makeSpan1;
            
        } else {
            this.schedule.add(p2);
            this.schedule.add(p1);
            makespan = p2Sum;
        }
//        System.out.println("scheduled jobs:"+this.schedule.get(0).getJobID()+" "+this.schedule.get(1).getJobID());
    }

//    private void assignPosition(Job p){
//        /**
//         * Assign the data point at right point in the existing schedule without
//         * changing the order of exisiting and already allocated jobs.
//         */
//    	
//        int positionIndex = 0;
//        int correctIndex = 0;
//        int minMakeSpan = Integer.MAX_VALUE;
//        int currentMakeSpan = 0;
//        System.out.println("assignPosition:"+p.getJobID());
//        while(positionIndex<this.schedule.size()+1){
//            int[] makeSpans = new int[this.schedule.size()];
//            for(int i=0; i<p.getProcessingTimes().size();i++){
//                boolean checkForPI = true;
//                for(int j=0; j<this.schedule.size(); j++){
//                    //get processing time
//                    int processingTime;
//                    if(j==positionIndex && checkForPI){
//                        processingTime = p.getProcessingTimes().get(i);
//                        System.out.println("111");
//                    } else {
//                        processingTime = this.schedule.get(j).getProcessingTimes().get(i);
//                        System.out.println("222");
//                    }
//
//                    //add for first element
//                    if(j==0 && (j != positionIndex || checkForPI)){
//                        makeSpans[j] += processingTime;
//                    } else {
//                        int max = 0;
//                        for(int k=0; k<=j;k++){
//                            if(makeSpans[k]>max)
//                                max = makeSpans[k];
//                        }
//                        System.out.println("333 max"+max);
//                        if(j+1>=makeSpans.length && (j != positionIndex || checkForPI)){
//                            currentMakeSpan = max + processingTime;
//                        } else{
//                            if(!(j != positionIndex || checkForPI))
//                                makeSpans[j+1] = max + processingTime;
//                            else
//                                makeSpans[j] = max + processingTime;
//                        }
//                    }
//
//                    if(j==positionIndex && checkForPI){
//                        j--;
//                        checkForPI = false;
//                    }
//                }
//                System.out.println("Result");
//                for(int f=0;f<makeSpans.length;f++){
//                    System.out.println(makeSpans[f]);
//                }
//                System.out.println(currentMakeSpan + "\n");
//            }
//            if(currentMakeSpan<minMakeSpan){
//                minMakeSpan = currentMakeSpan;
//                correctIndex = positionIndex;
//            }
//            positionIndex++;
//        }
//
//        this.schedule.add(correctIndex, p);
//    }
    
    public int calculateMakespan(List<Job> jobs) {
        int totalMakespan = 0;

        ArrayList<int[]> myJobSumList = new ArrayList<int[]>();
        // create a job1-array with the summings
        myJobSumList.add(new int[jobs.get(0).getSizePT()]);

        myJobSumList.get(0)[0] = jobs.get(0).getProcessingTimes().get(0);



        for (int i = 1; i < jobs.get(0).getSizePT(); i++) {
            myJobSumList.get(0)[i] = myJobSumList.get(0)[i - 1] + jobs.get(0).getProcessingTimes().get(i);
        }

        // end of job1-array creating

        // add all other "empty" job-arrays to the list
        for (int i = 1; i < jobs.size(); i++) {
          myJobSumList.add(new int[jobs.get(i).getSizePT()]);

        }

        // fill the first row (from left to right summing)
        for (int i = 1; i < myJobSumList.size(); i++) {
            myJobSumList.get(i)[0] = myJobSumList.get(i - 1)[0] + jobs.get(i).getProcessingTimes().get(0);
        }

        // go through all jobs each
        for (int i = 1; i < jobs.size(); i++) {
          for (int j = 1; j < myJobSumList.get(i).length; j++) {
            // is the first time of the first job larger, chose it ...
            if (myJobSumList.get(i - 1)[j] > myJobSumList.get(i)[j - 1]) {
                myJobSumList.get(i)[j] = myJobSumList.get(i - 1)[j] + jobs.get(i).getProcessingTimes().get(j);

            }

            // is the time of the second job larger, chose it ...
            else {
                myJobSumList.get(i)[j] = myJobSumList.get(i)[j - 1] + jobs.get(i).getProcessingTimes().get(j);

            }
          }
        }

        totalMakespan = myJobSumList
            .get(myJobSumList.size() - 1)[myJobSumList.get(myJobSumList.size() - 1).length - 1];

        return totalMakespan;
      }
}
