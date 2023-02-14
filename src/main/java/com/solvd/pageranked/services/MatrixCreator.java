package com.solvd.pageranked.services;

import com.solvd.pageranked.dao.jdbc.mysql.Impl.LinksDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.NodesDAO;
import com.solvd.pageranked.dao.jdbc.mysql.Impl.RelationsDAO;
import com.solvd.pageranked.models.Links;
import com.solvd.pageranked.models.Matrix;
import com.solvd.pageranked.models.Nodes;
import com.solvd.pageranked.models.Relations;

public class MatrixCreator {
    public static Relations find(Relations relations) {
        RelationsDAO relationsDAO = new RelationsDAO();
        return relationsDAO.getAllRelations().stream()
                .filter(e1 -> e1.getNodesId() == relations.getNodesId() && e1.getLinksId() == relations.getLinksId())
                .findAny().orElse(null);
    }

    public static Matrix getMyMatrix() {
        return MatrixCreator.matrixFilling();
    }

    public static Matrix matrixFilling() {
        NodesDAO nodesDAO = new NodesDAO();
        LinksDAO linksDAO = new LinksDAO();
        int[][] matrix = new int[nodesDAO.getAllNodes().size()][nodesDAO.getAllNodes().size()];

        for (int i = 1; i <= nodesDAO.getAllNodes().size(); i++) {
            Nodes node = nodesDAO.getById(i);
            for (int j = 1; j <= nodesDAO.getAllNodes().size(); j++) {
                Nodes node1 = nodesDAO.getById(j);
                Links link = linksDAO.getIdByLinkHref(node1.getName());
                Relations relations = new Relations(0, node.getId(), link.getId());
                if (MatrixCreator.find(relations) != null) {
                    matrix[i - 1][j - 1] = 1;
                } else {
                    matrix[i - 1][j - 1] = 0;
                }
            }
        }
        return new Matrix(nodesDAO.getAllNodes().size(), matrix);
    }
}