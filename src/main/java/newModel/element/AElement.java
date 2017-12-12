package newModel.element;

import newModel.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
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

    abstract void addExpression(VariableWorkFlow workflow, CtExpression exp);
    abstract void addExpression(CtExpression exp);

    abstract boolean bodyContains(CtExpression exp);



}
