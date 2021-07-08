package genetic.algorithm;

import java.util.Arrays;

public class GeneticAlgorithmMain {

	public static void main(String[] args) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
		
		
		System.out.println("-------------------------------------------");
		System.out.println("Generation # 0 "+" | FitTest chromosome fitness: "+ population.getChromosomes()[0].getFitness());
		printPopulation(population, "Target Chromosome: "+Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
		
		int generationNumber = 0;
		while(population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
			generationNumber++;
			System.out.println("\n-------------------------------------------");
			population = geneticAlgorithm.evolve(population);
			population.sortChromosomesByFitness();
			System.out.println("Generation # "+generationNumber+" | FitTest chromosome fitness: "+ population.getChromosomes()[0].getFitness());
			printPopulation(population, "Target Chromosome: "+Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
		}
	}

	public static void printPopulation(Population population, String heading) {
		System.out.println(heading);
		System.out.println("-------------------------------------------");
		for (int i = 0, size = population.getChromosomes().length; i < size; i++) {
			String string = "Chromosome # "+i+" : "+ Arrays.toString(population.getChromosomes()[i].getGenes());
			string += " | Fitness: "+population.getChromosomes()[i].getFitness();
			System.out.println(string);
		}
	}

}
