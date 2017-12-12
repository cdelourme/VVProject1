package Processor;

import services.fonctionnel.SpoonService;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtMethod;
import newService.*;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;
import java.util.stream.Collectors;

public class NewNpeProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        NPEService.instance.addMethod(ctMethod.getSignature(), ctMethod);
        List<CtVariableAccess> accessVars = ctMethod.getElements(new TypeFilter<>(CtVariableAccess.class));
        List<CtExpression> exp = accessVars.stream().map(p-> SpoonService.getParentExpression(p)).collect(Collectors.toList());

        exp.forEach(p->NPEService.instance.addExpression(ctMethod.getSignature(), p));
    }
}
