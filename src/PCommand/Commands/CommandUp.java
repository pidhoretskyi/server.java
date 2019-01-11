package PCommand.Commands;

import PCommand.ICommand;

public class CommandUp implements ICommand {
    @Override
    public void execude() {
        System.out.println("Команда: вгору.");
    }
}
