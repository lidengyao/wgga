package com.bcxd.wgga.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.ProjectDialogAdapter;
import com.bcxd.wgga.adapter.XiaoXiAdapter;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.bean.MessageBean;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.present.Z_XiaoXi_Present;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.view.Z_XiaoXi_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.PullLoadMoreListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class Z_XiaoXi_Fragment extends MvpFragment<Z_XiaoXi_Present> implements Z_XiaoXi_View {
    @Bind(R.id.XiaoXiListView)
    PullLoadMoreListView XiaoXiListView;

    XiaoXiAdapter xiaoXiAdapter;
    @Bind(R.id.xiaoxiSpinner)
    Spinner xiaoxiSpinner;
    @Bind(R.id.mainTitleTV)
    TextView mainTitleTV;
    @Bind(R.id.xiugaiRL)
    RelativeLayout xiugaiRL;
    @Bind(R.id.jiankongTopLL)
    RelativeLayout jiankongTopLL;
    @Bind(R.id.gongdiNextIV)
    ImageView gongdiNextIV;
    @Bind(R.id.GongDiTV)
    TextView GongDiTV;
    @Bind(R.id.GongDiRL)
    RelativeLayout GongDiRL;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    private int pageSize = 10;
    private int sid = -1;

    private ArrayList<ProjectInfo> projectInfoArrayList = new ArrayList<>();
    private GongDiInfo currentGongDiInfo;
    private DialogFromBottom Dialog_Project;
    private View Content_Project;
    private ArrayList<XiaoXiInfo.RecordsBean> recordsBeanArrayList;


    @Override
    protected Z_XiaoXi_Present createPresenter() {
        return new Z_XiaoXi_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_fragment_xiaoxi;
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
        GongDiRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Project.show();
            }
        });

//        XiaoXiListView.autoRefresh();
        xiaoXiAdapter = new XiaoXiAdapter(getActivity(), recordsBeanArrayList, R.layout.z_item_xiaoxi);
        XiaoXiListView.setAdapter(xiaoXiAdapter);

        XiaoXiListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                if (sid == -1) {
                    getMsgList();
                } else {
                    getMsgByIdList();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
                pageIndex += 1;
                if (sid == -1) {
                    getMsgList();
                } else {
                    getMsgByIdList();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
        ProjectList();
        getMsgList();
    }

    private void getMsgList() {
        MessageBean messageBean = new MessageBean();
        messageBean.setCurrent(pageIndex);
        messageBean.setSize(pageSize);
        presenter.list(messageBean, getContext());
    }

    private void getMsgByIdList() {
        MessageBean messageBean = new MessageBean();
        messageBean.setCurrent(pageIndex);
        messageBean.setSize(pageSize);
        presenter.listbysid(messageBean, sid + "", getContext());
    }

    private void ProjectList() {

        Content_Project = LayoutInflater.from(getContext()).inflate(R.layout.z_dialog_list, null, false);
        Dialog_Project = new DialogFromBottom(getContext());
        Dialog_Project.setContentView(Content_Project);

        //添加全部消息字段到列表顶部
        projectInfoArrayList = new ArrayList<>();
        ProjectInfo quanbuD = new ProjectInfo();
        quanbuD.setPname("全部消息");
        projectInfoArrayList.add(quanbuD);

        Gson gson = new Gson();
//        String gsonData = AppContext.dbHelper.GetValue(DbKeyS.ProjectInfo);
        String gsonData = SpUtils.getSettingNote(getContext(), DbKeyS.ProjectInfo);
        if (!gsonData.equals("")) {
            ArrayList<ProjectInfo> tempGList = gson.fromJson(gsonData, new TypeToken<List<ProjectInfo>>() {
            }.getType());
            projectInfoArrayList.addAll(tempGList);

            ProjectDialogAdapter projectDialogAdapter = new ProjectDialogAdapter(getContext(), projectInfoArrayList, R.layout.z_item_data);
            final PullLoadMoreListView pullLoadMoreListView = (PullLoadMoreListView) Content_Project.findViewById(R.id.DataListView);
            pullLoadMoreListView.setAdapter(projectDialogAdapter);

            pullLoadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    pageIndex = 1;
                    if (position == 0) {
                        sid = -1;
                        getMsgList();
                    } else {
                        sid = projectInfoArrayList.get(position).getPid();

                        getMsgByIdList();
                    }
                    GongDiTV.setText(projectInfoArrayList.get(position).getPname());
                    Dialog_Project.dismiss();
                }
            });
        }
//


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void BandData(XiaoXiInfo model) {
        if (model == null)
            return;

        if (this.pageIndex == 1) {
            recordsBeanArrayList = model.getRecords();
            xiaoXiAdapter.refresh(recordsBeanArrayList);
        } else {
            recordsBeanArrayList.addAll(model.getRecords());
            xiaoXiAdapter.notifyDataSetChanged();
        }
        XiaoXiListView.refreshComplete();

        if (model.getRecords().size() < this.pageSize) {
            XiaoXiListView.loadMoreFinish(false, false);
        } else {
            XiaoXiListView.loadMoreFinish(false, true);
        }

    }

    @Override
    public void listSuccess(XiaoXiInfo model) {
        BandData(model);
    }

    @Override
    public void listBySidSuccess(XiaoXiInfo model) {
        BandData(model);
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
