package Lab1;

import Lab1.Classes.ComplexNumber;
import Lab1.Classes.Matrix;
import Lab1.Exceptions.MatrixDeterminantException;
import Lab1.Exceptions.MatrixDimensionException;

public class Main {
    public static void main(String[] args) {
        // Тестирование класса комплексных чисел
        ComplexNumberTesting testingComplexNumber = new ComplexNumberTesting();

        testingComplexNumber.addTesting();
        System.out.println();

        testingComplexNumber.subtractTesting();
        System.out.println();

        testingComplexNumber.multiplyTesting();
        System.out.println();

        testingComplexNumber.divideTesting();
        System.out.println();

        // Тестирование класса матриц
        MatrixTesting testingMatrix = new MatrixTesting();

        testingMatrix.transposeTesting();
        System.out.println();

        testingMatrix.addTesting();
        System.out.println();

        testingMatrix.subtractTesting();
        System.out.println();

        testingMatrix.multiplyTesting();
        System.out.println();

        testingMatrix.divideTesting();
        System.out.println();

        testingMatrix.inverseTesting();
        System.out.println();

        testingMatrix.determinantTesting();
    }
}

class ComplexNumberTesting {

    // zero, r1, r2 - только действительные числа
    // i1, i2 - только числа с мнимой единицей
    // ri1, ri2 - числа и с действительной, и с мнимой частью
    private final ComplexNumber zero, r1, r2;
    private final ComplexNumber i1, i2;
    private final ComplexNumber ri1, ri2;

    public ComplexNumberTesting() {
        this.zero = new ComplexNumber();
        this.r1 = new ComplexNumber(21);
        this.r2 = new ComplexNumber(-10);

        i1 = new ComplexNumber(0, 1);
        i2 = new ComplexNumber(0, -5);

        this.ri1 = new ComplexNumber(1, 1);
        this.ri2 = new ComplexNumber(3, -5);
    }

