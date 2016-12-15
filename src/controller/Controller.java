package controller;

/**
 * Created by Christofferpalsgaard on 24/11/2016.
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import model.Book;
import model.Curriculum;
import sdk.Connection;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Controller {


    Scanner input;


    public Controller() {
        input = new Scanner(System.in);
    }

    private int userID;
    private String tokenId;


    public void mainMenu() {

        System.out.println("Welcome to the mainmenu");
        System.out.println("\nYou have the following choices");
        System.out.println("Login");
        System.out.println("Create user");

        try {
            switch (input.nextInt()) {
                case 1:
                    String username, password;

                    System.out.println("username:");
                    username = input.nextLine();
                    username = input.nextLine();

                    System.out.println("password:");
                    password = input.nextLine();

                    String token = Connection.authorizeLogin(username, password);

                    JsonParser parse = new JsonParser();

//                JsonArray testm = (JsonArray) parse.parse(token);
                  //  JsonObject user = (JsonObject) testm.get(0);
              //    userID = user.get("userID").getAsInt();

                 //  String temp=testm.get(1).toString();
                 // tokenId = temp.substring(1,temp.length()-1);
                    if (token != null) {
                        userMenu();

                    } else
                        System.out.println("I didn't work at all");
                    mainMenu();
                    break;
                case 2:
                    createUser();
                    mainMenu();
            }

        } catch (InputMismatchException e) {
            System.out.println("Wrong, you try again!");
            input.nextLine();
            mainMenu();

        }
    }


    public void createUser() {
        JsonObject data = new JsonObject();

        Scanner input = new Scanner(System.in);

        System.out.println("Input your firstname");
        data.addProperty("firstName", input.nextLine());

        System.out.println("Input your Lastname");
        data.addProperty("lastName", input.nextLine());

        System.out.println("Input your Email");
        data.addProperty("email", input.nextLine());

        System.out.println("Input your Username");
        data.addProperty("userName", input.nextLine());

        System.out.println("Input your Password");
        data.addProperty("password", input.nextLine());

        data.addProperty("usertype", "0");

        Connection.postUser(data);


    }






    public void userMenu() {
        System.out.println("\nI Welcome you to a world of books");
        System.out.println("1) Print a specific book");
        System.out.println("2) Print all books");
        System.out.println("3) Print all curriculums");
        System.out.println("4) Change your user info");
        System.out.println("5) Delete your user");
        System.out.println("6) Logout");

        switch (input.nextInt()) {
            case 1:
                printBook();
                userMenu();
                break;

            case 2:
                printBooks();
                userMenu();
                break;

            case 3:
                printcurriculums();
                userMenu();
                break;
            case 4:
                changeUserInfo();
                userMenu();
                break;
            case 5:
                deleteUser();
                userMenu();
                break;
            case 6:
                logout();
                mainMenu();


                default:
                    System.out.println("Intet");
                break;
        } while (true);

    }
    private void printBook() {
        System.out.println("\nWhat is the BookID");
        Book book = Connection.getBook(input.nextInt());
        System.out.println("\nId: " + book.getBookID() + "\nTitle: " + book.getTitle() + "\nPrice on Acedemic Books: " +
                book.getPriceAB() + "\nPrice on Saxo: " + book.getPriceSAXO() + "\nPrice on Cdon: " + book.getPriceCDON() + "\n");
    }


    public void printBooks() {
        ArrayList<Book> books = Connection.getBooks();
        System.out.println("books");
        for (Book book : books) {
            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
        }
    }

    public void printcurriculums() {
        ArrayList<Curriculum> curriculums = Connection.getCurriculum();
        System.out.println("curriculumms");
        for (Curriculum curriculum : curriculums) {
            System.out.println("id: " + curriculum.getCurriculumID() + "Uddannelse:" + curriculum.getEducation());
        }


        int showSpecificCurriculum;


        do {
            System.out.println("\ninput the id of the curriculum you want to have shown");
            while (!input.hasNextInt()) {
                System.out.println("\ntry again please, remember its an id(number) show in the curriculum list above");
                input.next();

            }
            showSpecificCurriculum = Integer.parseInt((input.next()));

        } while (showSpecificCurriculum <= 0 || showSpecificCurriculum > curriculums.size());

        ArrayList<Book> curriculumBooks = Connection.getCurriculumBooks(showSpecificCurriculum);

        System.out.println("\nHere are all the current books for your chosen semester," +
                "\nyou can choose too look at specific book details or go back to the usermenu" +
                "\nPress one of the following" +
                "\n1: For specific prices and info" +
                "\n2: Go back to UserMenu");


        for (Book book : curriculumBooks)
            System.out.println("ID: " + book.getBookID() + "Title: " + book.getTitle() + " ISBN: " + book.getISBN());
        switch (input.nextInt()) {

            case 1:
                printBook();
                break;
            case 2:
                userMenu();
                break;
            default:
                System.out.println("please type 1 or 2");

        }

    }




    public void changeUserInfo() {
Scanner input = new Scanner(System.in);

        System.out.println("Type in the areas you want to change");

        JsonObject data = new JsonObject();

        System.out.println("type first name");
        data.addProperty("firstName", input.nextLine());

        System.out.println("type first name");
        data.addProperty("lastName", input.nextLine());

        System.out.println("type first name");
        data.addProperty("email", input.nextLine());

        System.out.println("type first name");
        data.addProperty("userName", input.nextLine());

        System.out.println("type first name");
        data.addProperty("password", input.nextLine());

        data.addProperty("userType", "0");
        Connection.putUser(tokenId, data, userID);

    }

    public void deleteUser() {

    }

    public void logout() {
    mainMenu();
    }
}
