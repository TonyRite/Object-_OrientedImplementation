package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
      private static ArrayList<contact> contacts;
    private static Scanner scanner;
    private  static  int id = 0;

    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("Welcome to my humble world of programming ");
        showInitialOptions();
    }
    private static  void showInitialOptions(){
        System.out.println("Please select one: "+
                "\n\t1. Manage Contacts"+
                "\n\t2. Messages "+
                "\n\t3. Quit");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;

        }
    }

    private static  void manageContacts(){
        System.out.println("please select one:" +
                "\n\t1. show all contacts " +
                "\n\t2. add a new contact" +
                "\n\t3. search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllcontacs();
                break;
            case 2:
                addNewcontacts();
                break;
            case 3:
                SearchForContacts();
                break;
            case 4:
                deleteContacts();
                break;
            default:
                showInitialOptions();
                break;
        }
    }
    private static void addNewcontacts(){
        System.out.println("Adding a new contact ..."+
                "Please enter the new contact name.. ");
        String name = scanner.next();
        System.out.println("Please enetr the contact's number");
        String number = scanner.next();
        System.out.println("Please enter email");
        String email = scanner.next();

        if(name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter all of the information");
            addNewcontacts();
        }
        else{
            boolean doesExist = false;
            for(contact c:contacts){
                if (c.getName().equals(name)) {
                    doesExist = true;
                }
        }
            if(doesExist){
                System.out.println("We have  contact named" + name + " Saved on this device");
                addNewcontacts();
            }else{
                contact contact = new contact(name, number, email);
                contacts.add(contact);
                System.out.println("name added successfully");
            }
        }
        showInitialOptions();
    }

    private static  void SearchForContacts(){
        System.out.println("Please enter the contact name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter name");
            SearchForContacts();
        }else{
            boolean doesExist = false;
            for( contact c:contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if(!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }
    private  static void deleteContacts(){
        System.out.println("please enter the name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter name");
            deleteContacts();
        }else{
            boolean doesExist = false;
            for(contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                }

            }
            if (!doesExist){
                System.out.println("there is no such contact");
            }
        }
        showInitialOptions();

    }
    private  static void showAllcontacs(){
        if( contacts.size()>0){
            for( contact c: contacts){
                c.getDetails();
                System.out.println("********************************");
            }
           showInitialOptions();
        }else{
            System.out.println("You do no have any Contacts");
        }

    }
    private static  void manageMessages(){
        System.out.println("Please select one"+
                "\n\t1. Show all messages"+
                "\n\t2. Send a new messsage"+
                "\n\t3. Go back ");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                showAllmessages();
                break;
            case 2:
                sendNewmessages();
                break;
            default:
                showAllcontacs();
                break;

        }
    }

    private static void showAllmessages(){
        ArrayList<Message> allmessages = new ArrayList<>();
        for( contact c:contacts){
            allmessages.addAll(c.getMessages());
        }if(allmessages.size() > 0){
            for(Message m: allmessages){
                m.getDetails();
                System.out.println("**************");
            }
        }else{
            System.out.println("You dont have any messages");
            showInitialOptions();
        }

    }

    private  static  void sendNewmessages(){
        System.out.println("Who are you going to send the message");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("please enter name of contact");
            sendNewmessages();
        }else{
            boolean doesExits = false;
            for(contact c: contacts){
                if(c.getName().equals(name)){
                    doesExits = true;
                }
            }
            if(doesExits){
                System.out.println("What are yu going to say?");
                String text = scanner.next();
                if (text.equals("")) {
                    System.out.println("Please enter some text message");
                    sendNewmessages();
                }else{
                  id++;
                  Message newmessage = new Message(text,name,id);
                  for(contact c: contacts){
                      if(c.getName().equals(name)){
                          ArrayList<Message> newMessage = c.getMessages();
                          newMessage.add(newmessage);
                         c.setMessages(newMessage);
                      }
                  }
                }
            }else{
                System.out.println("There is no such contact") ;
            }
        }
        showInitialOptions();

    }
}
