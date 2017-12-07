package model;

import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;


public class SimpleElement extends Element {

    private Expression exp;
    public CtExpression getExp() { return exp.getExp(); }

    public SimpleElement(CtExpression elem, boolean isWrite){
        this.exp = new Expression(elem, isWrite);
    }

    @Override
    public boolean contains(CtExpression exp) {
        return this.exp.getExp() == exp;
    }

    @Override
    public CtElement getElem() {
        return this.exp.getExp();
    }

    @Override
    public boolean isWrite() {
        return this.exp.isWrite();
    }

    @Override
    public boolean isComplex() {
        return false;
    }

    @Override
    public Boolean throwNPE(){
        return this.exp.throwNPE();
    }
}
