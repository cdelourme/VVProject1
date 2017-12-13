import Processor.*;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import spoon.Launcher;
import services.fonctionnel.CyclomaticService;

import java.io.File;

import static org.junit.Assert.assertNull;
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

        inDir = new File("./input/src");

        //URL url = new URL("http://github.com/OPCFoundation/UA-Java/src/main/java/org/opcfoundation/ua/utils/");
        //Scanner s = new Scanner(url.openStream());
        //File inDir = new File("http://github.com/OPCFoundation/UA-Java/src/main/java/org/opcfoundation/ua/utils/");

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
        //CtModel root = launcher.getModel();
    }


    @Test
    public void cyclomaticProject() {

        launcher.addProcessor(new CyclomaticProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getProjectCyclomatic().intValue() >= 8);
    }

    //@Ignore
    @Test
    public void testForLoop() {
        Integer[] myNumbers = null;
        for (Integer myNumber : myNumbers) {
            System.out.println(myNumber);
        }
    }

    @Ignore
    @Test
    public void cyclomaticClass() {

        //launcher.addProcessor(new ClassProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getClassCyclomatics() != null);
    }

    @Test
    public void fileReportExists(){

        CyclomaticService.instance.printResult();
        File f = new File("CyclomaticReport.txt");
        assertTrue(f.exists());
    }


    @After
    public void end(){

        CyclomaticService.instance.printResult();
        File f = new File("CyclomaticReport.txt");
        assertTrue(f.exists());
    }
}
