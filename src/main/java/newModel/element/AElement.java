package newModel.element;

import newModel.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtVariable;

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

    /**
     * Definie si le block return ou non un NPE pour une variable
     * True -> NPE possible
     * False -> NPE impossible
     * Null -> voir l'assignation précedante
     * @param var
     * @return
     */
    abstract Boolean throwNPE(CtVariable var);

    /**
     * Definie l'élement retourne une NPE pour une expression.
     * l'expression dois appartenir au block
     * True -> NPE possible
     * False -> NPE impossible
     * Null -> voir l'assignation précedante
     * @param exp
     * @param workFlow
     * @return
     */
    abstract Boolean throwNPE(CtExpression exp, VariableWorkFlow workFlow);


}
