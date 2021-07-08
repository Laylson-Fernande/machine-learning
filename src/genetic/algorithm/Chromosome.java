package genetic.algorithm;

import java.util.Arrays;

public class Chromosome implements Comparable<Chromosome>{
	private boolean isFitnessCHanged = true;
	private int fitness = 0;
	private int[] genes;
	
	public Chromosome(int length) {
		this.genes = new int[length];
	}
	
	public Chromosome initializeChromosome() {
		for(int i = 0, size = genes.length; i < size; i++) {
			if(Math.random() >= 0.5) {
				genes[i] = 1;
			} else {
				genes[i] = 0;
			}
		}
		fitness = recalculateFitness();
		return this;
	}
	
	public int recalculateFitness() {
		int chromosomeFitness = 0;
		for(int i = 0, size = genes.length; i < size; i++) {
			if(genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i]) {
				chromosomeFitness++;
			}
		}
		return chromosomeFitness;
	}
	
	public String toString() {
		return Arrays.toString(this.genes);
	}
	
	public int[] getGenes() {
		isFitnessCHanged = true;
		return genes;
	}
	
	public int getFitness() {
		if(isFitnessCHanged) {
			fitness = recalculateFitness();
			isFitnessCHanged = false;
		}
		return fitness;
	}

	public boolean isFitnessCHanged() {
		return isFitnessCHanged;
	}

	@Override
	public int compareTo(Chromosome arg) {
		return arg.getFitness() - this.getFitness();
	}

}
