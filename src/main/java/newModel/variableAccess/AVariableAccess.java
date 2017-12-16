package newModel.variableAccess;

import newModel.AGeneralElement;
import newModel.element.AElement;
import spoon.reflect.cu.SourcePosition;

public abstract class AVariableAccess extends AGeneralElement implements IVariableAccess{

    public AVariableAccess(AElement parent){
        super(parent);
    }

}
