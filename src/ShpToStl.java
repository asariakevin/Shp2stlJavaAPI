import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ShpToStl {
    private String codeToRun = "var fs = require('fs');\n" +
            "var shp2stl = require('shp2stl');\n";

    private String inputFilePath;
    private String outputFilePath;
    String options = "{\n";

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = "\"" + inputFilePath + "\"";
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = "\"" + outputFilePath + "\"";
    }

    public void setWidth(double width){
        options = options + "width: " + width +",\n";
    }
    public void setHeight(double height){
        options = options + "height: " + height +",\n";
    }
    public void setExtraBaseHeight(double extraBaseHeight){
        options = options + "extraBaseHeight: " + extraBaseHeight +",\n";
    }
    public void setExtrudeBy(String extrudeBy){
        options = options + "extrudeBy: \"" + extrudeBy +"\",\n";
    }
    public void setSimplification(double simplification){
        options = options + "simplification: " + simplification +",\n";
    }
    public void setBinary(boolean binary){
        options = options + "binary: " + binary +",\n";
    }
    public void setCutoutHoles(boolean cutoutHoles){
        options = options + "cutoutHoles: " + cutoutHoles +",\n";
    }
    public void setVerbose(boolean verbose){
        options = options + "verbose: " + verbose +",\n";
    }
    public void setExtrusionMode(String extrusionMode){
        options = options + "extrusionMode: '" + extrusionMode +"',\n";
    }

    void printCode(){

        // close options
        options = options + "}";

        codeToRun = codeToRun + "shp2stl.shp2stl(" + inputFilePath
                +","
                +options
                +",\n"
                +"function(err,stl){ fs.writeFileSync("
                + outputFilePath + ", stl)});";
        System.out.println(codeToRun);
    }
    void convertShp2Stl() throws IOException {
        final NodeJS nodeJS = NodeJS.createNodeJS();

        // close options
        options = options + "}";

        codeToRun = codeToRun + "shp2stl.shp2stl(" + inputFilePath
                +","
                +options
                +",\n"
                +"function(err,stl){ fs.writeFileSync("
                + outputFilePath + ", stl)});";

        File nodeScript = File.createTempFile("example", ".js.tmp");
        PrintWriter writer = new PrintWriter(nodeScript, "UTF-8");

        try {
            writer.print(codeToRun);
        } finally {
            writer.close();
        }

        nodeJS.exec(nodeScript);

        while(nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }
        nodeJS.release();
    }
}
