package newModel.element;

import spoon.reflect.code.CtExpression;

public interface IElement {
    boolean contains(CtExpression exp);
    boolean get(CtExpression exp);
    Boolean throwNPE();
}
