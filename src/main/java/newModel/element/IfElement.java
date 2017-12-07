package newModel.element;

import spoon.reflect.code.CtExpression;
import spoon.reflect.cu.SourcePosition;

public class IfElement implements IElement{

    private SourcePosition Id;
    private ExpressionElement condition;
    private BlockElement ifBlock;
    private BlockElement elseBlock;

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
