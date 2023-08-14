package com.test.hua.model.outgoing;

/**
 * Represents one single latest conversation with a user
 */
public class Conversation {

    private String avatar;
    private String firstName;
    private String lastName;
    private int totalMessages;
    private String userId;

    private LatestMessage mostRecentMessage;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LatestMessage getMostRecentMessage() {
        return mostRecentMessage;
    }

    public void setMostRecentMessage(LatestMessage mostRecentMessage) {
        this.mostRecentMessage = mostRecentMessage;
    }

}
