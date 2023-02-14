package com.solvd.pageranked.models;

import java.util.Objects;

public class Nodes {
    private int id;
    private int quantityIn;
    private int quantityOut;
    private String name;
    private double pageRank;

    public Nodes() {
    }

    public Nodes(int id) {
        this.id = id;
    }

    public Nodes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Nodes(int id, int quantityIn, int quantityOut) {
        this.id = id;
        this.quantityIn = quantityIn;
        this.quantityOut = quantityOut;
    }

    public Nodes(int id, int quantityIn, int quantityOut, String name) {
        this.id = id;
        this.quantityIn = quantityIn;
        this.quantityOut = quantityOut;
        this.name = name;
    }

    public Nodes(int id, int quantityIn, int quantityOut, String name, double pageRank) {
        this.id = id;
        this.quantityIn = quantityIn;
        this.quantityOut = quantityOut;
        this.name = name;
        this.pageRank = pageRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityIn() {
        return quantityIn;
    }

    public void setQuantityIn(int quantityIn) {
        this.quantityIn = quantityIn;
    }

    public int getQuantityOut() {
        return quantityOut;
    }

    public void setQuantityOut(int quantityOut) {
        this.quantityOut = quantityOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPageRank() {
        return pageRank;
    }

    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodes nodes = (Nodes) o;
        return id == nodes.id && quantityIn == nodes.quantityIn && quantityOut == nodes.quantityOut && Double.compare(nodes.pageRank, pageRank) == 0 && name.equals(nodes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantityIn, quantityOut, name, pageRank);
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "id=" + id +
                ", quantityIn=" + quantityIn +
                ", quantityOut=" + quantityOut +
                ", name='" + name + '\'' +
                ", pageRank=" + pageRank +
                '}';
    }
}
