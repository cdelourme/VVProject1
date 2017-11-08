package m2.ila.vv;

import java.util.Random;

public class App {

    public void main(String[] args){

        int i = new Random().nextInt();
        String str = null;
        if( i > 100 )
            str = "fou";
        else
            str = "barre";

        while(i==10){
            i=10;
        }
        int x = str.length();//Possible NPE

    }
}
