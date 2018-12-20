package ua.nure.vorobiov.usermanagement.agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.vorobiov.usermanagement.User;

import java.util.Collection;
import java.util.Objects;

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
        return null;
    }
}
