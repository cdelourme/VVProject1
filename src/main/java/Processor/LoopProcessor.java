package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLoop;

public class LoopProcessor extends AbstractProcessor<CtLoop> {
    @Override
    public void process(CtLoop ctLoop) {
        System.out.println("CtLoop");
        System.out.println(ctLoop.getLabel());
    }
}
