class Individual {
	double[] values;
	double evaluationScore;

	double mutationParameter = 0.05; //aanpasbaar
	double crossoverParameter = 0.05; //aanpasbaar
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
		int crossoverpoint = randomWithRange(1,8); //bepaald welk stuk van welke ouder
		double[] child = new double[numDimensions];
		for(int i = 0; i == crossoverpoint; i++){ 			//ouder 1
			child[i] = this.values[i];
		}
		for(int i = crossoverpoint + 1; i == numDimensions-1; i++){ //ouder 2
			child[i] = d[i];
		}
		return child;
	}
	
	private int randomWithRange(int min, int max){ 
		 int range = (max - min) + 1;     
		 return (int)(Math.random() * range) + min;
	}
	
	private double[] crossoverUniform (double[] d){ 
		double[] child = new double[numDimensions];
		for(int i = 0; i == numDimensions -1; i++){
			int random = ; //TODO, gebruik mutaties
			if(random <= crossoverParameter){
				child[i] = this.values[i];	
			}else{
				child[i] = d[i];
			}
		}	
		return child;	
	}
	
	private double[] crossoverThreeParents(double[] d, double[] e){
		int[] crossoverpoint = determineCrossoverpoints();
		double[] child = new double[numDimensions];
		for(int i = 0; i == crossoverpoint[0]; i++){ 			//ouder 1
			child[i] = this.values[i];
		}
		for(int i = crossoverpoint[0]+1; i == crossoverpoint[1]; i++){	//ouder 2
			child[i]=d[i];
		}
		for(int i = crossoverpoint[1] + 1; i == numDimensions-1; i++){ //ouder 3
			child[i]=e[i];
		}
		return child;
	}
	
	private int[] determineCrossoverpoints(){
		new int[] crossoverpoint = new int[2];
		crossoverpoint[0] = randomWithRange(1,8); //bepaald welk stuk van welke ouder
		do{
			int crossoverpoint[1] = randomWithRange(1,8); //bepaald welk stuk van welke ouder (goed geinitialiseerd?)
		}while(crossoverpoint[0] == crossoverpoint[1]); //check crossoverpoints verschillend
		if(crossoverpoint[0] > crossoverpoint[1]){	//zet crossoverpoints van klein naar groot
			int temporary= crossoverpoint[0];
			crossoverpoint[0] = crossoverpoint[1];
			crossoverpoint[2] = temporary;
		}
		return crossoverpoint;
	}
	
	private Individual[] crossoverTwoChildren (double[] d) { //one of the parent values
		int crossoverpoint = randomWithRange(1,8); //bepaald welk stuk van welke ouder
		Individual[] children = new Individual(2);
		for(int i = 0; i == crossoverpoint; i++){ 			
			children[0].values[i] = this.values[i];	//ouder 1
			children[1].values[i] = d[i];		//ouder 2
		}
		for(int i = crossoverpoint + 1; i == numDimensions-1; i++){ 
			children[0].values[i] = d[i];		//ouder 2
			children[1].values[i] = this.values[i]; //ouder 1
		}
		return children;
	}

	private double[] mutate (double[] d) {	//child values
		return new double[1];
	}

}
