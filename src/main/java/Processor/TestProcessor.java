package Processor;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import services.fonctionnel.SpoonService;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtExpressionImpl;
import spoon.support.reflect.code.CtIfImpl;
import spoon.support.reflect.code.CtLoopImpl;

import javax.swing.plaf.SplitPaneUI;
import java.util.LinkedList;
import java.util.List;


public class TestProcessor extends AbstractProcessor<CtMethod> {

    @Override
    public void process(CtMethod method) {
        List<CtExpression> expS = method.filterChildren(new TypeFilter<>(CtExpression.class))
                .list(CtExpression.class);

        List<CtVariableAccess> varAccS = method.filterChildren(new TypeFilter<>(CtVariableAccess.class))
                .list(CtVariableAccess.class);

        System.out.println(varAccS.stream().allMatch(p->expS.contains(p)));
    }


    /*@Override
    public void process(CtVariableRead var) {
        CtExpression exp = SpoonService.getParentExpression(var);
        System.out.println(exp);
        CtBinaryOperator bin = exp.filterChildren(new TypeFilter<>(CtBinaryOperator.class)).first();
        if(bin != null){
            System.out.println(bin.getLeftHandOperand());
            System.out.println(bin.getKind());
            System.out.println(bin.getRightHandOperand());
        }
        System.out.println();
    }*/


    /*@Override
    public void process(CtVariableAccess var) {

        CtExpression exp = SpoonService.getParentExpression(var);
        System.out.println(exp);
        System.out.println();
        System.out.println();

    }*/


    /*@Override
    public void process(CtVariableWrite var) {
        CtExpression exp = SpoonService.getParentExpression(var);
        System.out.println(exp);

        System.out.println(exp.getElements(new TypeFilter(CtAssignment.class)));
        System.out.println();
    }*/

    /*@Override
    public void process(CtVariable test) {
        System.out.println(test);
        if(test.getParent(new TypeFilter<>(CtExpression.class))!= null){
            System.out.println( test.getParent(new TypeFilter<>(CtExpression.class)));
        }else{
            System.out.println("bad");
            System.out.println( test);
        }
        System.out.println();
    }*/

    /*@Override
    public void process(CtMethod test) {
        System.out.println(test.getSignature());
        test.getParameters().stream().forEach(p->{
            System.out.println(p);
            System.out.println(((CtVariable)p).getDefaultExpression());
        });
        System.out.println();
    }*/

    /*@Override
    public void process(CtVariableAccess test) {
        //System.out.println(test.getThenStatement().toString());
        System.out.println(test);
        CtIf ifParent = test.getParent(new TypeFilter<>(CtIf.class));
        CtLoop loopParent = test.getParent(new TypeFilter<>(CtLoop.class));

        if(ifParent != null || loopParent != null){
            if(ifParent != null && loopParent != null){
                System.out.println("if and loop imbriqué");
            }
            else{
                if(ifParent!=null){
                    System.out.println("only parent if");
                }
                else {
                    System.out.println("only parent loop");
                }
            }

        } else {
            System.out.println("no if or loop parent");
        }

        System.out.println();
    }*/


}
