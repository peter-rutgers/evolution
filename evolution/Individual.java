class Individual {
	double[] values;
	double evaluationScore;

	double mutationParameter = 0.05; //aanpasbaar
	int numDimensions = 10;

	public Individual () {
		double[] values = new double[numDimensions];
	}
	
	public void initialize(){
		//intialiseer de values met random getallen
	}
	
	public void initialize(int i){
		this.evaluationScore = i;
	}

	public Individual createOffspring (Individual i) {
		//child.values = mutate(crossover(i.values));
		return new Individual();
	}

	private double[] crossover (double[] d) { //one of the parent values

		return new double[1];
	}

	private double[] mutate (double[] d) {	//child values
		return new double[1];
	}

}
