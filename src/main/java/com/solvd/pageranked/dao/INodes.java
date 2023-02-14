package com.solvd.pageranked.dao;

import com.solvd.pageranked.models.Nodes;

import java.util.List;

public interface INodes {
    Nodes getById(int nodesId);

    Nodes getByName(String name);

    int getQuantityByNodes(int nodesId);

    int getQuantityByLinks(int linksId);

    List<Nodes> getAllNodes();

    void deleteAllNodes();

    void addNode(Nodes nodes);

    void updateNode(Nodes nodes);

    void updateQuantities(Nodes nodes);

    void deleteNode(int id);
}
