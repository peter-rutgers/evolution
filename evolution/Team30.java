import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class Team30 implements ContestSubmission
{
	Random rnd_;
	ContestEvaluation evaluation_;
	ArrayList<Individual> population = new ArrayList<Individual>;
	int populationSize = 150;

	public void initPopulation(){
		System.out.printf("Creating a new world\n");
		for(int i = 0; i < populationSize; i++){
			//individual genereert de random data
			population.add(new Individual());
			population.get(i).initialize();
		}
		System.out.printf("The world is ready for bucking\n");
	}
	
	public SubmissionTemplate()
	{
		rnd_ = new Random();
	}
	
	public void setSeed(long seed)
	{
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation)
	{
		// Set evaluation problem used in the run
		evaluation_ = evaluation;
		
		// Get evaluation properties
		Properties props = evaluation.getProperties();
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
	}
	
	public void run()
	{
		// Run your algorithm here
		// Maak de populatie aan.
		initPopulation();

		// Getting data from evaluation problem (depends on the specific evaluation implementation)
		// E.g. getting a vector of numbers
		// Vector<Double> data = (Vector<Doulbe>)evaluation_.getData("trainingset1");

		// Evaluating your results
		// E.g. evaluating a series of true/false predictions
		// boolean pred[] = ...
		// Double score = (Double)evaluation_.evaluate(pred);
	}
}
