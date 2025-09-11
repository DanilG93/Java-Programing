public class Present {

    private Sweet[] sweets;

    public void addSweet(Sweet sweet) {
        if (sweet == null) return;

        if (sweets == null) {
            sweets = new Sweet[]{sweet};
        } else {
            Sweet[] newSweets = new Sweet[sweets.length + 1];
            for (int i = 0; i < sweets.length; i++) {
                newSweets[i] = sweets[i];
            }
            newSweets[sweets.length] = sweet;
            sweets = newSweets;
        }
    }

    public double calculateTotalWeight() {

        if (sweets == null) return 0.0;
        double totalWeight = 0;

        for (Sweet sweet : sweets) {
            totalWeight += sweet.getWeight();
        }

        return totalWeight;
    }

    public Sweet[] filterSweetsBySugarRange(double minSugarWeight, double maxSugarWeight) {
        if (sweets == null || sweets.length == 0) {
            return new Sweet[0];
        }

        if (minSugarWeight < 0 || maxSugarWeight < 0 || minSugarWeight > maxSugarWeight) {
            return new Sweet[0];
        }

        int count = 0;
        for (Sweet sweet : sweets) {
            if (sweet.getSugarWeight() >= minSugarWeight && sweet.getSugarWeight() <= maxSugarWeight) {
                count++;
            }
        }

        Sweet[] result = new Sweet[count];
        int index = 0;
        for (Sweet sweet : sweets) {
            if (sweet.getSugarWeight() >= minSugarWeight && sweet.getSugarWeight() <= maxSugarWeight) {
                result[index++] = sweet;
            }
        }

        return result;


    }

    public Sweet[] getSweets() {
        if (sweets == null) return new Sweet[0];
        return sweets;
    }
}
