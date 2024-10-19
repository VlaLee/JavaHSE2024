package Lab1.Classes;

public class ComplexNumber implements MathematicalObject<ComplexNumber> {
    private final double real;
    private final double img;

    static void nullPointerChecker(final ComplexNumber pointer) {
        if (pointer == null)
            throw new NullPointerException("The complex number must not be a null");
    }

    public ComplexNumber(final double real, final double img) {
        this.real = real;
        this.img = img;
    }

    public ComplexNumber(final double real) {
        this(real, 0.0);
    }

    public ComplexNumber() {
        this(0.0, 0.0);
    }

    public double getReal() {
        return this.real;
    }

    public double getImg() {
        return this.img;
    }

    public ComplexNumber add(final ComplexNumber complex) {
        ComplexNumber.nullPointerChecker(complex);

        return new ComplexNumber(this.real + complex.real, this.img + complex.img);
    }

    public ComplexNumber subtract(final ComplexNumber complex) {
        ComplexNumber.nullPointerChecker(complex);

        return new ComplexNumber(this.real - complex.real, this.img - complex.img);
    }

    public ComplexNumber multiply(final ComplexNumber complex) {
        ComplexNumber.nullPointerChecker(complex);

        final double newReal = this.real * complex.real - this.img * complex.img;
        final double newImg = this.real * complex.img + this.img * complex.real;

        return new ComplexNumber(newReal, newImg);
    }

    public ComplexNumber divide(final ComplexNumber complex) {
        ComplexNumber.nullPointerChecker(complex);

        final ComplexNumber conjugate = new ComplexNumber(complex.real, -complex.img);
        final ComplexNumber numerator = this.multiply(conjugate);
        final double denominator = complex.real * complex.real + complex.img * complex.img;

        if (denominator == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return new ComplexNumber(numerator.real / denominator, numerator.img / denominator);
    }

    @Override
    public String toString() {
        if (this.real == 0 && this.img == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();

        if (this.real != 0)
            result.append(Double.toString(this.real));

        if (this.img != 0) {
            if (this.img > 0 && this.real != 0) {
                result.append("+");
            }

            if (this.img != 1)
                result.append(Double.toString(this.img));

            result.append("i");
        }

        return new String(result);
    }
}
