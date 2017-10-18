package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableRead;

public class VariableReadProcessor extends AbstractProcessor<CtVariableRead> {
    @Override
    public void process(CtVariableRead var) {
        System.out.println();
        System.out.println(var.getVariable().getSimpleName());
        System.out.println(var.getVariable().getDeclaration());

        System.out.println(var.getVariable().getParent());
        System.out.println(var.getVariable().getParent().getParent());
        System.out.println(var.getVariable().getType());
    }
}
