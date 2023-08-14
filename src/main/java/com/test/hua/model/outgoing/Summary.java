package com.test.hua.model.outgoing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// The batch object that will be posted to target
public class Summary {

    private static final Comparator<Conversation> COMPARATOR = (con1, con2) ->
            Long.compare(con2.getMostRecentMessage().getTimestamp(), con1.getMostRecentMessage().getTimestamp());


    private List<Conversation> conversations;
    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Summary(List<Conversation> conversations) {
        this.conversations = conversations;
        Collections.sort(conversations, COMPARATOR);
    }

    public Summary() {
         conversations = new ArrayList<>();
    }

    public void addConversation(Conversation conversation) {
        conversations.add(conversation);
        Collections.sort(conversations, COMPARATOR);
    }

}
