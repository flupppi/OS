import java.util.UUID;

public class Process {
    public Process(int time, String name){
        this.time = time;
        this.name = name;
        pid = UUID.randomUUID();
    }
    public String toString(){
        return pid + " " + name + " " + time;
    }
    public UUID pid;
    public String name;
    public int time;

}
