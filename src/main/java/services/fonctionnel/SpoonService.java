package services.fonctionnel;

import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonService {

    public static CtExpression getParentExpression(CtVariableAccess var ){
        if(var.getParent(new TypeFilter<>(CtExpression.class))!= null){
            return var.getParent(new TypeFilter<>(CtExpression.class));
        }else{
            return var;
        }
    }

    public static CtVariableAccess getVarAccess(CtExpression exp){
        return exp.filterChildren(new TypeFilter<>(CtVariableAccess.class)).first(CtVariableAccess.class);
    }

    /*public static CtExpression getParentExpression(CtVariable var ){
        if(var.getParent(new TypeFilter<>(CtExpression.class))!= null){
            return var.getParent(new TypeFilter<>(CtExpression.class));
        }else{
            return var;
        }
    }*/
}
