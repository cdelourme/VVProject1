package newModel.element;

import newModel.VariableWorkFlow;
import newModel.variableAccess.ExpressionElement;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.LinkedList;

public class BlockElement extends AElement {

    private CtBlock body;
    private LinkedList<VariableWorkFlow> workFlows = new LinkedList<>();
    private LinkedList<AElement> childrenIElems = new LinkedList<>();

    public BlockElement(AElement parent, CtBlock body)
    {
        super(parent);
        id = body.getPosition();
        this.body = body;
        constructTree();
    }

    /**
     * Construit la référence des enfants complexes
     * Ne prend pas en compte les block anonyme et les lambda expression
     */
    private void constructTree(){
        //Recherche des Ifs
        body.filterChildren(new TypeFilter<>(CtIf.class)).forEach((CtIf p)->
            childrenIElems.add(new IfElement(this, p))
        );
        //Recherche des Loops
        body.filterChildren(new TypeFilter<>(CtLoop.class)).forEach((CtLoop p)->
            childrenIElems.add(new LoopElement(this, p))
        );
    }

    /**
     * Recherche si la déclaration de la variable appartient au block
     * Si oui créer le worflow et ajoute la variable
     * Sinon ne fait rien
     * @param exp
     */
    public void addExpression(CtExpression exp){
        if(includeDeclaration(exp)) {
            VariableWorkFlow workFlow = getWorkFlowOf(exp);
            this.addExpression(workFlow,exp);
        }
        else
        {
            this.childrenIElems.stream().filter(p->p.bodyContains(exp)).forEach(p->p.addExpression(exp));
        }
    }

    /**
     * Ajout la variable si elle lui appartient sinon propage au enfant ou ne fait rien.
     * @param workFlow
     * @param exp
     */
    public void addExpression(VariableWorkFlow workFlow, CtExpression exp){
        if(hasParent(exp)){
            workFlow.addExpression(new ExpressionElement(this,exp));
        }
        else
        {
            this.childrenIElems.stream().filter(p->p.bodyContains(exp)).forEach(p->p.addExpression(workFlow, exp));
        }

    }

    public boolean bodyContains(CtExpression exp){
        //TODO a tester
        return body.filterChildren(new TypeFilter<>(CtExpression.class)).list(CtExpression.class).contains(exp);
    }

    /**
     * Methode donnant le workflow d'une variable du block
     * s'il existe le retourne
     * sinon le crée et le retourne
     * @param exp
     * @return
     */
    public VariableWorkFlow getWorkFlowOf(CtExpression exp){
        CtVariableAccess varAcc = SpoonService.getVarAccess(exp);
        if(workFlows.stream().anyMatch(p->p.as(varAcc))){
            return workFlows.stream().filter(p->p.as(varAcc)).findFirst().get();
        }
        else{
            VariableWorkFlow workFlow = new VariableWorkFlow(this,varAcc.getVariable().getDeclaration());
            workFlows.add(workFlow);
            return workFlow;
        }
    }

    /**
     * Definit si la declaration de la variable est faite a la racine de ce block
     * @param exp
     * @return
     */
    public boolean includeDeclaration(CtExpression exp){
        return workFlows.stream().anyMatch(p->  p.as(SpoonService.getVarAccess(exp)))
                || (body.filterChildren(new TypeFilter<>(CtExpression.class)).
                        list(CtVariable.class).contains(SpoonService.getVarAccess(exp).getVariable().getDeclaration()));


                /*(SpoonService.getVarAccess(exp).getVariable().getDeclaration() != null ?
                        SpoonService.getVarAccess(exp).getVariable().getDeclaration()
                            .getParent().getPosition() == id
                        : false);*/
    }

    public void createWorkFlow(CtVariable var){
        this.workFlows.add(new VariableWorkFlow(this, var));
    }


    public Boolean throwNPE() {
        return null;
    }

}
