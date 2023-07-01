package com.ap.stronghold.model.chat;

import com.ap.stronghold.model.User;

import java.util.ArrayList;

public class PublicChat extends Chat{

    public PublicChat( String id, String name) {
        super(null, id, name);
        for (User user : User.getUsers())
            this.addMember(user);
    }
}
