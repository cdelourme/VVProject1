package newModel.element;

import newModel.VariableWorkFlow;
import newModel.variableAccess.ExpressionElement;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.cu.SourcePosition;

public class IfElement extends AElement {

    private SourcePosition id;
    private ExpressionElement condition;
    private BlockElement ifBlock;
    private BlockElement elseBlock;
    private CtExpression refCondition;

    public IfElement(AElement parent, CtIf block) {
        super(parent);
        id = block.getPosition();
        refCondition = block.getCondition();
        CtBlock ifStatement = block.getThenStatement();
        if(ifStatement != null){
            ifBlock = new BlockElement(this,ifStatement);
        }
        CtBlock elseStatement = block.getElseStatement();
        if(elseStatement != null){
            elseBlock = new BlockElement(this, elseStatement);
        }
    }


    @Override
    public SourcePosition getId() {
        return id;
    }

    public void addExpression(VariableWorkFlow workFlow, CtVariableAccess varAcc) {
        CtExpression exp = SpoonService.getParentExpression(varAcc);

        if(refCondition.equals(exp)){
            condition = new ExpressionElement(this, exp);
            workFlow.addExpression(condition);
        }
        else{
            if(ifBlock.bodyContains(exp)){
                ifBlock.addExpression(workFlow, varAcc);
            }
            else {
                if(elseBlock.bodyContains(exp)){
                    elseBlock.addExpression(varAcc);
                }
                else{
                    System.out.println("an error occurre : IfElement - addExp");
                }
            }
        }
    }

    public void addExpression(CtVariableAccess varAcc) {
        CtExpression exp = SpoonService.getParentExpression(varAcc);

        if(ifBlock.bodyContains(exp)){
            ifBlock.addExpression(varAcc);
        }
        else {
            if(elseBlock != null){
                if(elseBlock.bodyContains(exp)){
                    elseBlock.addExpression(varAcc);
                }
                else{
                    System.out.println("an error occurre : IfElement - addExp");
                }
            }else{
                System.out.println("an error occurre : IfElement - addExp");
            }

        }
    }

    public boolean bodyContains(CtExpression exp){
        return refCondition == exp ||
                (ifBlock != null ? ifBlock.bodyContains(exp) : false) ||
                (elseBlock != null ? elseBlock.bodyContains(exp) : false);
    }

    public VariableWorkFlow getWorkFlow(CtVariableAccess varAcc){
        if(ifBlock.bodyContains(SpoonService.getParentExpression(varAcc))){
            return ifBlock.getWorkFlow(varAcc);
        }else {
            return elseBlock.getWorkFlow(varAcc);
        }
    }
}
