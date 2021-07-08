package genetic.algorithm;

public class GeneticAlgorithm {
	public static final int POPULATION_SIZE = 8;
	public static final int[] TARGET_CHROMOSOME = { 1, 1, 0, 1, 0, 0, 1, 1, 1, 0 };
	public static final int NUMBER_ELITE_CHROMOSOMES = 1;
	public static final int TOURNAMENT_SELECTION_SIZE = 4;
	public static final double MUTATION_RATE = 0.25;

	public Population evolve(Population population) {
		population = crossoverPopulation(population);
		population = mutatePopulation(population);
		return population;
	}

	private Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getChromosomes().length);
		for (int i = 0; i < NUMBER_ELITE_CHROMOSOMES; i++) {
			crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}
		for (int i = NUMBER_ELITE_CHROMOSOMES, size = population.getChromosomes().length; i < size; i++) {
			Chromosome a = selectTournamentPopulation(population).getChromosomes()[0];
			Chromosome b = selectTournamentPopulation(population).getChromosomes()[0];
			crossoverPopulation.getChromosomes()[i] = crossoverChromosome(a, b);

		}
		return crossoverPopulation;
	}

	private Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getChromosomes().length);
		for (int i = 0; i < NUMBER_ELITE_CHROMOSOMES; i++) {
			mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
		}

		for (int i = NUMBER_ELITE_CHROMOSOMES, size = population.getChromosomes().length; i < size; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
		}
		return mutatePopulation;
	}

	private Chromosome crossoverChromosome(Chromosome a, Chromosome b) {
		Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for (int i = 0, size = a.getGenes().length; i < size; i++) {
			if (Math.random() < 0.5) {
				crossoverChromosome.getGenes()[i] = a.getGenes()[i];
			} else {
				crossoverChromosome.getGenes()[i] = b.getGenes()[i];
			}
		}
		return crossoverChromosome;
	}

	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
		for (int i = 0, size = chromosome.getGenes().length; i < size; i++) {
			if (Math.random() < MUTATION_RATE) {
				if (Math.random() < 0.5) {
					mutateChromosome.getGenes()[i] = 1;
				} else {
					mutateChromosome.getGenes()[i] = 0;
				}
			} else {
				mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
			}
		}
		return mutateChromosome;
	}

	private Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
		int sizeChromosomes = population.getChromosomes().length;
		for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
			tournamentPopulation.getChromosomes()[i] = population.getChromosomes()[(int)(Math.random() * sizeChromosomes)];
		}
		tournamentPopulation.sortChromosomesByFitness();
		return tournamentPopulation;
	}

}
