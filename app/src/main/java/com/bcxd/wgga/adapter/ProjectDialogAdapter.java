package com.bcxd.wgga.adapter;

import android.content.Context;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.ProjectInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class ProjectDialogAdapter extends CommonAdapter<ProjectInfo> {

    public ProjectDialogAdapter(Context context, List<ProjectInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, ProjectInfo item) {
        helper.setText(R.id.ContentTV, item.getPname());
    }
}
