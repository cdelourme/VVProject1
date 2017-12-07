package model;

import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;

public abstract class Element {

    public abstract boolean contains(CtExpression exp);
    public abstract CtElement getElem();
    public abstract boolean isWrite();
    public abstract boolean isComplex();

    /**
     * Method which declare if Element throw an NPE
     * @return
     * true -> throw NPE.
     * false -> no throw NPE.
     * null -> go see up.
     */
    public abstract Boolean throwNPE();
}
