import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class Team30 implements ContestSubmission {
	Random rnd_;
	ContestEvaluation evaluation_;
	Population population;
	int populationSize = 150;
	int numSurvivors = 30;
	int iterations;

	public Team30() {
		rnd_ = new Random();
	}

	public void initPopulation() {

	}

	public void SubmissionTemplate() {
		rnd_ = new Random();
	}

	public void setSeed(long seed) {
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation) {
		// Set evaluation problem used in the run
		evaluation_ = evaluation;

		// Get evaluation properties
		Properties props = evaluation.getProperties();
		// Property keys depend on specific evaluation
		// E.g. double param =
		// Double.parseDouble(props.getProperty("property_name"));

		// Do sth with property values, e.g. specify relevant settings of your
		// algorithm

		iterations = (int)Math.floor(Integer.parseInt(props.get("Evaluations").toString()) / populationSize);
		props.get("Regular");
		props.get("Multimodal");
		props.get("Separable");
	}

	private void doEvaluations(Population p) {
		for (int i = 0; i < p.size(); i++) {
			Individual x = p.get(i);
			x.evaluationScore = (double) evaluation_.evaluate(x.values);
			//System.out.printf("Evaluated individual %.1f %.1f to %.12f\n", x.values[0], x.values[1], x.evaluationScore);
		}
		
	}

	private Population getSurvivors(Population p, int numSurvivors) {
		Population result = new Population();
		p.sort();
		for (int i = 0; i < numSurvivors; i++) {
			result.add(p.get(i));
		}
		//System.out.printf("Evaluation range = %.12f %.12f\n", p.get(0).evaluationScore, p.get(numSurvivors-1).evaluationScore);
		return result;
	}
	
	//linear ranking schemes, where value s should be 1.0<s<2.0
	//in this case s = 1.5
	//After ranking the individuals and assigning their chance, create a same wheel-like structure
	//and use the same algorithm described in RouletteWheel() to get the survivor.
	private Population rankingSelection(Population p, int numSurvivors){
		Population newP = new Population();
		double s = 1.5;
		double decision;
		p.sort();
		double[] ranking = new double[p.size()];
		for(int i = 0; i < p.size(); i++){
			ranking[i] = ((2-s)/p.size()) + (2*i*(s-1)/p.size()*(p.size()-1)); 
		}
		ranking = createWheel(ranking);
		for(int i = 0; i < numSurvivors; i++){
			decision = Math.random();
			for(int j = 0; j < p.size(); j++){
				//found the individual who comes after the one which is chosen
				if(ranking[j] > decision){
					newP.add(p.get(i-1));
				}
			}
		}
		return newP;
	}
	
	//map the population to a wheel (in this case line), 
	//normalize the populations fitness value. The chance of being selected is:
	//[own normalized fitness value]-[next normalized fitness value]
	//randomly spin the wheel (generate a random number), between 0 and 1.
	//
	private Population RouletteWheel(Population p, int numSurvivors){
		double[] wheel = createWheel(normalizeFitness(p));
		Population newP = new Population();
		double decision;
		for(int i = 0; i < numSurvivors; i++){
			decision = Math.random();
			for(int j = 0; j < p.size(); j++){
				//found the individual who comes after the one which is chosen
				if(wheel[j] > decision){
					newP.add(p.get(i-1));
				}
			}
		}
		return newP;
	}
	
	//create the list where, at the end, the final value is (close to) 1. 
	//doing this creates the list-structure used in the RouletteWheel function.
	private double[] createWheel(double[] d){
		double temp = 0;
		double[] wheel = new double[d.length];
		for(int i = 0; i < d.length; i++){
			wheel[i] = d[i]+temp;
			temp+=d[i];
		}
		return wheel;
	}
	
	//copy pasted from a website, which says it works.
	//if it doesn't work, Iĺl rewrite a new one.
	//source: http://geneticprogramming.us/Selection.html
	private Population FitnessProportionalSelection(Population p, int numSurvivors){
		Population newPop = new Population();
		double[] normalizedFitness;
		normalizedFitness = normalizeFitness(p);
		for(int i = 0; i < numSurvivors; i++){
			newPop.add(selectIndividual(p, normalizedFitness));
		}
		return newPop;
	}
	
	//normalize the fitness of each individual, so the sum is 1.
	//get the total fitness value of the population, divide each fitness by this.
	//this leads to the sum of the fitness to be 1.
	private double[] normalizeFitness(Population p){
		double sum = 0;
		double normalized[] = new double[p.size()];
		for(int i = 0; i < p.size(); i ++){
			sum+=p.get(i).evaluationScore;
		}
		for(int i = 0; i < p.size(); i++){
			normalized[i] = p.get(i).evaluationScore/sum;
		}
		return normalized;
	}

	private Individual selectIndividual(Population p,
			double[] normalizedFitness) {
		double decision = Math.random();
		double sum = 0;
		for (int i = 0; i < p.size(); i++) {
			sum += normalizedFitness[i];
			if (decision <= sum)
				return p.get(i);
		}
		// The following line should never be reached
		return null;
	}

	public void run() {
		// Run your algorithm here
		// Maak de populatie aan.
		population = new Population(populationSize);

		/**
		 * Micha: Hier crossover/mutate doen, evaluaten en daarna de survivors
		 * uitkiezen.
		 **/

		population = getSurvivors(population, numSurvivors);
		// Getting data from evaluation problem (depends on the specific
		// evaluation implementation)
		
		//setEvaluation(new Function3()); //tijdelijk, dit hoort opgegeven te worden via user input
		
		for (int i = 0; i < iterations; i++) {
			/**
			Micha: Hier crossover/mutate doen, evaluaten en daarna de survivors uitkiezen.
			
			**/
			doEvaluations(population);
			population = getSurvivors(population, numSurvivors);
			while (population.size() < populationSize) {
			// voorlopig een random parent selectie gezet om te kunnen runnen -Peter
				Individual xx = population.get(Individual.randomWithRange(0, numSurvivors - 1));
				Individual xy = population.get(Individual.randomWithRange(0, numSurvivors - 1));
				population.add(xx.createOffspring(xy));
			}
		}
		// Getting data from evaluation problem (depends on the specific evaluation implementation)
		// E.g. getting a vector of numbers
		// Vector<Double> data =
		// (Vector<Doulbe>)evaluation_.getData("trainingset1");

		// Evaluating your results
		// E.g. evaluating a series of true/false predictions
		// boolean pred[] = ...
		// Double score = (Double)evaluation_.evaluate(pred);
		//System.out.printf("Final result = %.2f", evaluation_.getFinalResult());
	}
}
