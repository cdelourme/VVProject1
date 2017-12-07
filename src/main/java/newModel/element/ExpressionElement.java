package newModel.element;

import spoon.reflect.code.CtExpression;

public class ExpressionElement implements IElement {

    private CtExpression expression;

    public ExpressionElement(CtExpression exp) {
        this.expression = exp;
    }

    public CtExpression getExp() {
        return expression;
    }

    @Override
    public boolean contains(CtExpression exp) {
        return false;
    }

    @Override
    public boolean get(CtExpression exp) {
        return false;
    }

    @Override
    public Boolean throwNPE() {
        return null;
    }
}
