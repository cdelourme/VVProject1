package m2.ila.vv;

import m2.ila.vv.targets.GetterSetterTarget;
import m2.ila.vv.targets.SingletonTarget;

import java.util.Random;

public class App {

    public static void main(String[] args){
        System.out.println("Program for test");

        System.out.println( "Hello World!" );
        playWithSingleton();

        GetterSetterTarget g = new GetterSetterTarget(1, "Hello");
        g.print();


        int i = (new Random()).nextInt();
        String str = null;
        if( i > 100 )
            str = "fou";
        else if ( i <= 100 )
            str = "barre";

        int x = str.length();//Possible NPE

    }

    public static void playWithSingleton() {
        SingletonTarget i1 = new SingletonTarget();
        SingletonTarget i2 = new SingletonTarget();
        SingletonTarget i3 = new SingletonTarget();
        System.out.println("There are currently " + SingletonTarget.getNbInstances() + " instances of SingletonTarget.");
    }
}
