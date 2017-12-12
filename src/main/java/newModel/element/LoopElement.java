package newModel.element;

import model.Expression;
import newModel.VariableWorkFlow;
import newModel.variableAccess.ExpressionElement;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtWhile;
import spoon.reflect.cu.SourcePosition;

public class LoopElement extends AElement {

    private SourcePosition id;
    private ExpressionElement condition;
    private BlockElement body;
    private CtExpression refCond = null;

    public LoopElement(AElement parent, CtLoop loop) {
        super(parent);
        this.body = new BlockElement(this,(CtBlock) loop.getBody());
        if (loop instanceof CtWhile) {
            refCond = ((CtWhile) loop).getLoopingExpression();
        }
    }

    public void addExpression(VariableWorkFlow workFlow, CtExpression exp) {
        if (refCond != null && refCond.equals(exp)) {
            this.condition = new ExpressionElement(this,exp);
            workFlow.addExpression(condition);
        } else {
            if (body.bodyContains(exp)) {
                this.body.addExpression(exp);
            } else {
                System.out.println("an error occured");
            }
        }
    }

    public void addExpression(CtExpression exp) {
        if (body.bodyContains(exp)) {
            this.body.addExpression(exp);
        } else {
            System.out.println("an error occured");
        }
    }

    public boolean bodyContains(CtExpression exp){
        return refCond == exp || body.bodyContains(exp);
    }


}
