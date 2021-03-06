package advisor;

import java.util.Scanner;



public class state {
    private static final Scanner scanner = new Scanner(System.in);
    private static State currentState = State.OPERATION_INPUT;
    private  static boolean isRunning = true;
    // to inform other functions that the authorization process is a success or not.
    public static boolean switch_control = false;
    private static String forPlaylists;
            enum State {
        OPERATION_INPUT,NEW,FEATURED,CATEGORIES,PLAYLISTS,AUTH
            }

            public static void start() {
                while (isRunning) {
                   operationManager(currentState);
                }
            }
    public static void operationManager(State currentS) {


                switch(currentS) {
                    case OPERATION_INPUT:
                        String input = scanner.nextLine();
                        // page changing precautions
                        if (input.equals("next")) {
                            page.nextOrPrevious(true);
                            input = "pageChange";
                        } else if(input.equals("prev")) {
                            page.nextOrPrevious(false);
                            input = "pageChange";
                        }
                        // precaution for playlists
                        String[] text = input.split(" ");
                        if (text.length == 2 && text[0].equals("playlists")) {
                            forPlaylists = text[1];
                            input = "playlists Mood";
                        }else if (text.length >= 3 && text[0].equals("playlists")) {
                            forPlaylists = text[1] +" "+ text[2];
                            input = "playlists Mood";
                        }
                        switch (input) {
                            case "new":
                                currentState = State.NEW;
                                // refresh page indexes
                                page.currentPage = 1;
                                page.currentIndex = 0;
                                Main.tempList = null;
                                break;
                            case "featured":
                                currentState  = State.FEATURED;
                                // refresh page indexes
                                page.currentPage = 1;
                                page.currentIndex = 0;
                                Main.tempList = null;
                                break;
                            case "categories":
                                currentState   = State.CATEGORIES;
                                // refresh page indexes
                                page.currentPage = 1;
                                page.currentIndex = 0;
                                Main.tempList = null;
                                break;
                            case "playlists Mood":
                                currentState = State.PLAYLISTS;
                                // refresh page indexes
                                page.currentPage = 1;
                                page.currentIndex = 0;
                                Main.tempList = null;
                                break;
                            case "auth":
                                currentState= State.AUTH;

                                break;
                            case "exit":
                                System.out.println("---GOODBYE!---");
                                isRunning = false;
                                break;
                            case "pageChange":
                                break;
                        }
                        break;
                    case NEW:
                        currentState = State.OPERATION_INPUT;
                        Main.newOption();
                        break;
                    case FEATURED:
                        currentState = State.OPERATION_INPUT;
                       Main.featured();
                        break;
                    case CATEGORIES:
                        currentState = State.OPERATION_INPUT;
                        Main.categories();
                        break;
                    case PLAYLISTS:
                        currentState = State.OPERATION_INPUT;
                        Main.playlists(forPlaylists);
                        break;
                    case AUTH:
                        currentState = State.OPERATION_INPUT;
                        Main.auth();
                        break;

                }

    }




}
