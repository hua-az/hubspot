package com.test.hua.model.incoming;

import com.test.hua.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Captures the whole batch of incoming data from source
 */
public class MessageHistory {

    private String userId;
    private List<User> users;
    private List<IncomingMessage> messages;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<IncomingMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<IncomingMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
