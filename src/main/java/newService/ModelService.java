package newService;

import newModel.AGeneralElement;
import newModel.element.AElement;
import newModel.variableAccess.AVariableAccess;

import java.util.LinkedList;

public class ModelService {
    public static ModelService instance = new ModelService();

    public LinkedList<AGeneralElement> converToAGenE(LinkedList<AVariableAccess> expS, AElement parent){
        LinkedList<AGeneralElement> returnList = new LinkedList<>();

        expS.forEach(p->{
            if(parent.isDirectParentOf(p)){
                returnList.add(p);
            }
            else {
                AElement lastchild = parent.getLastChildOf(p);
                if(!returnList.contains(lastchild)){
                    returnList.add(lastchild);
                }
            }
        });
        return returnList;
    }
}