    public void addTesting() {
        System.out.println("COMPLEX NUMBER ADD OPERATION TESTING:");

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(i1 + " + " + i2 + " = " + i1.add(i2));
        System.out.println();

        System.out.println(ri1 + " + " + ri2 + " = " + ri1.add(ri2));
        System.out.println(ri1 + " + " + r1 + " = " + ri1.add(r1));
        System.out.println(ri1 + " + " + i2 + " = " + ri1.add(i2));

        // Проверка сложения с нулевым указателем
        try {
            System.out.println(ri1.add(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void subtractTesting() {
        System.out.println("COMPLEX NUMBER SUBTRACT OPERATION TESTING:");

        System.out.println(ri1 + " - " + ri2 + " = " + ri1.subtract(ri2));
        System.out.println(ri1 + " - " + r2 + " = " + ri1.subtract(r2));
        System.out.println(ri1 + " - " + i2 + " = " + ri1.subtract(i2));

        // Проверка вычитания с нулевым указателем
        try {
            System.out.println(ri1.subtract(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void multiplyTesting() {
        System.out.println("COMPLEX NUMBER MULTIPLY OPERATION TESTING:");

        System.out.println(ri1 + " * " + ri2 + " = " + ri1.multiply(ri2));
        System.out.println(ri1 + " * " + r2 + " = " + ri1.multiply(r2));
        System.out.println(ri1 + " * " + i2 + " = " + ri1.multiply(i2));
        System.out.println(ri1 + " * " + zero + " = " + ri1.multiply(zero));

        // Проверка умножения с нулевым указателем
        try {
            System.out.println(ri1.multiply(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void divideTesting() {
        System.out.println("COMPLEX NUMBER DIVIDE OPERATION TESTING:");

        System.out.println(ri1 + " / " + ri2 + " = " + ri1.divide(ri2));
        System.out.println(ri1 + " / " + r2 + " = " + ri1.divide(r2));
        System.out.println(ri1 + " / " + i2 + " = " + ri1.divide(i2));

        // Проверка умножения с нулевым указателем
        try {
            System.out.println(ri1.divide(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Проверка деления ноль
        try {
            System.out.println(ri1.divide(zero));
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}

class MatrixTesting {
    private final Matrix matrix1, matrix2, matrix3, matrix4;
    private final ComplexNumber complex;

    public MatrixTesting() {
        this.complex = new ComplexNumber(3, -7);

        ComplexNumber i1 = new ComplexNumber(1, 1);
        ComplexNumber i2 = new ComplexNumber(2);
        ComplexNumber i3 = new ComplexNumber(5, 2);
        ComplexNumber i4 = new ComplexNumber(2, -3);
        ComplexNumber i5 = new ComplexNumber(17);
        ComplexNumber i6 = new ComplexNumber(5, -2);
        ComplexNumber i7 = new ComplexNumber(0, 4);
        ComplexNumber i8 = new ComplexNumber();
        ComplexNumber i9 = new ComplexNumber(0, -4);
        ComplexNumber[][] matrix1 = {{i1, i2, i3}, {i4, i5, i6}, {i7, i8, i9}};

        this.matrix1 = new Matrix(matrix1);

        ComplexNumber[][] matrix2 = new ComplexNumber[3][3];
        for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < 3; ++c) {
                matrix2[r][c] = new ComplexNumber(r + 4, c - 1);
            }
        }

        this.matrix2 = new Matrix(matrix2);

        ComplexNumber[][] matrix3 = {{i6}, {i2}, {i3}};
        this.matrix3 = new Matrix(matrix3);

        this.matrix4 = new Matrix();

        System.out.println("Matrix #1:\n" + this.matrix1);
        System.out.println("\nMatrix #2:\n" + this.matrix2);
        System.out.println("\nMatrix #3:\n" + this.matrix3);
        System.out.println("\nMatrix #4:\n" + this.matrix4);
    }

    public void transposeTesting() {
        System.out.println("MATRIX TRANSPOSE OPERATION TESTING:");

        System.out.println("Matrix #1:\n" + matrix1.getTranspose());
        System.out.println("\nMatrix #2:\n" + matrix2.getTranspose());
        System.out.println("\nMatrix #3:\n" + matrix3.getTranspose());
    }

    public void addTesting() {
        System.out.println("MATRIX ADD OPERATION TESTING:");

        System.out.println("Matrix #1 + Matrix #2 =\n" + matrix1.add(matrix2));

        // Проверка сложения с нулевым указателем
        try {
            System.out.println(matrix1.add(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Проверка сложения с матрицей другой размерности
        try {
            System.out.println(matrix1.add(matrix3));
        }
        catch (MatrixDimensionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void subtractTesting() {
        System.out.println("MATRIX SUBTRACT OPERATION TESTING:");

        System.out.println("Matrix #1 - Matrix #2 =\n" + matrix1.subtract(matrix2));

        // Проверка вычитания с нулевым указателем
        try {
            System.out.println(matrix1.subtract(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Проверка вычитания с матрицей другой размерности
        try {
            System.out.println(matrix1.subtract(matrix3));
        }
        catch (MatrixDimensionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void multiplyTesting() {
        System.out.println("MATRIX MULTIPLY OPERATION TESTING:");

        System.out.println("Matrix #1 * Matrix #2 =\n" + matrix1.multiply(matrix2));
        System.out.println("\nMatrix #1 * Matrix #3 =\n" + matrix1.multiply(matrix3));
        System.out.println("\nMatrix #2 * (" + complex + ") =\n" + matrix2.multiply(complex));

        // Проверки умножения с нулевым указателем не будет, так как это вызовет ошибку
        // компиляции, так как существуют два экземпляра метода multiply и компилятор
        // не знает, в каком из двух экземпляров метода в аргументе присвоить null

        // Проверка умножения с матрицей неподходящей размерности,
        // то есть, где кол-во столбцов 1-ой матрицы не совпадает с кол-вом строк 2-ой матрицы
        try {
            System.out.println(matrix1.multiply(matrix4));
        }
        catch (MatrixDimensionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void divideTesting() {
        System.out.println("MATRIX DIVIDE OPERATION TESTING:");

        System.out.println("Matrix #2 / Matrix #1 =\n" + matrix2.divide(matrix1));

        // Проверка деления с нулевым указателем
        try {
            System.out.println(matrix2.divide(null));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Проверка деления на матрицу другой размерности
        try {
            System.out.println(matrix1.divide(matrix3));
        }
        catch (MatrixDimensionException e) {
            System.out.println(e.getMessage());
        }

        // Проверка деления на матрицу, у которой не существует обратной
        try {
            System.out.println(matrix1.divide(matrix2));
        }
        catch (MatrixDeterminantException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inverseTesting() {
        System.out.println("MATRIX INVERSE OPERATION TESTING:");

        System.out.println("Inverse matrix #1:\n" + matrix1.getInverse());

        // Проверка вычисления обратной матрицу у неквадратной матрицы
        try {
            System.out.println(matrix3.getInverse());
        }
        catch (MatrixDimensionException e) {
            System.out.println(e.getMessage());
        }

        // Проверка вычисления обратной матрицы, где детерминант исходной матрицы равен 0
        try {
            System.out.println(matrix2.getInverse());
        }
        catch (MatrixDeterminantException e) {
            System.out.println(e.getMessage());
        }
    }

    public void determinantTesting() {
        System.out.println("MATRIX DETERMINANT OPERATION TESTING:");

        System.out.println("Determinant of matrix #1: " + matrix1.getDeterminant());
        System.out.println("Determinant of matrix #2: " + matrix2.getDeterminant());

        // Проверка вычисления детерминанта на неквадратной матрице
        try {
            System.out.println(matrix3.getDeterminant());
        }
        catch (MatrixDimensionException e) {
            System.out.println(e.getMessage());
        }
    }
}
