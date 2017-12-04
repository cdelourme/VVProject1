package model;

import javafx.util.Pair;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class VariableWorkFlow {

    public CtElement getDeclaration() {
        return declaration;
    }

    private CtElement declaration;
    private LinkedList<Element> variableAccess;

    public LinkedList<Element> getVariableAccess() {
        return variableAccess;
    }

    public VariableWorkFlow(CtElement decla){
        this.declaration = decla;
        variableAccess = new LinkedList<>();
    }

    public void addExp(CtExpression elem, Boolean isWrite){
       CtIf ifParent = elem.getParent(CtIf.class);
        if(ifParent != null){
            System.out.println(ifParent);
            variableAccess.add(new SimpleElement(elem,isWrite));
       }
        else{
            variableAccess.add(new SimpleElement(elem,isWrite));
        }

    }

    public CtElement getPreviousWriteExpression(CtVariableRead var) {
        CtExpression exp = var.getParent(CtExpression.class) != null ? var.getParent(CtExpression.class) : var;
        Element readvar = variableAccess.stream().filter(p->p.getExp()==exp).findFirst().get();
        List<Element> beforeWriteExps = variableAccess.subList(0,variableAccess.indexOf(readvar))
                .stream().filter(p->p.isWrite()).collect(Collectors.toList());
        if(beforeWriteExps.size()==0){
            return declaration;
        }
        else
        {
            int index = variableAccess.indexOf(beforeWriteExps.get(beforeWriteExps.size()-1));
            return variableAccess.get(index).getExp();
        }
    }


    // TODO: replace LinkedList<CtExpression> to LinkedList<Element>
    // Element pouvant ếtre complex si dans if,while...
    // ou simple
    // Element define if it's read or write
    // doit avoir une methode permettant de dire si il est placé to null.
}
