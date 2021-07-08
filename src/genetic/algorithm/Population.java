package genetic.algorithm;

import java.util.Arrays;

public class Population {
	private Chromosome[] chromosomes;

	public Population(int length) {
		chromosomes = new Chromosome[length];
	}

	public Population initializePopulation() {
		for (int i = 0, size = chromosomes.length; i < size; i++) {
			chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosome();
		}
		sortChromosomesByFitness();
		return this;
	}

	public void sortChromosomesByFitness() {
		Arrays.sort(chromosomes);
	}
	
	public Chromosome[] getChromosomes() {
		return chromosomes;
	}

}
