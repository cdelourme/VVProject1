package model;

import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.reference.CtTypeReference;

import javax.lang.model.type.NullType;

public class DeclarationElement extends Element {

    private CtVariable declaration;

    public DeclarationElement(CtVariable decla){
        this.declaration = decla;
    }

    @Override
    public boolean contains(CtExpression exp) {
        return false;
    }

    @Override
    public CtElement getElem() {
        return declaration;
    }

    @Override
    public boolean isWrite() {
        return false;
    }

    @Override
    public boolean isComplex() {
        return false;
    }

    @Override
    public Boolean throwNPE() {
        return this.declaration.getDefaultExpression() == null;
    }
}
