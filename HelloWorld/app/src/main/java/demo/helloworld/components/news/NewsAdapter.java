package demo.helloworld.components.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.helloworld.R;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/8/10.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.EventsViewHolder> {

    private Context context;
    private List<String> list;
    private LayoutInflater mInflater;

    public NewsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EventsViewHolder hodler = new EventsViewHolder(mInflater.inflate(R.layout.events_list_adapter_item_layout, null));
        return hodler;
    }

    @Override
    public void onBindViewHolder(final EventsViewHolder holder, int position) {
        holder.title.setText(list.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView des;

        public EventsViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.event_img);
            title = (TextView) itemView.findViewById(R.id.event_title);
            des = (TextView) itemView.findViewById(R.id.event_des);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
