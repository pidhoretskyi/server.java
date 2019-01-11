package PCommand.Commands;

import PCommand.ICommand;

public class CommandDown implements ICommand {
    @Override
    public void execude() {
        System.out.println("Команда: вниз.");
    }
}
