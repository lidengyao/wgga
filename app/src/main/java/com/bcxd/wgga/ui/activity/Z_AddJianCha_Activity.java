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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.FuZeRenDialogAdapter;
import com.bcxd.wgga.adapter.GongDiByProjectDialogAdapter;
import com.bcxd.wgga.adapter.GridImageAdapter;
import com.bcxd.wgga.adapter.ProjectAdapter;
import com.bcxd.wgga.adapter.TypeDialogAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.JianChaBean;
import com.bcxd.wgga.model.bean.QuestionTypeBean;
import com.bcxd.wgga.model.info.FuZeRenInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.TypeInfo;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.present.Z_AddJianCha_Present;
import com.bcxd.wgga.ui.view.Z_AddJianCha_View;
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

public class Z_AddJianCha_Activity extends MvpActivity<Z_AddJianCha_Present> implements Z_AddJianCha_View {
    @Bind(R.id.BtnTiJiao)
    Button BtnTiJiao;
    @Bind(R.id.GongDiRL)
    RelativeLayout GongDiRL;
    @Bind(R.id.wentiNextIV)
    ImageView wentiNextIV;
    @Bind(R.id.WenTiRL)
    RelativeLayout WenTiRL;
    @Bind(R.id.zerenrenNextIV)
    ImageView zerenrenNextIV;
    @Bind(R.id.ZeRenRenRL)
    RelativeLayout ZeRenRenRL;
    @Bind(R.id.GongDiTV)
    TextView GongDiTV;
    @Bind(R.id.WenTiTV)
    TextView WenTiTV;
    @Bind(R.id.ZeRenRenTV)
    TextView ZeRenRenTV;
    @Bind(R.id.JianChaET)
    EditText JianChaET;
    @Bind(R.id.ChuLiXiangMuTV)
    TextView XiangMuTV;
    @Bind(R.id.gongdiNextIV)
    ImageView gongdiNextIV;

    private ArrayList<ProjectInfo> projectInfoArrayList;
    private ArrayList<GongDiInfo> gongDiListInfoArrayList;
    private ArrayList<TypeInfo> typeInfoArrayList;
    private ArrayList<FuZeRenInfo> fuZeRenInfoArrayList;
    private ProjectInfo currentProjectInfo;
    //    private GongDiInfo currentGongDiInfo;
    private TypeInfo currentTypeInfo;
    private FuZeRenInfo currentFuZeRenInfo;

    private DialogFromBottom DialogFromBottom_Project;
    private View dialogContent_Project;
    private ProjectAdapter projectAdapter;

    private DialogFromBottom DialogFromBottom_GongDi;
    private View dialogContent_GongDi;
    private GongDiByProjectDialogAdapter gongDiByProjectDialogAdapter;

    private DialogFromBottom DialogFromBottom_FuZeRen;
    private View dialogContent_FuZeRen;
    private FuZeRenDialogAdapter fuzerenDialogAdapter;

    private DialogFromBottom DialogFromBottom_QuestionType;
    private View dialogContent_QuestionType;
    private int themeId;
    private PopupWindow pop;
    private RecyclerView mRecyclerView;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private String type;

    //上传文件成功数量
    private int UploadFileCount = 0;

    //上传图片返回id
    private ArrayList<Integer> upLoadFileList;


