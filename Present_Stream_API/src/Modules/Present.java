package Modules;

import java.util.Arrays;
import java.util.Objects;

public class Present {

    private Sweet[] sweets;


    // the method filters sweets by sugar weight inclusively
    public Sweet[] filterSweetsBySugarRange(double minSugarWeight, double maxSugarWeight) {

        if (sweets == null || minSugarWeight < 0 || maxSugarWeight < 0) {
            return new Sweet[0];
        }

        return Arrays.stream(sweets)
                .filter(sweet ->
                        sweet.getSugarWeight() >= minSugarWeight && sweet.getSugarWeight() <= maxSugarWeight)
                .toArray(Sweet[]::new);

    }


    // the method calculates total weight of the present
    public double calculateTotalWeight() {
        if (sweets == null) return 0;

        return Arrays.stream(sweets).mapToDouble(Sweet::getSugarWeight).sum();
    }

    // the method that adds sweet to the present
    public void addSweet(Sweet sweet) {

        if (sweet != null) {
            Sweet[] newSweet = Arrays.copyOf(sweets, sweets.length + 1);
            newSweet[newSweet.length - 1] = sweet;
            sweets = newSweet;
        }
    }

    // the method returns copy of the Sweet[] array so that nobody could
// modify state of the present without addSweet() method.
// Also array shouldn’t contain null values.
    public Sweet[] getSweets() {
        return Arrays.stream(sweets)
                .filter(Objects::nonNull)
                .toArray(Sweet[]::new);

    }

}
