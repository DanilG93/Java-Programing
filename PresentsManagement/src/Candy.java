public abstract class Candy extends Sweet {


    @Override
    public String toString() {
        return "Sweet{" +
                "name='" + getName() + '\'' +
                ", weight=" + getWeight() +
                ", sugarWeight=" + getSugarWeight() +
                '}';
    }
}
