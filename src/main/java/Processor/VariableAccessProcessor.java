package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableAccess;
import utils.VariableService;


public class VariableAccessProcessor extends AbstractProcessor<CtVariableAccess> {


    @Override
    public void process(CtVariableAccess var) {
        //VariableService.instance.addVariableAccess(var);

        // TODO detect if var was set to null


    }
}