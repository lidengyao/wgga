package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.SheBeiInfo;
import com.bcxd.wgga.present.Z_GongDiXiangQing_Present;
import com.bcxd.wgga.ui.view.Z_GongDiXiangQing_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bumptech.glide.Glide;
import com.cat.cc.taglibrary.view.ImageDotLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_GongDiXiangQing_Activity extends MvpActivity<Z_GongDiXiangQing_Present> implements Z_GongDiXiangQing_View {

    @Bind(R.id.gongDiName)
    TextView gongDiName;
    @Bind(R.id.DiTuIV)
    ImageView DiTuIV;
    @Bind(R.id.DiTuRL)
    RelativeLayout DiTuRL;
    @Bind(R.id.idl_idl_photo)
    ImageDotLayout imageDotLayout;
    private ProjectInfo projectInfo;

    @Override
    protected Z_GongDiXiangQing_Present createPresenter() {
        return new Z_GongDiXiangQing_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_gongdixiangqing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        gongDiName.setText(projectInfo.getPname());

        mPresenter.siteGraphic(projectInfo.getPid() + "",this);
    }

    @Override
    public void siteGraphicSuccess(SheBeiInfo model) {
        if (model == null)
            return;
        imageDotLayout.setImage(model.getGraphurl());
        initIcon(model);
        imageDotLayout.setOnIconClickListener(new ImageDotLayout.OnIconClickListener() {
            @Override
            public void onIconClick(View v) {


                ImageDotLayout.IconBean bean = (ImageDotLayout.IconBean) v.getTag();
//                Toast.makeText(Z_GongDiXiangQing_Activity.this, "位置=" + bean.id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Z_GongDiXiangQing_Activity.this, Z_Daping_Activity.class);
                intent.putExtra("shebei", devicesBeanArrayList.get(bean.id));
                intent.putExtra("ProjectInfo", projectInfo);
                startActivity(intent);
            }
        });

//        SetPointData(model);
    }

    List<ImageDotLayout.IconBean> iconBeanList = new ArrayList<>();
    List<SheBeiInfo.DevicesBean> devicesBeanArrayList = new ArrayList<>();

    private void initIcon(SheBeiInfo model) {
        iconBeanList.clear();
        devicesBeanArrayList.clear();
        if (model.getDevices() != null && model.getDevices().size() > 0) {
            for (int i = 0; i < model.getDevices().size(); i++) {
                SheBeiInfo.DevicesBean temp = model.getDevices().get(i);
                int imageWidth = imageDotLayout.getWidth();
                int imageHeight = imageDotLayout.getHeight();
                float sx = (float) temp.getPx() / imageWidth;
                float sy = (float) temp.getPy() / imageHeight;
                ImageDotLayout.IconBean bean = new ImageDotLayout.IconBean(i, sx, sy, null);
                if (temp.getDtype() == 1) {
                    iconBeanList.add(bean);

                }
                devicesBeanArrayList.add(temp);
            }

            //监听图片是否加载完成
            imageDotLayout.setOnLayoutReadyListener(new ImageDotLayout.OnLayoutReadyListener() {
                @Override
                public void onLayoutReady() {
                    imageDotLayout.addIcons(iconBeanList);
                }
            });
        }
    }

    private Canvas canvas;
    private Paint paint;
    private Bitmap baseBitmap;
    private int mapp_height;
    private int mapp_width;

    private void SetPointData(SheBeiInfo model) {
        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mapp_height = dm.heightPixels;//高度获取自己找，暂时没有解决
        mapp_width = dm.widthPixels;//屏幕宽度
        // 创建一张空白图片
        baseBitmap = Bitmap.createBitmap(mapp_width, mapp_width, Bitmap.Config.ARGB_8888);

        // 创建一张画布
        canvas = new Canvas(baseBitmap);

        mapp(model);

//        DiTuIV.setImageBitmap(blankBitmap);

    }

    public void mapp(SheBeiInfo model) {
//        Glide.with(this).load(model.getGraphurl()).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
//            @Override
//            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
//                try {
//                    savaBitmap(imgName, bytes);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ditu).copy(Bitmap.Config.ARGB_8888, true);
//        Canvas canvas = new Canvas(bitmap);
//        String text = "666";
//        Rect rect = new Rect();
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setTextSize(30);
//        paint.getTextBounds(text, 0, text.length(), rect);
//        canvas.drawText(text, (bitmap.getWidth() - rect.width())>>1, (bitmap.getHeight() + rect.height())>>1, paint);
//        canvas.save();
//        DiTuIV.setImageBitmap(bitmap);

        Glide.with(this)
                .load(model.getGraphurl())
                .into(DiTuIV);

        DiTuIV.setPivotX(DiTuIV.getWidth() / 2);
        DiTuIV.setPivotY(DiTuIV.getHeight() / 2);//支点在图片中心
        DiTuIV.setRotation(90);


    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {
        showMessage(code + "");
        if (code == 499) {
            mPresenter.refreshToken(this);
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }

}
