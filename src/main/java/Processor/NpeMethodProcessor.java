package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;

public class NpeMethodProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        //Faire un travail a l'échelle d'un methode avant de la faire a l'echelle d'un programme.
        //première étape regarger si la variable peut être a un moment donné déclarée a null.
        //Si oui lors de lecture regarder la dernière affectation
        // ou si un test est fait avant
        List<CtVariable> declarVar = ctMethod.getElements(new TypeFilter<>(CtVariable.class));
        System.out.println(declarVar);
    }

}
