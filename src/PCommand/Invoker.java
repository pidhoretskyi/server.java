package PCommand;

public class Invoker {
    private ICommand command;
    private String preaml = "";
    public Invoker(ICommand command,String s){
        this.command=command;
        this.preaml = s;
    }

    public void setPreaml(String preaml) {
        this.preaml = preaml;
    }

    public Invoker() {
    }

    public void run(){
        if(command!=null)command.execude();
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
}
