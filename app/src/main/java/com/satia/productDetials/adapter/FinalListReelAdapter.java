package com.satia.productDetials.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.satia.R;
import com.satia.productDetials.model.FinalProductReelModel;
import com.satia.productDetials.model.OnlyshowModel;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class FinalListReelAdapter extends RecyclerView.Adapter<FinalListReelAdapter.ViewHolder> {
    //ProductSheetDatabase productSheetDatabase;
    private ArrayList<FinalProductReelModel> mData;
    //public List<FinalProductModel> finalModelList;
    private LayoutInflater mInflater;
    private FinalListReelAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public FinalListReelAdapter(Context context, ArrayList<FinalProductReelModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed


    @NonNull
    @Override
    public FinalListReelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ButterKnife.bind(get);

        View view = mInflater.inflate(R.layout.final_screen_reel_row, parent, false);
        ButterKnife.bind(this,view);
        return new FinalListReelAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(FinalListReelAdapter.ViewHolder holder, int position) {
        // Log.e("arraylist",mData.get(position).getQuailty());
        holder.username.setText(mData.get(position).getUser_name());
        holder.quality.setText(mData.get(position).getQuality());


        holder.lot_no.setText(String.valueOf(mData.get(position).getLot_no()));
        holder.gsm.setText(mData.get(position).getGsm());
        holder.realno.setText(mData.get(position).getReal_no());
        holder.size.setText(mData.get(position).getSize());
        holder.netweight.setText(mData.get(position).getNet_weight());
        //holder.p_id.setText(mData.get(position).getProduct_id());




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


        TextView username;TextView quality;TextView lot_no;TextView realno;TextView gsm;
        TextView size;TextView netweight;TextView p_id;



        ViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.user_name);
            quality=itemView.findViewById(R.id.quality);

            lot_no = itemView.findViewById(R.id.lot_no);
            gsm=itemView.findViewById(R.id.gsm);
            size=itemView.findViewById(R.id.size);

            realno=itemView.findViewById(R.id.real_no);
            netweight=itemView.findViewById(R.id.netweight);
            p_id=itemView.findViewById(R.id.prodct_idd);



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
    void setClickListener(FinalListReelAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}