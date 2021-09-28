package advisor;

public class Main {

    public static String url = "https://accounts.spotify.com/authorize?" +
            "client_id=YOURCLIENT&redirect_uri=https://www.example.com&response_type=code";

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
        state.switch_control = true;
        System.out.println(url);
        System.out.println("---SUCCESS---");

    }


}
