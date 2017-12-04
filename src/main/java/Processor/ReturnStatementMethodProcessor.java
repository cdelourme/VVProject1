package Processor;

import services.fonctionnel.ReturnStatementService;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.chain.CtQuery;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtReturnImpl;

public class ReturnStatementMethodProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        //System.out.println(ctMethod);
        String name = ctMethod.getSimpleName();
        String className = ctMethod.getParent(new TypeFilter<>(CtClass.class)).getQualifiedName();
        ReturnStatementService.instance.setReturnStatement(className,name,ctMethod.getBody());

    }
}
