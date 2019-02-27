package com.cyhc.update.xupdate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cyhc.update.library.v2.AllenVersionChecker;
import com.cyhc.update.library.v2.builder.UIData;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    protected Dialog versionDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customVersionDialogOne();

    }

    private void customVersionDialogOne() {
        versionDialog = new BaseDialog(this, R.style.BaseDialog, R.layout.custom_dialog_one_layout);
        //设置dismiss listener 用于强制更新会回调dialogDismiss方法
        versionDialog.setOnDismissListener(this);
        TextView tvCancel = (TextView) versionDialog.findViewById(R.id.versionchecklib_version_dialog_cancel);
        TextView tvUpdate = (TextView) versionDialog.findViewById(R.id.versionchecklib_version_dialog_commit);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                versionDialog.dismiss();
                finish();

            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                versionDialog.dismiss();
                AllenVersionChecker
                        .getInstance()
                        .downloadOnly(
                                UIData.create()
                                        .setDownloadUrl("https://download.dgstaticresources.net/fusion/android/app-c6-release.apk")
                                        .setContent("新增内容")
                                        .setTitle("版本更新"))
                        .setShowDownloadingDialog(false)
                        .setDirectDownload(true)
                        .setShowDownloadFailDialog(false)
                        .excuteMission(getApplicationContext());

            }
        });
        versionDialog.show();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }
}
