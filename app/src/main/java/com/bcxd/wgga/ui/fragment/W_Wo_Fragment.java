package com.bcxd.wgga.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.BuildConfig;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.bean.HeadImgObject;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.model.info.VersionInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.present.Z_Wo_Present;
import com.bcxd.wgga.ui.activity.Z_About_Activity;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.activity.Z_Wodexiangmu_Activity;
import com.bcxd.wgga.ui.activity.Z_ZhangHaoAnQuan_Activity;
import com.bcxd.wgga.ui.view.Z_Wo_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.FileUtils;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.UpdateManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

import static com.bcxd.wgga.utils.FileUtils.getFileName;
import static com.bcxd.wgga.utils.FileUtils.getZipImg;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class W_Wo_Fragment extends MvpFragment<Z_Wo_Present> implements Z_Wo_View {

    @Override
    protected Z_Wo_Present createPresenter() {
        return new Z_Wo_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_fragment_wo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK
                && null != data) {
            String sdState = Environment.getExternalStorageState();
            if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss",
                    Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为图片格式
            Bitmap bmp = (Bitmap) bundle.get("data");
            File localImg = FileUtils.saveImage(bmp, "headimg.png");
            String fileName = getFileName(localImg.getPath());
            File upLoadImg = getZipImg(localImg.getPath(), 300, fileName);
//            String token = AppContext.dbHelper.GetValue(DbKeyS.token);
//
//            String id = AppContext.dbHelper.GetValue(DbKeyS.id);
            presenter.upload(upLoadImg,getContext());
//            headcircleImageView.setImageBitmap(bmp);
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getContext().getContentResolver().query(selectedImage,
                        filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String picturePath = c.getString(columnIndex);
                c.close();


                File upLoadImg = getZipImg(picturePath, 300, getFileName(picturePath));
//                String token = AppContext.dbHelper.GetValue(DbKeyS.token);
//                String id = AppContext.dbHelper.GetValue(DbKeyS.id);
                Bitmap bitmap = BitmapFactory.decodeFile(upLoadImg.getPath());
                presenter.upload(upLoadImg,getContext());
                // 获取图片并显示
//                Bitmap bitmap = BitmapFactory.decodeFile(upLoadImg.getPath());

            } catch (Exception e) {
            }
        }
    }


    @Override
    public void logoutSuccess(String model) {
        //停止推送服务
        JPushInterface.stopPush(getContext());
//        AppContext.dbHelper.SetData(DbKeyS.status, "0");
        SpUtils.saveSettingNote(getContext(), DbKeyS.status, "0");
//        AppContext.dbHelper.close();
        Intent intent = new Intent(getContext(), Z_Login_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
//                getActivity().finish();
    }

    private String getVERSION_NAME(Context context) {
        String VERSION_NAME = "";
        VERSION_NAME = BuildConfig.VERSION_NAME;
        return VERSION_NAME;
    }

    @Override
    public void uploadSuccess(UpLoadInfo model) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.picc)//图片加载出来前，显示的图片
                .fallback(R.mipmap.picc) //url为空的时候,显示的图片
                .error(R.mipmap.picc);//图片加载失败后，显示的图片


//        Glide.with(this)
//                .load(model.getUrl()) //图片地址
//                .apply(options)
//                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                .into(ImgCircleWebImage);


        //更新用户资料
        HeadImgObject headImgObject = new HeadImgObject();
//        headImgObject.setUid(AppContext.dbHelper.GetValue(DbKeyS.uid));
        headImgObject.setSelfieid(model.getDid() + "");
        presenter.updateHeadImg(headImgObject,getContext());
    }

    @Override
    public void updateSucess(String model) {
        showMessage("头像修改成功");
    }

    @Override
    public void updateHeadImg(String model) {
        showMessage("头像修改成功");
    }

    @Override
    public void checkVersionSuccess(VersionInfo model) {
        if (model == null)
            return;
        else {
//            int versionCode = getVersionCode(getContext());
//            int serviceCode = model.getVersion();
//            if (serviceCode > versionCode) {
//                UpdateManager updateManager = new UpdateManager(getContext());
//                updateManager.forceCheckUpdate(model.getUrl());
//
//
////                if (model.getForce().equals("true")) {
////                    updateManager.forceCheckUpdate(model.getUrl(), model.getVersionName());
////                } else {
////                    updateManager.noForceCheckUpdate(model.getUrl(), model.getVersionName());
////                }
//            } else {
//                showMessage("已经是最新版本");
//            }
        }


    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,getContext());
    }

    @Override
    public void onFailure(int code, String msg) {

        if (code == 499) {
            presenter.refreshToken(getContext());
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(getContext(), Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }
}
