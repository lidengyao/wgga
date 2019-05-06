package com.bcxd.wgga.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.VideoControlBean;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.SheBeiInfo;
import com.bcxd.wgga.present.Z_DaPing_Present;
import com.bcxd.wgga.ui.view.Z_DaPing_View;
import com.bcxd.wgga.utils.TokenUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_Daping_Activity extends MvpActivity<Z_DaPing_Present> implements TextureView.SurfaceTextureListener,
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnVideoSizeChangedListener, Z_DaPing_View {
    @Bind(R.id.KongzhiIV)
    ImageView KongzhiIV;
    @Bind(R.id.PaiZhaoLL)
    LinearLayout PaiZhaoLL;
    @Bind(R.id.JianKongWV)
    WebView JianKongWV;
    @Bind(R.id.JianKongTV)
    TextureView JianKongTV;
    @Bind(R.id.KongZhiBtn)
    Button KongZhiBtn;
    @Bind(R.id.TopLL)
    LinearLayout TopLL;
    @Bind(R.id.LeftLL)
    LinearLayout LeftLL;
    @Bind(R.id.RightLL)
    LinearLayout RightLL;
    @Bind(R.id.BottomLL)
    LinearLayout BottomLL;
    @Bind(R.id.NOVedio)
    ImageView NOVedio;
    @Bind(R.id.backIV)
    ImageView backIV;

    private MediaPlayer mp;
    public static String MY_VIDEO = "";
    public static String TAG = "TextureViewActivity";
    private SheBeiInfo.DevicesBean devicesBean;
    private ProjectInfo projectInfo;

    @Override
    protected Z_DaPing_Present createPresenter() {
        return new Z_DaPing_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_daping;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        ButterKnife.bind(this);
        devicesBean = (SheBeiInfo.DevicesBean) getIntent().getSerializableExtra("shebei");
        projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        //region 点击事件
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
//            mp.pause();
//            mp.release();
                    mp.stop();
                }
                finish();
            }
        });
        PaiZhaoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent paizhao = new Intent(Z_Daping_Activity.this, Z_JianKongPaiZhao_Activity.class);
//                startActivity(paizhao);
                if (ContextCompat.checkSelfPermission(Z_Daping_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Z_Daping_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 54);
                    Log.i("-->", "权限申请");
                } else {

                    getBitmap(JianKongTV);
                }
            }
        });

//        KongzhiIV.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    Toast.makeText(Z_Daping_Activity.this, "按下了", Toast.LENGTH_SHORT).show();
//                    KongzhiIV.setBackgroundColor(Color.RED);
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Toast.makeText(Z_Daping_Activity.this, "松开了", Toast.LENGTH_SHORT).show();
//                    KongzhiIV.setBackgroundColor(Color.BLUE);
//                }
//                return false;
//            }
//        });

        KongZhiBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    mPresenter.videoStart(videoControlBean,Z_Daping_Activity.this);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    mPresenter.videoStop(videoControlBean,Z_Daping_Activity.this);
                    KongZhiBtn.setBackgroundColor(Color.BLUE);
                }
                return false;
            }
        });


        TopLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhishang);
                    mPresenter.videoStart(videoControlBean,Z_Daping_Activity.this);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhidi);
                    mPresenter.videoStop(videoControlBean,Z_Daping_Activity.this);

                }
                return false;
            }
        });

        LeftLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhizuo);
                    mPresenter.videoStart(videoControlBean,Z_Daping_Activity.this);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhidi);
                    mPresenter.videoStop(videoControlBean,Z_Daping_Activity.this);

                }
                return false;
            }
        });

        RightLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhiyou);
                    mPresenter.videoStart(videoControlBean,Z_Daping_Activity.this);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhidi);
                    mPresenter.videoStop(videoControlBean,Z_Daping_Activity.this);

                }
                return false;
            }
        });

        BottomLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhixia);
                    mPresenter.videoStart(videoControlBean,Z_Daping_Activity.this);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    VideoControlBean videoControlBean = new VideoControlBean();
                    videoControlBean.setCvalue("9");//  "cvalue": "1:上转 2:左上 3:左转 4:左下 5:下转 6:右下 7:右转 8:右上 9:放大 10:缩小",
                    videoControlBean.setVcode(devicesBean.getDevicesn());
                    KongzhiIV.setImageResource(R.mipmap.kongzhidi);
                    mPresenter.videoStop(videoControlBean,Z_Daping_Activity.this);

                }
                return false;
            }
        });

        //endregion
        NOVedio.setVisibility(View.GONE);
        showLoading();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mp = new MediaPlayer();
                MY_VIDEO = devicesBean.getVideourl();
                JianKongTV.setSurfaceTextureListener(Z_Daping_Activity.this);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
//            new DownloadThread().run();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
//            mp.pause();
//            mp.release();
            mp.stop();
        }
    }

    public void getBitmap(TextureView vv) {
        String mPath = Environment.getExternalStorageDirectory().toString()
                + "/Pictures/" + "jietu" + ".png";

        Bitmap bm = vv.getBitmap();
        if (bm == null)
            Log.e(TAG, "bitmap is null");

        OutputStream fout = null;
        File imageFile = new File(mPath);

        try {
            fout = new FileOutputStream(imageFile);
            bm.compress(Bitmap.CompressFormat.PNG, 90, fout);
            fout.flush();
            fout.close();
//            Toast.makeText(getApplicationContext(), "Capturing Screenshot: " + mPath, Toast.LENGTH_SHORT).show();

            mp.pause();
            Intent intent = new Intent(Z_Daping_Activity.this, Z_AddJianCha_Activity.class);
            intent.putExtra("type", "0");
            intent.putExtra("jietu", mPath);
            intent.putExtra("ProjectInfo", projectInfo);
            startActivity(intent);


        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException");
            e.printStackTrace();
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        showMessage("onBufferingUpdate");
        if (NOVedio != null)
            NOVedio.setVisibility(View.GONE);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (NOVedio != null)
            NOVedio.setVisibility(View.VISIBLE);
        dismissLoading();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
//        showMessage("onPrepared");
        dismissLoading();
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//        showMessage("onVideoSizeChanged");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            dismissLoading();
            if (mp != null) {
                mp.stop();
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface s = new Surface(surface);

        try {

            mp.setDataSource(MY_VIDEO);
            mp.setSurface(s);
//            mp.prepare();
            mp.prepareAsync();
            mp.setOnBufferingUpdateListener(this);
            mp.setOnCompletionListener(this);
//            mp.setOnPreparedListener(this);
            mp.setOnVideoSizeChangedListener(this);

            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });


        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            showMessage(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//        showMessage("onSurfaceTextureSizeChanged");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//        showMessage("onSurfaceTextureUpdated");
        dismissLoading();
        if (NOVedio != null)
            NOVedio.setVisibility(View.GONE);
    }

    @Override
    public void videoStartSuccess(String model) {
        showMessage("控制操作成功");
    }

    @Override
    public void videoStopSuccess(String model) {
        showMessage("停止操作成功");
    }


    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {

        if (code == 499) {
            mPresenter.refreshToken(Z_Daping_Activity.this);
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
