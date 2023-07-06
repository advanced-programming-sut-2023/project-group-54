package com.ap.stronghold.model.chat;

import com.ap.stronghold.model.User;

import java.util.ArrayList;

public class PublicChat extends Chat{

    public PublicChat() {
        super(null, "1", "public chat");
        for (User user : User.getUsers())
            this.addMember(user);
    }
}
