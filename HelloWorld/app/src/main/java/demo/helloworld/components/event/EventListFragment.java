package demo.helloworld.components.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.helloworld.R;
import demo.helloworld.data.Constants;
import demo.helloworld.framework.BaseFragment;
import demo.helloworld.http.XUtils.EnginCallBack;
import demo.helloworld.http.XUtils.ServiceEngin;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/8/12.
 */
@ContentView(R.layout.activity_events)
public class EventListFragment extends BaseFragment {
    @ViewInject(R.id.events_list_recylerview)
    private RecyclerView mRecyclerView;
    private List<EventsListBean> list = new ArrayList<>();
    private EventsListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    protected void initUIandEvent() {
        super.initUIandEvent();
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        //设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        mAdapter = new EventsListAdapter(context, list);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new EventsListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });

//        loadDatas();
    }

    private void loadDatas() {
        String url = Constants.TODAY_IN_HISTORY_URL;
        Map<String, Object> map = new HashMap<>();
        map.put("key", Constants.TODAY_IN_HISTORY_APPKEY);
        map.put("v", "1.0");
        map.put("month", "1");
        map.put("day", "1");
        ServiceEngin.Get(url, map, new EnginCallBack<String>(context) {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);
                ParseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                LogUtil.d("onError");
            }
        });
    }

    private void ParseJson(String result) {
        JSONObject obj = JSON.parseObject(result);
        if (obj.get("error_code") != null
                && Integer.parseInt(obj.get("error_code").toString()) == 0) {// 请求成功
            JSONArray array = obj.getJSONArray("result");
            list.addAll(JSON.parseArray(array.toString(), EventsListBean.class));
            mAdapter.notifyDataSetChanged();
        }
    }

}
