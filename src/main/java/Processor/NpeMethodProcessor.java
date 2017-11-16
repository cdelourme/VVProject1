package Processor;

import model.VariableWorkFlow;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.code.CtVariableWrite;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;
import utils.VariableService;

import java.util.List;

public class NpeMethodProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        //Faire un travail a l'échelle d'un methode avant de la faire a l'echelle d'un programme.
        //première étape regarger si la variable peut être a un moment donné déclarée a null.
        //Si oui lors de lecture regarder la dernière affectation
        // ou si un test est fait avant

        List<CtVariableAccess> accessVars = ctMethod.getElements(new TypeFilter<>(CtVariableAccess.class));
        List<CtVariableWrite> writeVars = ctMethod.getElements(new TypeFilter<>(CtVariableWrite.class));

        if(accessVars.size() != 0 && writeVars.size() != 0){

            accessVars.forEach(p->
                VariableService.instance.addVariableAccess(p,writeVars.contains(p))
            );

            List<CtVariableRead> readVars = ctMethod.getElements(new TypeFilter<>(CtVariableRead.class));

            readVars.forEach(p->{
                VariableWorkFlow workFlow = VariableService.instance.getWorkFlow(p.getVariable().getDeclaration().hashCode());
                workFlow.getPreviousWriteExpression(p);
            });
        }




    }

}
