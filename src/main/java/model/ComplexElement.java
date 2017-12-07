package model;

import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.LinkedList;
import java.util.List;

public class ComplexElement extends Element {

    private CtIf complexBlock;
    private LinkedList<Expression> ifExps = new LinkedList<>();
    private LinkedList<Expression> elseEpxs = new LinkedList<>();
    private Expression condExp;
    private boolean isWrite;

    private SourcePosition id;
    public SourcePosition getId() { return id; }

    public ComplexElement(CtIf bloc, CtExpression elem, boolean isWrite){
        this.id = bloc.getPosition();
        this.complexBlock = bloc;
        this.isWrite = isWrite;
        this.sortExp(elem,isWrite);
    }

    private void sortExp(CtExpression elem, boolean isWrite){
        System.out.println("Start");
        System.out.println(this.complexBlock);
        System.out.println("test : "+elem);
        System.out.println(this.complexBlock.getCondition());
        System.out.println(this.complexBlock.getThenStatement()
                .filterChildren(new TypeFilter<>(CtVariableAccess.class)).list());
        /*System.out.println(this.complexBlock.getElseStatement()
                .filterChildren(new TypeFilter<>(CtExpression.class)).list());*/
        System.out.println();
        if(elem == this.complexBlock.getCondition()){
            this.condExp = new Expression(elem, isWrite);
        }
        if(this.complexBlock.getThenStatement()
                .filterChildren(new TypeFilter<>(CtVariableAccess.class)).list(CtVariableAccess.class).stream()
                .anyMatch(p->p.getParent(CtExpression.class) == elem)){
            this.ifExps.add(new Expression(elem, isWrite));
        }
        /*if(this.complexBlock.getElseStatement()
                .filterChildren(new TypeFilter<>(CtExpression.class)).list()
                .contains(elem)){
            this.elseEpxs.add(new Expression(elem, isWrite));
        }*/
    }

    public void addExpression(CtExpression elem, boolean isWrite){
        this.sortExp(elem,isWrite);
        this.isWrite = isWrite || isWrite;
    }

    @Override
    public boolean contains(CtExpression exp) {
        return ((this.condExp != null ? this.condExp.getExp() == exp : false)
                ||
                this.ifExps.stream().anyMatch(p->p.getExp()==exp)
                ||
                this.elseEpxs.stream().anyMatch(p->p.getExp()==exp)
                );
    }

    @Override
    public CtElement getElem() {
        return this.complexBlock;
    }

    @Override
    public boolean isWrite() {
        return isWrite;
    }

    @Override
    public boolean isComplex() {
        return true;
    }

    @Override
    public Boolean throwNPE(){
        //return this.condExp.throwNPE();
        return false;
    }

}
