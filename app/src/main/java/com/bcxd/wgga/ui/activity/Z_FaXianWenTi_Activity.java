package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.JianChaAdapter;
import com.bcxd.wgga.adapter.JianChaTypeAdapter;
import com.bcxd.wgga.adapter.TypeDialogAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.JianChaSearchBean;
import com.bcxd.wgga.model.info.JianChaInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.TypeInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.present.Z_FaXianWenTi_Present;
import com.bcxd.wgga.ui.view.Z_FaXianWenTi_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.PullLoadMoreListView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_FaXianWenTi_Activity extends MvpActivity<Z_FaXianWenTi_Present> implements Z_FaXianWenTi_View {


    @Bind(R.id.XmTitleTV)
    TextView XmTitleTV;
    @Bind(R.id.wentiNextIV)
    ImageView wentiNextIV;
    @Bind(R.id.TypeTV)
    TextView TypeTV;
    @Bind(R.id.TypeRL)
    RelativeLayout TypeRL;
    @Bind(R.id.JianChaListView)
    PullLoadMoreListView JianChaListView;
    @Bind(R.id.JianChaRL)
    RelativeLayout JianChaRL;
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
    private ProjectInfo _projectInfo;

    @Override
    protected Z_FaXianWenTi_Present createPresenter() {
        return new Z_FaXianWenTi_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_faxianwenti;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        _projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");

        dialogContent_QuestionType = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_QuestionType = new DialogFromBottom(this);
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
        typeInfo1.setVname("待处理");
        typeInfo1.setStatus(0);
        model.add(typeInfo1);

        TypeInfo typeInfo2 = new TypeInfo();
        typeInfo2.setVname("已处理");
        typeInfo2.setStatus(1);
        model.add(typeInfo2);

        TypeDialogAdapter typeDialogAdapter = new TypeDialogAdapter(this, model, R.layout.z_item_data);
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
        String gsonData = SpUtils.getSettingNote(Z_FaXianWenTi_Activity.this, DbKeyS.z_userinfo);
        Z_UserInfo z_userInfo = gson.fromJson(gsonData, Z_UserInfo.class);

        //权限处理，暂时注释
        if (z_userInfo.getUsertype() != 1) {
            JianChaRL.setVisibility(View.GONE);
        }
        searchbystatus();

        jianChaAdapter = new JianChaAdapter(this, _jianChaInfoArrayList, R.layout.z_item_jiancha);
        JianChaListView.setAdapter(jianChaAdapter);
        JianChaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Z_FaXianWenTi_Activity.this, Z_ChuLiJianCha_Activity.class);
                JianChaInfo.RecordsBean jianChaInfo = _jianChaInfoArrayList.get(position);
                intent.putExtra("jiancha", jianChaInfo);
                intent.putExtra("btype", -1 + "");
                startActivity(intent);
            }
        });
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

        JianChaRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addjianchaIntent = new Intent(Z_FaXianWenTi_Activity.this, Z_AddJianCha_Activity.class);
                addjianchaIntent.putExtra("type", "1");
                addjianchaIntent.putExtra("ProjectInfo", _projectInfo);
                startActivity(addjianchaIntent);
            }
        });
    }

    private void searchbystatus() {
        JianChaSearchBean jianChaSearchBean = new JianChaSearchBean();
        jianChaSearchBean.setCurrent(pageIndex);
        jianChaSearchBean.setSize(size);
        jianChaSearchBean.setPid(_projectInfo.getPid());
        mPresenter.searchbystatus(jianChaSearchBean, status, Z_FaXianWenTi_Activity.this);
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
    protected void onResume() {
        super.onResume();
        searchbystatus();
    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {

        if (code == 499) {
            mPresenter.refreshToken(Z_FaXianWenTi_Activity.this);
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
