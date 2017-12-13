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
     * @param varAcc
     */
    public void addExpression(CtVariableAccess varAcc){
        if(includeDeclaration(varAcc)) {
            VariableWorkFlow workFlow = getWorkFlowOf(varAcc);
            this.addExpression(workFlow,varAcc);
        }
        else
        {
            this.childrenIElems.stream().filter(p->p.bodyContains(SpoonService.getParentExpression(varAcc))).forEach(p->p.addExpression(varAcc));
        }
    }

    /**
     * Ajout la variable si elle lui appartient sinon propage au enfant ou ne fait rien.
     * @param workFlow
     * @param varAcc
     */
    public void addExpression(VariableWorkFlow workFlow, CtVariableAccess varAcc){
        CtExpression exp = SpoonService.getParentExpression(varAcc);
        if(hasParent(exp)){
            workFlow.addExpression(new ExpressionElement(this,exp));
        }
        else
        {
            this.childrenIElems.stream().filter(p->p.bodyContains(exp)).forEach(p->p.addExpression(workFlow, varAcc));
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
     * @param varAcc
     * @return
     */
    private VariableWorkFlow getWorkFlowOf(CtVariableAccess varAcc){
        if(workFlows.stream().anyMatch(p->p.as(varAcc))){
            return workFlows.stream().filter(p->p.as(varAcc)).findFirst().get();
        }
        else{
            VariableWorkFlow workFlow = new VariableWorkFlow(this,varAcc.getVariable().getDeclaration());
            workFlows.add(workFlow);
            return workFlow;
        }
    }

    public VariableWorkFlow getWorkFlow(CtVariableAccess varAcc){
        if(workFlows.stream().anyMatch(p->p.as(varAcc))){
            return workFlows.stream().filter(p->p.as(varAcc)).findFirst().get();
        }
        else {
            return this.childrenIElems.stream()
                    .filter(p->p.bodyContains(SpoonService.getParentExpression(varAcc)))
                    .findFirst().get().getWorkFlow(varAcc);
        }
    }

    /**
     * Definit si la declaration de la variable est faite a la racine de ce block
     * @param varAcc
     * @return
     */
    public boolean includeDeclaration(CtVariableAccess varAcc){
        return workFlows.stream().anyMatch(p->  p.as(varAcc))
                || varAcc.getVariable().getDeclaration().getParent().getPosition() == id;
    }

    public void createWorkFlow(CtVariable var){
        this.workFlows.add(new VariableWorkFlow(this, var));
    }
}
