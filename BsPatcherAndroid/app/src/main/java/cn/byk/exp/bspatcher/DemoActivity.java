package cn.byk.exp.bspatcher;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import cn.byk.pandora.bspatcher.BsPatcher;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Created by Byk on 2018/11/21.
 */
public class DemoActivity extends AppCompatActivity {

    private static final String FILE_OLD =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/bs/old.apk";
    private static final String FILE_NEW =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/bs/new.apk";
    private static final String FILE_PATCH =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/bs/patch.mjpatch";

    private Disposable mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        askPermission();
        findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPatch();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mTask != null) {
            mTask.dispose();
        }
        super.onDestroy();
    }

    private void askPermission() {
        RxPermissions permissions = new RxPermissions(this);
        mTask = permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            toast("Permission Granted");
                        }
                    }
                });
    }

    private void onPatch() {
        int result = BsPatcher.getInstance().run(
                FILE_OLD, FILE_NEW, FILE_PATCH);
        toast(BsPatcher.isSuccess(result) ? "Success" : "Failed");
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
