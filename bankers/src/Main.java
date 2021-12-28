public class Main {
    //these may be any values >= 0
    public static int NUMBER_OF_CUSTOMERS = 5;
    public static int NUMBER_OF_RESOURCES = 5;

    // the available amount of each resource */
    int[] available = new int[NUMBER_OF_RESOURCES];
    //NUMBER_OF_RESOURCES];
    //the maximum demand of each customer */
    int[][] maximum = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
    // the amount currently allocated to each customer */
    int[][] allocation = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
    // the remaining need of each customer */
    int[][] need = new int [NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];


    int request_resources(int customer_num, int[] request) {
        return 0;
    }

    int release_resources(int customer_num, int[] release) {
        return 0;
    }


}
