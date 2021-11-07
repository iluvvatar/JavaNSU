package task2_1;

public class Square extends Shape {
    private double side;

    public Square(){
        this(0);
    }
    public Square(float side){
        this.side = side;
    }

    @Override
    public String info(){
        return "Square. Side = " + side;
    }

    @Override
    public double getArea(){
        return side * side;
    }

    @Override
    public double getPerimeter(){
        return 4 * side;
    }

    public void setSide(double side){
        this.side = side;
    }
}
