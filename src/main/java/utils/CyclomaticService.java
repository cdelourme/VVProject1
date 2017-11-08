package utils;

import java.util.HashMap;

public class CyclomaticService {

    /*********************************Singleton**********************************/
    public static CyclomaticService instance = new CyclomaticService();

    private CyclomaticService(){

    }
    /****************************************************************************/



    private Integer projectCyclomatic = 0;
    public Integer getProjectCyclomatic() {
        return projectCyclomatic;
    }

    private HashMap<String,Integer> classCyclomatics = new HashMap<>();
    public HashMap<String,Integer> getClassCyclomatics() {
        return classCyclomatics;
    }

    private HashMap<String,Integer> methodCyclomatics = new HashMap<>();
    public HashMap<String,Integer> getMethodCyclomatics() {
        return methodCyclomatics;
    }
    public void setMethodCyclomatics(String keyClass, String keyMethod, Integer value) {
        value+=1;
        // mise a jour de la cyclomatic du projet
        this.projectCyclomatic += value;
        // mise jour de la cyclomatique de la classe
        this.classCyclomatics.put(keyClass,this.methodCyclomatics.getOrDefault(keyClass,0)+ value);
        //mise a jour de la cyclomatique de la methode
        this.methodCyclomatics.put(keyClass +"."+keyMethod, value);
    }

    public void printResult(){
        System.out.println("Cyclomatic du projet : "+this.projectCyclomatic);
        System.out.println("Cyclomatic des classes : ");
        this.classCyclomatics.forEach((key,value)->{
            System.out.println("    "+key+" : "+value);
        });
        System.out.println("Cyclomatic des methodes : ");
        this.methodCyclomatics.forEach((key,value)->{
            System.out.println("    "+key+" : "+value);
        });
    }




}
