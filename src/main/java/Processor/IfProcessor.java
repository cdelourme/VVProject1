package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;

public class IfProcessor extends AbstractProcessor<CtIf>{
    @Override
    public void process(CtIf ctIf) {
        System.out.println("CtIf : " + ctIf.toString());
    }
}
