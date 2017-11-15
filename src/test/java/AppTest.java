import Processor.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import spoon.Launcher;
import spoon.reflect.CtModel;
import utils.CyclomaticService;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class AppTest {

    File inDir;
    Launcher launcher;

    @Before
    public void initLauncher(){

        CyclomaticService.instance.resetStats();

        launcher = new Launcher();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);
        inDir = new File("/home/cedric/IdeaProjects/VVProject1.bis/input/src");
        //inDir = new File("/home/bob/IdeaProjects/VVproject1/input/src");

        //URL url = new URL("http://github.com/OPCFoundation/UA-Java/src/main/java/org/opcfoundation/ua/utils/");
        //Scanner s = new Scanner(url.openStream());
        //File inDir = new File("http://github.com/OPCFoundation/UA-Java/src/main/java/org/opcfoundation/ua/utils/");

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        //CtModel root = launcher.getModel();
    }


    @Test
    public void cyclomaticProject() {

        launcher.addProcessor(new MethodProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getProjectCyclomatic().intValue() == 8);
    }

    @Ignore
    @Test
    public void cyclomaticMethod() {

        launcher.addProcessor(new MethodProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getMethodCyclomatics() != null);
    }

    @Ignore
    @Test
    public void cyclomaticClass() {

        //launcher.addProcessor(new ClassProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getClassCyclomatics() != null);
    }

    @After
    public void end(){
        CyclomaticService.instance.printResult();
    }
}
