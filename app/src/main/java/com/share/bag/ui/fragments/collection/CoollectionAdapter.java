package com.share.bag.ui.fragments.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.share.bag.R;
import com.share.bag.SBUrls;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CoollectionAdapter extends RecyclerView.Adapter<CoollectionAdapter.ViewHolder> {
    private Context context;
    private List<CollectionLookBean.InfoBean> list;

    public CoollectionAdapter(Context context, List<CollectionLookBean.InfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CoollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.coollection_item1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoollectionAdapter.ViewHolder holder, final int position) {

        String s = SBUrls.LOGURL + list.get(position).getImg();


        Glide.with(context).load(SBUrls.LOGURL + list.get(position).getImg()).into(holder.collection_adapter_image);
        holder.collection_name.setText(list.get(position).getTitle());
        holder.collection_original.setText(list.get(position).getOriginalprice());


        holder.collection_salebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "购买成功", Toast.LENGTH_SHORT).show();
            }
        });
        holder.collection_rentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "租下成功", Toast.LENGTH_SHORT).show();
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
    public void setCallback(CoollectionAdapter.AdapterCallback callback) {
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

//        private final ImageView recyler_Collection;

        public ImageView collection_adapter_image;
        private final TextView collection_name;
        private final TextView collection_sale;
        private final TextView collection_rent;
        private final TextView  collection_original;
        private final CheckBox  collection_adapter_checkBox;
        private final TextView  collection_rentbutton;
        private final TextView  collection_salebutton;
        public ViewHolder(View itemView) {
            super(itemView);

            /* <CheckBox
           android:id="@+id/collection_adapter_checkBox"
         */

            collection_adapter_checkBox=(CheckBox)   itemView.findViewById(R.id.collection_adapter_checkBox);
            collection_adapter_image = (ImageView) itemView.findViewById(R.id.collection_adapter_image);
            collection_name = (TextView) itemView.findViewById(R.id.collection_name);
            collection_rent = (TextView) itemView.findViewById(R.id.collection_rent);
            collection_sale = (TextView) itemView.findViewById(R.id.collection_sale);
            collection_original = (TextView) itemView.findViewById(R.id.collection_original);
            collection_salebutton = (TextView) itemView.findViewById(R.id.collection_salebutton);
            collection_rentbutton = (TextView) itemView.findViewById(R.id.collection_rentbutton);
        }
    }

}
