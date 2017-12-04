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

        //Recupération des acces au variable en acces (ecriture& lecture.
        //Recupération des acces au variable en ecriture.
        List<CtVariableAccess> accessVars = ctMethod.getElements(new TypeFilter<>(CtVariableAccess.class));
        List<CtVariableWrite> writeVars = ctMethod.getElements(new TypeFilter<>(CtVariableWrite.class));

        if(accessVars.size() != 0 && writeVars.size() != 0){
            //Construction de l'arborescence des acces au variable.

            accessVars.forEach(p-> VariableService.instance.addVariableAccess(p,writeVars.contains(p)) );
            //writeVars.forEach(p-> VariableService.instance.addWriteVariableAccess(p,true) );

            //recherche d'éventuelle NPE
            List<CtVariableRead> readVars = ctMethod.getElements(new TypeFilter<>(CtVariableRead.class));
            readVars.forEach(p->{
                if(p.getVariable().getDeclaration()!=null) { //évite les variable Sys
                    VariableWorkFlow workFlow = VariableService.instance.getWorkFlow(p.getVariable().getDeclaration().hashCode());
                    workFlow.getPreviousWriteExpression(p);
                }
            });
        }




    }

}
