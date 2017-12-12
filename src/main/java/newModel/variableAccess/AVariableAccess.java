package newModel.variableAccess;

import newModel.element.AElement;
import spoon.reflect.cu.SourcePosition;

public abstract class AVariableAccess {

    protected SourcePosition id;
    public SourcePosition getId() {
        return id;
    }

    protected AElement parent;

    public AVariableAccess(AElement parent){
        this.parent = parent;
    }

}
