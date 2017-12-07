package Processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;


public class TestProcessor extends AbstractProcessor<CtVariable> {

    @Override
    public void process(CtVariable test) {
        System.out.println(test);
        if(test.getParent(new TypeFilter<>(CtExpression.class))!= null){
            System.out.println( test.getParent(new TypeFilter<>(CtExpression.class)));
        }else{
            System.out.println("bad");
            System.out.println( test);
        }
        System.out.println();
    }
}
