package com.solvd.pageranked.models;

import java.util.Objects;

public class Relations {
    private int id;
    private int nodesId;
    private int linksId;

    public Relations() {
    }

    public Relations(int id, int nodesId, int linksId) {
        this.id = id;
        this.nodesId = nodesId;
        this.linksId = linksId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodesId() {
        return nodesId;
    }

    public void setNodesId(int nodesId) {
        this.nodesId = nodesId;
    }

    public int getLinksId() {
        return linksId;
    }

    public void setLinksId(int linksId) {
        this.linksId = linksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relations relations = (Relations) o;
        return id == relations.id && nodesId == relations.nodesId && linksId == relations.linksId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodesId, linksId);
    }

    @Override
    public String toString() {
        return "Relations{" +
                "id=" + id +
                ", nodesId=" + nodesId +
                ", linksId=" + linksId +
                '}';
    }
}
