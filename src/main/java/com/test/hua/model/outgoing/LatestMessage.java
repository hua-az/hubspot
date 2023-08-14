package com.test.hua.model.outgoing;

import com.test.hua.model.Message;

/**
 * The data about the most recent message in a conversation
 */
public class LatestMessage extends Message {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
