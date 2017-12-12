package newService;

import com.sun.deploy.util.BlackList;
import newModel.VariableWorkFlow;
import newModel.element.BlockElement;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;

import java.util.HashMap;
import java.util.LinkedList;

public class NPEService {
    public static NPEService instance = new NPEService();

    private HashMap<String,BlockElement> methods = new HashMap<>();

    private LinkedList<VariableWorkFlow> globalVars = new LinkedList<>(); //for class var not implement for the moment

    public BlockElement getBlock(String name) {
        return methods.getOrDefault(name,null);
    }


    public void addMethod(String name, CtMethod method) {
        BlockElement methodBlock = new BlockElement(null, method.getBody());
        method.getParameters().stream().forEach(p->{
            methodBlock.createWorkFlow(((CtVariable) p));
        });
        this.methods.put(name,methodBlock);
    }

    public void addExpression(String methodName, CtExpression exp){
        BlockElement method = methods.get(methodName);

        //Gestion des variable de class
        if(method.includeDeclaration(exp)){
            method.addExpression(exp);
        }
        else
        {
            CtVariable var =  SpoonService.getVarAccess(exp).getVariable().getDeclaration();
            if(var != null){
                VariableWorkFlow workFlow = new VariableWorkFlow(null,var);
                this.globalVars.add(workFlow);
                method.addExpression(workFlow,exp);
            }
        }
    }

    /*public VariableWorkFlow getVariableWorkFlow(String methodName, CtVariableAccess varAcc){
        //faire une recherche de work flow en local
        //recherche dans la methode qui entraine une récursive
    }*/

}
