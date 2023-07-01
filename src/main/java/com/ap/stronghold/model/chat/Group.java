package com.ap.stronghold.model.chat;

import com.ap.stronghold.model.User;

public class Group extends Chat{
    public Group(User owner, String id, String name) {
        super(owner, id, name);
        this.addMember(owner);
        this.addMessages(new Message(owner,"group created"));

    }
}
