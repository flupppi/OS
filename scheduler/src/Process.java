import java.util.UUID;

public class Process {


    public Process(int time, String name){
        this.time = time;
        this.name = name;
        pid = UUID.randomUUID();
    }
    public UUID getPid(){
        return pid;
    }

    public String toString(){
        return pid + " " + name + " " + time;
    }
    public static UUID pid;
    String name;
    int time;

}
