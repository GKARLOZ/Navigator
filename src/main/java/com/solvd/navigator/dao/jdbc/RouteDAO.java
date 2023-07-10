package com.solvd.navigator.dao.jdbc;

import com.solvd.navigator.connection.ConnectionPool;
import com.solvd.navigator.dao.IRouteDAO;
import com.solvd.navigator.model.Route;
import com.solvd.navigator.model.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements IRouteDAO {
    private static final Logger logger = LogManager.getLogger("RouteDAO");
    private static final String SELECT_ALL = "SELECT * FROM Routes";
    private static final String SELECT_BY_ID = "SELECT * FROM Routes WHERE id = ?";
    private static final String SELECT_BY_LOC_ID = "SELECT * FROM Routes WHERE location_a = ? AND location_b=?";
    private static final String SELECT_BY_LOCAT_ID = "SELECT * FROM Routes WHERE location_a = ?";
    private static final String INSERT = "INSERT INTO Routes (id, location_a, location_b, duration, transportation_id, cost, distance) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Routes SET location_a=?, location_b=?, duration=?, transportation_id=?, cost=?, distance=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Routes WHERE id = ?";
    @Override
    public Route getById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Route route = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            route = fillRouteByResultSet(resultSet);

        } catch (SQLException | InterruptedException | IOException e)  {
            logger.error("Error query: "+ SELECT_BY_ID+ " cause: "+e.getCause());
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return route;
    }

    @Override
    public List<Route> getAll() {
        List<Route> routes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                routes.add(fillRouteByResultSet(resultSet));
            }

        } catch (SQLException | InterruptedException | IOException e) {
            logger.error("Error executing query: "+SELECT_ALL+ "error cause: "+ e.getCause());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }

            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return routes;
    }


    @Override
    public void insert(Route route) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,route.getId());
            statement.setLong(2,route.getLocationA().getId());
            statement.setLong(3,route.getLocationB().getId());
            statement.setInt(4, route.getDuration());
            statement.setLong(5,route.getTransportation().getId());
            statement.setInt(6, route.getCost());
            statement.setInt(7, route.getDistance());
            statement.executeUpdate();
            logger.info("Record created");

        } catch (SQLException | InterruptedException | IOException e)  {
            logger.error("Error query: "+ INSERT+ " error cause: "+e.getCause()+ e.getStackTrace());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(Route route){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //location_a=?, location_b=?, duration=?, transportation_id=?, cost=?, distance=?
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setLong(1, route.getLocationA().getId());
            statement.setLong(2, route.getLocationB().getId());
            statement.setInt(3,route.getDuration());
            statement.setLong(4,route.getTransportation().getId());
            statement.setInt(5, route.getCost());
            statement.setInt(6, route.getDistance());
            statement.setLong(7, route.getId());
            statement.executeUpdate();
            logger.info("Record created");
            statement.close();
        } catch (SQLException | InterruptedException | IOException e)  {
            logger.error("Error query: "+ UPDATE+ " error cause: "+e.getCause());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Record deleted");

        } catch (SQLException | InterruptedException | IOException e) {
            logger.error("Error query: "+ DELETE+ " error cause: "+e.getCause());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Route getRouteByLocationsId(long locationAId, long locationBId) {
        Connection connection = null;
        PreparedStatement statement = null;
        Route route = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_LOC_ID);
            statement.setLong(1, locationAId);
            statement.setLong(2, locationBId);
            resultSet = statement.executeQuery();
            resultSet.next();
            route = fillRouteByResultSet(resultSet);

        } catch (SQLException | InterruptedException | IOException e)  {
            logger.error("Error query: "+ SELECT_BY_LOC_ID+ " cause: "+e.getCause());
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return route;
    }

    @Override
    public List<Route> getAllRoutesByLocationId(long locationId) {

        List<Route> routes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_LOCAT_ID);
            preparedStatement.setLong(1, locationId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                routes.add(fillRouteByResultSet(resultSet));
            }

        } catch (SQLException | InterruptedException | IOException e) {
            logger.error("Error executing query: "+SELECT_BY_LOCAT_ID+ "error cause: "+ e.getCause());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing statement. Error code: "+e.getErrorCode());
            }

            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return routes;

    }

    private Route fillRouteByResultSet(ResultSet resultSet) {
        Route route= null;
        try {
            route = new Route();
            route.setId(resultSet.getLong("id"));
            route.setDuration(resultSet.getInt(4));
            route.setCost(resultSet.getInt(6));
            route.setDistance(resultSet.getInt(7));

            route.getLocationA().setId(resultSet.getLong("location_a"));
            route.getLocationB().setId(resultSet.getLong("location_b"));
            route.getTransportation().setId(resultSet.getLong("transportation_id"));

        } catch (SQLException e) {
            logger.error("SQL Exception"+e.getErrorCode());
        }
        return route;
    }
}
