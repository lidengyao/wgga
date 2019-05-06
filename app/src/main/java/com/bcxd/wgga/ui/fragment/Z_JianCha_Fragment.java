package com.bcxd.wgga.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.JianChaAdapter;
import com.bcxd.wgga.adapter.JianChaTypeAdapter;
import com.bcxd.wgga.adapter.TypeDialogAdapter;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.bean.JianChaSearchBean;
import com.bcxd.wgga.model.info.JianChaInfo;
import com.bcxd.wgga.model.info.TypeInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.present.Z_JianCha_Present;
import com.bcxd.wgga.ui.activity.Z_AddJianCha_Activity;
import com.bcxd.wgga.ui.activity.Z_ChuLiJianCha_Activity;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.view.Z_JianCha_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.PullLoadMoreListView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class Z_JianCha_Fragment extends MvpFragment<Z_JianCha_Present> implements Z_JianCha_View {
    @Bind(R.id.JianChaListView)
    PullLoadMoreListView JianChaListView;
    @Bind(R.id.JianChaRL)
    RelativeLayout JianChaRL;
    @Bind(R.id.TypeTV)
    TextView TypeTV;
    @Bind(R.id.TypeRL)
    RelativeLayout TypeRL;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    private int size = 10;
    private int status = 10;//全部数据

    /**
     * 列表数据集合
     */
    ArrayList<JianChaInfo.RecordsBean> _jianChaInfoArrayList;

    private DialogFromBottom DialogFromBottom_QuestionType;
    private View dialogContent_QuestionType;
    JianChaAdapter jianChaAdapter;
    JianChaTypeAdapter jianChaTypeAdapter;
    private ArrayList<String> typeList;

    @Override
    protected Z_JianCha_Present createPresenter() {
        return new Z_JianCha_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_fragment_jiancha;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        JianChaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), Z_ChuLiJianCha_Activity.class);
                JianChaInfo.RecordsBean jianChaInfo = _jianChaInfoArrayList.get(position);
                intent.putExtra("jiancha", jianChaInfo);
                getContext().startActivity(intent);
            }
        });
        JianChaRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addjianchaIntent = new Intent(getContext(), Z_AddJianCha_Activity.class);
                addjianchaIntent.putExtra("type", "1");
                startActivity(addjianchaIntent);
            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialogContent_QuestionType = LayoutInflater.from(getContext()).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_QuestionType = new DialogFromBottom(getContext());
        DialogFromBottom_QuestionType.setContentView(dialogContent_QuestionType);
        TypeRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFromBottom_QuestionType.show();
            }
        });

        final ArrayList<TypeInfo> model = new ArrayList<>();

        TypeInfo typeInfo = new TypeInfo();
        typeInfo.setVname("全部问题");
        typeInfo.setStatus(10);
        model.add(typeInfo);

        TypeInfo typeInfo1 = new TypeInfo();
        typeInfo1.setVname("新问题");
        typeInfo1.setStatus(0);
        model.add(typeInfo1);

        TypeInfo typeInfo2 = new TypeInfo();
        typeInfo2.setVname("已查看");
        typeInfo2.setStatus(1);
        model.add(typeInfo2);

        TypeDialogAdapter typeDialogAdapter = new TypeDialogAdapter(getContext(), model, R.layout.z_item_data);
        final PullLoadMoreListView pullLoadMoreListView = (PullLoadMoreListView) dialogContent_QuestionType.findViewById(R.id.DataListView);
        pullLoadMoreListView.setAdapter(typeDialogAdapter);
        pullLoadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TypeTV.setText(model.get(position).getVname());
                JianChaSearchBean jianChaSearchBean = new JianChaSearchBean();
                pageIndex = 1;
                status = model.get(position).getStatus();
                searchbystatus();
//                jianChaSearchBean.setCurrent(pageIndex);
//                jianChaSearchBean.setSize(size);
//                presenter.searchbystatus(jianChaSearchBean, model.get(position).getStatus());
                DialogFromBottom_QuestionType.dismiss();
            }
        });

        Gson gson = new Gson();
//        String gsonData = AppContext.dbHelper.GetValue(DbKeyS.z_userinfo);
        String gsonData = SpUtils.getSettingNote(getContext(), DbKeyS.z_userinfo);
        Z_UserInfo z_userInfo = gson.fromJson(gsonData, Z_UserInfo.class);

        //权限处理，暂时注释
        if (z_userInfo.getUsertype() != 1) {
            JianChaRL.setVisibility(View.GONE);
        }
        searchbystatus();

        jianChaAdapter = new JianChaAdapter(getActivity(), _jianChaInfoArrayList, R.layout.z_item_jiancha);
        JianChaListView.setAdapter(jianChaAdapter);

        JianChaListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                searchbystatus();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
                pageIndex += 1;
                searchbystatus();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });

    }

    private void searchbystatus() {
        JianChaSearchBean jianChaSearchBean = new JianChaSearchBean();
        jianChaSearchBean.setCurrent(pageIndex);
        jianChaSearchBean.setSize(size);
        presenter.searchbystatus(jianChaSearchBean, status, getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void searchbystatusSuccess(JianChaInfo model) {
        if (model == null)
            return;

        if (this.pageIndex == 1) {
            _jianChaInfoArrayList = model.getRecords();
            jianChaAdapter.refresh(_jianChaInfoArrayList);
        } else {
            _jianChaInfoArrayList.addAll(model.getRecords());
            jianChaAdapter.notifyDataSetChanged();
        }
        JianChaListView.refreshComplete();

        if (model.getRecords().size() < this.size) {
            JianChaListView.loadMoreFinish(false, false);
        } else {
            JianChaListView.loadMoreFinish(false, true);
        }

    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model, getContext());
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("data");
                if ("refresh".equals(msg)) {
                    searchbystatus();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);

    }
}
