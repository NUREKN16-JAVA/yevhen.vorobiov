package ua.nure.vorobiov.usermanagement.agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.vorobiov.usermanagement.User;
import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.DatabaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.StringTokenizer;

public class RequestServer extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage message = myAgent.receive();
        if (Objects.nonNull(message)) {
            if (message.getPerformative() == ACLMessage.REQUEST) {
                myAgent.send(createReply(message));
            } else {
                Collection<User> users = parseMessage(message);
                ((SearchAgent) myAgent).showUsers(users);
            }
        } else {
            block();
        }
    }

    private Collection<User> parseMessage(ACLMessage message) {
        return null;
    }

    private ACLMessage createReply(ACLMessage message) {
        ACLMessage reply = message.createReply();
        String content = message.getContent();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        if (tokenizer.countTokens() == 2) {
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            Collection<User> users;
            try {
                users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            } catch (DatabaseException e) {
                users = new ArrayList<>();
                e.printStackTrace();
            }
            reply.setContent(convertToString(users));
        }
        return reply;
    }

    private String convertToString(Collection<User> users) {
        StringBuilder builder = new StringBuilder();
        users.forEach(user -> {
            builder.append(user.getId()).append(",");
            builder.append(user.getFirstName()).append(",");
            builder.append(user.getLastName()).append(";");
        });
        return builder.toString();
    }
}
