import Processor.*;
import spoon.Launcher;
import spoon.reflect.CtModel;
import utils.CyclomaticService;
import utils.RapportService;
import utils.VariableService;

import java.io.File;

public class App {


    public static void main( String[] arg){
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);

        ///home/bob/IdeaProjects/VVproject1/input/src
        //File inDir = new File("/home/bob/IdeaProjects/VVproject1/input/src");
        File inDir = new File("/home/cedric/IdeaProjects/VVProject1.bis/input/src");

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        CtModel root = launcher.getModel();

//        launcher.addProcessor(new MethodProcessor());
        launcher.addProcessor(new NpeMethodProcessor());
        launcher.process();

        CyclomaticService.instance.printResult();
        try{
            new RapportService().cyclomaticRapport(CyclomaticService.instance);

        }catch (Exception ex){

        }

        //VariableService.instance.toString();
        //print the transform code
        /*File outDir = new File("/home/bob/IdeaProjects/DummyProject/gen");
        launcher.setSourceOutputDirectory(outDir.getPath());
        launcher.prettyprint();*/


    }
}
