package newModel.element;

import newModel.VariableWorkFlow;
import org.omg.CORBA.portable.ValueInputStream;
import spoon.reflect.code.*;
import spoon.reflect.cu.SourcePosition;

import java.util.LinkedList;

public class BlockElement implements IElement {

    private SourcePosition id;
    private LinkedList<VariableWorkFlow> variables;

    public BlockElement(SourcePosition id){
        this.id = id;
    }

    public void addVariableAccess(CtVariableAccess varAcc) {
        if(variables.stream().anyMatch(p->p.as(varAcc))){
            //VariableWorkFlow workFlow = this.variables.
        }
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
