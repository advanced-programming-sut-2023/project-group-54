package com.ap.stronghold.model.chat;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.model.User;

public class PrivateChat extends Chat{
    public PrivateChat(User owner, String id, String name) {
        super(owner, id, name);
        this.addMember(owner);
        this.addMember(User.findUserByUsername(id));
        this.addMessages(new Message(owner,"private chat created"));
    }

    public String getId2() {
        return this.getId();
    }

    public String getName2() {
        return this.getName();
    }
}
