package main.domain.classes;

import java.util.Comparator;

public class GeneticComparator implements Comparator<Genetic> {
    public int compare(Genetic n1, Genetic n2){
        if(n1.getFitness() < n2.getFitness()) {
            return -1;
        }else if(n1.getFitness() > n2.getFitness()) {
            return 1;
        }
        return 0;
    }
}
