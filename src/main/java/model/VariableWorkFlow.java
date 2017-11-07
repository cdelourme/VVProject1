package model;

import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;

import java.util.LinkedList;

public class VariableWorkFlow {

    public CtElement getDeclaration() {
        return declaration;
    }

    private CtElement declaration;
    private LinkedList<CtExpression> variableAccess;

    public LinkedList<CtExpression> getVariableAccess() {
        return variableAccess;
    }

    public VariableWorkFlow(CtElement decla){
        this.declaration = decla;
        variableAccess = new LinkedList<>();
    }

    public void addExp(CtExpression elem){
        variableAccess.add(elem);
    }

    public CtExpression getPreviousExpression(CtVariableRead var) {
        CtExpression exp = var.getParent(CtExpression.class);
        System.out.println(variableAccess);
        System.out.println(exp);
        if(variableAccess.contains(exp)){
            System.out.println("contains");
            int index = variableAccess.indexOf(exp);

            if(index==0){
                System.out.println(declaration);
            }
            else{
                System.out.println(variableAccess.get(index-1));
            }
        }
        return null;
    }


    // TODO: replace LinkedList<CtExpression> to LinkedList<Element>
    // Element pouvant ếtre complex si dans if,while...
    // ou simple
    // Element define if it's read or write
    // doit avoir une methode permettant de dire si il est placé to null.
}
