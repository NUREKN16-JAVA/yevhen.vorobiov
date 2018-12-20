package ua.nure.vorobiov.usermanagement.agent;


import org.dbunit.util.search.SearchException;

import jade.core.Agent;
import ua.nure.vorobiov.usermanagement.User;
import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.DatabaseException;

import java.util.Collection;

public class SearchAgent extends Agent {

    @Override
    protected void setup() {
        super.setup();
        System.out.println(getAID().getName() + " started");
    }

    @Override
    protected void takeDown() {
        System.out.println(getAID().getName() + " terminated");
        super.takeDown();
    }

    public void search(String firstName, String lastName) throws SearchException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            if (users.size() > 0) {
                showUsers(users);
            } else {
            }
        } catch (DatabaseException e) {
            throw new SearchException(e);
        }
    }

    private void showUsers(Collection<User> users) {

    }
}