    @Override
    protected Z_AddJianCha_Present createPresenter() {
        return new Z_AddJianCha_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_addjiancha;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
        currentProjectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        SetFuZeRenData(currentProjectInfo.getPid());

        XiangMuTV.setText(currentProjectInfo.getPname());
        dialogContent_Project = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_Project = new DialogFromBottom(this);
        DialogFromBottom_Project.setContentView(dialogContent_Project);


        dialogContent_GongDi = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_GongDi = new DialogFromBottom(this);
        DialogFromBottom_GongDi.setContentView(dialogContent_GongDi);

        dialogContent_FuZeRen = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_FuZeRen = new DialogFromBottom(this);
        DialogFromBottom_FuZeRen.setContentView(dialogContent_FuZeRen);

        dialogContent_QuestionType = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_QuestionType = new DialogFromBottom(this);
        DialogFromBottom_QuestionType.setContentView(dialogContent_QuestionType);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        themeId = R.style.picture_default_style;


        type = getIntent().getStringExtra("type");
        if (type.equals("0")) {
            InitPaiZhaoData();
        }
        if (type.equals("1")) {
            InitPaiXinJianData();
        }
        SetQuestionTyeData();

//        ChuLiXiangMuRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (projectInfoArrayList == null || projectInfoArrayList.size() == 0) {
//                    showMessage("没有查询到项目列表，请重试");
//                    return;
//                }
//                DialogFromBottom_Project.show();
//            }
//        });

//        GongDiRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (gongDiListInfoArrayList == null || gongDiListInfoArrayList.size() == 0) {
//                    showMessage("没有查询到工地列表，请重试");
//                    return;
//                }
//                DialogFromBottom_GongDi.show();
//            }
//        });

        ZeRenRenRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fuZeRenInfoArrayList == null || fuZeRenInfoArrayList.size() == 0) {
                    showMessage("没有查询到责任人列表，请重试");
                    return;
                }
                DialogFromBottom_FuZeRen.show();
            }
        });

        WenTiRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFromBottom_QuestionType.show();
            }
        });
        BtnTiJiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAdd();
            }
        });


    }

    private void InitPaiZhaoData() {
        String picPath = getIntent().getStringExtra("jietu");
//        currentGongDiInfo = (GongDiInfo) getIntent().getSerializableExtra("gongdi");


//            showMessage("截图地址：" + picPath);
        LocalMedia localMedia = new LocalMedia();
        localMedia.setChecked(false);
        localMedia.setCut(false);
        localMedia.setCompressed(false);
        localMedia.setPath(picPath);
        selectList.add(localMedia);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener, false);
        adapter.setList(selectList);
        adapter.setSelectMax(1);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(Z_AddJianCha_Activity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Z_AddJianCha_Activity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Z_AddJianCha_Activity.this).externalPictureAudio(media.getPath());
                            break;

                    }
                }
            }
        });
    }

    private void InitPaiXinJianData() {
//        SetProjectData();
        initWidget();
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener, true);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(Z_AddJianCha_Activity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Z_AddJianCha_Activity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Z_AddJianCha_Activity.this).externalPictureAudio(media.getPath());
                            break;

                    }
                }
            }
        });
    }

    private void checkAdd() {
        try {

            if (currentProjectInfo == null) {
                showMessage("请选择项目");
                return;
            }

//            //检查上传信息
//            if (currentGongDiInfo == null) {
//                showMessage("请选择工地");
//                return;
//            }
            if (currentTypeInfo == null) {
                showMessage("请选择问题类型");
                return;
            }

            if (currentFuZeRenInfo == null) {
                showMessage("请选择责任人");
                return;
            }
            if (JianChaET.getText().toString().equals("")) {
                showMessage("请填写检查意见");
                return;
            }

            if (selectList.size() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示").setMessage("确定不上传问题图片吗?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        JianChaBean jianChaBean = new JianChaBean();
                        jianChaBean.setClassify(currentTypeInfo.getCvalue() + "");//问题分类
                        jianChaBean.setPid(currentProjectInfo.getPid());//项目ID
                        //jianChaBean.setCreatetime();//问题诊断描述信息(该参数不需要传)
                        //jianChaBean.setCsid();//检查事件ID
                        //                        ArrayList<Integer> picturelist = new ArrayList<>();
//                        for (int i = 0; i < upLoadFileList.size(); i++) {
//                            picturelist.add(upLoadFileList.get(i));
//                        }
//
//                        jianChaBean.setPicturelist(picturelist);//检查事件拍摄的照片ID列表

                        //jianChaBean.setPosition();//发生位置
                        //jianChaBean.setShottime();//拍照时间(提交时不需要传该值)
//                        jianChaBean.setSid(currentGongDiInfo.getSid());//工地ID
                        jianChaBean.setDealuser(currentFuZeRenInfo.getId());//当前处理人ID(即当前登录用户ID)
                        jianChaBean.setDesc(JianChaET.getText().toString());//问题诊断描述信息
                        jianChaBean.setStatus(0);//检查快照状态 0已提交 1已查看 2已回复 3已完结 4已打回
                        mPresenter.checkAdd(jianChaBean, Z_AddJianCha_Activity.this);
                    }
                }).show();
            } else {
                //region第一步先上传图片拿到返回的urllist
                upLoadFileList = new ArrayList<>();
                if (selectList.size() > 0) {
                    String picturePath = "";
                    if (type.equals("0")) {
                        picturePath = selectList.get(upLoadPicIndex).getPath();
                    }
                    if (type.equals("1")) {
                        picturePath = selectList.get(upLoadPicIndex).getCompressPath();
                    }


                    File upLoadImg = new File(picturePath);
                    mPresenter.upload(upLoadImg, Z_AddJianCha_Activity.this);
                }

                //endregion
            }


        } catch (Exception e) {

        }
    }

    private Integer upLoadPicIndex = 0;

    private void SetProjectData() {
        mPresenter.projectlist(2, 0, Z_AddJianCha_Activity.this);
    }

    private void SetQuestionTyeData() {
        QuestionTypeBean questionTypeBean = new QuestionTypeBean();
        questionTypeBean.setTcode("CHECK_QUESTION_KIND");
        mPresenter.listbycondition_question(questionTypeBean, Z_AddJianCha_Activity.this);
    }

    private void SetFuZeRenData(Integer pid) {
        mPresenter.humanduty(pid, Z_AddJianCha_Activity.this);
    }

    @Override
    public void listbycondition_questionSuccess(ArrayList<TypeInfo> model) {
        typeInfoArrayList = model;
        TypeDialogAdapter typeDialogAdapter = new TypeDialogAdapter(Z_AddJianCha_Activity.this, model, R.layout.z_item_data);
        final PullLoadMoreListView pullLoadMoreListView = (PullLoadMoreListView) DialogFromBottom_QuestionType.findViewById(R.id.DataListView);
        pullLoadMoreListView.setAdapter(typeDialogAdapter);
        pullLoadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentTypeInfo = typeInfoArrayList.get(position);
                DialogFromBottom_QuestionType.dismiss();
                WenTiTV.setText(currentTypeInfo.getVname());
            }
        });
    }

    @Override
    public void checkAddSuccess(String model) {
        showMessage("提交成功");

        //返回时刷新fragment使用
        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
        intent.putExtra("data", "refresh");
        LocalBroadcastManager.getInstance(Z_AddJianCha_Activity.this).sendBroadcast(intent);
        sendBroadcast(intent);
        finish();
    }

    @Override
    public void uploadSuccess(UpLoadInfo model) {
        UploadFileCount += 1;
        upLoadFileList.add(model.getDid());


        upLoadPicIndex += 1;
        if (upLoadPicIndex < selectList.size()) {
            String picturePath = selectList.get(upLoadPicIndex).getCompressPath();
            File upLoadImg = new File(picturePath);
            mPresenter.upload(upLoadImg, Z_AddJianCha_Activity.this);
        }
        //图片全部上传完成
        if (UploadFileCount == selectList.size()) {
            BtnTiJiao.setEnabled(false);
            JianChaBean jianChaBean = new JianChaBean();
            jianChaBean.setClassify(currentTypeInfo.getCvalue() + "");//问题分类
            jianChaBean.setPid(currentProjectInfo.getPid());
            jianChaBean.setDealuser(currentFuZeRenInfo.getId());//当前处理人ID(即当前登录用户ID)
            jianChaBean.setDesc(JianChaET.getText().toString());//问题诊断描述信息
            ArrayList<JianChaBean.PicturelistBean> picturelist = new ArrayList<>();

            for (int i = 0; i < upLoadFileList.size(); i++) {
                JianChaBean.PicturelistBean tempPic = new JianChaBean.PicturelistBean();
                tempPic.setBigpicid(upLoadFileList.get(i));
                tempPic.setSmallpicid(upLoadFileList.get(i));
                picturelist.add(tempPic);
            }

            jianChaBean.setPicturelist(picturelist);//检查事件拍摄的照片ID列表
//            jianChaBean.setSid(currentGongDiInfo.getSid());//工地ID
            jianChaBean.setStatus(0);//检查快照状态 0已提交 1已查看 2已回复 3已完结 4已打回
            mPresenter.checkAdd(jianChaBean, Z_AddJianCha_Activity.this);
        }
    }

    @Override
    public void projectlistSuccess(ArrayList<ProjectInfo> model) {
        projectInfoArrayList = model;
        if (model != null) {
            if (projectInfoArrayList.size() > 0) {
                XiangMuTV.setText(projectInfoArrayList.get(0).getPname());
                currentProjectInfo = projectInfoArrayList.get(0);
                mPresenter.gongdibyprojectlist(currentProjectInfo.getPid(), Z_AddJianCha_Activity.this);
            } else {
                XiangMuTV.setText("");
                currentProjectInfo = null;
            }

        } else {
            XiangMuTV.setText("");
            currentProjectInfo = null;
        }
        projectAdapter = new ProjectAdapter(Z_AddJianCha_Activity.this, projectInfoArrayList, R.layout.z_item_data);
        final PullLoadMoreListView pullLoadMoreListView_pro = (PullLoadMoreListView) dialogContent_Project.findViewById(R.id.DataListView);
        pullLoadMoreListView_pro.setAdapter(projectAdapter);
        pullLoadMoreListView_pro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentProjectInfo = projectInfoArrayList.get(position);
                DialogFromBottom_Project.dismiss();
                XiangMuTV.setText(currentProjectInfo.getPname());
                mPresenter.gongdibyprojectlist(currentProjectInfo.getPid(), Z_AddJianCha_Activity.this);
            }
        });
    }

    @Override
    public void gongdibypidlistSuccess(ArrayList<GongDiInfo> model) {
//        gongDiListInfoArrayList = model;
//        if (model != null) {
//            if (gongDiListInfoArrayList.size() > 0) {
//                GongDiTV.setText(gongDiListInfoArrayList.get(0).getSname());
//                currentGongDiInfo = gongDiListInfoArrayList.get(0);
////                SetFuZeRenData(currentGongDiInfo.getSid() + "");
//            } else {
//                GongDiTV.setText("");
//                currentGongDiInfo = null;
//            }
//
//
//        } else {
//            GongDiTV.setText("");
//            currentGongDiInfo = null;
//        }
//        gongDiByProjectDialogAdapter = new GongDiByProjectDialogAdapter(Z_AddJianCha_Activity.this, gongDiListInfoArrayList, R.layout.z_item_data);
//        final PullLoadMoreListView pullLoadMoreListView_gongdi = (PullLoadMoreListView) dialogContent_GongDi.findViewById(R.id.DataListView);
//        pullLoadMoreListView_gongdi.setAdapter(gongDiByProjectDialogAdapter);
//        pullLoadMoreListView_gongdi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                GongDiInfo gongDiInfo = gongDiListInfoArrayList.get(position);
//                currentGongDiInfo = gongDiInfo;
//                GongDiTV.setText(gongDiInfo.getSname());
//                DialogFromBottom_GongDi.dismiss();
////                SetFuZeRenData(gongDiInfo.getSid() + "");
//            }
//        });
    }

    @Override
    public void humandutySuccess(ArrayList<FuZeRenInfo> model) {
        fuZeRenInfoArrayList = model;
        if (model != null) {

            if (fuZeRenInfoArrayList.size() > 0) {
                ZeRenRenTV.setText(fuZeRenInfoArrayList.get(0).getUsername());
                currentFuZeRenInfo = fuZeRenInfoArrayList.get(0);
            } else {
                ZeRenRenTV.setText("");
                currentFuZeRenInfo = null;
            }


        } else {
            ZeRenRenTV.setText("");
            currentFuZeRenInfo = null;
        }
        fuzerenDialogAdapter = new FuZeRenDialogAdapter(Z_AddJianCha_Activity.this, fuZeRenInfoArrayList, R.layout.z_item_data);
        final PullLoadMoreListView pullLoadMoreListView_fuzeren = (PullLoadMoreListView) dialogContent_FuZeRen.findViewById(R.id.DataListView);
        pullLoadMoreListView_fuzeren.setAdapter(fuzerenDialogAdapter);


        pullLoadMoreListView_fuzeren.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentFuZeRenInfo = fuZeRenInfoArrayList.get(position);
                DialogFromBottom_FuZeRen.dismiss();
                ZeRenRenTV.setText(currentFuZeRenInfo.getUsername());
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {

            //第一种方式，弹出选择和拍照的dialog
            showPop();

            //第二种方式，直接进入相册，但是 是有拍照得按钮的
            //参数很多，根据需要添加

//            PictureSelector.create(Z_AddJianCha_Activity.this)
//                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
//                    .minSelectNum(1)// 最小选择数量
//                    .imageSpanCount(4)// 每行显示个数
//                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
//                    .previewImage(true)// 是否可预览图片
////                    .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
//                    .isCamera(true)// 是否显示拍照按钮
//                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
////                    .enableCrop(true)// 是否裁剪
//                    .compress(true)// 是否压缩
////                    .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                    .selectionMedia(selectList)// 是否传入已选图片
//                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
//                    //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
//                    //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
//                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
//                    .rotateEnabled(false) // 裁剪是否可旋转图片
//                    //.scaleEnabled()// 裁剪是否可放大缩小图片
//                    //.recordVideoSecond()//录制视频秒数 默认60s
//                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(Z_AddJianCha_Activity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .compress(true)// 是否压缩
//                                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                                .selectionMedia(selectList)// 是否传入已选图片
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(Z_AddJianCha_Activity.this)
                                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                                .theme(themeId)// 主题样式设置 具体参考 values/styles
                                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                                .minSelectNum(1)// 最小选择数量
                                .compress(true)
                                .selectionMedia(selectList)// 是否传入已选图片
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
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
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
                    selectList.clear();
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);

//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model, this);
    }

    @Override
    public void onFailure(int code, String msg) {
        BtnTiJiao.setEnabled(true);
        if (code == 499) {
            mPresenter.refreshToken(Z_AddJianCha_Activity.this);
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
