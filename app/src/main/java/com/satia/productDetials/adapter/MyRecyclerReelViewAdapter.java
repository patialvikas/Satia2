package com.satia.productDetials.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satia.R;
import com.satia.productDetials.model.FinalProductModel;
import com.satia.productDetials.model.FinalProductReelModel;
import com.satia.productDetials.model.ProductReelDatabaseModel;
import com.satia.productDetials.model.ProductSheetDatabaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class MyRecyclerReelViewAdapter extends RecyclerView.Adapter<MyRecyclerReelViewAdapter.ViewHolder> {
    //ProductSheetDatabase productSheetDatabase;
    private List<ProductReelDatabaseModel> mData;
    public ArrayList<FinalProductReelModel> finalModelList;
    FinalProductReelModel finalProductModel;
    private LayoutInflater mInflater;
    private MyRecyclerReelViewAdapter.ItemClickListener mClickListener;
    private final SparseBooleanArray sarray=new SparseBooleanArray();
    java.util.HashMap<Integer,Integer> HashMap=new HashMap<Integer,Integer>();
    int startpoint=0;
    // data is passed into the constructor
    public MyRecyclerReelViewAdapter(Context context, List<ProductReelDatabaseModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        //this.finalModelList=new List<FinalProductModel>;
    }

    // inflates the row layout from xml when needed


    @NonNull
    @Override
    public MyRecyclerReelViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ButterKnife.bind(get);
        // finalModelList=new List<FinalProductModel>;
        finalModelList=new ArrayList<FinalProductReelModel>();
        View view = mInflater.inflate(R.layout.recyclerview_row_reel, parent, false);
        ButterKnife.bind(this,view);
        return new MyRecyclerReelViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MyRecyclerReelViewAdapter.ViewHolder holder, int position) {
        // Log.e("arraylist",mData.get(position).getQuailty());
        //final ObjectIncome objIncome = myItems.get(position);
        holder.product_id.setText(String.valueOf(mData.get(position).getProduct_id()));
        holder.gsm.setText(mData.get(position).getGSM());
        holder.lotnumber.setText(mData.get(position).getLot_number());
        holder.netweight.setText(mData.get(position).getNet_weight());
        holder.ptype.setText(mData.get(position).getProduct_type());
        holder.reamno.setText(mData.get(position).getReal_num());
        holder.quality.setText(mData.get(position).getQuailty());
        holder.size.setText(mData.get(position).getSize());
        holder.username.setText(mData.get(position).getUser_name());

       // holder.checkBox.setChecked(mData.get(position).isSelected());
        if(sarray.get(position)){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if(holder.checkBox.isChecked()){
                   // mData.get(pos).setSelected(false);
                    sarray.put(position,true);
                    finalProductModel=new FinalProductReelModel(mData.get(position).getProduct_id(),mData.get(position).getUser_name(),mData.get(position).getProduct_type(),
                            mData.get(position).getGSM(),mData.get(position).getLot_number(),mData.get(position).getReal_num(),
                            mData.get(position).getSize(),mData.get(position).getQuailty(),mData.get(position).getNet_weight());

                    finalModelList.add(finalProductModel);

                    HashMap.put(startpoint,position);
                    startpoint++;
                }else{
                    sarray.put(position,false);
                   // mData.get(pos).setSelected(true);
                    if(!finalModelList.isEmpty()){
                        // finalProductModel=new FinalProductModel(mData.get(position).getProduct_id(),mData.get(position).getType(),mData.get(position).getProduct_type(),
                        // mData.get(position).getGSM(),mData.get(position).getLot_number(),mData.get(position).getReem_weight());

                        int y=getKey(HashMap,position);
                        finalModelList.remove(y);
                        startpoint--;
                        //finalModelList.r
                    }
                }
            }
        });


   /*     holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    finalModelList.add(new FinalProductModel(mData.get(position).getProduct_id(),mData.get(position).getProduct_type(),
                            mData.get(position).getGSM(),mData.get(position).getLot_number(),mData.get(position).getReem_weight() ));
                }
                else{
                    finalModelList.remove(new FinalProductModel(mData.get(position).getProduct_id(),mData.get(position).getProduct_type(),
                            mData.get(position).getGSM(),mData.get(position).getLot_number(),mData.get(position).getReem_weight()));
                }
            }
        });*/
        //String animal = mData.get(position);
        // holder.myTextView.setText(animal);
    }
    public List<FinalProductReelModel> getArrayList(){

        return finalModelList;
    }
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckBox checkBox;
        TextView product_id;TextView username;TextView quality;TextView lotnumber;TextView gsm;TextView size;

        TextView reamno;TextView netweight;TextView ptype;

        ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            product_id=itemView.findViewById(R.id.product_id);
            username=itemView.findViewById(R.id.user_name);
            quality=itemView.findViewById(R.id.quality);
            lotnumber=itemView.findViewById(R.id.lotnumber);
            gsm=itemView.findViewById(R.id.gsm);
            size=itemView.findViewById(R.id.size);
            reamno=itemView.findViewById(R.id.reamno);
            netweight=itemView.findViewById(R.id.netweight);
            ptype=itemView.findViewById(R.id.ptype);
            //finalModelList=new List<FinalProductModel>();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
   /* String getItem(int id) {
        return mData.get(id);
    }*/

    // allows clicks events to be caught
    void setClickListener(MyRecyclerReelViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
