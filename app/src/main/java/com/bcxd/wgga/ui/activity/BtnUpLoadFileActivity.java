package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bcxd.wgga.ActionSheet.Ldy_OnActionSheetSelected;
import com.bcxd.wgga.ActionSheet.SetUserCenterHeadImg_ActionSheet;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.UploadfileBean;
import com.bcxd.wgga.present.BtnUpLoadFilePresent;
import com.bcxd.wgga.ui.view.BtnUpLoadFileView;
import com.bcxd.wgga.utils.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-09-17 0017.
 */
public class BtnUpLoadFileActivity extends MvpActivity<BtnUpLoadFilePresent> implements Ldy_OnActionSheetSelected,BtnUpLoadFileView {
    @Bind(R.id.homeTopLL)
    RelativeLayout homeTopLL;
    @Bind(R.id.BtnChooseImage)
    Button BtnChooseImage;
    @Bind(R.id.IVDemo)
    ImageView IVDemo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_btnuploadfile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        BtnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetUserCenterHeadImg_ActionSheet.showSheet(
                        BtnUpLoadFileActivity.this, BtnUpLoadFileActivity.this,
                        BtnUpLoadFileActivity.this);
            }
        });
    }

    @Override
    protected BtnUpLoadFilePresent createPresenter() {
        return new BtnUpLoadFilePresent();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 结果码不等于取消时候
        if (resultCode != this.RESULT_CANCELED) {

            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (Tools.hasSdcard()) {
                        File tempFile = new File(
                                Environment.getExternalStorageDirectory() + "/"
                                        + IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG)
                                .show();
                    }

                    break;
                case RESULT_REQUEST_CODE:
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            IVDemo.setImageBitmap(photo);

            // 临时保存图片到本地
            SaveBitmap(photo, "headimg.png");
            File temppath = Environment.getExternalStorageDirectory(); // 取得SD卡的路径
            String folderString = temppath.getPath() + "/kangji/";
            String filePath = folderString + "headimg.png";
            File picture = new File(filePath);
//            mPresenter.uploadimg(0, "6d53fb5a-8969-465c-9097-38a944f0b399", picture);
        }
    }

    private void SaveBitmap(Bitmap bm, String picName) {

        File temppath = Environment.getExternalStorageDirectory(); // 取得SD卡的路径
        String folderString = temppath.getPath() + "/kangji";
        File destDir = new File(folderString);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File f = new File(folderString, picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }


    /* 请求码 */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2; /* 头像名称 */
    private static final String IMAGE_FILE_NAME = "headimg.png";

    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case 3:
                Intent intentFromCapture = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                // 判断存储卡是否可以用，可用进行存储
                if (Tools.hasSdcard()) {

                    intentFromCapture
                            .putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                    .fromFile(new File(Environment
                                            .getExternalStorageDirectory(),
                                            IMAGE_FILE_NAME)));
                }

                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                break;

            case 4:
                Intent intentFromGallery = new Intent();
                intentFromGallery.setType("image/*"); // 设置文件类型
                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void uploadfileSuccess(UploadfileBean model) {

    }
}
