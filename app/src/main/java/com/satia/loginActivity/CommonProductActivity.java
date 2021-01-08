package com.satia.loginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.satia.FinalScreenProduct;
import com.satia.R;
import com.satia.loginActivity.viewModel.SimpleDividerItemDecoration;
import com.satia.loginActivity.viewModel.VerticalSpaceItemDecoration;
import com.satia.productDetials.adapter.MyRecyclerViewAdapter;
import com.satia.productDetials.model.FinalProductModel;
import com.satia.productDetials.model.ProductSheetDatabase;
import com.satia.productDetials.model.ProductSheetDatabaseModel;
import com.satia.utils.AppExecutors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class CommonProductActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.button)
    Button next;
    @BindView(R.id.imgBack)
    ImageView backbutton;
    private static final int VERTICAL_ITEM_SPACE = 20;
    MyRecyclerViewAdapter adapter;
    ArrayList<FinalProductModel>finalProductModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_product);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        //or

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
finalProductModelList=new ArrayList<FinalProductModel>();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("common","incat");
                ProductSheetDatabase db = Room.databaseBuilder(getApplicationContext(),
                        ProductSheetDatabase.class, "productsSheetdetail_db").build();
                final List<ProductSheetDatabaseModel> sheetDatabaseModelList =  db.prodSheetDIo().getAll();
                adapter=new MyRecyclerViewAdapter(getApplicationContext(),sheetDatabaseModelList);

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
                ProductSheetDatabase productSheetDatabase=ProductSheetDatabase.getInstance(getApplicationContext());
                final List<ProductSheetDatabaseModel> sheetDatabaseModelList =  productSheetDatabase.prodSheetDIo().getAll();
                runOnUiThread(new Runnable() {
                    @Override

                    public void run() {
                        Log.e("work","yes");
                        adapter = new MyRecyclerViewAdapter(getApplicationContext(), sheetDatabaseModelList);
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

        finalProductModelList= (ArrayList<FinalProductModel>) adapter.getArrayList();
        if(finalProductModelList.size()==0){
            Toast.makeText(getApplicationContext(),"You have check some product to see",Toast.LENGTH_LONG).show();
        }
        else{

        Intent i=new Intent(getApplicationContext(), FinalScreenProduct.class);
            i.putExtra("LIST", (Serializable) finalProductModelList);
        startActivity(i);

        }

    }
});


        
    }
}