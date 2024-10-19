package Lab1.Classes;

import Lab1.Exceptions.MatrixDeterminantException;
import Lab1.Exceptions.MatrixDimensionException;
import package_1.package_2.package_3.C;

public class Matrix implements MathematicalObject<Matrix> {
    private final int rows, cols;
    private final ComplexNumber[][] matrix;

    static void nullPointerChecker(final Matrix pointer) {
        if (pointer == null)
            throw new NullPointerException("The matrix must not be a null");
    }

    static void dimensionValidityChecking(final Matrix matrix1, final Matrix matrix2) {
        Matrix.nullPointerChecker(matrix1);
        Matrix.nullPointerChecker(matrix2);

        if (matrix1.rows != matrix2.rows || matrix1.cols != matrix2.cols)
            throw new MatrixDimensionException("Matrices have different dimensions");
    }

    public Matrix(final ComplexNumber[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("The argument must not be a null");
        }

        if (matrix.length == 0) {
            this.rows = 0;
            this.cols = 0;
            this.matrix = new ComplexNumber[0][0];
            return;
        }

        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix.clone();
    }

    public Matrix() {
        this(new ComplexNumber[0][0]);
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public ComplexNumber[][] getMatrix() {
        return this.matrix;
    }

    public Matrix getTranspose() {
        ComplexNumber[][] transpose = new ComplexNumber[this.cols][this.rows];

        for (int c = 0; c < this.cols; ++c) {
            for (int r = 0; r < this.rows; ++r) {
                transpose[c][r] = this.matrix[r][c];
            }
        }

        return new Matrix(transpose);
    }

    public Matrix add(final Matrix other) {
        Matrix.dimensionValidityChecking(this, other);

        ComplexNumber[][] result = new ComplexNumber[this.rows][this.cols];

        for (int r = 0; r < this.rows; ++r) {
            for (int c = 0; c < this.cols; ++c) {
                result[r][c] = this.matrix[r][c].add(other.matrix[r][c]);
            }
        }

        return new Matrix(result);
    }

    public Matrix subtract(final Matrix other) {
        Matrix.dimensionValidityChecking(this, other);

        ComplexNumber[][] result = new ComplexNumber[this.rows][this.cols];

        for (int r = 0; r < this.rows; ++r) {
            for (int c = 0; c < this.cols; ++c) {
                result[r][c] = this.matrix[r][c].subtract(other.matrix[r][c]);
            }
        }

        return new Matrix(result);
    }

    public Matrix multiply(final Matrix other) {
        Matrix.nullPointerChecker(other);

        if (this.cols != other.rows) {
            throw new MatrixDimensionException("The number of columns of the first matrix " +
                    "is not equal to the number of rows of the second matrix");
        }

        ComplexNumber[][] result = new ComplexNumber[this.rows][other.cols];

        for (int r = 0; r < this.rows; ++r) {
            for (int c = 0; c < other.cols; ++c) {
                result[r][c] = new ComplexNumber();
                for (int k = 0; k < this.cols; ++k) {
                    result[r][c] = result[r][c].add(this.matrix[r][k].multiply(other.matrix[k][c]));
                }
            }
        }

        return new Matrix(result);
    }

    public Matrix multiply(final ComplexNumber complex) {
        if (complex == null) {
            throw new NullPointerException("The complex number must not be a null");
        }

        ComplexNumber[][] result = new ComplexNumber[this.rows][this.cols];

        for (int r = 0; r < this.rows; ++r) {
            for (int c = 0; c < this.cols; ++c) {
                result[r][c] = this.matrix[r][c].multiply(complex);
            }
        }

        return new Matrix(result);
    }

    public Matrix divide(final Matrix other) {
        Matrix.nullPointerChecker(other);

        final Matrix inverse = other.getInverse();

        return this.multiply(inverse);
    }

    public Matrix getInverse() {
        ComplexNumber det = this.getDeterminant();

        if (det.getReal() == 0 && det.getImg() == 0) {
            throw new MatrixDeterminantException("Inverse matrix does not exist, " +
                    "because the determinant is equal to zero");
        }

        ComplexNumber[][] inverse = new ComplexNumber[this.rows][this.cols];

        for (int r = 0; r < this.rows; ++r) {
            for (int c = 0; c < this.cols; ++c) {
                ComplexNumber temp = this.getAlgAddition(r, c);
                inverse[r][c] = new ComplexNumber(temp.getReal(), temp.getImg());
            }
        }

        Matrix result = new Matrix(inverse);
        result = result.getTranspose();
        result = result.multiply(new ComplexNumber(1.0).divide(det));

        return result;
    }

    public ComplexNumber getDeterminant() {
        if (this.cols != this.rows) {
            throw new MatrixDimensionException("Determinant can be calculated only for square matrices");
        }

        if (this.cols == 1) {
            return this.matrix[0][0];
        }

        if (this.cols == 2) {
            ComplexNumber temp1 = this.matrix[0][0].multiply(this.matrix[1][1]);
            ComplexNumber temp2 = this.matrix[0][1].multiply(this.matrix[1][0]);

            return temp1.subtract(temp2);
        }

        ComplexNumber det = new ComplexNumber();

        // Раскладываем определитель по нулевой строке
        for (int c = 0; c < this.cols; ++c) {
            ComplexNumber algAddition = this.getAlgAddition(0, c);

            // det = det + algAddition[0][c] * matrix[0][c];
            det = det.add(algAddition.multiply(matrix[0][c]));
        }

        return det;
    }

    private ComplexNumber getAlgAddition(final int row, final int column) {
        // algAddition[row][column] = det(minor) * (-1)^(row + column);

        Matrix minor = this.getMinor(row, column);

        return minor.getDeterminant().multiply(new ComplexNumber(Math.pow(-1, row + column)));
    }

    private Matrix getMinor(final int row, final int column) {
        ComplexNumber[][] minor = new ComplexNumber[this.rows - 1][this.cols - 1];

        int i = 0, j = 0;

        for (int r = 0; r < this.rows; ++r) {
            if (r == row) {
                continue;
            }

            for (int c = 0; c < this.cols; ++c) {
                if (c == column) {
                    continue;
                }

                minor[i][j] = this.matrix[r][c];
                ++j;
            }

            ++i;
            j = 0;
        }

        return new Matrix(minor);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int r = 0; r < this.rows; ++r) {
            result.append("[ ");

            for (int c = 0; c < this.cols; ++c) {
                result.append(this.matrix[r][c].toString());

                if (c != this.cols - 1)
                    result.append(", ");
            }

            result.append(" ]\n");
        }

        return new String(result);
    }
}
