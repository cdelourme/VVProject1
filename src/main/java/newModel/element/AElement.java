package newModel.element;


import newModel.AGeneralElement;
import newModel.variableAccess.AVariableAccess;

public abstract class AElement extends AGeneralElement implements IElement {

    public AElement(AElement parent){
        super(parent);
    }

    public boolean isDirectParentOf(AGeneralElement expE){
        return ((expE.getParent() != null ) ? expE.getParent().getId() == id : false);
    }

    public AElement getLastChildOf(AVariableAccess expE){
        if(!isDirectParentOf(expE)){
            AElement lastChild = expE.getParent();
            while(!isDirectParentOf(lastChild)){
                lastChild = lastChild.getParent();
                if(lastChild== null){
                    break;
                }
            }
            return lastChild;
        }
        else {
            return null;
        }
    }
}
