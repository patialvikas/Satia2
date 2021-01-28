package com.satia.productDetials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.satia.FinalScreenProduct;
import com.satia.R;
import com.satia.SucessActivity;
import com.satia.loginActivity.LoginActivity;
import com.satia.mainActivity.MainActivity;
import com.satia.networks.RetrofitClientInstance;
import com.satia.productDetials.adapter.FinalListAdapter;
import com.satia.productDetials.adapter.FinalListReelAdapter;
import com.satia.productDetials.adapter.MyRecyclerReelViewAdapter;
import com.satia.productDetials.model.FinalProductModel;
import com.satia.productDetials.model.FinalProductReelModel;
import com.satia.productDetials.model.OnlyshowModel;
import com.satia.productDetials.model.OnlyshowReelModel;
import com.satia.productDetials.model.ProductReelDatabase;
import com.satia.productDetials.model.ProductReelDatabaseModel;
import com.satia.productDetials.model.ProductUpdateModel;
import com.satia.utils.AppController2;
import com.satia.utils.AppExecutors;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalScreenReemProduct extends AppCompatActivity {
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;
    @BindView(R.id.scan)
    Button scan;
    @BindView(R.id.finish)
    Button finish;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.imgBack)
    ImageView backbutton;
   // @BindView(R.id.create_pdf)
    //Button create_pdf;
    FinalListReelAdapter adapter;
    ArrayList<FinalProductReelModel> list;
    ArrayList<OnlyshowReelModel>onlyshowReelModelArrayList;
    OnlyshowModel onlyshowModel;
    double tot=0.0;
    ArrayList<Integer>indexlist;
    ArrayList<Integer>uniquelist;
    ProductUpdateModel productUpdateModel;
    private Bitmap bitmap;

    @Override
    protected void onPause() {
        super.onPause();
       // finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("permision", String.valueOf(requestCode));
        if(requestCode==MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do what you want;
                generatePDF(recyclerView);
            }
            else{
                Toast.makeText(getApplicationContext(),"Sorry you denied permision",Toast.LENGTH_LONG).show();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen_reem_product);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        productUpdateModel=new ProductUpdateModel();
        list=new ArrayList<FinalProductReelModel>();
        indexlist=new ArrayList<Integer>();
        uniquelist=new ArrayList<Integer>();
        onlyshowReelModelArrayList=new ArrayList<OnlyshowReelModel>();
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent i = getIntent();
        list = (ArrayList<FinalProductReelModel>) i.getSerializableExtra("LIST");

       // Set<String> store = new HashSet<>();

       /* for (int j=0;j<list.size();j++) {
            if (store.add(list.get(j).getType()) == false) {
                System.out.println("found a duplicate element in array : "
                        + String.valueOf(j));

                indexlist.add(j);
            }
            else{
                System.out.println("found a unique element in array : "
                        + String.valueOf(j));
                uniquelist.add(j);
            }
        }


        for(int p=0;p<uniquelist.size();p++){

            if(!indexlist.isEmpty()&&list.get(uniquelist.get(p)).getType().equals(list.get(indexlist.get(0)).getType())) {
                for (int g = 0; g < indexlist.size(); g++) {
                    tot = tot + Double.parseDouble(list.get(indexlist.get(g)).getWeight().replaceAll("kg",""));
                }
                double d=Double.parseDouble(list.get(uniquelist.get(p)).getWeight().replaceAll("kg",""))+tot;

                onlyshowModel=new OnlyshowModel(
                        list.get(uniquelist.get(p)).getType(),
                        list.get(uniquelist.get(p)).getGsm(),
                        list.get(uniquelist.get(p)).getLot_no(),
                        list.get(uniquelist.get(p)).getSize(),
                        String.valueOf(d)+" kg",
                        list.get(uniquelist.get(p)).getPallet_weight(),
                        list.get(uniquelist.get(p)).getQuality());
                onlyshowModelArrayList.add(onlyshowModel);

            }
            else{
                onlyshowModel=new OnlyshowModel(list.get(uniquelist.get(p)).getType(),list.get(uniquelist.get(p)).getGsm(),
                        list.get(uniquelist.get(p)).getLot_no(),list.get(uniquelist.get(p)).getSize(),
                        list.get(uniquelist.get(p)).getWeight(),list.get(uniquelist.get(p)).getPallet_weight(),
                        list.get(uniquelist.get(p)).getQuality());
                onlyshowModelArrayList.add(onlyshowModel);

            }

        }*/

        //for(int sum=indexlist){}

        adapter = new FinalListReelAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);


       /* create_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bitmap = loadBitmapFromView(recyclerView, recyclerView.getWidth(), recyclerView.getHeight());

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(FinalScreenReemProduct.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(FinalScreenReemProduct.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {

                    //createPdf();
                    generatePDF(recyclerView);
                    // Permission has already been granted
                }
            }
        });  */





backbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                SharedPreferences as=getSharedPreferences("logindata", Context.MODE_PRIVATE);
                String u_id=as.getString("userId","we");
                Gson gson = new Gson();
                String json = gson.toJson(list);
                Log.e("jsonarray",json);
              // String idd= AppController2.getInstance(getApplicationContext()).getString(AppController2.Key.ID);

                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("product", json);
                requestBody.put("user_id", u_id);

                RetrofitClientInstance.getRetrofitInstance().productupdate(requestBody
                ).enqueue(new Callback<ProductUpdateModel>() {
                    @Override
                    public void onResponse(Call<ProductUpdateModel> call, Response<ProductUpdateModel> response) {
                        if(response.body().getStatus()==200) {
                            productUpdateModel = response.body();

                            //Log.e("chooooo", tvGmail.getText().toString());
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("common","incat");
                                    ProductReelDatabase db = Room.databaseBuilder(getApplicationContext(),
                                            ProductReelDatabase.class, "productsReel_db").build();
                                   /// final List<ProductReelDatabaseModel> sheetDatabaseModelList =  db.prodReelDIo().getAll();
                                  ///  adapter=new MyRecyclerReelViewAdapter(getApplicationContext(),sheetDatabaseModelList);

                                   // recyclerView.setAdapter(adapter);
                                    db.prodReelDIo().nukeTable();

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                           /* Log.e("table deleted","yes");
                                            Intent intent = new Intent(FinalScreenReemProduct.this, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);*/

                                            AlertDialog.Builder builder = new AlertDialog.Builder(FinalScreenReemProduct.this);
                                            builder.setTitle("Do you want to Genrate PDF?")
                                                    .setMessage(response.body().getMessage())
                                                    .setIcon(android.R.drawable.btn_dialog)
                                                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int whichButton) {
                                                            Intent it=new Intent(FinalScreenReemProduct.this, SucessActivity.class);
                                                            it.putExtra("openpdf","no");
                                                            //i.putExtra("path",path);
                                                            startActivity(it);
                                                            //finish();
                                                        }

                                                    })
                                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int whichButton) {

                                                           if (ContextCompat.checkSelfPermission(getApplicationContext(),
                                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                                    != PackageManager.PERMISSION_GRANTED) {

                                                                // Permission is not granted
                                                                // Should we show an explanation?
                                                                if (ActivityCompat.shouldShowRequestPermissionRationale(FinalScreenReemProduct.this,
                                                                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                                                    // Show an explanation to the user *asynchronously* -- don't block
                                                                    // this thread waiting for the user's response! After the user
                                                                    // sees the explanation, try again to request the permission.
                                                                } else {
                                                                    Log.e("fhjfg","cvnbjcv");

                                                                    askPermision();
                                                                    // No explanation needed; request the permission
                                                                    /*ActivityCompat.requestPermissions(FinalScreenReemProduct.this,
                                                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                                            MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);*/

                                                                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                                                                    // app-defined int constant. The callback method gets the
                                                                    // result of the request.
                                                                }
                                                            } else {
                                                                generatePDF(recyclerView);
                                                                //createPdf();
                                                                // Permission has already been granted
                                                            }


                                                        }

                                                    }).show();
                                        }
                                    });
                                    // Person person = mDb.personDao().loadPersonById(mPersonId);
                                    //populateUI(person);
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Invalid Login Detail.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductUpdateModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), " Something Went Wrong.",
                                Toast.LENGTH_LONG).show();

                    }


                });



            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
