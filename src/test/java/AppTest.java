import Processor.*;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import services.report.CyclomaticRapportService;
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

        launcher.addInputResource(inDir.getPath());
        launcher.buildModel();
    }


    @Test
    public void cyclomaticProject() {

        launcher.addProcessor(new CyclomaticProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getProjectCyclomatic().intValue() >= 1);
    }

    @Test
    public void cyclomaticClass() {

        launcher.addProcessor(new CyclomaticProcessor());
        launcher.process();
        assertTrue(CyclomaticService.instance.getClassCyclomatics() != null);
    }

    @Test
    public void fileReportExistsCyclomaticTxt() throws Exception{

        new CyclomaticRapportService().cyclomaticRapport(CyclomaticService.instance);
        File f = new File("CyclomaticReport.txt");
        assertTrue(f.exists());
    }

    @Test
    public void fileReportExistsCyclomaticHtml() throws Exception{

        new CyclomaticRapportService().cyclomaticRapportHTML(CyclomaticService.instance);
        File f = new File("CyclomaticReport.html");
        assertTrue(f.exists());
    }

}
