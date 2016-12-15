package sdk;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import encrypters.Crypter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import model.Book;
import model.Curriculum;
import model.UserLogin;

import java.util.ArrayList;

/**
 * Created by Christofferpalsgaard on 24/11/2016.
 */
public class Connection {


    public static String authorizeLogin(String username, String password) {
        UserLogin userLogin = new UserLogin(username, password);
        ClientResponse clientResponse = HttpRequests.post("/user/login", new Gson().toJson(userLogin));
        String token = null;

        if (clientResponse == null) {
            System.out.println("There is no connection");
        } else {
            String json = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                token = json;
            } else {
                System.out.println("Sorry Buddy Boy");
            }
        }
        return token;
    }

    public static ArrayList<Book> getBooks() {
        ClientResponse clientResponse = HttpRequests.get("/book");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Server error");
            }
        }
        return books;
    }

    public static ArrayList<Curriculum> getCurriculum() {
        ClientResponse clientResponse = HttpRequests.get("/curriculum/");
        ArrayList<Curriculum> curriculums = null;

        if (clientResponse == null) {
            System.out.println("no sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                curriculums = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Curriculum>>() {
                }.getType());
            } else
                System.out.println("Server error");

        }
        return curriculums;
    }


    public static Book getBook(int id) {
        ClientResponse clientResponse = HttpRequests.get("book/" + id);
        Book book = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                book = new Gson().fromJson(decryptedJson, Book.class);
            } else {
                System.out.println("Server error");
            }
        }
        return book;
    }


    public static ArrayList<Book> getCurriculumBooks(int curriculumID) {
        ClientResponse clientResponse = HttpRequests.get("/curriculum/" + curriculumID + "/books/");

        ArrayList<Book> books = null;

        if (clientResponse == null) {

            System.out.println("Error on SDK");
        } else {
            String enryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(enryptedJson);
                books = new Gson().fromJson(java.lang.String.valueOf(decryptedJson), new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else
                System.out.println("error mopho");
        }


    return books;

    }

    public static String postUser(JsonObject data) {
        ClientResponse clientResponse = HttpRequests.post("user/", Crypter.encryptDecryptXOR(new Gson().toJson(data)));
        System.out.println(data);
        String response = null;

        if (clientResponse == null) {

            System.out.println("Error on SDK");
        } else {
            response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                System.out.println(response);
            } else
                System.out.println("error");
        }
        clientResponse.close();
        return response;

    }

    public static String putUser(String token, JsonObject data, int userID) {

        ArrayList<String> info = new ArrayList<>();
        info.add("authorization");
        info.add(token);
        ClientResponse clientResponse = HttpRequests.put("/user/" + userID, Crypter.encryptDecryptXOR(new Gson().toJson(data)), info);

        String response = null;
        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                System.out.println(response);
            } else {
                System.out.println("Error");
            }
        }
        clientResponse.close();
        return response;
    }
}

