package newModel;

import com.sun.org.apache.xpath.internal.operations.Bool;
import newModel.element.*;
import newModel.variableAccess.DeclarationElement;
import newModel.variableAccess.ExpressionElement;
import spoon.reflect.code.*;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtVariable;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class VariableWorkFlow {

    private SourcePosition id;
    private DeclarationElement declaration;
    private LinkedList<ExpressionElement> variableAccess;

    public DeclarationElement getDeclaration() {
        return declaration;
    }

    public VariableWorkFlow(AElement parent, CtVariable var) {
        id = var.getPosition();
        declaration = new DeclarationElement(parent, var);
        variableAccess = new LinkedList<>();
    }


    public void addExpression(ExpressionElement exp){
        this.variableAccess.add(exp);
    }


    public boolean as(CtVariableAccess varAcc){
        return varAcc.getVariable().getDeclaration().getPosition() == id;
    }

    private LinkedList<ExpressionElement> getPreviousWriteExp(ExpressionElement exp) {
        int indexExp = variableAccess.indexOf(exp);
        LinkedList<ExpressionElement> previousAccess = (LinkedList<ExpressionElement>)variableAccess.subList(0,indexExp);
        LinkedList<ExpressionElement> previousWriteAccess = (LinkedList<ExpressionElement>)previousAccess.stream()
                .filter(p->p.isWrite).collect(Collectors.toList());
        return previousWriteAccess;

    }

    private ExpressionElement getExp(CtExpression exp) {
        return variableAccess.stream().filter(p->p.contains(exp)).findFirst().get();
    }

    public Boolean throwNPE(CtExpression exp, AElement parent) {
        ExpressionElement expE = getExp(exp);
        LinkedList<ExpressionElement> previousExpEs = getPreviousWriteExp(expE);

        previousExpEs = (LinkedList<ExpressionElement>)previousExpEs.stream()
                .filter(p->p.getParent().getId() == parent.getId())
                .collect(Collectors.toList());


        //test si affection dans les block avant lecture
            //oui (remonte les exp en cas de null)
                //affect a null -> true
                //affect non null -> false
                //affect indefinie -> null
            //non
                //null


        if(previousExpEs.size()>0){
            ListIterator<ExpressionElement> iterator = previousExpEs.listIterator(previousExpEs.size());

            while (iterator.hasPrevious()){
                ExpressionElement previousExpE = iterator.previous();
                Boolean throwNPE = previousExpE.throwNPE();
                if(throwNPE!=null){
                    return throwNPE;
                }
            }
            return null;
        }
        else {
            return null;
        }




    }



}
