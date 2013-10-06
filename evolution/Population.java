import java.util.ArrayList;


public class Population {
	ArrayList<Individual> population = new ArrayList<Individual>();
	
	//constructors, eerste is om de populatie op te zetten
	//tweede wordt gebruikt voor nieuwe populaties (overlevenden enzo)
	public Population(int populationSize){
		System.out.printf("Creating a new world\n");
		for(int i = 0; i < populationSize; i++){
			population.add(new Individual());
			population.get(i).initialize(populationSize-i);
		}
		System.out.printf("The world is ready for bucking\n");
	}
	
	public Population(){
		
	}
	
	
	//om code toch mooi te houden een get en add toegevoegd
	//anders zou het er zo uitzien:
	//population.population.add(new Individual());
	//ipv
	//population.add(newIndividual());
	public void add(Individual i){
		population.add(i);
	}
	
	public Individual get(int i){
		return population.get(i);
	}
	
	public ArrayList<Individual> sort(){
		ArrayList<Individual> result = new ArrayList<Individual>();
		population = bubbleSort(population);
		return result;
	}
	
	public int size(){
		return population.size();
	}
	
  static ArrayList<Individual> bubbleSort(ArrayList<Individual> list){
	  int count = 0;
	  for (int outer = 0; outer < list.size() - 1; outer++){
		  for (int inner = 0; inner < list.size()-outer-1; inner++){
			  if (list.get(inner).evaluationScore > list.get(inner + 1).evaluationScore){
				  swapEm(list, inner);
				  count = count + 1;
			  }
		  }
	  }
	  return list;
  }

	static  void swapEm(ArrayList<Individual>list,  int inner){
		Individual temp = list.get(inner);
		list.set(inner, list.get(inner + 1));
		list.set(inner + 1, temp);
	}
	
	
}
