import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the program
 *
 */
public class Flowshop {
	/**
	 * This method handles main loop of the program 
	 * It parses the input file
	 * It executes the greedy algorithm and prints the results
	 * @param args
	 * @throws IOException 
	 */
	  public static void main (String[] args) throws IOException
	  {
		  int fileNum = 5;
		  System.out.println("************************ Start of program ************************\n\n");
		  System.out.println("Running Iterative Greedy algorithm on jobs read from " + fileNum + " files\n\n");
		  long totalStart = System.currentTimeMillis();

		  BufferedWriter bw = new BufferedWriter(new FileWriter("output-IG.txt"));

		  for (int i = 0; i <= fileNum; i++)
		  {
			  String path = "input/2/Ta"+ String.format("%03d", i) +"_2.txt";
			  ArrayList<Job> jobs = readFile(path);	  
			
			  if (jobs.size() != 0)
			  {
				  long s = System.currentTimeMillis();

				  IterativeGreedy ig = new IterativeGreedy(jobs);
				  List<Job> solution = ig.CalculateSolution();
				  long duration = System.currentTimeMillis()-s;

				  String out = "\nExecuting test " + String.format("%03d", i) + " of "+fileNum+":\n"+
				  "   Flow-shop Schedule: ";
				  for (int j = 0; j < solution.size(); j++) {
					  out = out + solution.get(j).getJobID() + " ";
//					  System.out.print(solution.get(j).getJobID() + " ");
				  }
				  s = System.currentTimeMillis();
				  out = out + "\n   Flow-shop Makespan: " + NEH.calculateMakespan(solution);
				  duration = duration+  (System.currentTimeMillis()-s);
				  out = out + "\n   Time Duration: "+ duration;
				  bw.write(out);
				  bw.flush();
			  }  
		  }
		  long totalDuration =(System.currentTimeMillis()-totalStart);

		  System.out.println("Time of running Iterative Greedy algorithm:"+totalDuration+"\n\n");
		  bw.write("\n\nTime of running Iterative Greedy algorithm:"+totalDuration);

		  bw.close();
		  System.out.println("************************ End of program ************************");

	  }
	  
	  /**
	   * Method to parse the input file given by the path parameter
	   * @param path - the path of the file to be opened
	   * @return the list of jobs, each job has a list of processing time
	   */
	  public static ArrayList<Job> readFile(String path)
	  {
		  ArrayList<Job> jobs = new ArrayList<Job>();
		  try {
				BufferedReader reader = new BufferedReader(new FileReader(path));
				String line;
				String[] jobParameters;
				Integer id = 0;
				while ((line = reader.readLine()) != null)
				{
					jobParameters = line.split("	");
					ArrayList<Integer> processingTimes = new ArrayList<Integer>() ;
					if (jobParameters.length > 2)
					{
						for (int i = 2; i < jobParameters.length; i+=2)
							processingTimes.add( Integer.parseInt(jobParameters[i]));
						Job j = new Job(id , processingTimes);
						id ++;
						jobs.add(j);
					}					
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		  return jobs;
	  }
}
