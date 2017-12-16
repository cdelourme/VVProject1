package newModel.element;

import newModel.VariableWorkFlow;
import newModel.variableAccess.ExpressionElement;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;

public class LoopElement extends AElement {

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
            this.condition = new ExpressionElement(this, exp, varAcc instanceof CtVariableWrite);
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

    public boolean bodyContains(CtElement exp){
        return ((refCond != null)? false : refCond == exp) || body.bodyContains(exp);
    }


    public VariableWorkFlow getWorkFlow(CtVariableAccess varAcc){
        return body.getWorkFlow(varAcc);
    }


    public Boolean throwNPE(CtVariable var){
        // - si affectation(last) a nul -> possible NPE.
        // - si affectation(last) (non nul) -> Recherche de l'affectation précédente.
        if(body.throwNPE(var)){
            return true;
        }
        else{
            return null;
        }
    }

    public Boolean throwNPE(CtExpression exp, VariableWorkFlow workFlow){
        //recherche dans le boby
            //true -> true
            //false -> false
            //null
                //test de la condition -> var le non null
                    //non -> null
                    //oui -> false
        if(condition!=null){
            Boolean bodyThrow = body.throwNPE(exp,workFlow);
            if(bodyThrow == null){
                return (condition.testToNull(workFlow.getDeclaration().getVariable()))
                        ? false : null;
            }
            else {
                return bodyThrow;
            }
        }
        else {
            return body.throwNPE(exp,workFlow);
        }
    }
}
