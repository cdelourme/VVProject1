package Processor;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtIfImpl;
import spoon.support.reflect.code.CtLoopImpl;


public class TestProcessor extends AbstractProcessor<CtVariableAccess> {

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

    @Override
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
    }
}
