import cn.byk.pandora.bsdiff.BsDifferUtil;
 

public class BsDiffDemo {
 
    private static String oldFile = "old.apk";
    private static String newFile = "new.apk";
    private static String patchFile = "patch.mjpatch";
 
    public static void main(String[] args)
    {
        int result = BsDifferUtil.getInstance().run(oldFile, newFile, patchFile);
        System.out.println("result:" + result);//0:success 1:wrong
    }
 
}
