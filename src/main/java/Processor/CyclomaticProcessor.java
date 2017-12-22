package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.visitor.filter.TypeFilter;
import services.fonctionnel.CyclomaticService;

import java.util.List;

/**
 * Processor use for cyclomatic research
 */
public class CyclomaticProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        String name = ctMethod.getSimpleName();
        String className = ctMethod.getParent(new TypeFilter<>(CtClass.class)).getQualifiedName();

        List<CtIf> ifElements = ctMethod.getElements(new TypeFilter<>(CtIf.class));
        List<CtSwitch> switchElements = ctMethod.getElements(new TypeFilter<>(CtSwitch.class));
        List<CtLoop> loopElements = ctMethod.getElements(new TypeFilter<>(CtLoop.class));

        CyclomaticService.instance.setMethodCyclomatics(className,name,ifElements.size()+switchElements.size()+loopElements.size());

//        List<CtElement> list = ctMethod.getElements(new TypeFilter<>(CtElement.class));
//        for(CtElement p : list){
//            System.out.println( " Fonction : " + name + " : " + p.getClass().getSimpleName() + " : " + p.toString() );
//        }
    }

}