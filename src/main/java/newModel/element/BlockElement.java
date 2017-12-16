package newModel.element;

import newModel.AGeneralElement;
import newModel.VariableWorkFlow;
import newModel.variableAccess.AVariableAccess;
import newModel.variableAccess.ExpressionElement;
import newService.ModelService;
import services.fonctionnel.SpoonService;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class BlockElement extends AElement {

    private CtBlock body;
    private LinkedList<VariableWorkFlow> workFlows = new LinkedList<>();
    private LinkedList<AElement> childrenIElems = new LinkedList<>();

    public BlockElement(AElement parent, CtBlock body) {
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

    public boolean hasParent(CtExpression exp){
        CtStatement parent = exp.getParent(new TypeFilter<>(CtStatement.class));
        if(parent instanceof CtVariable){
            parent = parent.getParent(new TypeFilter<>(CtStatement.class));
        }
        if(exp.getParent() instanceof CtReturn) {
            return parent.getParent().getPosition() == id;
        }
        else {
            return parent.getPosition() == id;
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
            //System.out.println(exp);
            //System.out.println(varAcc instanceof CtVariableWrite);
            workFlow.addExpression(new ExpressionElement(this,exp, varAcc instanceof CtVariableWrite));
        }
        else
        {
            this.childrenIElems.stream().filter(p->p.bodyContains(exp)).forEach(p->p.addExpression(workFlow, varAcc));
        }

    }

    public boolean bodyContains(CtElement exp){
        return body.filterChildren(new TypeFilter<>(CtElement.class)).list(CtElement.class).contains(exp);
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

    public Boolean throwNPE(CtVariable var){
        LinkedList<CtVariableWrite> writeVars = new LinkedList<CtVariableWrite>(body
                .filterChildren(new TypeFilter<>(CtVariableWrite.class)).list(CtVariableWrite.class)
                .stream().filter((CtVariableWrite p)-> p.getVariable().getDeclaration().getPosition() == var.getPosition())
                .collect(Collectors.toList()));
        //conversion en list d'expression
        LinkedList<CtExpression> exps = new LinkedList<CtExpression>(writeVars.stream()
                .map(p->SpoonService.getParentExpression(p)).collect(Collectors.toList()));
        //parcour en sens inverse
        ListIterator<CtExpression> iterator = exps.listIterator(exps.size());
        //Parcour de la list a l'envers jusqu'a avoir une value stricte (true/false)
        //Si liste fini retourne une valeur null;
        while(iterator.hasPrevious()) {
            CtExpression exp = iterator.previous();
            CtAssignment assig = (CtAssignment)exp.getElements(new TypeFilter(CtAssignment.class)).get(0);
            if(assig != null){
                //return si la valeur d'assignement est null.
                return assig.getAssignment() == null;
            }
        }
        return null;
    }


    public Boolean throwNPE(CtExpression exp, VariableWorkFlow workFlow) {
        CtVariable var = workFlow.getDeclaration().getVariable();
        ExpressionElement expE = workFlow.getExp(exp);
        if(expE == null){
            return null;
        }
        LinkedList<AVariableAccess> previousExpEs = workFlow.getPreviousWriteExpS(expE);

        if(previousExpEs.size()>0){
            if(isDirectParentOf(expE)) {

                previousExpEs = new LinkedList<>(previousExpEs.stream().filter(p->bodyContains(p.getValue()))
                        .collect(Collectors.toList()));
                if(previousExpEs.size()==0){
                    return null;
                }

                LinkedList<AGeneralElement> previousElems = ModelService.instance
                        .converToAGenE(previousExpEs, this);

                //parcour des expression precédante
                // par le biais des enfants
                ListIterator<AGeneralElement> iterator = previousElems.listIterator(previousElems.size());

                while (iterator.hasPrevious()) {
                    AGeneralElement previousElem = iterator.previous();
                    Boolean returnOfThrow = previousElem.throwNPE(var);
                    if(returnOfThrow != null) {
                        return returnOfThrow;
                    }
                }
                return null;
            }
            else{
                AElement lastchild = getLastChildOf(expE);
                //test du child
                if(lastchild == null){
                    return null;
                }
                Boolean throwOfLastChild = lastchild.throwNPE(exp, workFlow);
                if(throwOfLastChild != null){
                    return throwOfLastChild;
                }
                else {
                    previousExpEs = new LinkedList<AVariableAccess>(previousExpEs.stream().
                            filter(p->bodyContains(p.getValue())).collect(Collectors.toList()));

                    if(previousExpEs.size()>0){

                        LinkedList<AGeneralElement> previousElems = ModelService.instance
                                .converToAGenE(previousExpEs, this);

                        //parcour des expression precédante
                        // par le biais des enfants
                        ListIterator<AGeneralElement> iterator = previousElems.listIterator(previousElems.size());

                        while (iterator.hasPrevious()) {
                            AGeneralElement previousElem = iterator.previous();
                            Boolean returnOfThrow = previousElem.throwNPE(var);
                            if(returnOfThrow != null) {
                                return returnOfThrow;
                            }
                        }
                        return null;
                    }
                    else {
                        return null;
                    }
                }
            }
        }
        else {
            return null;
        }

    }

}
