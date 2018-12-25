package ua.nure.vorobiov.usermanagement.agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.vorobiov.usermanagement.User;
import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.DatabaseException;

import java.util.*;
import java.util.function.Function;

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
        Collection<User> users = new LinkedList<>();
        String content = message.getContent();
        if (Objects.nonNull(content) && !content.isEmpty()) {
            Arrays.stream(content.split(";")).map(getUserMappingFunction()).forEach(users::add);
        }
        return users;
    }

    private Function<String, User> getUserMappingFunction() {
        return s -> {
            StringTokenizer tokenizer = new StringTokenizer(s, ",");
            String id = tokenizer.nextToken();
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            return new User(Long.parseLong(id), firstName, lastName, null);
        };
    }

    private ACLMessage createReply(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.INFORM);
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
