package m2.ila.vv;

import java.util.Random;

public class App {
    Integer[] myNumbers;

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
        if(str!=null){
            int y = str.length();
        }
        int x = str.length();//Possible NPE

        for (Integer myNumber : myNumbers) { //NPE
            System.out.println(myNumber);
        }
    }

    public void test(){
        if(true);
    }

    public int returnInt(){
        int i = new Random().nextInt();

        String str = null;
        if( i > 100 )
            str = "fou";
        else
            str = null;

        return str.length();
    }

    public void fixedNPE(){
        String str = null;
        int i = new Random().nextInt();

        if( i > 100 )
            str = "fou";
        else if ( i==10 )
            str = "barre";
        if (str != null)
            i = str.length();//Fixed
    }
}