// set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });





    }

    private void askPermision() {
        ActivityCompat.requestPermissions(FinalScreenReemProduct.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
    }

    // set here loading and creating functions
   // exmple bitmap
   public  Bitmap loadBitmapFromView(View v, int width, int height) {
      // Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
       Bitmap b=getScreenshotFromRecyclerView(recyclerView);
       Canvas c = new Canvas(b);
       v.draw(c);

       return b;
   }

    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            // document = new PdfDocument();
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();

            Paint paint = new Paint();
            canvas.drawPaint(paint);

            bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

            paint.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap, 0, 0, null);
            document.finishPage(page);

            // write the document content
            //String targetPdf = "/sdcard/pdffromlayout.pdf";
            //File filePath;
            final String APPLICATION_PACKAGE_NAME = getApplication().getBaseContext().getPackageName();
            File path = new File( Environment.getExternalStorageDirectory(), APPLICATION_PACKAGE_NAME );
            if ( !path.exists() ){ path.mkdir(); }
            File file = new File(path, "Real.pdf");
            //filePath = new File(targetPdf);
            try {
                document.writeTo(new FileOutputStream(file));

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            }

            // close the document
            document.close();
            Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

            openGeneratedPDF(path);

        }else{
            Toast.makeText(getApplicationContext(),"Device Has low api Version",Toast.LENGTH_SHORT).show();

        }

    }


    //open

    // open pdf
    private void openGeneratedPDF(File path){

        File file = new File(path+"/Real.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName()
                    + ".provider", file);
            intent.setDataAndType(uri, "application/pdf");
            Intent chooser= intent.createChooser(intent, "ChooseApp To View Pdf");
            chooser.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            try
            {
                startActivity(chooser);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(FinalScreenReemProduct.this,  "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

// another testing function bigbitmap
public Bitmap getScreenshotFromRecyclerView(RecyclerView view) {
    RecyclerView.Adapter adapter = view.getAdapter();
    Bitmap bigBitmap = null;
    if (adapter != null) {
        int size = adapter.getItemCount();
        int height = 0;
        Paint paint = new Paint();
        int iHeight = 0;
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
        for (int i = 0; i < size; i++) {
            RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
            adapter.onBindViewHolder(holder, i);
            holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
            holder.itemView.setDrawingCacheEnabled(true);
            holder.itemView.buildDrawingCache();
            Bitmap drawingCache = holder.itemView.getDrawingCache();
            if (drawingCache != null) {

                bitmaCache.put(String.valueOf(i), drawingCache);
            }

            height += holder.itemView.getMeasuredHeight();
        }

        bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
        Canvas bigCanvas = new Canvas(bigBitmap);
        bigCanvas.drawColor(Color.WHITE);

        for (int i = 0; i < size; i++) {
            Bitmap bitmap = bitmaCache.get(String.valueOf(i));
            bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
            iHeight += bitmap.getHeight();
            bitmap.recycle();
        }

    }
    return bigBitmap;
}


    public void generatePDF(RecyclerView view) {

        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {

                    bitmaCache.put(String.valueOf(i), drawingCache);
                }

                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            bigCanvas.drawColor(Color.WHITE);

            Document document = new Document(PageSize.A4);
            final String APPLICATION_PACKAGE_NAME = getApplication().getBaseContext().getPackageName();
            File path = new File( Environment.getExternalStorageDirectory(), APPLICATION_PACKAGE_NAME );
            if ( !path.exists() ){ path.mkdir(); }
            File file = new File(path, "Real.pdf");
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file));
            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < size; i++) {

                try {
                    //Adding the content to the document
                    Bitmap bmp = bitmaCache.get(String.valueOf(i));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Image image = Image.getInstance(stream.toByteArray());
                    float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                            - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
                    image.scalePercent(scaler);
                    image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER | com.itextpdf.text.Image.ALIGN_TOP);
                    if (!document.isOpen()) {
                        document.open();
                    }
                    document.add(image);

                } catch (Exception ex) {
                    Log.e("TAG-ORDER PRINT ERROR", ex.getMessage());
                }
            }

            if (document.isOpen()) {
                document.close();
            }
            // Set on UI Thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    AlertDialog.Builder builder = new AlertDialog.Builder(FinalScreenReemProduct.this);
                    builder.setTitle("View Pdf")
                            .setMessage("PDF File Generated Successfully.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent it=new Intent(FinalScreenReemProduct.this,SucessActivity.class);
                                    it.putExtra("openpdf","no");
                                    //i.putExtra("path",path);
                                    startActivity(it);
                                    finish();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {


                                    Intent i=new Intent(FinalScreenReemProduct.this,SucessActivity.class);
                                    i.putExtra("openpdf","yes");
                                    i.putExtra("screen","reem");
                                    i.putExtra("path",path.toString());
                                    startActivity(i);
                                    finish();
                                   /* File file = new File(path+"/Real.pdf");
                                    if (file.exists())
                                    {
                                        Intent intent=new Intent(Intent.ACTION_VIEW);
                                        Uri uri = Uri.fromFile(file);
                                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName()
                                                + ".provider", file);
                                        intent.setDataAndType(uri, "application/pdf");
                                        Intent chooser= intent.createChooser(intent, "ChooseApp To View Pdf");
                                        chooser.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                        try
                                        {
                                            startActivity(chooser);
                                        }
                                        catch(ActivityNotFoundException e)
                                        {
                                            Toast.makeText(FinalScreenReemProduct.this,  "No Application available to view pdf", Toast.LENGTH_LONG).show();
                                        }
                                    }*/
                                }

                            }).show();
                }
            });

        }

    }






}