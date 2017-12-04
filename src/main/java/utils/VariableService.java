package utils;

import model.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.code.CtVariableWrite;

import java.util.HashMap;

public class VariableService {
     public static VariableService instance = new VariableService();

     private HashMap<Integer, VariableWorkFlow> varAccess = new HashMap<>();

     /**
      * Recupération de la déclaration, et création d'un WorkFlow pour la variable si il n'existe pas
      * @param varAcc
      * @return
      */
     private Integer addVariableAccess(CtVariableAccess varAcc){
          if(varAcc.getVariable().getDeclaration()==null){
               return null;
          }
          int hashCode = varAcc.getVariable().getDeclaration().hashCode();
          if(!varAccess.containsKey(hashCode)){
               varAccess.put(hashCode ,
                       new VariableWorkFlow(varAcc.getVariable().getDeclaration()));
          }
          return hashCode;
     }

     /**
      * Ajout de l'expression en tant que lecture de la variable.
      * @param varAcc
      */
     public void addVariableAccess(CtVariableAccess varAcc, boolean isWrite){
          Integer index =addVariableAccess(varAcc);
          if(index!=null){
               varAccess.get(addVariableAccess(varAcc)).addExp(varAcc.getParent(CtExpression.class),isWrite);
          }
     }

     private VariableService() {

     }

     public VariableWorkFlow getWorkFlow(int hash){
          return this.varAccess.get(hash);
     }

}
