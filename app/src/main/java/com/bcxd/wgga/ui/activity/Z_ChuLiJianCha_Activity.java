package com.bcxd.wgga.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.DataAdapter;
import com.bcxd.wgga.adapter.GridImageAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.JianChaUpdateBean;
import com.bcxd.wgga.model.info.JianChaDetailInfo;
import com.bcxd.wgga.model.info.JianChaInfo;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.present.Z_ChuLiJianCha_Present;
import com.bcxd.wgga.ui.view.Z_ChuLiJianCha_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.PullLoadMoreListView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

public class Z_ChuLiJianCha_Activity extends MvpActivity<Z_ChuLiJianCha_Present> implements Z_ChuLiJianCha_View {
    @Bind(R.id.zhuangtaiSpinner)
    Spinner zhuangtaiSpinner;
    @Bind(R.id.BtnTiJiao)
    Button BtnTiJiao;
    @Bind(R.id.DeseTV)
    TextView DeseTV;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.GongDiNameTV)
    TextView GongDiNameTV;
    @Bind(R.id.WenTiTypeTV)
    TextView WenTiTypeTV;
    @Bind(R.id.FuZeRenTV)
    TextView FuZeRenTV;
    @Bind(R.id.FanKuiET)
    EditText FanKuiET;
    @Bind(R.id.ProjectTV)
    TextView ProjectTV;
    @Bind(R.id.ShowFanKuiTV)
    TextView ShowFanKuiTV;
    @Bind(R.id.ShowFanKuiLL)
    LinearLayout ShowFanKuiLL;
    @Bind(R.id.EditFanKuiLL)
    LinearLayout EditFanKuiLL;
    @Bind(R.id.ShowFanKuiRenTV)
    TextView ShowFanKuiRenTV;
    @Bind(R.id.ShowFanKuiRenLL)
    LinearLayout ShowFanKuiRenLL;
    @Bind(R.id.CheckLogLL)
    LinearLayout CheckLogLL;
    @Bind(R.id.ReplayRecycler)
    RecyclerView ReplayRecycler;
    @Bind(R.id.wentiNextIV)
    ImageView wentiNextIV;
    @Bind(R.id.WenTiTV)
    TextView WenTiTV;
    @Bind(R.id.WenTiRL)
    RelativeLayout WenTiRL;
    @Bind(R.id.HuiFuLL)
    LinearLayout HuiFuLL;
    @Bind(R.id.CheckLogLine)
    View CheckLogLine;

    private GridImageAdapter adapter_selectList;
    private GridImageAdapter adapter_selectListAdd;
    private int themeId;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectListAdd = new ArrayList<>();
    private List<LocalMedia> selectList = new ArrayList<>();
    //上传文件成功数量
    private int UploadFileCount = 0;

    //上传图片返回id
    private ArrayList<Integer> upLoadFileList;
    private Integer upLoadPicIndex = 0;


    private DialogFromBottom DialogFromBottom_Status;
    private View dialogContent_Status;
    private int _Status = 0;
    private int cid;

    @Override
    protected Z_ChuLiJianCha_Present createPresenter() {
        return new Z_ChuLiJianCha_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_chulijiancha;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        String btype = getIntent().getStringExtra("btype");
        if (btype.equals("-1")) {
            JianChaInfo.RecordsBean jianChaInfo = (JianChaInfo.RecordsBean) getIntent().getSerializableExtra("jiancha");
            cid = jianChaInfo.getCsid();
            mPresenter.checksearchbycheckid(cid, this);
        }
        if (btype.equals("1")) {
            cid = Integer.parseInt(getIntent().getStringExtra("bid"));
            mPresenter.checksearchbycheckid(cid, this);
        }


        BtnTiJiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAddUpate();
            }
        });
        themeId = R.style.picture_default_style;
        SetBottomData();


        dialogContent_Status = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_Status = new DialogFromBottom(this);
        DialogFromBottom_Status.setContentView(dialogContent_Status);

        final ArrayList<String> dataList = new ArrayList<>();
        dataList.add("否");
        dataList.add("是");

        DataAdapter dataAdapter = new DataAdapter(Z_ChuLiJianCha_Activity.this, dataList, R.layout.z_item_data);
        final PullLoadMoreListView dataListView = (PullLoadMoreListView) dialogContent_Status.findViewById(R.id.DataListView);
        dataListView.setAdapter(dataAdapter);

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = dataList.get(position);
                if (temp.equals("否")) {
                    _Status = 0;
                }
                if (temp.equals("是")) {
                    _Status = 1;
                }
                WenTiTV.setText(temp);
                DialogFromBottom_Status.dismiss();
            }
        });

        WenTiRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFromBottom_Status.show();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String btype = intent.getStringExtra("btype");
        if (btype.equals("-1")) {
            JianChaInfo.RecordsBean jianChaInfo = (JianChaInfo.RecordsBean) getIntent().getSerializableExtra("jiancha");
            cid = jianChaInfo.getCsid();
            mPresenter.checksearchbycheckid(cid, this);
        }
        if (btype.equals("1")) {
            cid = Integer.parseInt(getIntent().getStringExtra("bid"));
            mPresenter.checksearchbycheckid(cid, this);
        }
    }

    private void checkAddUpate() {
        if (FanKuiET.getText().toString().equals("")) {
            showMessage("请填写反馈信息");
            return;
        }
        if (selectListAdd.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("确定不上传问题图片吗?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    JianChaUpdateBean jianChaUpdateBean = new JianChaUpdateBean();
                    jianChaUpdateBean.setContext(FanKuiET.getText().toString());
                    jianChaUpdateBean.setCsid(cid);
//                    jianChaUpdateBean.setDealuser(Integer.parseInt(AppContext.dbHelper.GetValue(DbKeyS.uid)));
                    jianChaUpdateBean.setDealuser(Integer.parseInt(SpUtils.getSettingNote(Z_ChuLiJianCha_Activity.this, DbKeyS.uid)));
                    jianChaUpdateBean.setStatus(_Status);
                    mPresenter.checkAddUpate(jianChaUpdateBean, Z_ChuLiJianCha_Activity.this);
                }
            }).show();
        } else {
            //region第一步先上传图片拿到返回的urllist
            upLoadFileList = new ArrayList<>();
            if (selectListAdd.size() > 0) {
                String picturePath = selectListAdd.get(upLoadPicIndex).getCompressPath();
                File upLoadImg = new File(picturePath);
                mPresenter.upload(upLoadImg, Z_ChuLiJianCha_Activity.this);
            }

            //endregion
        }


    }

    //region  底部新添加问题图片
    private PopupWindow pop_Add;

    private void SetBottomData() {
        initWidget();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicAddClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {
            showPopAdd();
        }
    };

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        ReplayRecycler.setLayoutManager(manager);
        adapter_selectListAdd = new GridImageAdapter(this, onAddPicAddClickListener, true);
        adapter_selectListAdd.setList(selectListAdd);
        adapter_selectListAdd.setSelectMax(maxSelectNum);
        ReplayRecycler.setAdapter(adapter_selectListAdd);
        adapter_selectListAdd.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectListAdd.size() > 0) {
                    LocalMedia media = selectListAdd.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPicturePreview(position, selectListAdd);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPictureAudio(media.getPath());
                            break;

                    }
                }
            }
        });
    }

    private void initWidget_WenTi() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        recycler.invalidate();
        adapter_selectList = new GridImageAdapter(this, onAddPicClickListener, false);
        adapter_selectList.setList(selectList);
        adapter_selectList.setSelectMax(selectList.size());
        recycler.setAdapter(adapter_selectList);
