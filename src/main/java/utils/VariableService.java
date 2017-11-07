package utils;

import model.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;

import java.util.HashMap;

public class VariableService {
     public static VariableService instance = new VariableService();

     private HashMap<Integer, VariableWorkFlow> varAccess = new HashMap<>();

     public void addVariableAccess(CtVariableAccess varAcc){
          int hashCode = varAcc.getVariable().getDeclaration().hashCode();
          if(!varAccess.containsKey(hashCode)){
               varAccess.put(hashCode ,
                       new VariableWorkFlow(varAcc.getVariable().getDeclaration()));
          }
          varAccess.get(hashCode).addExp(varAcc.getParent(CtExpression.class));
     }

     private VariableService() {

     }

     public VariableWorkFlow getWorkFlow(int hash){
          return this.varAccess.get(hash);
     }

     @Override
     public String toString(){
          this.varAccess.values().forEach(p -> {
               System.out.println(p.getDeclaration());
               System.out.println(p.getVariableAccess());
               System.out.println();
          });
          return "";
     }
}
