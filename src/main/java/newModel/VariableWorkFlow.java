package newModel;

import newModel.element.*;
import newModel.variableAccess.AVariableAccess;
import newModel.variableAccess.DeclarationElement;
import newModel.variableAccess.ExpressionElement;
import spoon.reflect.code.*;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtVariable;

import java.util.LinkedList;
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

    public LinkedList<AVariableAccess> getPreviousWriteExpS(ExpressionElement exp) {
        int indexExp = variableAccess.indexOf(exp);
        LinkedList<ExpressionElement> previousAccess = new LinkedList<ExpressionElement>(variableAccess.subList(0,indexExp));
        LinkedList<ExpressionElement> previousWriteAccess = new LinkedList<ExpressionElement>(previousAccess.stream()
                .filter(p->p.isWrite).collect(Collectors.toList()));
        LinkedList<AVariableAccess> previousAAccess = new LinkedList<>(previousWriteAccess);
        previousAAccess.add(0,declaration);
        return previousAAccess;
    }

    public ExpressionElement getExp(CtExpression exp) {
        if(variableAccess.stream().filter(p->p.contains(exp)).findFirst().isPresent()){
            return variableAccess.stream().filter(p->p.contains(exp)).findFirst().get();
        }
        else {
            System.out.println("var " + exp.toString() + " not found");
            return null;
        }
    }

    public Boolean throwNPE(CtExpression exp) {
        return this.declaration.getParent().throwNPE(exp, this);
    }
}
