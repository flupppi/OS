import java.io.File;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//    #TODO 1. Create a linked list with 10 objects. Each object will represent a process. At the beginning, all objects are basically empty (Null).
        LinkedList<Process> prcs = new LinkedList<Process>();
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
        // Debug line
        Iterator itr = prcs.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        try {
            File myObj = new File("/home/flupppi/IdeaProjects/OS/scheduler/processes.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {

                int time = myReader.nextInt();
                System.out.println("You entered time: " + time);
                String name = myReader.next();
                System.out.println("You entered name: " + name);
                Process pcs = new Process(time, name);
                System.out.println(pcs.toString());
                prcs.set(i,pcs);
                i++;
                   }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Iterator itr2 = prcs.iterator();
        while(itr2.hasNext()) {
            System.out.println(itr2.next());
        }

        Collections.sort(prcs, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                Process temp = o1;
                return Collator.getInstance().compare(temp.name, o2.name);
            }
        });
        System.out.println(" ");
        Iterator itr3 = prcs.iterator();
        while(itr3.hasNext()) {
            System.out.println(itr3.next());
        }



        System.out.println("Please enter execution time in seconds and Process Name: 30 a");
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        System.out.println("You entered integer " + a);
        String s = in.next();
        System.out.println("You entered string " + s);

        // closing scanner
          in.close();
    }
}


    /**
     *Operating Systems Coding Assignments
Coding Assignment 1: Process Scheduling
Due on Thursday December 30
Write a program that implements a process scheduler based on priority. Your program should
have the following:


     #TODO 2. Process creation: when creating a process, the program should ask for the process name and duration, then your program should assign an ID to the process that is different than all current processes available. (Hint: to make it easier to work with next steps, let the duration of the process be around 30 seconds).



     #TODO 3. Sort the created processes into a queue (linked list) based on the process name (i.e., sort alphabetically).

     #TODO 4. The program should extract the process at the head of the queue and assign to the CPU (virtual one, not physically assign it) for the duration specified in the process.

     #TODO 5. When the process is finished, your scheduler should extract the next process from the linked list, and so on.

     #TODO 6. When a process is finished executing, the process should be deleted, and the process ID can be used again for a later process.

     #TODO 7. If the queue is empty (i.e., no process is available), the scheduler should check periodically (every 5 second) if there are any new processes.txt.

     #TODO 8. In order to avoid process starvation, a process that remains at the tail of the queue while creating 3 new processes.txt should be moved to the head of the queue.

     #TODO 9. Bonus (5 points): The scheduler above is non-preemptive (once a process starts execution, it remains active until it is done). A

     #TODO 5 points bonus will be awarded if you get the scheduler to be preemptive (i.e., if a new process with higher priority is created while a process is currently running, context switch needs to be done).
*/