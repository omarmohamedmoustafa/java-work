abstract class GeoShape {
    
    protected double dim1;
    protected double dim2;

    abstract double calcArea();

    public GeoShape() {
        this.dim1 = 0;
        this.dim2 = 0;
    }

    public GeoShape(double dim1) {
        this.dim1 = dim1;
        this.dim2 = 0;
    }

    public GeoShape(double dim1, double dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    public void setDim1(double dim1) {
        this.dim1 = dim1;
    }

    public double getDim1() {
        return dim1;
    }

    public void setDim2(double dim2) {
        this.dim2 = dim2;
    }

    public double getDim2() {
        return dim2;
    }

    public void display() {
        System.out.println("Dimension 1: " + dim1);
        System.out.println("Dimension 2: " + dim2);
        System.out.println("Area: " + calcArea());
    }

    public static double sumAreas(GeoShape... shapes) {
	    double totalArea = 0.0;
	    for (GeoShape shape : shapes) {
	        totalArea += shape.calcArea();
	    }
	    return totalArea;
    }

    public static void main(String[] args) {
    	System.out.println("Triangle Object:");

        GeoShape shape1 = new Triangle(5, 10);
        shape1.display();

    	System.out.println("Circle Object:");

        GeoShape shape2 = new Circle(7);
        shape2.display(); 

    	System.out.println("Rectangle Object:");

        GeoShape shape3 = new Rectangle(4, 6);
        shape3.display(); 

        System.out.print("Total areas of GeoShapes=");

        System.out.println(sumAreas(shape1,shape2,shape3));
    }
}

class Triangle extends GeoShape {
    public Triangle() {
        super();
    }

    public Triangle(double base, double height) {
        super(base, height);
    }

    double calcArea() {
        return 0.5 * dim1 * dim2;
    }
}

class Circle extends GeoShape {
    public Circle() {
        super();
    }

    public Circle(double radius) {
        super(radius, 0);
    }


    double calcArea() {
        return Math.PI * dim1 * dim1; 
    }
}

class Rectangle extends GeoShape {
    public Rectangle() {
        super();
    }

    public Rectangle(double length, double width) {
        super(length, width);
    }

    double calcArea() {
        return dim1 * dim2;
    }
}
