import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JsonParser {

    public static String getJsonDataFromURLAddress(final String urlAddress) throws MalformedURLException, IOException {
        URL u = new URL(urlAddress);
        InputStream is = u.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder result = new StringBuilder();
        String theLine;
        while ((theLine = br.readLine()) != null) {
            result.append(theLine);
        }

        return result.toString();
    }

    public static ArrayList<User> getUsers(final String jsonData) throws JsonSyntaxException, JsonParseException {
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>() {}.getType();

        return gson.fromJson(jsonData, userListType);
    }

    public static ArrayList<ToDo> getToDos(final String jsonData) throws JsonSyntaxException, JsonParseException {
        Gson gson = new Gson();
        Type toDoListType = new TypeToken<ArrayList<ToDo>>() {}.getType();

        return gson.fromJson(jsonData, toDoListType);
    }
}
