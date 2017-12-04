package services.fonctionnel;

import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.visitor.chain.CtQuery;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtReturnImpl;

import java.util.HashMap;

public class ReturnStatementService {
    public static ReturnStatementService instance = new ReturnStatementService();

    private HashMap<String,Boolean> method = new HashMap<String, Boolean>();


    public void setReturnStatement(String classe, String method, CtBlock methodBody){

        CtQuery returnStatement = methodBody.filterChildren(new TypeFilter<>(CtReturn.class));
        returnStatement.forEach((CtReturnImpl p) -> {
            System.out.println(p);
            CtExpression returnExp = p.getReturnedExpression();

            System.out.println(returnExp.getElements(new TypeFilter<>(CtVariableRead.class)));
        });
    }
}
