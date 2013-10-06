import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Properties;

public class Team30 implements ContestSubmission
{
	Random rnd_;
	ContestEvaluation evaluation_;
	Population population;
	int populationSize = 150;
	int numSurvivors = 30;

	public Team30() {
		rnd_ = new Random();
	}
	
	public void initPopulation(){
		
	}
	
	public void SubmissionTemplate()
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
	
	private Population getSurvivors(Population p, int numSurvivors){
		Population result = new Population();
		p.sort();
		for(int i = 0; i<numSurvivors; i++){
			result.add(p.get(i));
		}
		return result;
	}
	
	public void run()
	{
		// Run your algorithm here
		// Maak de populatie aan.
		population = new Population(populationSize);
		/**
		Micha: Hier crossover/mutate doen, evaluaten en daarna de survivors uitkiezen.
		
		**/
		
		population = getSurvivors(population, numSurvivors);
		// Getting data from evaluation problem (depends on the specific evaluation implementation)
		// E.g. getting a vector of numbers
		// Vector<Double> data = (Vector<Doulbe>)evaluation_.getData("trainingset1");

		// Evaluating your results
		// E.g. evaluating a series of true/false predictions
		// boolean pred[] = ...
		// Double score = (Double)evaluation_.evaluate(pred);
	}
}
