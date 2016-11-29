package controller;

/**
 * Created by Christofferpalsgaard on 24/11/2016.
 */
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


    public void mainMenu() {

        System.out.println("Welcome to the mainmenu");
        System.out.println("You have the following choices");
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
                    if (token != null) {
                        userMenu();

                    } else
                        System.out.println("I didn't work at all");
            }

        } catch (InputMismatchException e) {
            System.out.println("Wrong, you try again!");
            input.nextLine();
            mainMenu();

        }
    }

//    public void menu(){
//
//
////        do {
////            System.out.println("Velkommen til Biancas bogklub");
////            System.out.println("1) Print en bog");
////            System.out.println("2) Print alle bøger");
////            switch (input.nextInt()) {
////                case 1:
////                    printBook();
////                    break;
////                case 2:
////                    printBooks();
////                    break;
////                default:
////                    System.out.println("Indtast enten 1 eller 2");
////            }
////        }while(true);//Brug noget andet en true. CurrentUser != null
//
//
//        String username, password;
//        System.out.println("Login");
//        System.out.println("Iindtast username");
//        username = input.nextLine();
//        System.out.println("Iindtast password");
//        password = input.nextLine();
//
//        String token = Connection.authorizeLogin(username, password);
//        if(token != null){
//            do {
//                System.out.println("Velkommen til Biancas bogklub");
//                System.out.println("1) Print en bog");
//                System.out.println("2) Print alle bøger");
//                switch (input.nextInt()) {
//                    case 1:
//                        printBook();
//                        break;
//                    case 2:
//                        printBooks();
//                        break;
//                    default:
//                        System.out.println("Indtast enten 1 eller 2");
//                }
//            }while(true);//Brug noget andet en true. CurrentUser != null
//        }
//        else {
//            System.out.println("user pas fejl");
//        }
//
//
//    }



    public void userMenu() {
        System.out.println("I Welcome you to a world of books");
        System.out.println("1) Print a specific book");
        System.out.println("2) Print all books");
        System.out.println("3) Print all curriculums");

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


                default:
                    System.out.println("Intet");
                break;
        } while (true);

    }
    private void printBook() {
        System.out.println("What is the BookID");
        Book book = Connection.getBook(input.nextInt());
        System.out.println("id " + book.getBookID() + " title " + book.getTitle());
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
            System.out.println("input the id of the curriculum you want to have shown");
            while (!input.hasNextInt()) {
                System.out.println("try again please, remember its an id(number) show in the curriculum list above");
                input.next();

            }
            showSpecificCurriculum = Integer.parseInt((input.next()));

        } while (showSpecificCurriculum <= 0 || showSpecificCurriculum > curriculums.size());

        ArrayList<Book> curriculumBooks = Connection.getCurriculumBooks(showSpecificCurriculum);

        System.out.println("here are all the current books your chosen semester");

        for (Book book : curriculumBooks)
            System.out.println("ID: " + book.getBookID() + "title: " + book.getTitle() + " ISBN: " + book.getISBN());
        printBook();
    }



    public void changeUserInfo() {

    }

    public void deleteUser() {

    }

    public void logout() {

    }
}
