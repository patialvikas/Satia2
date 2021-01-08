package com.satia.productDetials.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satia.R;
import com.satia.productDetials.model.FinalProductModel;
import com.satia.productDetials.model.OnlyshowModel;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class FinalListAdapter extends RecyclerView.Adapter<FinalListAdapter.ViewHolder> {
    //ProductSheetDatabase productSheetDatabase;
    private ArrayList<OnlyshowModel> mData;
    //public List<FinalProductModel> finalModelList;
    private LayoutInflater mInflater;
    private FinalListAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public FinalListAdapter(Context context, ArrayList<OnlyshowModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed


    @NonNull
    @Override
    public FinalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ButterKnife.bind(get);

        View view = mInflater.inflate(R.layout.final_screen_row, parent, false);
        ButterKnife.bind(this,view);
        return new FinalListAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(FinalListAdapter.ViewHolder holder, int position) {
        // Log.e("arraylist",mData.get(position).getQuailty());
        holder.type.setText(mData.get(position).getType());
        holder.quality.setText(mData.get(position).getQuality());


        holder.real_no.setText(String.valueOf(mData.get(position).getLot_no()));
        holder.gsm.setText(mData.get(position).getGsm());
        holder.weight.setText(mData.get(position).getWeight());
        holder.size.setText(mData.get(position).getSize());
        holder.palletweight.setText(mData.get(position).getPallet_weight());



        //String animal = mData.get(position);
        // holder.myTextView.setText(animal);
    }
   /* public List<FinalProductModel> getArrayList(){
        return finalModelList;
    }*/
    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView type;TextView quality;TextView real_no;TextView weight;TextView gsm;
        TextView size;TextView palletweight;



        ViewHolder(View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            quality=itemView.findViewById(R.id.quality);

            real_no = itemView.findViewById(R.id.real_no);
            gsm=itemView.findViewById(R.id.gsm);
            size=itemView.findViewById(R.id.size);

            weight=itemView.findViewById(R.id.weight);
            palletweight=itemView.findViewById(R.id.palletweight);



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
    void setClickListener(FinalListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
