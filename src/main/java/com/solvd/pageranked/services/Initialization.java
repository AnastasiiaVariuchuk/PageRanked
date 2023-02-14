package com.solvd.pageranked.services;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;

public class Initialization {
    public static void deleteAllFromDB() {
        deleteAllRelations();
        deleteAllNodes();
        deleteAllLinks();
    }

    public static void deleteAllRelations() {
        RelationsDAO relationsDAO = new RelationsDAO();
        relationsDAO.deleteAllRelations();
    }

    public static void deleteAllNodes() {
        NodesDAO nodesDAO = new NodesDAO();
        nodesDAO.deleteAllNodes();
    }

    public static void deleteAllLinks() {
        LinksDAO linksDAO = new LinksDAO();
        linksDAO.deleteAllLinks();
    }
}

