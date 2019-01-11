package PCommand.Commands;

import PCommand.ICommand;

import java.math.BigInteger;

/**
 * Created by pidho on 09.05.2018.
 */
public class Command implements ICommand {

    private int n;
    public Command(int n){
        this.n=n;
    }


    @Override
    public void execude() {
        BigInteger big = BigInteger.valueOf(n);
        boolean res = big.isProbablePrime((int) Math.log(n));
        if(res){
            System.out.println("Число є простим.");
        }else{
            System.out.println("Число є складеним.");
        }
    }
}
