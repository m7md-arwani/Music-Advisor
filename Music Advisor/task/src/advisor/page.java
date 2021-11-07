package advisor;

import java.util.List;
// The class is implementing SingleTon design pattern.
public class page {
    private page() {
    }

    private static final page instance = new page();
    private static int totalPages;
    public static int contentPerPage = 5; // default value 5.
    public static List<String> resultsList;
   public static int currentPage = 1;
    private static int stepsForOneRecord;
    public static int currentIndex = 0;


    public static page getInstnace(List<String> content, int stepsRequiredForOneRecord) {
        resultsList = content;
        stepsForOneRecord = stepsRequiredForOneRecord;
        totalPages = (resultsList.size() / stepsForOneRecord) / contentPerPage;
        return instance;
    }

    // initial state
    public void print() {
        for (int i = 0; i < contentPerPage; i++) {
            for (int j = 0; j < stepsForOneRecord; j++) {
                System.out.println(resultsList.get(currentIndex++));

            }
            System.out.println();
        }
        System.out.println("---PAGE " + currentPage + " OF " + totalPages + "---");

    }

    public static void nextOrPrevious(boolean isForward) {
        if (isForward) {
            if (currentPage + 1 > totalPages) {
                System.out.println("No more pages");
            } else {
                for (int i = 0; i < contentPerPage; i++) {
                    for (int j = 0; j < stepsForOneRecord; j++) {
                        System.out.println(resultsList.get(currentIndex++));

                    }
                    System.out.println();
                }
                System.out.println("---PAGE " + ++currentPage + " OF " + totalPages + "---");

            }
        // The case if the user wants the previous page.
        } else {
            if (currentPage - 1 <= 0) {
                System.out.println("No more pages");
            } else {
                currentIndex = currentIndex - 2 * ( contentPerPage * stepsForOneRecord)  ;
                for (int i = 0; i < contentPerPage; i++) {
                    for (int j = 0; j < stepsForOneRecord; j++) {
                        System.out.println(resultsList.get(currentIndex++));

                    }
                    System.out.println();
                }
                System.out.println("---PAGE " + --currentPage + " OF " + totalPages + "---");

            }

        }
    }


}
