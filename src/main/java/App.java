import Processor.ClassProcessor;
import Processor.VariableAccessProcessor;
import Processor.VariableReadProcessor;
import spoon.Launcher;
import spoon.reflect.CtModel;

import java.io.File;

public class App {


    public static void main( String[] arg){
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);

        File inDir = new File("/home/bob/IdeaProjects/VVproject1/input/src");

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        CtModel root = launcher.getModel();

        //launcher.addProcessor(new VariableReadProcessor());
        launcher.addProcessor((new VariableAccessProcessor()));
        launcher.process();

        //print the transform code
        /*File outDir = new File("/home/bob/IdeaProjects/DummyProject/gen");
        launcher.setSourceOutputDirectory(outDir.getPath());
        launcher.prettyprint();*/


    }
}
