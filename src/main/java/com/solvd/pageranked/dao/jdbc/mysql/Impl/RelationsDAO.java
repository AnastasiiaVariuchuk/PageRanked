package com.solvd.pageranked.dao.jdbc.mysql.Impl;

import com.solvd.pageranked.connection.ConnectionUtil;
import com.solvd.pageranked.dao.IRelations;
import com.solvd.pageranked.models.Nodes;
import com.solvd.pageranked.models.Relations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationsDAO implements IRelations {
    private static final Logger LOGGER = LogManager.getLogger(Relations.class);
    private static final String INSERT = "INSERT INTO Relations VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Relations SET Nodes_id = ?, Links_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Relations WHERE id = ";
    private static final String DELETE_ALL = "DELETE FROM Relations";
    private static final String GET_BY_NODE_AND_LINK = "SELECT * FROM Relations WHERE Nodes_id = ?, Links_id = ?";
    private static final String GET_ALL = "SELECT * FROM Relations";

    @Override
    public Relations getByNodeAndLink(int nodesId, int linksId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_NODE_AND_LINK);
            preparedStatement.setInt(1, nodesId);
            preparedStatement.setInt(2, linksId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Relations relations = new Relations();
                relations.setId(resultSet.getInt("id"));
                relations.setNodesId(resultSet.getInt("Nodes_id"));
                relations.setLinksId(resultSet.getInt("Links_id"));
                return relations;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<Relations> getAllRelations() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            List<Relations> relations = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Relations relation = new Relations();
                relation.setId(resultSet.getInt("id"));
                relation.setNodesId(resultSet.getInt("Nodes_id"));
                relation.setLinksId(resultSet.getInt("Links_id"));
                relations.add(relation);
            }
            return relations;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public void deleteAllRelations() {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_ALL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void addRelations(Relations relations) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, relations.getId());
            preparedStatement.setInt(2, relations.getNodesId());
            preparedStatement.setInt(3, relations.getLinksId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void updateRelations(Relations relations) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, relations.getNodesId());
            preparedStatement.setInt(2, relations.getLinksId());
            preparedStatement.setInt(3, relations.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void deleteRelations(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE + id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }
}
