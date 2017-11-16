package model;

import javafx.util.Pair;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;

import java.util.LinkedList;

public class VariableWorkFlow {

    public CtElement getDeclaration() {
        return declaration;
    }

    private CtElement declaration;
    private LinkedList<Pair<CtExpression,Boolean>> variableAccess;

    public LinkedList<Pair<CtExpression,Boolean>> getVariableAccess() {
        return variableAccess;
    }

    public VariableWorkFlow(CtElement decla){
        this.declaration = decla;
        variableAccess = new LinkedList<>();
    }

    public void addExp(CtExpression elem, Boolean isWrite){
        variableAccess.add(new Pair(elem,isWrite));
    }

    public CtExpression getPreviousWriteExpression(CtVariableRead var) {
        CtExpression exp = var.getParent(CtExpression.class);
        System.out.println(exp);
        variableAccess.forEach(p->{
            if(p.getKey()==exp){
                int index = variableAccess.indexOf(p);
                if(index==0){
                    System.out.println(declaration);
                }
                else{
                    int indexbis = index;
                    while(indexbis>0){
                        if(variableAccess.get(indexbis).getValue()){
                            System.out.println(variableAccess.get(indexbis).getKey());
                            return;
                        }else{
                            indexbis = indexbis-1;
                        }
                    }
                    System.out.println(declaration);

                }
            }
        });
        return null;
    }


    // TODO: replace LinkedList<CtExpression> to LinkedList<Element>
    // Element pouvant ếtre complex si dans if,while...
    // ou simple
    // Element define if it's read or write
    // doit avoir une methode permettant de dire si il est placé to null.
}
