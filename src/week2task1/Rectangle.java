package week2task1;

public class Rectangle extends Shape{
    private double a;
    private double b;

    public Rectangle(){
        this(0, 0);
    }
    public Rectangle(double a){
        this(a, 0);
    }
    public Rectangle(double a, double b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String info(){
        return "Rectangle. Sides = (" + a + ", " + b + ")";
    }

    @Override
    public double getArea(){
        return a * b;
    }

    @Override
    public double getPerimeter(){
        return 2 * (a + b);
    }

    public void setA(double a){
        this.a = a;
    }

    public void setB(double b){
        this.b = b;
    }
}
