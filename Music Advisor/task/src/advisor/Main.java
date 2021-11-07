package advisor;


import com.google.gson.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    // Will be given to the user to authorize us.
    public static String url = "https://accounts.spotify.com/authorize?" +
            "client_id=085b812857a44fc1aa6b496a251cb3cf&redirect_uri=http://localhost:8080&response_type=code";
    public static String resource_api = "https://api.spotify.com";

    private static final myServer server = new myServer();
    public static String access = null;
    public static String access_token;
    // categories name & id will be saved for later use.
    public static HashMap<String, String> categories = new HashMap<>();
    public static List<String> tempList;


    public static void main(String[] args) {
        // for testing purposes
        // Using a hashmap to collect arguments as pairs <String,String>
//        HashMap<String, String> hash = new HashMap<>();
//        for (int i = 0; i < args.length; i += 2) {
//            hash.put(args[i], args[i + 1]);
//        }
//        if (hash.containsKey("-access")) {
//            access = hash.get("-access");
//            url = access + "/authorize?client_id=085b812857a44fc1aa6b496a251cb3cf&redirect_uri=http://localhost:8080&response_type=code";
//        }
//        if (hash.containsKey("-resource")) {
//            resource_api = hash.get("-resource");
//        }
//        if (hash.containsKey("-page")) {
//            page.contentPerPage = Integer.parseInt(hash.get("-page"));
//        }


        state.start();


    }

    public static void newOption() {
        if (state.switch_control) {
            //Prepare a request.
            HttpRequest httpRequest = request_creator("/v1/browse/new-releases");
            HttpClient categories_client = HttpClient.newBuilder().build();
            // send request
            try {
                tempList = new ArrayList<>();
                HttpResponse<String> response = categories_client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                // simplify & remove white spaces from Json
                Gson gson = new GsonBuilder().create();
                JsonElement json = JsonParser.parseString(response.body());
                String json_ready = gson.toJson(json);

                JsonObject jo = JsonParser.parseString(json_ready).getAsJsonObject();
                JsonObject newObj = jo.getAsJsonObject("albums");
                JsonArray arrayOfItems = newObj.get("items").getAsJsonArray();
                // Array for the names of albums

                for (JsonElement item : arrayOfItems) {
                    JsonObject itemObj = item.getAsJsonObject();
                    String name = itemObj.get("name").getAsString();
                    tempList.add(name);
                    // collecting artists
                    ArrayList<String> artists_names = new ArrayList<>();
                    JsonArray arrayOfArtists = itemObj.get("artists").getAsJsonArray();
                    for (JsonElement artist : arrayOfArtists) {
                        JsonObject artObj = artist.getAsJsonObject();
                        String nameOfArtist = artObj.get("name").getAsString();
                        artists_names.add(nameOfArtist);
                    }

                    tempList.add(artists_names.toString());
                    // printing the link
                    JsonObject linkObj = itemObj.get("external_urls").getAsJsonObject();
                    String link = linkObj.get("spotify").getAsString();
                    tempList.add(link);


                    // System.out.println();


                }
                page pager = page.getInstnace(tempList, 3);
                pager.print();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Please, provide access for application.");
        }


    }

    public static void featured() {
        if (state.switch_control) {
            // Prepare a request.
            HttpRequest httpRequest = request_creator("/v1/browse/featured-playlists");
            HttpClient categories_client = HttpClient.newBuilder().build();
            // send request
            try {
                tempList = new ArrayList<>();
                HttpResponse<String> response = categories_client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                Gson gson = new GsonBuilder().create();
                JsonElement json = JsonParser.parseString(response.body());
                String json_ready = gson.toJson(json);

                JsonObject jo = JsonParser.parseString(json_ready).getAsJsonObject();
                JsonObject featuredObj = jo.getAsJsonObject("playlists");
                JsonArray arrayOfItems = featuredObj.get("items").getAsJsonArray();
                for (JsonElement item : arrayOfItems) {
                    JsonObject itemObj = item.getAsJsonObject();
                    String name = itemObj.get("name").getAsString();
                    String href = itemObj.get("href").getAsString();
                    tempList.add(name);
                    tempList.add(href.replaceAll("https://api.spotify.com/v1/playlist", "http://open.spotify.com/playlist").replaceAll("playlists", "playlist"));

                }
                page pager = page.getInstnace(tempList, 2);
                pager.print();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Please, provide access for application.");
        }

    }

    public static void categories() {
        if (state.switch_control) {
            // Prepare a request
            HttpRequest httpRequest = request_creator("/v1/browse/categories");
            HttpClient categories_client = HttpClient.newBuilder().build();
            // send request
            try {
                tempList = new ArrayList<>();
                HttpResponse<String> response = categories_client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                // simplify & remove white spaces from Json
                Gson gson = new GsonBuilder().create();
                JsonElement json = JsonParser.parseString(response.body());
                String json_ready = gson.toJson(json);

                JsonObject jo = JsonParser.parseString(json_ready).getAsJsonObject();
                JsonObject categObj = jo.getAsJsonObject("categories");
                JsonArray arrayOfItems = categObj.get("items").getAsJsonArray();
                for (JsonElement item : arrayOfItems) {
                    JsonObject itemObj = item.getAsJsonObject();
                    String id = itemObj.get("id").getAsString();
                    String name = itemObj.get("name").getAsString();
                    tempList.add(name);
                    categories.put(name, id);
                }

                page pager = page.getInstnace(tempList, 1);
                pager.print();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please, provide access for application.");
        }

    }


    public static void playlists(String nameOfPlaylist) {
        if (state.switch_control) {
            String categId = categories(nameOfPlaylist);
            assert categId != null; // make sure no null pointer will happen
            if (!categId.equals("not found") && !categId.equals("error")) {
                // Prepare a request
                HttpRequest httpRequest = request_creator("/v1/browse/categories/" + categId + "/playlists");
                HttpClient categories_client = HttpClient.newBuilder().build();
                // send request
                try {
                    tempList = new ArrayList<>();
                    HttpResponse<String> response = categories_client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    // simplify & remove white spaces from Json
                    Gson gson = new GsonBuilder().create();
                    JsonElement json = JsonParser.parseString(response.body());
                    String json_ready = gson.toJson(json);

                    JsonObject jo = JsonParser.parseString(json_ready).getAsJsonObject();
                    JsonObject playlistObj = jo.getAsJsonObject("playlists");
                    JsonArray arrayOfItems = playlistObj.get("items").getAsJsonArray();
                    for (JsonElement item : arrayOfItems) {
                        JsonObject itemObj = item.getAsJsonObject();
                        String name = itemObj.get("name").getAsString();
                        tempList.add(name);
                        // getting the link from object external_urls
                        JsonObject externObj = itemObj.get("external_urls").getAsJsonObject();
                        String link = externObj.get("spotify").getAsString();
                        tempList.add(link);
                        //System.out.println();
                    }
                    page pager = page.getInstnace(tempList, 2);
                    pager.print();


                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }


            } else if (!categId.equals("error")) {

                System.out.println("Unknown category name.");


            }


        } else {
            System.out.println("Please, provide access for application.");
        }

    }


    public static void auth() {
        server.creatServer();
        server.start();
        System.out.println(url);
        System.out.println("waiting for code...");
        sleep();

        if (httpHandler.code.equals("does not exist")) {
            System.out.println("Authorization process failed, please try again");
            server.stop();

        } else {
            System.out.println("code received");
            System.out.println("Making http request for access_token...");
            state.switch_control = true;
            server.stop();
            // using the class httpClient
            httpClient client = new httpClient();
            client.buildRequest();
            client.sendRequest();
            modStringGetAccess(httpClient.complete_response);
            System.out.println("SUCCESS!");
        }


    }

    private static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // will extract access_token from spotify response.
    private static void modStringGetAccess(String str) {
        String[] tokens = str.split(","); //separate different tokens
        String[] access__token = tokens[0].split(":"); // separate naming & value
        access_token = access__token[1].replaceAll("\"", ""); // remove extra semicolons
    }

    // overloaded version, will return a category id to serve playlists function.
    private static String categories(String categoryWanted) {
        // Prepare a request.
        HttpRequest httpRequest = request_creator("/v1/browse/categories");
        HttpClient categories_client = HttpClient.newBuilder().build();
        // send request
        try {
            HttpResponse<String> response = categories_client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            // simplify & remove white spaces from Json
            Gson gson = new GsonBuilder().create();
            String responseBody = response.body();
            JsonElement json = JsonParser.parseString(response.body());
            String json_ready = gson.toJson(json);
            JsonObject jo = JsonParser.parseString(json_ready).getAsJsonObject();
            if (responseBody.contains("error")) {
                JsonObject errorObj = jo.getAsJsonObject("error");
                String message = errorObj.get("message").getAsString();
                System.out.println(message);
                return "error";
            } else {
                JsonObject categObj = jo.getAsJsonObject("categories");
                JsonArray arrayOfItems = categObj.get("items").getAsJsonArray();
                for (JsonElement item : arrayOfItems) {
                    JsonObject itemObj = item.getAsJsonObject();
                    String id = itemObj.get("id").getAsString();
                    String name = itemObj.get("name").getAsString();
                    categories.put(name, id);
                }
                if (categories.containsKey(categoryWanted)) {
                    return categories.get(categoryWanted);
                }

            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "not found";
    }

    private static HttpRequest request_creator(String secondHalhfOfUri) {
        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + access_token)
                .uri(URI.create(resource_api + secondHalhfOfUri))
                .GET()
                .build();


    }


}
