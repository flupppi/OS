import java.util.Timer;
import java.util.TimerTask;

public class CPU {
    public static Process assignedProcess = null;
    public static Timer timer = new Timer();
    public void timerInit(){
        timer.scheduleAtFixedRate(lookForProcess, 0,5000);
    }
    public TimerTask lookForProcess = new TimerTask() {
        // 7. If the queue is empty (i.e., no process is available), the scheduler should check periodically (every 5 second) if there are any new processes.

        @Override
        public void run() {
            if (assignedProcess == null) {
                System.out.println("Looking for new process");

                if(Main.prcs.get(0)!=null){
                    System.out.println("Found new process");
                    assignProcess(Main.prcs.getFirst());
                    execute();
                }
            }
        }
    };
    public void assignProcess(Process pcs) {
        // 4. The program should extract the process at the head of the queue and assign to the CPU
        // (virtual one, not physically assign it) for the duration specified in the process.
        assignedProcess = pcs;
            Main.prcs.pop();
            Main.prcs.addLast(null);
    }
    public void execute(){
        new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("The Process " + assignedProcess.name + " has finished execution.");
                    // 6. When a process is finished executing, the process should be deleted, and the process ID can be used again for a later process.
                    assignedProcess = null; // loosing the reference to an object automatically deletes it through the garbage collector.
                    // 5. When the process is finished, your scheduler should extract the next process from the linked list, and so on.
                    lookForProcess.run();
                    Main.insertionCounter--;
                }
            }, assignedProcess.time * 1000L);
            System.out.println("The Process " + assignedProcess.name + " has been assigned for " + assignedProcess.time + " seconds.");
    }
}



