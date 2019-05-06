package com.bcxd.wgga.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.GongDiAdapter;
import com.bcxd.wgga.adapter.ProjectAdapter;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.bean.MessageBean;
import com.bcxd.wgga.model.info.CityenvironmentInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.model.info.apppicInfo;
import com.bcxd.wgga.present.Z_ShouYe_Present;
import com.bcxd.wgga.ui.activity.Z_BaojingChuLi_Activity;
import com.bcxd.wgga.ui.activity.Z_ChuLiJianCha_Activity;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.activity.Z_XiangMuXiangQing_Activity;
import com.bcxd.wgga.ui.activity.Z_ZhengGaiChuLi_Activity;
import com.bcxd.wgga.ui.view.Z_ShouYe_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.PullLoadMoreListView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class Z_ShouYe_Fragment extends MvpFragment<Z_ShouYe_Present> implements Z_ShouYe_View {


    @Bind(R.id.XinWenLLLeft)
    RelativeLayout XinWenLLLeft;
    @Bind(R.id.XinWenLLRight)
    LinearLayout XinWenLLRight;
    @Bind(R.id.BtnOK)
    Button BtnOK;
    @Bind(R.id.XiangMuListView)
    PullLoadMoreListView XiangMuListView;
    @Bind(R.id.TitleTV)
    TextView TitleTV;
    @Bind(R.id.ShouYeBgRL)
    RelativeLayout ShouYeBgRL;
    @Bind(R.id.cityenvironmentIV)
    ImageView cityenvironmentIV;
    @Bind(R.id.cityenvironmentTV)
    TextView cityenvironmentTV;
    @Bind(R.id.ShouYeBannerIV)
    ImageView ShouYeBannerIV;
    @Bind(R.id.HavMsgLL)
    LinearLayout HavMsgLL;
    @Bind(R.id.NoMsgLL)
    LinearLayout NoMsgLL;
    @Bind(R.id.FirstTV)
    TextView FirstTV;
    @Bind(R.id.SecTV)
    TextView SecTV;
    private ArrayList<ProjectInfo> projectInfoArrayList = new ArrayList<>();
    GongDiAdapter gongDiAdapter;
    private ProjectAdapter projectAdapter;
    private View dialogContent_Project;

    @Override
    protected Z_ShouYe_Present createPresenter() {
        return new Z_ShouYe_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_fragment_shouye;
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

        setProjectInfo();
//        presenter.projectlist(3, 3);
        presenter.apppicQuery(1, 0,getContext());
        presenter.Cityenvironment(getContext());
        MessageBean messageBean = new MessageBean();
        messageBean.setCurrent(1);
        messageBean.setSize(10);
        presenter.listMsg(messageBean,getContext());
        //待完善（中间滚动消息）

        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getContext(), Z_XiangMuXiangQing_Activity.class);
                startActivity(mainIntent);
            }
        });
    }

    private void setProjectInfo() {
        Gson gson = new Gson();
//        String gsonData = AppContext.dbHelper.GetValue(DbKeyS.ProjectInfo);
        String gsonData = SpUtils.getSettingNote(getContext(), DbKeyS.ProjectInfo);
        if (!gsonData.equals("")) {
            ArrayList<ProjectInfo> tempGList = gson.fromJson(gsonData, new TypeToken<List<ProjectInfo>>() {
            }.getType());
            projectInfoArrayList.addAll(tempGList);
        }

        projectAdapter = new ProjectAdapter(getContext(), projectInfoArrayList, R.layout.z_item_gongdi);
        XiangMuListView.setAdapter(projectAdapter);
        XiangMuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProjectInfo projectInfo = (ProjectInfo) projectInfoArrayList.get(position);
                Intent intent = new Intent(getContext(), Z_XiangMuXiangQing_Activity.class);
                intent.putExtra("ProjectInfo", projectInfo);
                startActivity(intent);
            }
        });
        XiangMuListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
//                pageIndex = 1;
//                findHouseList();
                presenter.projectlist(3, 3,getContext());
                XiangMuListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
