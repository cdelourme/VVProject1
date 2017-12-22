package services.report;

import services.fonctionnel.CyclomaticService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.TreeMap;

public class CyclomaticRapportService {


    public void cyclomaticRapport(CyclomaticService cycloServ) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("CyclomaticReport.txt", "UTF-8");

        writer.println("Cyclomatic du projet : "+cycloServ.getProjectCyclomatic());
        writer.println("Cyclomatic des classes : ");
        TreeMap tmc = new TreeMap(cycloServ.getClassCyclomatics());
        tmc.forEach((key,value)->{
            writer.println("\t"+key+" : "+value);
        });

        writer.println("Cyclomatic des methodes : ");
        tmc = new TreeMap(cycloServ.getMethodCyclomatics());
        tmc.forEach((key,value)->{
            writer.println("\t"+key+" : "+value);
        });

        writer.close();


    }

    public void cyclomaticRapportHTML(CyclomaticService cycloServ) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("CyclomaticReport.html", "UTF-8");

        writer.println("<html><head><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" crossorigin=\"anonymous\">"+
                "</head><body>"+
                "<div class='container'>"+
                "   <div class='row' style='min-height: 100px'>"+
                "       <div class='col-md-6' style='padding-top: 30px;text-align: center;'>"+
                "       <h2>Cyclomatic report</h2>"+
                "       </div>"+
                "   <div class='col-md-3' style='text-align: right;'>"+
                "       <img src='https://istic.univ-rennes1.fr/sites/istic.univ-rennes1.fr/files/logoisticfr_0.png' style='width: 216px;'>"+
                "   </div>"+
                "</div>");

        writer.println("Cyclomatic du projet : "+cycloServ.getProjectCyclomatic() + "<br><br>");
        writer.println("Cyclomatic des classes : " + "<br>");
        TreeMap tmc = new TreeMap(cycloServ.getClassCyclomatics());
        tmc.forEach((key,value)->{
            writer.println("\t"+key+" : "+value + " <br>");
        });
        writer.println("<br>");
        writer.println("Cyclomatic des methodes : ");
        tmc = new TreeMap(cycloServ.getMethodCyclomatics());
        tmc.forEach((key,value)->{
            writer.println("\t"+key+" : "+value + " <br>");
        });

        writer.println("</body></html>");
        writer.close();


    }
}
