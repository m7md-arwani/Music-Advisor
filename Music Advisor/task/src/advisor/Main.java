package advisor;


public class Main {
    // Will be given to the user to authorize us.
    public static String url = "https://accounts.spotify.com/authorize?" +
            "client_id=085b812857a44fc1aa6b496a251cb3cf&redirect_uri=http://localhost:8080&response_type=code";

    private static final myServer server = new myServer();
    public static String access = null;


    public static void main(String[] args) {

        if (args[0].equals("-access")) {
          access = args[1];
            url = access + "/authorize?client_id=085b812857a44fc1aa6b496a251cb3cf&redirect_uri=http://localhost:8080&response_type=code";
       }
        state.start();


    }

    public static void newOption() {
        if (state.switch_control) {
            System.out.println("---NEW RELEASES---");
        } else {
            System.out.println("Please, provide access for application.");
        }


    }

    public static void featured() {
        if (state.switch_control) {
            System.out.println("---FEATURED---");
        } else {
            System.out.println("Please, provide access for application.");
        }

    }

    public static void categories() {
        if (state.switch_control) {
            System.out.println("---CATEGORIES---");
        } else {
            System.out.println("Please, provide access for application.");
        }

    }

    public static void playlists() {
        if (state.switch_control) {
            System.out.println("---MOOD PLAYLISTS---");
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
            state.switch_control = true;
            server.stop();
            //to continue, token request result.
            httpClient client = new httpClient();
            client.buildRequest();
            client.sendPostRequest();
            System.out.println(httpClient.complete_response);
            System.out.println("---SUCCESS---");
        }



    }

    private static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
