import Processor.*;
import spoon.Launcher;
import spoon.reflect.CtModel;
import services.fonctionnel.CyclomaticService;
import services.report.CyclomaticRapportService;

import java.io.File;

public class App {


    public static void main( String[] arg){
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);

        ///home/bob/IdeaProjects/VVproject1/input/src
        //File inDir = new File("/Documents/Source/IdeaProject/VVProject/input/src");
        File inDir = new File("/home/bob/Documents/Source/IdeaProject/VVProject/input/src");

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        CtModel root = launcher.getModel();

        //launcher.addProcessor(new CyclomaticProcessor());
        //launcher.addProcessor(new NpeMethodProcessor());
        launcher.addProcessor(new TestProcessor());
        //launcher.addProcessor(new NewNpeProcessor());
        launcher.process();

        /*CyclomaticService.instance.printResult();*/
        try{
            new CyclomaticRapportService().cyclomaticRapport(CyclomaticService.instance);

        }catch (Exception ex){

        }

        //NPEService.instance.toString();
        //print the transform code
        /*File outDir = new File("/home/bob/IdeaProjects/DummyProject/gen");
        launcher.setSourceOutputDirectory(outDir.getPath());
        launcher.prettyprint();*/


    }
}
