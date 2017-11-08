import Processor.*;
import junit.framework.*;
import spoon.Launcher;
import spoon.reflect.CtModel;
import utils.CyclomaticService;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class AppTest extends TestCase{

    public static void cyclomatic(){
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);

        //File inDir = new File("/home/bob/IdeaProjects/VVproject1/input/src");
        File inDir = new File("/home/cedric/IdeaProjects/VVProject1.bis/input/src");

        //URL url = new URL("http://github.com/OPCFoundation/UA-Java/src/main/java/org/opcfoundation/ua/utils/");
        //Scanner s = new Scanner(url.openStream());
        //File inDir = new File("http://github.com/OPCFoundation/UA-Java/src/main/java/org/opcfoundation/ua/utils/");


        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        CtModel root = launcher.getModel();

        launcher.addProcessor(new LoopProcessor());
        launcher.addProcessor(new IfProcessor());
        launcher.addProcessor(new SwitchProcessor());
        launcher.addProcessor(new ClassProcessor());
        launcher.addProcessor(new MethodProcessor());
        launcher.process();

        CyclomaticService.instance.printResult();

        // il faut maintenant un assert !!
        assertTrue( true );
    }

    public static void main( String[] arg){
        cyclomatic();
    }
}
