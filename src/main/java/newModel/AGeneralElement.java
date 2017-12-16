package newModel;

import newModel.element.AElement;
import spoon.reflect.cu.SourcePosition;

public abstract class AGeneralElement implements IGeneralElement {

    protected SourcePosition id;
    public SourcePosition getId(){
        return id;
    }

    private AElement parent;
    public AElement getParent() { return parent; }

    public AGeneralElement(AElement parent){
        this.parent = parent;
    }


}
