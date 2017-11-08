package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtSwitch;
import utils.CyclomaticService;

public class SwitchProcessor extends AbstractProcessor<CtSwitch>{
    @Override
    public void process(CtSwitch ctSwitch) {
        CyclomaticService.instance.addProjectCyclomatic();
    }
}
