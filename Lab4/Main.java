import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final String urlUsersAddress = "https://fake-json-api.mock.beeceptor.com/users";
        final String urlToDosAddress = "https://dummy-json.mock.beeceptor.com/todos";
        final String separator = "====================";

        try {

            String usersDataJson = JsonParser.getJsonDataFromURLAddress(urlUsersAddress);
            ArrayList<User> users = JsonParser.getUsers(usersDataJson);

            System.out.printf("Received users from %s:\n", urlUsersAddress);
            for (User user : users) {
                System.out.println(user);
                System.out.println(separator);
            }

            String toDosDataJson = JsonParser.getJsonDataFromURLAddress(urlToDosAddress);
            ArrayList<ToDo> toDos = JsonParser.getToDos(toDosDataJson);

            System.out.printf("Received toDos from %s:\n", urlToDosAddress);
            for (ToDo toDo : toDos) {
                System.out.println(toDo);
                System.out.println(separator);
            }

        } catch (MalformedURLException e) {
            System.out.printf("Opening URL Exception: %s\n", e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.printf("JSON Syntax Exception: %s\n", e.getMessage());
        } catch (JsonParseException e) {
            System.out.printf("JSON Parsing Exception: %s\n", e.getMessage());
        } catch (IOException e) {
            System.out.printf("Input/Output Stream Exception: %s\n", e.getMessage());
        } catch (Exception e) {
            System.out.printf("Unexpected exception: %s\n", e.getMessage());
        }
    }
}