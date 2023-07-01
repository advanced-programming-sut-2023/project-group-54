package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.model.User;
import com.ap.stronghold.model.chat.*;
import com.ap.stronghold.view.enums.commands.Regexes;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.sound.sampled.Control;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatMenu {
    private Chat chat;

    public ChatMenu(Chat chat) {
        this.chat = chat;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Pattern sendMessagePattern = Pattern.compile(Regexes.SEND_MESSAGE.getRegex());
        Pattern addMemberPattern = Pattern.compile(Regexes.ADD_MEMBER.getRegex());
        Matcher matcher;
        while (true) {
            if (command.matches("back")) return;
            else if (command.matches(Regexes.SEND_MESSAGE.getRegex())) {
                matcher = sendMessagePattern.matcher(command);
                System.out.println(sendMessage(matcher));
            } else if (command.matches(Regexes.ADD_MEMBER.getRegex())) {
                matcher = addMemberPattern.matcher(command);
                System.out.println(addMember(matcher));
            } else if (command.matches(Regexes.SHOW_ALL_MESSAGES.getRegex())) {
                showMessages();
            } else if (command.matches(Regexes.SHOW_ALL_MEMBERS.getRegex())) {
                showMembers();
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
        if (chat instanceof PublicChat)
            publicChatRun();
        else if (chat instanceof PrivateChat)
            privateChatRun();
        else if (chat instanceof Group)
            groupRun();
    }


    public String showMessages() {
        System.out.println("Messages:");
        for (Message message : chat.getMessages()) {
            System.out.println("message from : " + message.getOwner().getUsername() + "(" + message.getOwner().getNickname() + ")\n"
                    + ": \"" + message.getContent() + "\"\n\t\t\t" + message.getMessageTime());
        }
        return "correct";
    }

    public String showMembers() {
        if (chat instanceof PrivateChat) {
            System.out.println("Invalid command!");
            return "incorrect";
        }
        System.out.println("Members:");
        for (User user : chat.getMember()) {
            if (user.equals(chat.getOwner()))
                System.out.println("name: " + user.getUsername() + ", nickname: " + user.getNickname() + " *owner");
            else System.out.println("name: " + user.getUsername() + ", nickname: " + user.getNickname());
        }
        return "correct";
    }

    public String addMember(Matcher matcher) {
        matcher.find();
        User user = User.findUserByUsername(matcher.group("id"));
        if (user == null) return "No user with this id exists!";
        if (chat instanceof PrivateChat || chat instanceof PublicChat) return "Invalid command!";
        if (!chat.getOwner().equals(Controller.getLoggedInUser())) return "You don't have access to add a member!";
        if (chat.findMember(user) != null) return "This user is already in the chat!";
        user.getAllChat().addChat(chat);
        chat.addMember(user);
        chat.addMessages(new Message(Controller.getLoggedInUser(), user.getUsername() + " has been added to the group!"));
        addChatToFirst(matcher.group("id"));
        return "User has been added successfully!";
    }

    public String sendMessage(Matcher matcher) {
        matcher.find();
            chat.addMessages(new Message(Controller.getLoggedInUser(), matcher.group("message")));
            addChatToFirst(matcher.group("message"));
            return "Message has been sent successfully!";
    }

    private void addChatToFirst(String message) {
        if (chat instanceof PrivateChat) {
            Controller.getLoggedInUser().getAllChat().getAllChatsOfUser().remove(chat);
            Controller.getLoggedInUser().getAllChat().addChat(chat);
            User user2 = User.findUserByUsername(chat.getId());
            int index;
            for (Chat chat1 : user2.getAllChat().getAllChatsOfUser()) {
                if (chat1 instanceof PrivateChat) {
                    if (chat1.getId().equals(Controller.getLoggedInUser().getUsername())) {
                        if (!Controller.getLoggedInUser().equals(user2)) chat1.addMessages(new Message(Controller.getLoggedInUser(), message));
                        index = user2.getAllChat().getAllChatsOfUser().indexOf(chat1);
                        user2.getAllChat().addChat(chat1);
                        user2.getAllChat().getAllChatsOfUser().remove(index + 1);
                        break;
                    }
                }
            }
        } else {
            for (User user : chat.getMember()) {
                user.getAllChat().getAllChatsOfUser().remove(chat);
                user.getAllChat().addChat(chat);
            }
        }
    }

    private void groupRun() {

    }

    private void privateChatRun() {

    }

    private void publicChatRun() {

    }
}
