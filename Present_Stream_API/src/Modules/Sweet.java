package Modules;

public abstract class Sweet {

    private String name;
    private double weight;
    private double sugarWeight;

    public Sweet() {

    }

    public Sweet(String name, double weight, double sugarWeight) {
        setName(name);
        setWeight(weight);
        setSugarWeight(sugarWeight);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSugarWeight() {
        return sugarWeight;
    }

    private void setSugarWeight(double sugarWeight) {
        this.sugarWeight = sugarWeight;
    }
}
