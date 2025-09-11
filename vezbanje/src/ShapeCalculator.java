import java.util.List;

public class ShapeCalculator {


    public double calculateTotalArea(List<Shape> shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }
        return  totalArea;
    }

}
