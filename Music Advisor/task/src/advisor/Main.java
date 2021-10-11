package advisor;


public class Main {
    // Will be given to the user to authorize us.
    public static String url = "https://accounts.spotify.com/authorize?" +
            "client_id=085b812857a44fc1aa6b496a251cb3cf&redirect_uri=http://localhost:8080&response_type=code";

    private static final myServer server = new myServer();


    public static void main(String[] args) {
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
        System.out.println(url);
        System.out.println("waiting for code...");
        server.creatServer();
        server.start();
        sleep();

        if (httpHandler.code.equals("does not exist")) {
            System.out.println("Authorization process failed, please try again");

        } else {
            System.out.println("code received");
            System.out.println("---SUCCESS---");
            state.switch_control = true;
            //to continue, token request result.
        }
        server.stop();


    }

    private static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
