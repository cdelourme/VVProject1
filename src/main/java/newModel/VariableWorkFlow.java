package newModel;

import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.bind.v2.TODO;
import model.Expression;
import newModel.element.*;
import newModel.variableAccess.DeclarationElement;
import newModel.variableAccess.ExpressionElement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.LinkedList;

public class VariableWorkFlow {

    private SourcePosition id;
    private DeclarationElement declaration;
    private LinkedList<ExpressionElement> variableAccess;

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



}
