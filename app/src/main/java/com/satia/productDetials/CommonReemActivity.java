package com.satia.productDetials;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.satia.FinalScreenProduct;
import com.satia.R;
import com.satia.loginActivity.viewModel.SimpleDividerItemDecoration;
import com.satia.loginActivity.viewModel.VerticalSpaceItemDecoration;
import com.satia.productDetials.adapter.MyRecyclerReelViewAdapter;
import com.satia.productDetials.adapter.MyRecyclerViewAdapter;
import com.satia.productDetials.model.FinalProductModel;
import com.satia.productDetials.model.FinalProductReelModel;
import com.satia.productDetials.model.ProductReelDatabase;
import com.satia.productDetials.model.ProductReelDatabaseModel;
import com.satia.productDetials.model.ProductSheetDatabase;
import com.satia.productDetials.model.ProductSheetDatabaseModel;
import com.satia.utils.AppExecutors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommonReemActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.button)
    Button next;
    private static final int VERTICAL_ITEM_SPACE = 20;
    @BindView(R.id.imgBack)
    ImageView backbutton;
    MyRecyclerReelViewAdapter adapter;
    ArrayList<FinalProductReelModel> finalProductReelModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_reem);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        //or

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        finalProductReelModels=new ArrayList<FinalProductReelModel>();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("common","incat");
                ProductReelDatabase db = Room.databaseBuilder(getApplicationContext(),
                        ProductReelDatabase.class, "productsReel_db").build();
                final List<ProductReelDatabaseModel> sheetDatabaseModelList =  db.prodReelDIo().getAll();
                adapter=new MyRecyclerReelViewAdapter(getApplicationContext(),sheetDatabaseModelList);

                recyclerView.setAdapter(adapter);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("work in ui","gg");
                    }
                });
                // Person person = mDb.personDao().loadPersonById(mPersonId);
                //populateUI(person);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("workes","yes");
                ProductReelDatabase productSheetDatabase=ProductReelDatabase.getInstance(getApplicationContext());
                final List<ProductReelDatabaseModel> sheetDatabaseModelList =  productSheetDatabase.prodReelDIo().getAll();
                runOnUiThread(new Runnable() {
                    @Override

                    public void run() {
                        Log.e("work","yes");
                        adapter = new MyRecyclerReelViewAdapter(getApplicationContext(), sheetDatabaseModelList);
                        recyclerView.setAdapter(adapter);
                    }
                });

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finalProductReelModels= (ArrayList<FinalProductReelModel>) adapter.getArrayList();
                if(finalProductReelModels.size()==0){
                    Toast.makeText(getApplicationContext(),"You have to check some product to see",Toast.LENGTH_LONG).show();
                }
                else{

                    Intent i=new Intent(getApplicationContext(), FinalScreenReemProduct.class);
                    i.putExtra("LIST", (Serializable) finalProductReelModels);
                    startActivity(i);

                }

            }
        });



    }
}