class Individual {
	double[] values;
	double evaluationScore;

	double mutationParameter = 0.05; //aanpasbaar
	int numDimensions = 10;

	public Individual () {
		values = double[numDimensions];
	}

	public Individual createOffspring (Individual i) {
		child.values = mutate(crossover(i.values));
		return child;
	}

	private double[] crossover (double[] d) { //one of the parent values

		return result;
	}

	private double[] mutate (double[] d) {	//child values
		
	}

}