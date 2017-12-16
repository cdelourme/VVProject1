package newModel.variableAccess;

import newModel.element.AElement;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;

public class ExpressionElement extends AVariableAccess {

    private CtExpression expression;
    public CtExpression getExp() {
        return expression;
    }
    public boolean isWrite;

    public ExpressionElement(AElement parent, CtExpression exp, boolean isWrite) {
        super(parent);
        this.expression = exp;
        this.isWrite = isWrite;
        id = this.expression.getPosition();
    }

    public CtElement getValue(){
        return expression;
    }

    public boolean contains(CtExpression exp) {
        return this.expression == exp;
    }

    /**
     * definie si la variable est assigner a une valeur potentiellement nullable
     * null voir l'assign sup
     * true -> possible NPE
     * false -> NPE impossible
     * @return
     */
    public Boolean throwNPE(CtVariable var) {
        CtAssignment assig = (CtAssignment)expression.getElements(new TypeFilter(CtAssignment.class)).get(0);
        if(assig != null){
            //return si la valeur d'assignement est null.
            return assig.getAssignment() == null;
        }
        else {
            //pas d'assignement donc non déterminé.
            return null;
        }
    }

    /**
     * Definie si l'expression contien un test de la variable a null :
     * Null -> n'en contient pas
     * false -> Test si test de non null
     * true -> Test si null
     * @param var
     * @return
     */
    public Boolean testToNull(CtVariable var) {
        List<CtBinaryOperator> binOps = expression.filterChildren(new TypeFilter<>(CtBinaryOperator.class)).list();

        for (CtBinaryOperator p : binOps) {
            if(p.getLeftHandOperand().equals(var) || p.getRightHandOperand().equals(var)){
                if(p.getRightHandOperand().equals(null) || p.getLeftHandOperand().equals(null)){
                    if(p.getKind().equals(BinaryOperatorKind.NE)){
                        return false;
                    }else {
                        if(p.getKind().equals(BinaryOperatorKind.EQ)){
                            return true;
                        }
                    }
                }
            }
        }
        return null;
    }

}
