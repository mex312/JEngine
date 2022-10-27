package com.mex312.JEngine;

public class Matrix {
    public final Vector2 size;

    public final float[][] matrixArray;

    public Matrix(float[][] matrixArray) {
        this.matrixArray = matrixArray;
        size = new Vector2(matrixArray.length, matrixArray[0].length);
    }

    public Matrix(Vector2 size) {
        this.size = size;
        matrixArray = new float[(int)size.x][(int)size.y];
    }

    public Matrix inverse()
    {
        double temp;

        Matrix E = new Matrix(size);
        Matrix A = this.clone();


        for (int i = 0; i < size.x; i++)
            for (int j = 0; j < size.x; j++)
            {
                E.matrixArray[i][j] = 0f;

                if (i == j)
                    E.matrixArray[i][j] = 1f;
            }

        for (int k = 0; k < E.size.x; k++)
        {
            temp = A.matrixArray[k][k];

            for (int j = 0; j < size.x; j++)
            {
                A.matrixArray[k][j] /= temp;
                E.matrixArray[k][j] /= temp;
            }

            for (int i = k + 1; i < size.x; i++)
            {
                temp = A.matrixArray[i][k];

                for (int j = 0; j < size.x; j++)
                {
                    A.matrixArray[i][j] -= A.matrixArray[k][j] * temp;
                    E.matrixArray[i][j] -= E.matrixArray[k][j] * temp;
                }
            }
        }

        for (int k = (int)size.x - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = A.matrixArray[i][k];

                for (int j = 0; j < size.x; j++)
                {
                    A.matrixArray[i][j] -= A.matrixArray[k][j] * temp;
                    E.matrixArray[i][j] -= E.matrixArray[k][j] * temp;
                }
            }
        }

        return E;
    }


    public Matrix multiply(Matrix other) {
        Matrix out = new Matrix(new Vector2(size.x, other.size.y));

        for(int i = 0; i < out.size.x; i++) {
            for(int j = 0; j < out.size.y; j++) {
                float temp = 0;
                for(int l = 0; l < other.size.x; l++) {
                    temp += matrixArray[i][l] * other.matrixArray[l][j];
                }
                out.matrixArray[i][j] = temp;
            }
        }

        return out;
    }

    public static Matrix createRotationMatrix(float angle) {
        float s = (float)Math.sin(angle);
        float c = (float)Math.cos(angle);
        return new Matrix(new float[][]{
                {c,-s, 0},
                {s, c, 0},
                {0, 0, 1}
        });
    }
    public static Matrix createRotationMatrix(float angle, Vector2 axis) {
        float s = (float)Math.sin(angle);
        float c = (float)Math.cos(angle);

        return createTranslationMatrix(axis).multiply(new Matrix(new float[][]{
                {c,-s, 0},
                {s, c, 0},
                {0, 0, 1}
        })).multiply(createTranslationMatrix(axis.multiply(-1)));
    }

    public static Matrix createTranslationMatrix(Vector2 delta) {
        float x = delta.x;
        float y = delta.y;

        return new Matrix(new float[][]{
                {1, 0, x},
                {0, 1, y},
                {0, 0, 1}
        });
    }

    public static Matrix createScaleMatrix(Vector2 delta) {
        float x = delta.x;
        float y = delta.y;

        return new Matrix(new float[][]{
                {x, 0, 0},
                {0, y, 0},
                {0, 0, 1}
        });
    }

    public static Matrix createUnitMatrix() {
        return new Matrix(new float[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }


    @Override
    public Matrix clone() {
        return new Matrix(matrixArray.clone());
    }
}
