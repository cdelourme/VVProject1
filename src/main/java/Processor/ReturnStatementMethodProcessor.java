package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.chain.CtQuery;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtReturnImpl;

public class ReturnStatementMethodProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        //System.out.println(ctMethod);
        CtQuery returnStatement = ctMethod.getBody().filterChildren(new TypeFilter<>(CtReturn.class));
        returnStatement.forEach((CtReturnImpl p) -> {
            System.out.println(p);
            CtExpression returnExp = p.getReturnedExpression();

            System.out.println(returnExp.getElements(new TypeFilter<>(CtVariableRead.class)));
        });
    }
}
