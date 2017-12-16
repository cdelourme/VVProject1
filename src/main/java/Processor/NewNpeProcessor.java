package Processor;

import newModel.VariableWorkFlow;
import services.fonctionnel.SpoonService;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtMethod;
import newService.*;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;
import java.util.stream.Collectors;

public class NewNpeProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod ctMethod) {
        NPEService.instance.addMethod(ctMethod.getSignature(), ctMethod);
        List<CtVariableAccess> accessVars = ctMethod.getElements(new TypeFilter<>(CtVariableAccess.class));
        //Suppression des variables sans déclaration
        accessVars = accessVars.stream().filter(p->p.getVariable().getDeclaration()!=null).collect(Collectors.toList());

        accessVars.forEach(p->NPEService.instance.addExpression(ctMethod.getSignature(), p));
        /*accessVars.forEach(p->{
            VariableWorkFlow workFlow = NPEService.instance.getVariableWorkFlow(ctMethod.getSignature(),p);
            if(workFlow!=null){
                System.out.println(p);
                System.out.println(p.getPosition());
                System.out.println(workFlow.throwNPE(p));
            }

            System.out.println();
        } );*/

        System.out.println();
        System.out.println();
        System.out.println(ctMethod.getSignature());
        List<CtVariableRead> readVarS = ctMethod.getElements(new TypeFilter<>(CtVariableRead.class));

        readVarS = readVarS.stream().filter(p->p.getVariable().getDeclaration()!=null).collect(Collectors.toList());
        readVarS.forEach(p->{

            VariableWorkFlow workFlow = NPEService.instance.getVariableWorkFlow(ctMethod.getSignature(),p);
            CtExpression exp = SpoonService.getParentExpression(p);
            if(workFlow!=null){
                System.out.println(exp);
                System.out.println(exp.getPosition());
                System.out.println(workFlow.throwNPE(exp));
            }

            System.out.println();

        });

    }
}
