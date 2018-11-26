package cn.byk.pandora.bsdiff;
 
public class BsDifferUtil {
 
    private static BsDifferUtil instance = new BsDifferUtil();
 
    public BsDifferUtil(){}
 
    public static BsDifferUtil getInstance() {
        return instance;
    }
 
    static {
        System.loadLibrary("BsDiffer");
    }
 
 
    public native int run(String oldFile, String newFile, String patchFile);
}
