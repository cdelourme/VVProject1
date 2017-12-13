package newModel.element;

import newModel.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.cu.SourcePosition;

public abstract class AElement {

    private AElement parent;
    protected SourcePosition id;

    public SourcePosition getId(){
        return id;
    }

    public AElement(AElement parent){
        this.parent = parent;
    }

    public boolean hasParent(CtExpression exp){
        return exp.getParent().getPosition() == id;
    }

    abstract void addExpression(VariableWorkFlow workflow, CtVariableAccess varAcc);
    abstract void addExpression(CtVariableAccess varAcc);

    abstract boolean bodyContains(CtExpression exp);

    abstract VariableWorkFlow getWorkFlow(CtVariableAccess varAcc);



}
