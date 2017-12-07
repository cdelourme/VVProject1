package newModel.element;

import spoon.reflect.code.CtExpression;

public class LoopElement implements IElement {

    private ExpressionElement condition;
    private BlockElement body;

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
