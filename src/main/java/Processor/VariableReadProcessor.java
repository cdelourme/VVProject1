package Processor;

import model.VariableWorkFlow;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableRead;
import utils.VariableService;

public class VariableReadProcessor extends AbstractProcessor<CtVariableRead> {
    @Override
    public void process(CtVariableRead var) {
        System.out.println(var.getParent());
        VariableWorkFlow workFlow = VariableService.instance.getWorkFlow(var.getVariable().getDeclaration().hashCode());
        System.out.println(workFlow.getVariableAccess());
        workFlow.getPreviousExpression(var);
    }
}
