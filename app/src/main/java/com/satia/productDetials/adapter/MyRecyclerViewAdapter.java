package com.satia.productDetials.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satia.R;
import com.satia.productDetials.model.FinalProductModel;
import com.satia.productDetials.model.ProductSheetDatabase;
import com.satia.productDetials.model.ProductSheetDatabaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    //ProductSheetDatabase productSheetDatabase;
    private List<ProductSheetDatabaseModel> mData;
    public ArrayList<FinalProductModel> finalModelList;
    FinalProductModel finalProductModel;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private final SparseBooleanArray sarray=new SparseBooleanArray();
    java.util.HashMap<Integer,Integer> HashMap=new HashMap<Integer,Integer>();
    int startpoint=0;
    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<ProductSheetDatabaseModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        //this.finalModelList=new List<FinalProductModel>;
    }

    // inflates the row layout from xml when needed


    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // ButterKnife.bind(get);
       // finalModelList=new List<FinalProductModel>;
        finalModelList=new ArrayList<FinalProductModel>();
        View view = mInflater.inflate(R.layout.recyclerview_row_sheet, parent, false);
        ButterKnife.bind(this,view);
        return new MyRecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // Log.e("arraylist",mData.get(position).getQuailty());
        holder.product_id.setText(String.valueOf(mData.get(position).getProduct_id()));
        holder.gsm.setText(mData.get(position).getGSM());
        holder.lotnumber.setText(mData.get(position).getLot_number());
        holder.palletsweight.setText(mData.get(position).getPallet_weight());
        holder.ptype.setText(mData.get(position).getProduct_type());
        holder.reamweight.setText(mData.get(position).getReem_weight());
        holder.quality.setText(mData.get(position).getQuailty());
        holder.size.setText(mData.get(position).getSize());
        holder.type.setText(mData.get(position).getType());

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
            sarray.put(position,true);

            finalProductModel=new FinalProductModel(mData.get(position).getProduct_id(),mData.get(position).getType(),mData.get(position).getProduct_type(),
                    mData.get(position).getGSM(),mData.get(position).getLot_number(),mData.get(position).getReem_weight(),
                    mData.get(position).getSize(),mData.get(position).getQuailty(),mData.get(position).getPallet_weight());

            finalModelList.add(finalProductModel);

            HashMap.put(startpoint,position);
            startpoint++;
        }else{
            sarray.put(position,false);
            if(!finalModelList.isEmpty()){
               // finalProductModel=new FinalProductModel(mData.get(position).getProduct_id(),mData.get(position).getType(),mData.get(position).getProduct_type(),
                       // mData.get(position).getGSM(),mData.get(position).getLot_number(),mData.get(position).getReem_weight());

                //Log.e("Before..",String.valueOf(finalModelList.size()));
                //Log.e("indee",getKey(HashMap,position).toString());
                int y=getKey(HashMap,position);
                finalModelList.remove(y);
                startpoint--;
               // Log.e("After..",String.valueOf(finalModelList.size()));
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
    public List<FinalProductModel> getArrayList(){

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

        CheckBox checkBox;TextView product_id;TextView type;TextView quality;TextView lotnumber;TextView gsm;TextView size;

        TextView reamweight;TextView palletsweight;TextView ptype;

        ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            product_id=itemView.findViewById(R.id.product_id);
            type=itemView.findViewById(R.id.type);
            quality=itemView.findViewById(R.id.quality);
            lotnumber=itemView.findViewById(R.id.lotnumber);
            gsm=itemView.findViewById(R.id.gsm);
            size=itemView.findViewById(R.id.size);
            reamweight=itemView.findViewById(R.id.reamweight);
            palletsweight=itemView.findViewById(R.id.palletsweight);
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
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
