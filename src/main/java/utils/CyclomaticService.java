package utils;

import java.util.HashMap;

public class CyclomaticService {

    /*********************************Singleton**********************************/
    public static CyclomaticService instance = new CyclomaticService();

    private CyclomaticService(){

    }
    /****************************************************************************/



    private Integer projectCyclomatic = 1;
    public Integer getProjectCyclomatic() {
        return projectCyclomatic;
    }
    public void addProjectCyclomatic() {
        projectCyclomatic = projectCyclomatic +1;
    }

    private HashMap<String,Integer> classCyclomatics = new HashMap<>();
    public HashMap<String,Integer> getClassCyclomatics() {
        return classCyclomatics;
    }
    public void setClassCyclomatics(String key, Integer value) {
        this.classCyclomatics.put(key,value + 1);
    }

    private HashMap<String,Integer> methodCyclomatics = new HashMap<>();
    public HashMap<String,Integer> getMethodCyclomatics() {
        return methodCyclomatics;
    }
    public void setMethodCyclomatics(String keyClass, String keyMethod, Integer value) {
        this.methodCyclomatics.put(keyClass +"."+keyMethod, value + 1);
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

    public void resetStats(){
        this.methodCyclomatics = new HashMap<>();
        this.classCyclomatics = new HashMap<>();
        this.projectCyclomatic = 1;
    }


}
