package services.fonctionnel;

import model.Element;
import model.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.VariableAccessFilter;

import java.util.HashMap;

public class NPEService {
     public static NPEService instance = new NPEService();

     private HashMap<Integer, VariableWorkFlow> varAccess = new HashMap<>();

     /**
      * Recupération de la déclaration, et création d'un WorkFlow pour la variable si il n'existe pas
      * @param varAcc
      * @return
      */
     private VariableWorkFlow getWorkFlow(CtVariableAccess varAcc){
          if(varAcc.getVariable().getDeclaration()==null){
               return null;
          }

          int hashCode = varAcc.getVariable().getDeclaration().hashCode();
          VariableWorkFlow returnVal = varAccess.getOrDefault(hashCode, null);
          if(returnVal == null ){
               returnVal = new VariableWorkFlow(varAcc.getVariable().getDeclaration());
               varAccess.put(hashCode, returnVal);
          }
          return returnVal;
     }

     /**
      * Ajout de l'expression en tant que lecture de la variable.
      * @param varAcc
      */
     public void addVariableAccess(CtVariableAccess varAcc, boolean isWrite){
          VariableWorkFlow workFlow = getWorkFlow(varAcc);
          if(workFlow!=null){
               workFlow.addExp(SpoonService.getParentExpression(varAcc),isWrite);
          }
     }

     public Boolean throwNPE(CtVariableRead readVar){
          System.out.println("\tPour : "+ readVar);
          VariableWorkFlow workFlow = getWorkFlow(readVar);
          if(workFlow!=null){
               Element previousExp = workFlow.getPreviousWriteExpression(SpoonService.getParentExpression(readVar));
               if(previousExp!= null){
                    System.out.println("\tla dernière écriture est : "+ previousExp.getElem());
                    previousExp.throwNPE();
               }

          }
          return false;
     }

}
