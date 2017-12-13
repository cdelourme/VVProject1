package newModel.element;

import model.Expression;
import newModel.VariableWorkFlow;
import newModel.variableAccess.ExpressionElement;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.*;
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

    public void addExpression(VariableWorkFlow workFlow, CtVariableAccess varAcc) {
        CtExpression exp = SpoonService.getParentExpression(varAcc);

        if (refCond != null && refCond.equals(exp)) {
            this.condition = new ExpressionElement(this,exp);
            workFlow.addExpression(condition);
        } else {
            if (body.bodyContains(exp)) {
                this.body.addExpression(varAcc);
            } else {
                System.out.println("an error occured");
            }
        }
    }

    public void addExpression(CtVariableAccess varAcc) {
        CtExpression exp = SpoonService.getParentExpression(varAcc);

        if (body.bodyContains(exp)) {
            this.body.addExpression(varAcc);
        } else {
            System.out.println("an error occured");
        }
    }

    public boolean bodyContains(CtExpression exp){
        return refCond == exp || body.bodyContains(exp);
    }


    public VariableWorkFlow getWorkFlow(CtVariableAccess varAcc){
        return body.getWorkFlow(varAcc);
    }

}
