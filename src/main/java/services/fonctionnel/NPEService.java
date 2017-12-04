package services.fonctionnel;

import model.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;

import java.util.HashMap;

public class NPEService {
     public static NPEService instance = new NPEService();

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
               if(varAcc.getParent(CtExpression.class)==null){
                    varAccess.get(addVariableAccess(varAcc)).addExp(varAcc,isWrite);

               }else{
                    varAccess.get(addVariableAccess(varAcc)).addExp(varAcc.getParent(CtExpression.class),isWrite);

               }
          }
     }


     public VariableWorkFlow getWorkFlow(int hash){
          return this.varAccess.get(hash);
     }

}
