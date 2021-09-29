package week2task1;

public class Main {
    public static void main(String[] args) {

        // Circle
        Circle cir = new Circle(1);
        System.out.println(cir.info());
        System.out.println("Square = " + cir.getArea());
        System.out.println("Perimeter = " + cir.getPerimeter());
        System.out.println();

        cir.setRadius(2);
        System.out.println(cir.info());
        System.out.println("Square = " + cir.getArea());
        System.out.println("Perimeter = " + cir.getPerimeter());
        System.out.println();

        // Square
        Square sq = new Square(2);
        System.out.println(sq.info());
        System.out.println("Square = " + sq.getArea());
        System.out.println("Perimeter = " + sq.getPerimeter());
        System.out.println();

        sq.setSide(4);
        System.out.println(sq.info());
        System.out.println("Square = " + sq.getArea());
        System.out.println("Perimeter = " + sq.getPerimeter());
        System.out.println();

        // Rectangle
        Rectangle rec = new Rectangle(2, 3);
        System.out.println(rec.info());
        System.out.println("Square = " + rec.getArea());
        System.out.println("Perimeter = " + rec.getPerimeter());
        System.out.println();

        rec.setA(4);
        rec.setB(5);
        System.out.println(rec.info());
        System.out.println("Square = " + rec.getArea());
        System.out.println("Perimeter = " + rec.getPerimeter());
        System.out.println();
    }
}
