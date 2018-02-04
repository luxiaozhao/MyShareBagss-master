package com.share.bag.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.share.bag.R;
import com.share.bag.SBUrls;
import com.share.bag.entity.CollectionBean;
import com.share.bag.entity.selected.SelectedBean;
import com.share.bag.utils.okhttp.OkHttpUtils;
import com.share.bag.utils.okhttp.callback.MyNetWorkCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/29.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private Context context;
    private List<SelectedBean> list;

    public PopularAdapter(Context context, List<SelectedBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.recy_item1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(SBUrls.LOGURL + list.get(position).getImg()).into(holder.recyler_commodity);

        holder.recyler_name.setText(list.get(position).getTitle());
        holder.recyler_price.setText(list.get(position).getDays_money());
        holder.recyler_money.setText(list.get(position).getOriginalprice());
        holder.recyler_commodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onitemlistener.Back(view, position);
            }
        });

        if (list.get(position).getIslive().equals("false")) {
            holder.recyler_Collection.setImageResource(R.mipmap.shoucang1);
        } else {
            holder.recyler_Collection.setImageResource(R.mipmap.shoucanghong1);
        }
        holder.recyler_Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String ,String> collection=new HashMap();
                collection.put("baglist_id",position+"");
                OkHttpUtils.getInstance().post(SBUrls.COLLECTION, collection, new MyNetWorkCallback<CollectionBean>() {
                    @Override
                    public void onSuccess(CollectionBean collectionBean) {
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                    }
                });


                if (list.get(position).getIslive().equals("false")) {
                    list.get(position).setIslive("true");
                } else {
                    list.get(position).setIslive("false");
                }
                notifyDataSetChanged();
            }
        });



    }

    public interface AdapterCallback {
        public void callBack(View v, int position);
    }

    private AdapterCallback callback;

    /**
     * 通过该方法连接起来
     **/
    public void setCallback(AdapterCallback callback) {
        this.callback = callback;
    }

    private OnitemClickedListener onitemlistener;

    public void setOnitemClickedListener(OnitemClickedListener Listener) {
        this.onitemlistener = Listener;

    }

    public interface OnitemClickedListener {
        public void Back(View v, int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView recyler_commodity;
        private final ImageView recyler_Collection;
        private final TextView recyler_name;
        private final TextView recyler_price;
        private final TextView recyler_money;

        public ViewHolder(View itemView) {
            super(itemView);
            recyler_commodity = (ImageView) itemView.findViewById(R.id.recyler_commodity);
            recyler_Collection = (ImageView) itemView.findViewById(R.id.recyler_Collection);
            recyler_name = (TextView) itemView.findViewById(R.id.recyler_name);
            recyler_price = (TextView) itemView.findViewById(R.id.recyler_price);
            recyler_money = (TextView) itemView.findViewById(R.id.recyler_money);

        }
    }

}
