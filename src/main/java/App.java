import Processor.ClassProcessor;
import spoon.Launcher;
import spoon.reflect.CtModel;

import java.io.File;

public class App {


    public static void main( String[] arg){
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);

        File inDir = new File("/home/cedric/IdeaProjects/VVProject1.bis/input/src");

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        CtModel root = launcher.getModel();

        launcher.addProcessor(new ClassProcessor());
        launcher.process();

        //print the transform code
        File outDir = new File("/home/cedric/IdeaProjects/VVProject1.bis/input/gen");
        launcher.setSourceOutputDirectory(outDir.getPath());
        launcher.prettyprint();


    }
}
