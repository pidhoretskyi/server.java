package PCommand;

import java.util.ArrayList;

public class Macros implements ICommand{
    private ArrayList<ICommand> list;
    private String preambl;
    public Macros(String s){
        list=new ArrayList<>();
        preambl = s;
    }
    public void addCommand(ICommand command){
        list.add(command);
    }
    public void clear(){
        list.clear();
    }
    public void execude(){
        for (ICommand aList : list) {
            System.out.print(preambl);
            aList.execude();
        }
    }
}
