package model;

import javafx.util.Pair;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class VariableWorkFlow {

    private Element declaration;
    private LinkedList<Element> variableAccess;

    public VariableWorkFlow(CtVariable decla){
        this.declaration = new DeclarationElement(decla);
        variableAccess = new LinkedList<>();
    }

    public void addExp(CtExpression elem, Boolean isWrite){
       CtIf ifParent = elem.getParent(CtIf.class);
        if(ifParent != null){
            if(this.variableAccess.stream().filter(p-> p.isComplex())
                    .filter(p -> (((ComplexElement) p).getId() == ifParent.getPosition()))
                    .count()==0){
                variableAccess.add(new ComplexElement(ifParent, elem ,isWrite));
            }
            else{
                ((ComplexElement)this.variableAccess.stream().filter(p-> p.isComplex())
                        .filter(p -> (((ComplexElement) p).getId() == ifParent.getPosition()))
                        .toArray()[0]).addExpression(elem, isWrite);
            }

        }
        else{
            variableAccess.add(new SimpleElement(elem,isWrite));
        }

    }

    public Element getPreviousWriteExpression(CtExpression var) {
        Element readvar = variableAccess.stream().filter(p->p.contains(var)).findFirst().orElse(null);
        if(readvar != null){
            List<Element> beforeWriteExps = variableAccess.subList(0,variableAccess.indexOf(readvar))
                    .stream().filter(p->p.isWrite()).collect(Collectors.toList());
            if(beforeWriteExps.size()==0){
                return declaration;
            }
            else
            {
                int index = variableAccess.indexOf(beforeWriteExps.get(beforeWriteExps.size()-1));
                return variableAccess.get(index);
            }
        }else{
            System.out.println("Elem not fond on model...");
            return null;
        }

    }
}
