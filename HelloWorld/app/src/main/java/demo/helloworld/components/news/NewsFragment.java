package demo.helloworld.components.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.helloworld.R;
import demo.helloworld.components.event.EventsListBean;
import demo.helloworld.framework.BaseFragment;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/8/16.
 */
public class NewsFragment extends BaseFragment {
    private static final int REFRESH_COMPLETE = 0X110;
    private static final int LOADMORE_COMPLETE = 0X111;

    @BindView(R.id.mRecylerview)
    RecyclerView mRecylerview;
    @BindView(R.id.mSwiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;

    private NewsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int lastVisibleItem;

    private int i, j;

    private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java", "Javascript", "C++", "Ruby"));

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
//                    i++;
//                    mDatas.add(0, "Lucene" + i);
//                    mAdapter.notifyDataSetChanged();
//                    mSwiperefreshlayout.setRefreshing(false);
                    break;
                case LOADMORE_COMPLETE:
                    j++;
                    mDatas.add("Json" + j);
                    mAdapter.notifyDataSetChanged();
                    mSwiperefreshlayout.setRefreshing(false);
                    break;

            }
        }

        ;
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initUIandEvent() {
        super.initUIandEvent();
        mSwiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.green_common), getResources().getColor(R.color.blue),
                getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimaryDark));
        mSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
            }

        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        mRecylerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecylerview.setLayoutManager(mLayoutManager);
        mRecylerview.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(context, mDatas);
        mRecylerview.setAdapter(mAdapter);

        mRecylerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mSwiperefreshlayout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    mHandler.sendEmptyMessageDelayed(LOADMORE_COMPLETE, 2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mSwiperefreshlayout.setEnabled(topRowVerticalPosition >= 0);

            }
        });
    }

}
