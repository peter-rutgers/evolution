import java.util.Random;

class Individual {
	double[] values;
	double evaluationScore;

	Random rnd;
	double mutationParameter = 1; //aanpasbaar
	double mutation_stddev = 0.5; //voor mutaties uit normale verdeling (pag. 44)
    double msize_decrease_factor = 0.98; // bij mutate_decreasing
	double crossoverParameter = 0.5; //aanpasbaar
	int numDimensions = 10;
    double minValue = -5;
    double maxValue = 5;
    double max_mutation_size = maxValue - minValue; // bij mutate_decreasing
    
	public Individual () {
		values = new double[numDimensions];
		rnd = new Random();
		initialize();
	}
	
	public void initialize(){
		//intialiseer de values met random getallen
		for (int i = 0; i < numDimensions; i++) {
			values[i] = Math.random() * (maxValue - minValue) + minValue;
		}
	}
	
	public void initialize(int i){
		this.evaluationScore = i;
	}

	public Individual createOffspring (Individual i) {
		Individual child = new Individual();
		child.values = mutateNonuniform(crossoverUniform(i.values));
		return child;
	}
	
	public Individual createOffspring (Individual i, Individual i2) {
		Individual child = new Individual();
		child.values = mutateNonuniform(crossoverThreeParents(i.values, i2.values));
		return child;
	}
	
	public Individual[] multipleChildren(Individual i){
		Individual[] c = crossoverTwoChildren(i.values);
		c[0].values = mutateDecreasing(c[0].values);
		c[1].values = mutateDecreasing(c[1].values);
		return c;
	}

	private double[] crossover (double[] d) { //one of the parent values
		int crossoverpoint = randomWithRange(1,numDimensions-1); //bepaald welk stuk van welke ouder
		double[] child = new double[numDimensions];
		for(int i = 0; i < crossoverpoint; i++){ 			//ouder 1
			child[i] = this.values[i];
		}
		for(int i = crossoverpoint; i < numDimensions; i++){ //ouder 2
			child[i] = d[i];
		}
		return child;
	}
	
	public static int randomWithRange(int min, int max){ 
		 int range = (max - min) + 1;     
		 return (int)(Math.random() * range) + min;
	}
	
	private double[] crossoverUniform (double[] d){ 
		double[] child = new double[numDimensions];
		for(int i = 0; i < numDimensions; i++){
			double random = Math.random(); //TODO, gebruik mutaties
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
		for(int i = 0; i < crossoverpoint[0]; i++){ 			//ouder 1
			child[i] = this.values[i];
		}
		for(int i = crossoverpoint[0]+1; i < crossoverpoint[1]; i++){	//ouder 2
			child[i]=d[i];
		}
		for(int i = crossoverpoint[1] + 1; i < numDimensions; i++){ //ouder 3
			child[i]=e[i];
		}
		return child;
	}
	
	private int[] determineCrossoverpoints(){
		int[] crossoverpoint = new int[2];
		crossoverpoint[0] = randomWithRange(1,8); //bepaald welk stuk van welke ouder
		do{
			crossoverpoint[1] = randomWithRange(1,8); //bepaald welk stuk van welke ouder (goed geinitialiseerd?)
		}while(crossoverpoint[0] == crossoverpoint[1]); //check crossoverpoints verschillend
		if(crossoverpoint[0] > crossoverpoint[1]){	//zet crossoverpoints van klein naar groot
			int temporary= crossoverpoint[0];
			crossoverpoint[0] = crossoverpoint[1];
			crossoverpoint[1] = temporary;
		}
		return crossoverpoint;
	}
	
	private Individual[] crossoverTwoChildren (double[] d) { //one of the parent values
		int crossoverpoint = randomWithRange(1,8); //bepaald welk stuk van welke ouder
		Individual[] children = new Individual[2];
		children[0] = new Individual();
		children[1] = new Individual();
		for(int i = 0; i < crossoverpoint; i++){ 
			children[0].values[i] = this.values[i];	//ouder 1
			children[1].values[i] = d[i];		//ouder 2
		}
		for(int i = crossoverpoint; i < numDimensions; i++){ 
			children[0].values[i] = d[i];		//ouder 2
			children[1].values[i] = this.values[i]; //ouder 1
		}
		return children;
	}


    private double[] mutate (double[] d) {    //child values
        if (Math.random() > mutationParameter) {
            return d;
        }
        int mutationpoint = randomWithRange(0,numDimensions-1);
        d[mutationpoint] = Math.random() * (maxValue - minValue) + minValue;
        return d;
    }
    
    private double[] mutateNonuniform (double[] d) {
    	double replacement = 0;
    	
    	for (int i = 0; i < numDimensions; i++) {
    		// Mutate each position using normal distribution
    		do {
    			replacement = d[i] + rnd.nextGaussian() * mutation_stddev;
    		} while (replacement > maxValue || replacement < minValue);
    		
    		d[i] = replacement;
    	}
    	
		return d;
    }
     
    private double[] mutateDecreasing (double[] d) {
		
        int mutationpoint = randomWithRange(0,numDimensions-1);
        
        double currentMaxValue = Math.min(maxValue, d[mutationpoint] + max_mutation_size);
        double currentMinValue = Math.max(minValue, d[mutationpoint] - max_mutation_size);
        
        d[mutationpoint] = Math.random() * (currentMaxValue - currentMinValue) + currentMinValue;
        
        max_mutation_size *= msize_decrease_factor;
        
        return d;
    }
}