//                pageIndex += 1;
//                findHouseList();
                presenter.projectlist(3, 3,getContext());
                XiangMuListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void projectlistSuccess(ArrayList<ProjectInfo> model) {

        if (model == null)
            return;
        Gson gson = new Gson();
        String gsonData = gson.toJson(model);
//        if (AppContext.dbHelper.SetData(DbKeyS.ProjectInfo, gsonData)) {
        if (SpUtils.saveSettingNote(getContext(), DbKeyS.ProjectInfo, gsonData)) {
            projectInfoArrayList = model;

            projectAdapter = new ProjectAdapter(getContext(), projectInfoArrayList, R.layout.z_item_gongdi);
            XiangMuListView.setAdapter(projectAdapter);
            XiangMuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ProjectInfo projectInfo = (ProjectInfo) projectInfoArrayList.get(position);
                    Intent intent = new Intent(getContext(), Z_XiangMuXiangQing_Activity.class);
                    intent.putExtra("ProjectInfo", projectInfo);
                    startActivity(intent);
                }
            });
            XiangMuListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
                @Override
                public void onRefresh() {
//                pageIndex = 1;
//                findHouseList();
                    presenter.projectlist(3, 3,getContext());
                    XiangMuListView.refreshComplete();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 500);

                }

                @Override
                public void onLoadMore() {
//                pageIndex += 1;
//                findHouseList();
                    presenter.projectlist(3, 3,getContext());
                    XiangMuListView.refreshComplete();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 500);
                }
            });
        }

    }

    private XiaoXiInfo _xiaoxiinfo;
    private long baseTimer;

    private ArrayList<XiaoXiInfo.RecordsBean> topList = new ArrayList<>();
    private ArrayList<XiaoXiInfo.RecordsBean> downList = new ArrayList<>();

    private int topIndex = 0;
    private int downIndex = 0;

    @Override
    public void listMsgSuccess(final XiaoXiInfo model) {
        if (model != null) {
            _xiaoxiinfo = model;


            if (model.getRecords().size() > 0) {
                NoMsgLL.setVisibility(View.GONE);
                HavMsgLL.setVisibility(View.VISIBLE);

                //存储消息列表
                if (_xiaoxiinfo.getRecords().size() > 0) {
                    for (int i = 0; i < _xiaoxiinfo.getRecords().size(); i++) {
                        XiaoXiInfo.RecordsBean recordsBean = _xiaoxiinfo.getRecords().get(i);
                        if (i % 2 == 0) {
                            topList.add(recordsBean);
                        }
                        if (i % 2 == 1) {
                            downList.add(recordsBean);
                        }

                    }
                }

                final Handler topTimehandler = new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        if (null != FirstTV) {

                            final XiaoXiInfo.RecordsBean recordsBean = (XiaoXiInfo.RecordsBean) msg.obj;
                            FirstTV.setText(recordsBean.getMdesc());
                            FirstTV.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToDetail(recordsBean);
                                }
                            });
                        }
                    }
                };

                final Handler downTimehandler = new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        if (null != SecTV) {
                            final XiaoXiInfo.RecordsBean recordsBean = (XiaoXiInfo.RecordsBean) msg.obj;
                            SecTV.setText(recordsBean.getMdesc());
                            SecTV.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToDetail(recordsBean);
                                }
                            });
                        }
                    }
                };
                new Timer("开机计时器").scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.obj = topList.get(topIndex);
                        topTimehandler.sendMessage(msg);
                        if (topIndex == topList.size() - 1) {
                            topIndex = 0;
                        } else
                            topIndex = topIndex + 1;


                        Message downmsg = new Message();
                        downmsg.obj = downList.get(downIndex);
                        downTimehandler.sendMessage(downmsg);
                        if (downIndex == downList.size() - 1) {
                            downIndex = 0;
                        } else
                            downIndex = downIndex + 1;
                    }

                }, 0, 5000);


//                new Thread(new MyThread()).start();

            } else {
                NoMsgLL.setVisibility(View.VISIBLE);
                HavMsgLL.setVisibility(View.GONE);
            }
        } else {
            NoMsgLL.setVisibility(View.VISIBLE);
            HavMsgLL.setVisibility(View.GONE);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

            }
            super.handleMessage(msg);

        }
    };

    private void ToDetail(XiaoXiInfo.RecordsBean temp) {
        int mtype = temp.getMtype();
        int bid = temp.getBid();
        //检查通知
        if (mtype == 1) {
            Intent i = new Intent(getContext(), Z_ChuLiJianCha_Activity.class);
            i.putExtra("btype", mtype + "");
            i.putExtra("bid", bid + "");
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getContext().startActivity(i);
        }

        //整改通知
        if (mtype == 2) {
            Intent i = new Intent(getContext(), Z_ZhengGaiChuLi_Activity.class);
            i.putExtra("btype", mtype + "");
            i.putExtra("bid", bid + "");
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getContext().startActivity(i);
        }

        //报警通知
        if (mtype == 3) {
            Intent i = new Intent(getContext(), Z_BaojingChuLi_Activity.class);
            i.putExtra("btype", mtype + "");
            i.putExtra("bid", bid + "");
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getContext().startActivity(i);
        }
    }

    class MyThread extends Thread {//这里也可用Runnable接口实现

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);//每隔1s执行一次
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void apppicQuery(apppicInfo model) {
        TitleTV.setText(model.getTitle());

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.shouyebanner)//图片加载出来前，显示的图片
                .fallback(R.mipmap.shouyebanner) //url为空的时候,显示的图片
                .error(R.mipmap.shouyebanner);//图片加载失败后，显示的图片
        Glide.with(this)
                .load(model.getPicurl()) //图片地址
                .apply(options)
                .into(ShouYeBannerIV);
    }

    @Override
    public void Cityenvironment(CityenvironmentInfo model) {
        cityenvironmentTV.setText(model.getAtmospheric() + "  " + model.getTemperature() + "℃");
        String atmospheric = model.getAtmospheric();
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.tianqi)//图片加载出来前，显示的图片
                .fallback(R.mipmap.tianqi) //url为空的时候,显示的图片
                .error(R.mipmap.tianqi);//图片加载失败后，显示的图片
        Glide.with(this)
                .load(model.getIcon()) //图片地址
                .apply(options)
                .into(cityenvironmentIV);
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