//        adapter_selectList.notifyDataSetChanged();
        adapter_selectList.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).themeStyle(themeId).openExternalPreview(position, "/custom_file", selectList);
//                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPictureAudio(media.getPath());
                            break;

                    }
                }
            }
        });
    }


    private void showPopAdd() {
        View bottomView = View.inflate(this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop_Add = new PopupWindow(bottomView, -1, -2);
        pop_Add.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop_Add.setOutsideTouchable(true);
        pop_Add.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop_Add.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop_Add.setAnimationStyle(R.style.main_menu_photo_anim);
        pop_Add.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(Z_ChuLiJianCha_Activity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .compress(true)// 是否压缩
//                                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                                .selectionMedia(selectListAdd)// 是否传入已选图片
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(Z_ChuLiJianCha_Activity.this)
                                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                                .theme(themeId)// 主题样式设置 具体参考 values/styles
                                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                                .minSelectNum(1)// 最小选择数量
                                .selectionMedia(selectListAdd)// 是否传入已选图片
                                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    public void closePopupWindow() {
        if (pop_Add != null && pop_Add.isShowing()) {
            pop_Add.dismiss();
            pop_Add = null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectListAdd.clear();
                    images = PictureSelector.obtainMultipleResult(data);
                    selectListAdd.addAll(images);

//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter_selectListAdd.setList(selectListAdd);
                    adapter_selectListAdd.notifyDataSetChanged();
                    break;
            }
        }
    }

    //endregion


    //region 顶部当前图片
    private void SetTopData() {
        initWidget_WenTi();
//        showPop();
    }

    private PopupWindow pop;
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {
            showPop();
        }
    };

    private void showPop() {
        View bottomView = View.inflate(this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        PictureSelector.create(Z_ChuLiJianCha_Activity.this);
//                .openGallery(PictureMimeType.ofImage())
//                .maxSelectNum(maxSelectNum)
//                .minSelectNum(1)
//                .imageSpanCount(4)
//                .compress(true)// 是否压缩
////                                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                .selectionMedia(selectList)// 是否传入已选图片
//                .selectionMode(PictureConfig.MULTIPLE)
//                .forResult(PictureConfig.CHOOSE_REQUEST);
//        closePopupWindowTemp();

    }

    public void closePopupWindowTemp() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    //endregion

    //region 添加盖楼部分
    private void SetGaiLouData(JianChaDetailInfo.CheckLogListBean checkLogListBean, boolean isShowLine) {
        View itemView = View.inflate(this, R.layout.z_item_wenti, null);
        TextView contextTV = (TextView) itemView.findViewById(R.id.ContentTV);
        TextView chulirenTV = (TextView) itemView.findViewById(R.id.ChuliRenTV);
        TextView chulitimeTV = (TextView) itemView.findViewById(R.id.ChuLiTimeTV);
        RecyclerView recyclerView = (RecyclerView) itemView.findViewById(R.id.ItemRecycler);
        View itemLine = (View) itemView.findViewById(R.id.ItemLine);
        contextTV.setText(checkLogListBean.getContext());
        chulirenTV.setText("处理人：" + checkLogListBean.getDealusername());
        chulitimeTV.setText("处理时间：" + checkLogListBean.getActtime());
        if (isShowLine == false) {
            itemLine.setVisibility(View.GONE);
        }
        //添加图片
        if (checkLogListBean.getPiclist().size() > 0) {
            List<LocalMedia> selectList = new ArrayList<>();
            for (int i = 0; i < checkLogListBean.getPiclist().size(); i++) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setChecked(false);
                localMedia.setCut(false);
                localMedia.setCompressed(false);
                localMedia.setPath(checkLogListBean.getPiclist().get(i).getUrl());
                selectList.add(localMedia);
            }
            SetItemData(recyclerView, selectList);
        }

        CheckLogLL.addView(itemView);
    }

    private void SetItemData(RecyclerView recyclerView, final List<LocalMedia> _selectList) {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        GridImageAdapter adapter_item = new GridImageAdapter(this, onAddPicClickListener, false);
        adapter_item.setList(_selectList);
        adapter_item.setSelectMax(_selectList.size());
        recyclerView.setAdapter(adapter_item);
        adapter_item.notifyDataSetChanged();
        adapter_item.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (_selectList.size() > 0) {
                    LocalMedia media = _selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).themeStyle(themeId).openExternalPreview(position, "/custom_file", _selectList);
//                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPicturePreview(position, _selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Z_ChuLiJianCha_Activity.this).externalPictureAudio(media.getPath());
                            break;

                    }
                }
            }
        });
    }

    //endregion

    @Override
    public void checkAddUpateSuccess(String model) {
        showMessage("提交成功");
        //返回时刷新fragment使用
        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
        intent.putExtra("data", "refresh");
        LocalBroadcastManager.getInstance(Z_ChuLiJianCha_Activity.this).sendBroadcast(intent);
        sendBroadcast(intent);
        finish();
    }

    @Override
    public void uploadSuccess(UpLoadInfo model) {
        UploadFileCount += 1;
        upLoadFileList.add(model.getDid());


        upLoadPicIndex += 1;
        if (upLoadPicIndex < selectListAdd.size()) {
            String picturePath = selectListAdd.get(upLoadPicIndex).getCompressPath();
            File upLoadImg = new File(picturePath);
            mPresenter.upload(upLoadImg, this);
        }
        //图片全部上传完成
        if (UploadFileCount == selectListAdd.size()) {
            BtnTiJiao.setEnabled(false);

            JianChaUpdateBean jianChaUpdateBean = new JianChaUpdateBean();
            jianChaUpdateBean.setContext(FanKuiET.getText().toString());
            jianChaUpdateBean.setCsid(cid);
//            jianChaUpdateBean.setDealuser(Integer.parseInt(AppContext.dbHelper.GetValue(DbKeyS.uid)));
            jianChaUpdateBean.setDealuser(Integer.parseInt(SpUtils.getSettingNote(Z_ChuLiJianCha_Activity.this, DbKeyS.uid)));

            ArrayList<JianChaUpdateBean.PicturelistBean> picturelist = new ArrayList<>();
            for (int i = 0; i < upLoadFileList.size(); i++) {
                JianChaUpdateBean.PicturelistBean tempPic = new JianChaUpdateBean.PicturelistBean();
                tempPic.setBigpicid(upLoadFileList.get(i));
                tempPic.setSmallpicid(upLoadFileList.get(i));
                picturelist.add(tempPic);
            }
            jianChaUpdateBean.setPicturelist(picturelist);
            jianChaUpdateBean.setStatus(_Status);
            mPresenter.checkAddUpate(jianChaUpdateBean, this);
        }
    }

    @Override
    public void checksearchbycheckidSuccess(JianChaDetailInfo model) {

        JianChaDetailInfo.CheckBean checkBean = model.getCheck();
        if (model.getCheck().getStatus() == 1) {
            BtnTiJiao.setVisibility(View.GONE);
            ShowFanKuiLL.setVisibility(View.VISIBLE);
            EditFanKuiLL.setVisibility(View.GONE);
            ShowFanKuiRenLL.setVisibility(View.VISIBLE);
            HuiFuLL.setVisibility(View.GONE);
//            ShowFanKuiTV.setText(model.getCheckLogList().get(1).getContext());
//            ShowFanKuiRenTV.setText("反馈人员：" + model.getCheckLogList().get(1).getDealusername());
        } else {
            HuiFuLL.setVisibility(View.VISIBLE);
            BtnTiJiao.setVisibility(View.VISIBLE);
            ShowFanKuiLL.setVisibility(View.GONE);
            EditFanKuiLL.setVisibility(View.VISIBLE);
            ShowFanKuiRenLL.setVisibility(View.GONE);

        }
        DeseTV.setText(checkBean.getDesc());
        ProjectTV.setText(checkBean.getProjectname());
        GongDiNameTV.setText(checkBean.getSname() + "");
        WenTiTypeTV.setText(checkBean.getClassifyname() + "");
        FuZeRenTV.setText(checkBean.getDealusername());

        //加载问题图片
        if (model.getCheck().getPiclist() != null) {

            selectList.clear();
            for (int i = 0; i < model.getCheck().getPiclist().size(); i++) {
                JianChaDetailInfo.CheckBean.PiclistBean piclistBean = model.getCheck().getPiclist().get(i);
                LocalMedia localMedia = new LocalMedia();
                localMedia.setChecked(false);
                localMedia.setCut(false);
                localMedia.setCompressed(false);
                localMedia.setPath(piclistBean.getUrl());
                selectList.add(localMedia);


            }
        }

        SetTopData();

        //界面赋值盖楼数据
        if (model.getCheckLogList().size() > 0) {
            CheckLogLine.setVisibility(View.VISIBLE);
            for (int i = 0; i < model.getCheckLogList().size(); i++) {
                JianChaDetailInfo.CheckLogListBean temp = model.getCheckLogList().get(i);
                boolean isShowLine = true;
                if (i == model.getCheckLogList().size() - 1) {
                    isShowLine = false;
                }
                SetGaiLouData(temp, isShowLine);
            }
        }

    }


    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {

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
