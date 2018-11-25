package ua.nure.vorobiov.usermanagement.db;

import ua.nure.vorobiov.usermanagement.User;

import java.util.Collection;

public class HsqldbUserDao implements UserDao {
    @Override
    public User create(User user) throws DatabaseException {
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
