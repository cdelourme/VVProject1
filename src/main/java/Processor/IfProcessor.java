package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import utils.CyclomaticService;

public class IfProcessor extends AbstractProcessor<CtIf>{
    @Override
    public void process(CtIf ctIf) {
        CyclomaticService.instance.addProjectCyclomatic();
        System.out.println("CtIf : " + ctIf.toString());
    }
}
