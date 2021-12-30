import java.io.*;
import java.util.Scanner;
/*
 * Banker's Algorithm
 * Author: Felix Kalchschmid
 *
 * Task: Write a program to implement the Banker’s Algorithm.
 * The pseudocode for the algorithm is in the book and in the slides in Chapter 7.
 * Your code should check if the system is in a safe state and determine<<
 * if a process should be assigned the requested resources or not.
 * Example Input:
 * Allocation
 *   | A B C D E
 * --|-----------
 * P0| 3 0 1 4
 * P1| 2 2 1 0
 * P2| 3 1 2 1
 * P3| 0 5 1 0
 * P4| 4 2 1 2
 * Max
 *   | A B C D
 * --|---------
 * P0| 5 1 1 7
 * P1| 3 2 1 1
 * P2| 3 3 2 1
 * P3| 4 6 1 2
 * P4| 6 3 2 5
a.Available = (0, 3, 0, 1) - unsafe
b.Available = (1, 0, 0, 2) - safe
 */
class Bankers {
    static int NUMBER_OF_RESOURCES;
    static int NUMBER_OF_PROCESSES;
    static int[] safeSequence = new int[20];
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("""
                -----------------------------------------------------------------------------------------------------
                The Banker's Algorithm needs some Inputs from you to be able to calculate it's results.
                You will be asked for the number of processes and resources, the available resources,\s
                the allocated resources and the maximum allocatable resources for each process.
                You can enter your own values or just follow the example.
                For the second phase the algorithm will calculate all the results and print them to the command line.\s
                -----------------------------------------------------------------------------------------------------
                Press ENTER to Start""");
        // ----------------------------------------------------------------
        // Reading all input from the User
        System.in.read();
        // Get Processes and Resources
        System.out.println("Enter number of processes and resources: e.g. 5 4");
        NUMBER_OF_PROCESSES = Integer.parseInt(in.next());
        NUMBER_OF_RESOURCES = in.nextInt();
        int[] available = new int[NUMBER_OF_RESOURCES];
        int[][] allocated = new int[NUMBER_OF_PROCESSES][NUMBER_OF_RESOURCES];
        int[][] maximum = new int[NUMBER_OF_PROCESSES][NUMBER_OF_RESOURCES];
        int[][] need = new int[NUMBER_OF_PROCESSES][NUMBER_OF_RESOURCES];

        populateArrays(available, allocated, maximum);
        // Calculate all values in the need array
        calculateNeed(allocated, maximum, need);
        if (isSafe( available, allocated, need, maximum)) {
            System.out.println("The system is in a safe state.");
            System.out.print("Safe sequence:");
            for (int i = 0; i < NUMBER_OF_PROCESSES; i++)
                System.out.print( "P" + safeSequence[i] + " " );
        } else
            System.out.println("The system is unsafe");
    }
    static boolean isSafe(int[] available, int[][] allocated, int[][] need, int[][] maximum) {
        // Initialize Work = Available
        int[] work = new int[NUMBER_OF_RESOURCES]; // Let Work and Finish be vectors of length m and n, respectively.
        System.arraycopy(available, 0, work, 0, NUMBER_OF_RESOURCES);
        // and Finish[i] = false for i = 0, 1, ..., n − 1.
        boolean[] finish = new boolean[NUMBER_OF_PROCESSES];
        printArrays(allocated, need, work, maximum);

        int processCount = 0;
        while(processCount < NUMBER_OF_PROCESSES){
            boolean found = false;
            for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {//2. Find an index i such that both
                if (!finish[i]) { //a. Finish[i] == false
                    int j;
                    for ( j = 0; j < NUMBER_OF_RESOURCES; j++) {
                        if (work[j] < need[i][j]) { // check by disproving
                            break;
                        }
                    }
                    // Wenn wir einmal erfolgreich den prozess überprüft habe
                    if (j == NUMBER_OF_RESOURCES){
                        for (int p = 0; p < NUMBER_OF_RESOURCES; p++) {
                            work[p] += allocated[i][p]; //3. Work = Work + Allocationi
                        }
                        safeSequence[processCount++] = i;
                        finish[i] = true;//Finish[i] = true
                        found = true;
                    }
                }
            }
            if(!found)
                 return false;
        }
        return true;
    }
    private static void populateArrays(int[] available, int[][] allocated, int[][] maximum) throws IOException {
        //Get Available Resources
        System.out.println("""
                Enter your available resources: e.g.
                0 3 0 1 - unsafe
                1 0 0 2 - safe""");
        for (int i = 0; i < NUMBER_OF_RESOURCES; i++)
            available[i] = in.nextInt();
        // Get Allocated Array
        System.out.println("""
                Enter your allocated resources: e.g.
                  | A B C D
                --|---------
                P0| 3 0 1 4
                P1| 2 2 1 0
                P2| 3 1 2 1
                P3| 0 5 1 0
                P4| 4 2 1 2
                 each line separately""");
        for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {
            for (int j = 0; j < NUMBER_OF_RESOURCES; j++)
                allocated[i][j] = in.nextInt();
        }
        // Get Maximum Array
        System.out.println("""
                Enter your maximum allocatable resources: e.g.
                  | A B C D
                --|---------
                P0| 5 1 1 7
                P1| 3 2 1 1
                P2| 3 3 2 1
                P3| 4 6 1 2
                P4| 6 3 2 5
                 each line separately""");
        for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {
            for (int j = 0; j < NUMBER_OF_RESOURCES; j++)
                maximum[i][j] = in.nextInt();
        }
        //---------------------------------------------------------------------------------
        System.out.println("""
                -----------------------------------------------------------------------------------------------------
                All data was read successfully!
                Press ENTER to get your results""");
        System.in.read();
    }
    private static void calculateNeed(int[][] allocated, int[][] maximum, int[][] need) {
        for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {
            for (int j = 0; j < NUMBER_OF_RESOURCES; j++)
                need[i][j] = maximum[i][j] - allocated[i][j];
        }
    }
    private static void printArrays(int[][] allocated, int[][] need, int[] work, int[][] maximum) {
        System.out.println("-------maximum-------");
        for (int[] i : maximum) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println("-------available-------");
        for (int i : work)
           System.out.print(i + " ");
        System.out.println("\n-------allocated-------");
        for (int[] value : allocated) {
            for (int i : value)
                System.out.print(i + " ");
            System.out.println();
        }
        System.out.println("-------need-------");
        for (int[] i : need) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }
}