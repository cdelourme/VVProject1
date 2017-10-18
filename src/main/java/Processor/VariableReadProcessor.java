package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableRead;

public class VariableReadProcessor extends AbstractProcessor<CtVariableRead> {
    @Override
    public void process(CtVariableRead var) {
        System.out.println(var.getVariable().getSimpleName());
    }
}
