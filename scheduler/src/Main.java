import java.text.Collator;
import java.util.*;

/**
 * Operating Systems Coding Assignments
 * Coding Assignment 1: Process Scheduling
 * Due on Thursday December 30
 * Write a program that implements a process scheduler based on priority.
 * Author: Felix Kalchschmid - s20211051
 * Each of the subtasks I did is marked with a comment inside the file.
 */

public class Main {
    static boolean runProcess = true;
    static LinkedList<Process> prcs = new LinkedList<>();
    static int insertionCounter = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CPU cpu = new CPU();
//     1. Create a linked list with 10 objects. Each object will represent a process. At the beginning, all objects are basically empty (Null).
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        prcs.add(null);
        printProcesses();
        System.out.println("Type: \n end -> finish input \n stop -> stop the program \n print -> print the process queue \n duration name -> to create a process");
        System.out.println("Please enter the execution time in seconds and a process Name: 30 a");
        cpu.timerInit();
        while(runProcess) { // kontrollstruktur
            // Input Loop
            // Using Scanner for Getting Input from User
            if (in.hasNextInt()) {
                // 2. Process creation: when creating a process, the program should ask for the process name and duration,
                // then your program should assign an ID to the process that is different than all current processes available.
                // (Hint: to make it easier to work with next steps, let the duration of the process be around 30 seconds).
                int time = in.nextInt();
                //System.out.println("You entered time: " + time);
                String name = in.next();
                //System.out.println("You entered name: " + name);
                Process pcs = new Process(time, name);
                System.out.println(pcs);
                insertProcess(pcs);
                  //3. Sort the created processes into a queue (linked list) based on the process name (i.e., sort alphabetically).
                sortProcessesByName();
                insertionCounter++;
                if(insertionCounter>4){
                    // 8. In order to avoid process starvation, a process that remains at the tail of the queue while creating 3 new processes should be moved to the head of the queue.
                    swapOldProcess();
                    insertionCounter=0;
                }
            }
            else if (in.hasNext("end")){
                in.nextLine();
                runProcess = false;
                // closing scanner
                in.close();
                System.exit(0);
            }
            else if (in.hasNext("print")) {
                in.nextLine();
                printProcesses();
            }
            else if (in.hasNext("help")){
                in.nextLine();
                System.out.println("Type: \n end -> finish input \n stop -> stop the program \n print -> print the process queue \n duration name -> to create a process");
            }
            else{
                in.nextLine();
            }
        }
    }

    private static void insertProcess(Process pcs){
        Iterator<Process> itr = prcs.iterator();
        int i = 0;
        while(itr.hasNext()&&prcs.get(i)!=null) {
            i++;
            itr.next();
        }
        if(prcs.size()>i)
            prcs.set(i, pcs);
        else
            prcs.addLast(pcs);
    }
    private static void printProcesses() {
        System.out.println("Linked List Print");
        for (Process prc : prcs) {
            System.out.println(prc);
        }
    }
    private static void sortProcessesByName() {
        prcs.sort((o1, o2) -> {
            if (o1 != null && o2 != null) {
                return Collator.getInstance().compare(o1.name, o2.name);
            }
            return 0;
        });
    }
    private static void swapOldProcess(){
        Iterator<Process> itr = prcs.iterator();
        int i = 0;
        while(itr.hasNext()&&prcs.get(i)!=null) {
            i++;
            itr.next();
        }
        if (i>3) {
            Process first = prcs.getFirst();
            Process last = prcs.get(i-1);
            prcs.set(0, last);
            prcs.set(i-1, first);
        }
    }
}
/* What i didn't do
     #TODO 9. Bonus (5 points): The scheduler above is non-preemptive (once a process starts execution, it remains active until it is done). A

     #TODO A 5 points bonus will be awarded if you get the scheduler to be preemptive (i.e., if a new process with higher priority is created while a process is currently running, context switch needs to be done).
*/