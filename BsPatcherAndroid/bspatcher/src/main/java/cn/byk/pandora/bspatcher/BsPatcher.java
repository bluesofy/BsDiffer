package cn.byk.pandora.bspatcher;

/**
 * @author Created by Byk on 2018/11/21.
 */
public class BsPatcher {

    private static final int RESULT_CODE_SUCCESS = 0;
    private static volatile BsPatcher sInstance;

    private BsPatcher() {}

    public static BsPatcher getInstance() {
        if (sInstance == null) {
            synchronized (BsPatcher.class) {
                if (sInstance == null) {
                    sInstance = new BsPatcher();
                }
            }
        }
        return sInstance;
    }

    /**
     * Check Result Success
     */
    public static boolean isSuccess(int code) {
        return RESULT_CODE_SUCCESS == code;
    }

    static {
        System.loadLibrary("BsPatcher");
    }

    public native int run(String oldFile, String newFile, String patchFile);
}
