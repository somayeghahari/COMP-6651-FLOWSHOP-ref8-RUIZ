import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Flowshop {
	  public static void main (String[] args)
	  {
		  String path = "input/2/Ta004_2.txt";
		  ArrayList<Job> jobs = readFile(path);	  
		
		  if (jobs.size() != 0)
		  {
			  // Call the methods to process the jobs
			  
			  // this is a test to see if it is working, we can erase that
			  for (int i = 0; i < jobs.size(); i++)
				  System.out.println(jobs.get(i).getJobID()+ " "+ jobs.get(i).getProcessingTimes());
			
			  NEH neh = new NEH(jobs);
			  neh.getFeasibleSolution();
		  }
	  }
	  
	  /**
	   * Method to parse the input file given by the path parameter
	   * @param path - the path of the file to be opened
	   * @return the list with the cost for each job j
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
