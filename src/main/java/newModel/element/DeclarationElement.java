package newModel.element;

import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtVariable;

public class DeclarationElement implements IElement {

    private CtVariable declaration;

    public DeclarationElement(CtVariable var){
        this.declaration = var;
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
