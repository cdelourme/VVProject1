package model;

import spoon.reflect.code.CtExpression;

public abstract class Element {

    private CtExpression exp;
    public CtExpression getExp() { return exp; }

    private boolean isComplex;
    public boolean isComplex() {
        return isComplex;
    }

    private boolean isWrite;
    public boolean isWrite() {
        return isWrite;
    }



    public Element(CtExpression exp, boolean isWrite){
        this.exp = exp;
        this.isWrite = isWrite;
    }




}
