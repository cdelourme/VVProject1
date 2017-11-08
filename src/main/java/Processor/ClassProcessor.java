package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;
import utils.CyclomaticService;

import java.util.List;

public class ClassProcessor extends AbstractProcessor<CtClass> {

    @Override
    public void process(CtClass ctClass) {
        String name = ctClass.getQualifiedName();
        List<CtIf> ifElements = ctClass.getElements(new TypeFilter<>(CtIf.class));
        List<CtSwitch> switchElements = ctClass.getElements(new TypeFilter<>(CtSwitch.class));
        List<CtLoop> loopElements = ctClass.getElements(new TypeFilter<>(CtLoop.class));

        CyclomaticService.instance.setClassCyclomatics(name,ifElements.size()+switchElements.size()+loopElements.size());

    }

}