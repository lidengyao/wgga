package com.bcxd.wgga.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.GongDiAdapter;
import com.bcxd.wgga.adapter.JianKongAdapter;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.ui.activity.Z_GongDiXiangQing_Activity;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.MarkerOverlay;
import com.bcxd.wgga.utils.SpUtils;
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
public class Z_JianKong_Fragment extends MvpFragment implements AMap.OnMapClickListener, AMap.OnMapLoadedListener, AMap.OnMapLongClickListener, AMap.OnMarkerClickListener {
    @Bind(R.id.dituTab)
    LinearLayout dituTab;
    @Bind(R.id.MingChengTab)
    LinearLayout MingChengTab;
    @Bind(R.id.MingChengLL)
    LinearLayout MingChengLL;
    @Bind(R.id.dituRL)
    RelativeLayout dituRL;
    @Bind(R.id.MingChenglanLL)
    LinearLayout MingChenglanLL;
    @Bind(R.id.MingChenghuiLL)
    LinearLayout MingChenghuiLL;
    @Bind(R.id.ditulanLL)
    LinearLayout ditulanLL;
    @Bind(R.id.dituhuiLL)
    LinearLayout dituhuiLL;
    JianKongAdapter jianKongAdapter;
    @Bind(R.id.map)
    MapView mMapView;
    @Bind(R.id.MingChengListView)
    PullLoadMoreListView MingChengListView;
    @Bind(R.id.SouSuoNameET)
    EditText SouSuoNameET;

    private AMap aMap;
    private MarkerOverlay markerOverlay;
    private UiSettings uiSettings;

    private LatLng center;
    private List<LatLng> _pointList;
    private ArrayList<GongDiInfo> gongDiListInfoArrayList;
    private ArrayList<Marker> mMarkers = new ArrayList<Marker>();

    private ArrayList<GongDiInfo> gongDiListInfoArrayList_All;
    GongDiAdapter gongDiAdapter;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_fragment_jiankong;
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
        //获取地图控件引用
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
            uiSettings = aMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
        }
        aMap.setOnMapClickListener(this);// 地图点击监听
        aMap.setOnMapLoadedListener(this); //地图加载完成监听
        aMap.setOnMapLongClickListener(this); // 地图长按监听
        aMap.setOnMarkerClickListener(this);

        Gson gson = new Gson();
//        String gsonData = AppContext.dbHelper.GetValue(DbKeyS.gongdilist);
        String gsonData = SpUtils.getSettingNote(getContext(), DbKeyS.gongdilist);
        if (!gsonData.equals("")) {
            gongDiListInfoArrayList = gson.fromJson(gsonData, new TypeToken<List<GongDiInfo>>() {
            }.getType());

            _pointList = getPointList(gongDiListInfoArrayList);
        }

        dituhuiLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MingChengLL.setVisibility(View.GONE);
                dituRL.setVisibility(View.VISIBLE);

                ditulanLL.setVisibility(View.VISIBLE);
                dituhuiLL.setVisibility(View.GONE);

                MingChenglanLL.setVisibility(View.GONE);
                MingChenghuiLL.setVisibility(View.VISIBLE);
            }
        });

        MingChenghuiLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dituRL.setVisibility(View.GONE);
                MingChengLL.setVisibility(View.VISIBLE);

                ditulanLL.setVisibility(View.GONE);
                dituhuiLL.setVisibility(View.VISIBLE);

                MingChenglanLL.setVisibility(View.VISIBLE);
                MingChenghuiLL.setVisibility(View.GONE);
            }
        });

        gongDiListInfoArrayList_All = gson.fromJson(gsonData, new TypeToken<List<GongDiInfo>>() {
        }.getType());

        gongDiListInfoArrayList = gson.fromJson(gsonData, new TypeToken<List<GongDiInfo>>() {
        }.getType());
        gongDiAdapter = new GongDiAdapter(getActivity(), gongDiListInfoArrayList, R.layout.z_item_gongdi);
        MingChengListView.setAdapter(gongDiAdapter);
        MingChengListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent jianceIntent = new Intent(getContext(), Z_Jiance_Activity.class);
