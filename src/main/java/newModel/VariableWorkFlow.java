package newModel;

import model.ComplexElement;
import model.Expression;
import model.SimpleElement;
import newModel.element.DeclarationElement;
import newModel.element.ExpressionElement;
import newModel.element.IElement;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.cu.SourcePosition;

import java.util.LinkedList;

public class VariableWorkFlow {

    private SourcePosition id;
    private DeclarationElement declaration;
    private LinkedList<IElement> variableAccess;


    public VariableWorkFlow(CtVariableAccess varAcc) {
        id = varAcc.getVariable().getDeclaration().getPosition();
        declaration = new DeclarationElement(varAcc.getVariable().getDeclaration());
    }

    public void addExp(CtVariableAccess varAcc) {
        ExpressionElement exp = new ExpressionElement(SpoonService.getParentExpression(varAcc));

        CtIf ifParent = exp.getExp().getParent(CtIf.class);
        if(ifParent != null) {

        }
        else{
        }

    }

    public boolean as(CtVariableAccess varAcc){
        return varAcc.getVariable().getDeclaration().getPosition() == id;
    }



}
