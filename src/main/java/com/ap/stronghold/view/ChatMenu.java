package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.model.User;
import com.ap.stronghold.model.chat.*;
import com.ap.stronghold.view.enums.commands.Regexes;

import javax.swing.plaf.synth.ColorType;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatMenu extends Thread{
    private Chat chat;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String ip;
    private int port;
    public ChatMenu(Chat chat, String ip, int port) throws IOException, ClassNotFoundException {
        this.chat = chat;
        this.ip = ip;
        this.port = port;
    }


    @Override
    public synchronized void run()  {
        System.out.println("Starting Client service...");
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sendAllInformation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String command = MainMenu.getScanner().nextLine();
        try {
            getAllInformation();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Pattern sendMessagePattern = Pattern.compile(Regexes.SEND_MESSAGE.getRegex());
        Pattern addMemberPattern = Pattern.compile(Regexes.ADD_MEMBER.getRegex());
        Pattern editMessagePattern = Pattern.compile(Regexes.EDIT_MESSAGE.getRegex());
        Pattern deleteMessagePattern = Pattern.compile(Regexes.DELETE_MESSAGE.getRegex());
        Pattern putReactionPattern = Pattern.compile(Regexes.PUT_REACTION.getRegex());
        Pattern createGroupPattern = Pattern.compile(Regexes.CREATE_GROUP.getRegex());
        Pattern createPrivateChatPattern = Pattern.compile(Regexes.CREATE_PRIVATE_CHAT.getRegex());
        Matcher matcher;
        while (true) {
            try {
                getAllInformation();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (command.matches("back")) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            else if (command.matches(Regexes.SEND_MESSAGE.getRegex())) {
                matcher = sendMessagePattern.matcher(command);
                System.out.println(sendMessage(matcher));
            } else if (command.matches(Regexes.ADD_MEMBER.getRegex())) {
                matcher = addMemberPattern.matcher(command);
                try {
                    System.out.println(addMember(matcher));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.matches(Regexes.SHOW_ALL_MESSAGES.getRegex())) {
                try {
                    showMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.matches(Regexes.SHOW_ALL_MEMBERS.getRegex())) {
                try {
                    showMembers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.matches(Regexes.EDIT_MESSAGE.getRegex())) {
                matcher = editMessagePattern.matcher(command);
                editMessage(matcher);
            } else if (command.matches(Regexes.DELETE_MESSAGE.getRegex())) {
                matcher = deleteMessagePattern.matcher(command);
                removeMessage(matcher);
            } else if (command.matches(Regexes.PUT_REACTION.getRegex())) {
                matcher = putReactionPattern.matcher(command);
                putReaction(matcher);
            } else if (command.matches(Regexes.SHOW_FRIENDSHIP_REQUESTS.getRegex()))
                showFriendShipRequests();
            else if (command.matches(Regexes.CREATE_GROUP.getRegex())) {
                matcher = createGroupPattern.matcher(command);
                createGroup(matcher);
            }
            else if (command.matches(Regexes.CREATE_PRIVATE_CHAT.getRegex())) {
                matcher = createPrivateChatPattern.matcher(command);
                createPrivateChat(matcher);
            }

            else System.out.println("Invalid command!");
            try {
                sendAllInformation();
            } catch (IOException e) {
                e.printStackTrace();
            }
            command = MainMenu.getScanner().nextLine();
        }
    }

    private synchronized void sendAllInformation() throws IOException {
        dataOutputStream.writeUTF("save");
        dataOutputStream.writeUTF(User.saveJson());
    }

    private synchronized void getAllInformation() throws IOException, ClassNotFoundException {
        dataOutputStream.writeUTF("load");
        if (dataInputStream.available() != 0) {
            User.loadJsonString(dataInputStream.readUTF());
        }
    }

    private synchronized void createPrivateChat(Matcher matcher) {
        matcher.find();
        User user;
        if ((user = User.findUserByUsername(matcher.group("id"))) == null) {
            System.out.println("no username with this id exists");
            return;
        } else if (Controller.getLoggedInUser().getAllChat().findChatsOfUser(matcher.group("id")) !=null) {
            System.out.println("you have a chat with this id(actually with this username)");
            return;
        }
        PrivateChat privateChat = new PrivateChat(Controller.getLoggedInUser(),matcher.group("id"),matcher.group("name"));
        Controller.getLoggedInUser().getAllChat().addChat(privateChat);
        user.getAllChat().addChat(privateChat);
        System.out.println("private chat created");
    }

    private synchronized void createGroup(Matcher matcher) {
        matcher.find();
        if (AllChat.findChatInAll(matcher.group("id")) != null) {
            System.out.println("chat already exists");
            return;
        }
        Controller.getLoggedInUser().getAllChat().addChat(new Group(Controller.getLoggedInUser(),matcher.group("id"),matcher.group("name")));
        System.out.println("group created");
    }

    private synchronized void showFriendShipRequests() {
        //sending request to server
    }


    public synchronized String showMessages() throws IOException {
        //dataOutputStream.writeUTF("show messages");

        System.out.println("Messages:");
        int count = 1;
        for (Message message : chat.getMessages()) {
            System.out.println(count + " - message from : " + (message.getOwner() == null ? "" : message.getOwner().getUsername()) +
                    "(" + (message.getOwner() == null ? "" :message.getOwner().getNickname()) + ")\n"
                    + ": \"" + message.getContent() + "\"\n\t\t\t" + message.getMessageTime());
            count++;
        }
        return "correct";
    }

    public synchronized String showMembers() throws IOException {
        //dataOutputStream.writeUTF("show members");
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

    public String addMember(Matcher matcher) throws IOException {
        //dataOutputStream.writeUTF("add member");
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

    public synchronized String sendMessage(Matcher matcher) {
        matcher.find();
            chat.addMessages(new Message(Controller.getLoggedInUser(), matcher.group("message")));
            addChatToFirst(matcher.group("message"));
            return "Message has been sent successfully!";
    }

    private synchronized void addChatToFirst(String message) {
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

    public synchronized void editMessage(Matcher matcher) {
        matcher.find();
        int number = Integer.parseInt(matcher.group("number")) - 1;
        if (number < 0 || number > chat.getMessages().size() - 1) {
            System.out.println("you have entered invalid number for message to edit");
            return;
        } else if (!chat.getMessages().get(number).getOwner().equals(Controller.getLoggedInUser())) {
            System.out.println("you can not change someone else message");
            return;
        }
        String message = chat.getMessages().get(number).getContent();
        System.out.println("enter new message the last content is :" + message);
        String newMessage = MainMenu.getScanner().nextLine();
        if (message.equals(newMessage)) {
            System.out.println("new message is equal to old one!");
            return;
        }
        chat.getMessages().get(number).setMessageTime();
        chat.getMessages().get(number).setContent("*** edited message ***\n" + newMessage);
        System.out.println("message edited");
    }

    public synchronized void removeMessage(Matcher matcher) {
        matcher.find();
        int number = Integer.parseInt(matcher.group("number")) - 1;
        if (number < 0 || number > chat.getMessages().size() - 1) {
            System.out.println("you have entered invalid number for message to remove");
            return;
        } else if (!chat.getMessages().get(number).getOwner().equals(Controller.getLoggedInUser())) {
            System.out.println("you can not remove someone else message");
            return;
        }
        chat.getMessages().remove(number);
        System.out.println(("message removed"));
    }

    public synchronized void putReaction(Matcher matcher) {
        matcher.find();
        int number = Integer.parseInt(matcher.group("number")) - 1;
        if (number < 0 || number > chat.getMessages().size() - 1) {
            System.out.println("you have entered invalid number for message to remove");
            return;
        }
        chat.getMessages().get(number).setReactions("sender : " + Controller.getLoggedInUser().getUsername()
                + "reaction : " + matcher.group("reaction") + "\n");
    }

}