//                jianceIntent.putExtra("gongdi", gongDiListInfoArrayList.get(position));
//                startActivity(jianceIntent);

                GongDiInfo tempGongDi = (GongDiInfo) gongDiListInfoArrayList.get(position);
                Intent intent = new Intent(getContext(), Z_GongDiXiangQing_Activity.class);
                intent.putExtra("gongdi", tempGongDi);
                startActivity(intent);
            }
        });
        MingChengListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
//                pageIndex = 1;
//                findHouseList();
                MingChengListView.refreshComplete();
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
                MingChengListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
        SouSuoNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                gongDiListInfoArrayList.clear();
                for (int i = 0; i < gongDiListInfoArrayList_All.size(); i++) {
                    GongDiInfo tempG = gongDiListInfoArrayList_All.get(i);
                    if (tempG.getSname().contains(SouSuoNameET.getText().toString())) {
                        gongDiListInfoArrayList.add(tempG);
                    }
                }
                gongDiAdapter.notifyDataSetChanged();
            }
        });
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    private List<LatLng> getPointList(ArrayList<GongDiInfo> gongDiListInfoArrayList) {
        List<LatLng> pointList = new ArrayList<LatLng>();
        for (int i = 0; i < gongDiListInfoArrayList.size(); i++) {
            GongDiInfo tempGongdi = gongDiListInfoArrayList.get(i);
            double lat = tempGongdi.getLat();
            double lon = tempGongdi.getLon();
            pointList.add(new LatLng(lat, lon));
        }

        return pointList;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mMapView != null)
            mMapView.onDestroy();
    }

    @Override
    public void onMapLoaded() {
//        double v = 0;
//        double v1 = 0;
//
//        double vTotal = 0;
//        double v1Total = 0;
//        for (int i = 0; i < _pointList.size(); i++) {
//            LatLng tempLatLng = _pointList.get(i);
//            vTotal += tempLatLng.latitude;
//            v1Total += tempLatLng.longitude;
//        }
//
//        v = vTotal / _pointList.size();
//        v1 = v1Total / _pointList.size();
//        center = new LatLng(v, v1);// 中心点
//        markerOverlay = new MarkerOverlay(aMap, _pointList, center, gongDiListInfoArrayList,getContext());
//        markerOverlay.addToMap();
//        markerOverlay.zoomToSpanWithCenter();

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度

        try {
            if (_pointList.size() > 0) {
                for (int i = 0; i < _pointList.size(); i++) {

                    GongDiInfo temp = gongDiListInfoArrayList.get(i);
                    View makerView = View.inflate(getContext(), R.layout.view_marker, null);
                    TextView dingweiName = (TextView) makerView.findViewById(R.id.dingweiName);
                    dingweiName.setText(temp.getSname());

                    Bitmap bitmap = convertViewToBitmap(makerView);

                    Marker marker = aMap.addMarker(new MarkerOptions()
                            .position(_pointList.get(i))
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));

                    marker.setObject(temp);
                    mMarkers.add(marker);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mMarkers.size(); i++) {
            boundsBuilder.include(mMarkers.get(i).getPosition());//把所有点都include进去（LatLng类型）
        }

        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度
    }

    @Override
    public void onMapClick(LatLng latLng) {
//        showMessage(latLng.latitude + "-" + latLng.longitude);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for (int i = 0; i < _pointList.size(); i++) {
            if (marker.getPosition().equals(_pointList.get(i))) {
                if (aMap != null) {
//                    jumpPoint(marker,i);

                    GongDiInfo tempGongDi = (GongDiInfo) marker.getObject();
//                    showMessage(tempGongDi.getSname());
                    Intent intent = new Intent(getContext(), Z_GongDiXiangQing_Activity.class);
                    intent.putExtra("gongdi", tempGongDi);
                    startActivity(intent);
                }
            }
        }
        return false;
    }
}
