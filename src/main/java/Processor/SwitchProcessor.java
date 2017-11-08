package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtSwitch;

public class SwitchProcessor extends AbstractProcessor<CtSwitch>{
    @Override
    public void process(CtSwitch ctSwitch) {
        System.out.println("CtSwitch : " + ctSwitch.toString());
    }
}
