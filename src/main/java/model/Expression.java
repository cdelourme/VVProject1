package model;

import spoon.reflect.code.CtExpression;

public class Expression {

    private CtExpression exp;
    public CtExpression getExp() { return exp; }

    private boolean isWrite;
    public boolean isWrite() {
        return isWrite;
    }

    public Expression(CtExpression exp, boolean isWrite){
        this.exp = exp;
        this.isWrite = isWrite;
    }

    public boolean throwNPE(){
        /*List<CtAssignment> assigs = this.exp.getElements(new TypeFilter(CtAssignment.class));
        if(assigs.size()>=1){
            //System.out.println("\t" + assigs.get(0).getAssignment());
            return assigs.get(0).getAssignment() == null;
        }
        else {
            System.out.println("\tno Assignment");
        }*/
        return false;
    }
}
