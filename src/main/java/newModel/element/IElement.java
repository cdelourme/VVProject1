package newModel.element;

import newModel.VariableWorkFlow;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;

public interface IElement {

    void addExpression(VariableWorkFlow workflow, CtVariableAccess varAcc);
    void addExpression(CtVariableAccess varAcc);

    boolean bodyContains(CtElement exp);

    VariableWorkFlow getWorkFlow(CtVariableAccess varAcc);

    /**
     * Definie si le block return ou non un NPE pour une variable
     * True -> NPE possible
     * False -> NPE impossible
     * Null -> voir l'assignation précedante
     * @param var
     * @return
     */
    Boolean throwNPE(CtVariable var);

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
    Boolean throwNPE(CtExpression exp, VariableWorkFlow workFlow);
}
