package newModel.variableAccess;

import newModel.element.AElement;
import spoon.reflect.code.CtExpression;

public class ExpressionElement extends AVariableAccess{

    private CtExpression expression;
    public CtExpression getExp() {
        return expression;
    }
    private boolean isWrite;

    public ExpressionElement(AElement parent, CtExpression exp) {
        super(parent);
        this.expression = exp;
        id = this.expression.getPosition();
    }



    public boolean contains(CtExpression exp) {
        return this.expression == exp;
    }

    public Boolean throwNPE() {
        return null;
    }

}
