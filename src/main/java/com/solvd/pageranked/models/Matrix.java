package com.solvd.pageranked.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    private int dimension;
    private int[][] matrix;

    public Matrix() {
    }

    public Matrix(int dimension) {
        this.dimension = dimension;
    }

    public Matrix(int dimension, int[][] matrix) {
        this.dimension = dimension;
        this.matrix = matrix;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "dimension=" + dimension +
                ", matrix=" + Arrays.toString(matrix) +
                '}';
    }

    public void print() {
        for (int i = 0; i < dimension; i++) {
            List<Integer> integerList = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                integerList.add(matrix[i][j]);
            }
            System.out.println(integerList);
        }
    }
}