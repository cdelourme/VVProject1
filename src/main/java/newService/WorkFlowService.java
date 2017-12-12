package newService;

public class WorkFlowService {
    public static WorkFlowService instance = new WorkFlowService();

    /*public void addToWorkFlow(VariableWorkFlow workFlow, CtExpression exp) {
        CtIf ifParent = exp.getParent(new TypeFilter<>(CtIf.class));
        if(ifParent != null){
            if(workFlow.stream().anyMatch(p->p.getId() == ifParent.getPosition())) {
                ((IfElement) variableAccs.stream().filter(p -> p.getId() == ifParent.getPosition()).findFirst().get()).addExp(exp);
            }
            else {
                variableAccs.add(new IfElement(ifParent, exp));
            }
        }
        else {
            CtLoop loopParent =  exp.getParent(new TypeFilter<>(CtLoop.class));
            if(loopParent != null){
                if(variableAccs.stream().anyMatch(p->p.getId() == loopParent.getPosition())){
                    ((LoopElement) variableAccs.stream().filter(p -> p.getId() == loopParent.getPosition()).findFirst().get()).addExp(exp);
                }
                else{
                    variableAccs.add(new LoopElement(loopParent, exp));
                }
            }
            else {
                variableAccs.add(new ExpressionElement(exp));
            }
        }
    }*/
}
