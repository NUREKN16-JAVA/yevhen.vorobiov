package ua.nure.vorobiov.usermanagement.db;

import ua.nure.vorobiov.usermanagement.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class HsqldbUserDao implements UserDao {

    private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    private ConnectionFactory connectionFactory;

    public HsqldbUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            int count = 1;
            preparedStatement.setString(count++, user.getFirstName());
            preparedStatement.setString(count++, user.getLastName());
            preparedStatement.setDate(count, (Date) user.getDateOfBirth());
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows != 1) {
                throw new DatabaseException("Number of the inserted rows: " + insertedRows);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(User user) throws DatabaseException {

    }

    @Override
    public void delete(User user) throws DatabaseException {

    }

    @Override
    public User find(Long id) throws DatabaseException {
        return null;
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        return null;
    }
}
