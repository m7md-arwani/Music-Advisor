package advisor;

import java.util.Scanner;

//under production
// Not used yet.

public class state {
    private static final Scanner scanner = new Scanner(System.in);
    private static State currentState = State.OPERATION_INPUT;
    private  static boolean isRunning = true;
    // to inform other functions that the authorization process is a success or not.
    public static boolean switch_control = false;
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
                        switch (input) {
                            case "new":
                                currentState = State.NEW;
                                break;
                            case "featured":
                                currentState  = State.FEATURED;
                                break;
                            case "categories":
                                currentState   = State.CATEGORIES;
                                break;
                            case "playlists Mood":
                                currentState = State.PLAYLISTS;
                                break;
                            case "auth":
                                currentState= State.AUTH;
                                break;
                            case "exit":
                                System.out.println("---GOODBYE!---");
                                isRunning = false;
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
                        Main.playlists();
                        break;
                    case AUTH:
                        currentState = State.OPERATION_INPUT;
                        Main.auth();
                        break;

                }

    }




}
