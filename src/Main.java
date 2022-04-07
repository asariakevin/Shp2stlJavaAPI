public class Main {
    public static void main(String[] args) {
        ShpToStl shpToStl =new ShpToStl();
        shpToStl.setInputFilePath("san.shp");
        shpToStl.setOutputFilePath("san.stl");
        shpToStl.setWidth(100);
        shpToStl.setHeight(10);
        shpToStl.setExtraBaseHeight(0);
        shpToStl.setExtrudeBy("Pop_psmi");
        shpToStl.setSimplification(0);
        shpToStl.setBinary(true);
        shpToStl.setCutoutHoles(false);
        shpToStl.setVerbose(true);
        shpToStl.setExtrusionMode("straight");
        shpToStl.printCode();
    }
}
