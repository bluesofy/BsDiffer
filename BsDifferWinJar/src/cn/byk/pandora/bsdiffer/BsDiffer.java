package cn.byk.pandora.bsdiffer;

public class BsDiffer {

    private static volatile BsDiffer sInstance;

    private BsDiffer() {
    }

    public static BsDiffer getInstance() {
        if (sInstance == null) {
            synchronized (BsDiffer.class) {
                if (sInstance == null) {
                    sInstance = new BsDiffer();
                }
            }
        }
        return sInstance;
    }

    static {
        System.loadLibrary("BsDiffer");
    }

    public native int run(String oldFile, String newFile, String patchFile);
}
