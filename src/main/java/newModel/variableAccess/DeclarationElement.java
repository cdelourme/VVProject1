package newModel.variableAccess;

import newModel.element.AElement;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtVariable;

public class DeclarationElement extends AVariableAccess {

    private CtVariable declaration;

    public DeclarationElement(AElement parent, CtVariable var){
        super(parent);
        this.declaration = var;
        this.id = var.getPosition();
    }
}
