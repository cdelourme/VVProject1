package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLoop;
import utils.CyclomaticService;

public class LoopProcessor extends AbstractProcessor<CtLoop> {
    @Override
    public void process(CtLoop ctLoop) {
        CyclomaticService.instance.addProjectCyclomatic();
        System.out.println("CtLoop : " + ctLoop.toString());
    }
}
