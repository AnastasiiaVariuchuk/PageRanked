package com.solvd.pageranked.services;

import com.solvd.pageranked.models.Matrix;

public class PageRank {
    public int nodes;
    public int[][] path;
    public double[] pagerank;

    public PageRank(Matrix matrix) {
        this.nodes = matrix.getDimension();
        this.path = setZerosToMainDiagonal(matrix.getMatrix());
        this.pagerank = new double[nodes];
    }

    public double[] calculate() {
        double InitialPageRank;
        double OutgoingLinks;
        double DampingFactor = 0.85;
        double totalNodes = nodes;
        double[] TempPageRank = new double[nodes];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k;
        int iterationStep = 1;
        InitialPageRank = 1 / totalNodes;

        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
        }

        while (iterationStep <= 2)
        {
            for (k = 0; k < totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
            }

            for (InternalNodeNumber = 0; InternalNodeNumber < totalNodes; InternalNodeNumber++) {
                for (ExternalNodeNumber = 0; ExternalNodeNumber < totalNodes; ExternalNodeNumber++) {
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) {
                        k = 0;
                        OutgoingLinks = 0;
                        while (k < totalNodes) {
                            if (this.path[ExternalNodeNumber][k] == 1) {
                                OutgoingLinks = OutgoingLinks + 1;
                            }
                            k = k + 1;
                        }

                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    }
                }
            }

            iterationStep = iterationStep + 1;
        }

        for (k = 0; k < totalNodes; k++) {
            this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
        }


        return pagerank;
    }

    private int[][] setZerosToMainDiagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 0;
        }
        return matrix;
    }
}
