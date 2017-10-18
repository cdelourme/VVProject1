package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableAccess;

public class VariableAccessProcessor extends AbstractProcessor<CtVariableAccess> {

    @Override
    public void process(CtVariableAccess var) {
        System.out.println();
        System.out.println(var.getVariable());
        System.out.println(var.getVariable().getDeclaration());
        System.out.println(var.getParent());
    }
}