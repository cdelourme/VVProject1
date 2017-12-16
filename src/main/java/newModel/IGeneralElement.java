package newModel;

import spoon.reflect.declaration.CtVariable;

public interface IGeneralElement {
    /**
    * Definie si le block return ou non un NPE pour une variable
    * True -> NPE possible
    * False -> NPE impossible
    * Null -> voir l'assignation précedante
    * @param var
    * @return
    */
    Boolean throwNPE(CtVariable var);
}
