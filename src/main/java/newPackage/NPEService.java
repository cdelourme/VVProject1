package newPackage;

import newModel.element.BlockElement;
import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtMethod;

import java.util.HashMap;

public class NPEService {
    public static NPEService instance = new NPEService();

    public HashMap<String,BlockElement> methods = new HashMap<>();

    public BlockElement GetBlock(String name) {
        return methods.getOrDefault(name,null);
    }

    /*public void addMethod(String name, CtMethod method) {
        this.methods.put(name, construct(method));
    }*/

    /*private BlockElement construct(CtMethod methodsBody) {
        BlockElement returnBlock = new BlockElement(methodsBody.getPosition());

    }*/
}
