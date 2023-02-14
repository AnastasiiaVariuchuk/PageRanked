package com.solvd.pageranked.dao.jdbc.mysql.Impl;

import com.solvd.pageranked.connection.ConnectionUtil;
import com.solvd.pageranked.dao.ILinks;
import com.solvd.pageranked.models.Links;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinksDAO implements ILinks {
    private static final Logger LOGGER = LogManager.getLogger(LinksDAO.class);
    private static final String INSERT = "INSERT INTO Links VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE Links SET linkHref = ?, linkText = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Links WHERE id = ";
    private static final String DELETE_ALL = "DELETE FROM Links";
    private static final String GET_BY_ID = "SELECT * FROM Links WHERE id = ?";
    private static final String GET_BY_LINK_HREF = "SELECT * FROM Links WHERE linkHref = ?";
    private static final String GET_ALL = "SELECT * FROM Links";

    @Override
    public Links getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Links links = new Links();
                links.setId(resultSet.getInt("id"));
                links.setLinkHref(resultSet.getString("linkHref"));
                links.setLinkText(resultSet.getString("linkText"));
                return links;
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
    public Links getIdByLinkHref(String linkHref) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_LINK_HREF);
            preparedStatement.setString(1, linkHref);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Links links = new Links();
                links.setId(resultSet.getInt("id"));
                links.setLinkHref(resultSet.getString("linkHref"));
                links.setLinkText(resultSet.getString("linkText"));
                return links;
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
    public List<Links> getAllLinks() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            List<Links> links = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Links link = new Links();
                link.setId(resultSet.getInt("id"));
                link.setLinkHref(resultSet.getString("linkHref"));
                link.setLinkText(resultSet.getString("linkText"));
                links.add(link);
            }
            return links;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public void deleteAllLinks() {
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
    public void addLink(Links links) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, links.getId());
            preparedStatement.setString(2, links.getLinkHref());
            preparedStatement.setString(3, links.getLinkText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void updateLink(Links links) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, links.getLinkHref());
            preparedStatement.setString(2, links.getLinkText());
            preparedStatement.setInt(3, links.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionUtil.close(preparedStatement);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void deleteLink(int id) {
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
