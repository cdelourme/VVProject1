package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeElement;
import spoon.reflect.declaration.CtClass;

public class ClassProcessor extends AbstractProcessor<CtClass> {

    @Override
    public void process(CtClass ctClass) {
        ctClass.getDeclaredFields().forEach(p -> {
            System.out.println(p.getQualifiedName());
        });
    }

}