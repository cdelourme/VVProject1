package m2.ila.vv;

import java.util.Random;

public class App {
    public void main(String[] args){
        System.out.println("Program for test");
        int i = (new Random()).nextInt();
        String str = null;
        if( i > 100 )
            str = "fou";
        else if ( i <= 100 )
            str = "barre";

        int x = str.length();//Possible NPE

    }
}
