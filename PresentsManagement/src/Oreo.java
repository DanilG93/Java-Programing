public class Oreo extends Cookie {

    @Override
    public String toString() {
        return "Oreo{ " +
                "name='" + getName() + '\'' +
                ", weight=" + getWeight() +
                ", sugarWeight=" + getSugarWeight() +
                ", doughWeight=" + getDoughWeight() +
                '}';
    }
}
