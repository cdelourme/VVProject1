package services.report;

import services.fonctionnel.CyclomaticService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CyclomaticRapportService {


    public void cyclomaticRapport(CyclomaticService cycloServ) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("CyclomaticReport.txt", "UTF-8");
        writer.println("Cyclomatic du projet : "+cycloServ.getProjectCyclomatic());
        writer.println("Cyclomatic des classes : ");
        cycloServ.getClassCyclomatics().forEach((key,value)->{
            writer.println("    "+key+" : "+value);
        });
        writer.println("Cyclomatic des methodes : ");
        cycloServ.getMethodCyclomatics().forEach((key,value)->{
            writer.println("    "+key+" : "+value);
        });
        writer.close();
    }
}
