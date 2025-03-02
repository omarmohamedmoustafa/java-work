class Complex {
    private double real;
    private double imag;

    public Complex() {
        this.real = 0;
        this.imag = 0;
    }

    public Complex(double both) {
        this.real = both;
        this.imag = both;
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public static Complex addUsingTwoObjs(Complex c1, Complex c2) {
        return new Complex(c1.real + c2.real, c1.imag + c2.imag);
    }

    public static Complex subUsingTwoObjs(Complex c1, Complex c2) {
        return new Complex(c1.real - c2.real, c1.imag - c2.imag);
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imag + other.imag);
    }

    public Complex sub(Complex other) {
        return new Complex(this.real - other.real, this.imag - other.imag);
    }

    public String print() {
        if (this.real == 0 && this.imag == 0) {
            return "0";
        }
        if (this.real == 0) {
            return this.imag + "i";
        }
        if (this.imag == 0) {
            return this.real + "";
        }
        if (this.imag < 0) {
            return this.real + " - " + (-this.imag) + "i";
        }
        return this.real + " + " + this.imag + "i";
    }

    public static void main(String[] args) {
        Complex c1 = new Complex(-1, -2);
        Complex c2 = new Complex(3, 4);

        System.out.println("Using single object");
        System.out.println(c1.add(c2).print()); 
        System.out.println(c1.sub(c2).print()); 

        System.out.println("Using two objects");
        System.out.println(Complex.addUsingTwoObjs(c1, c2).print());
        System.out.println(Complex.subUsingTwoObjs(c1, c2).print());
    }
}
