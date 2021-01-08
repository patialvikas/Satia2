package com.satia.productDetials;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.satia.R;
import com.satia.baseClass.BaseClass;
import com.satia.loginActivity.CommonProductActivity;
import com.satia.loginActivity.LoginActivity;
import com.satia.networks.NetworkUtills;
import com.satia.productDetials.interfaces.IPProductDetails;
import com.satia.productDetials.interfaces.IProductDetails;
import com.satia.productDetials.model.ProductSheetDatabase;
import com.satia.productDetials.model.ProductSheetDatabaseModel;
import com.satia.productDetials.presenter.PProductDetails;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductDetailResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;
import com.satia.utils.AppControler;
import com.satia.utils.AppExecutors;
import com.satia.utils.UtilsDialog;
import com.satia.utils.UtilsFontFamily;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseClass implements IProductDetails {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgNodata)
    ImageView imgNodata;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.tvtype)
    TextView tvtype;
    @BindView(R.id.quality)
    TextView quality;
    @BindView(R.id.tvquality)
    TextView tvquality;
    @BindView(R.id.lotnumber)
    TextView lotnumber;
    @BindView(R.id.tvlotnumber)
    TextView tvlotnumber;
    @BindView(R.id.gsm)
    TextView gsm;
    @BindView(R.id.tvgsm)
    TextView tvgsm;
    @BindView(R.id.noofsheet)
    TextView noofsheet;
    @BindView(R.id.tvnoofsheet)
    TextView tvnoofsheet;
    @BindView(R.id.reamweight)
    TextView reamweight;
    @BindView(R.id.tvreamweight)
    TextView tvreamweight;
    @BindView(R.id.noofreamunit)
    TextView noofreamunit;
    @BindView(R.id.tvnoofreamunit)
    TextView tvnoofreamunit;
    @BindView(R.id.unitpallets)
    TextView unitpallets;
    @BindView(R.id.tvunitpallets)
    TextView tvunitpallets;
    @BindView(R.id.palletsweight)
    TextView palletsweight;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.tvsize)
    TextView tvsize;
    @BindView(R.id.tvpalletsweight)
    TextView tvpalletsweight;
   // @BindView(R.id.layouttvVerifiy)
   // RelativeLayout layouttvVerifiy;
    //@BindView(R.id.layouttvVerified)
    //RelativeLayout layouttvVerified;
    @BindView(R.id.layoutProduct)
    RelativeLayout layoutProduct;
   @BindView(R.id.ok)
    Button ok;
   @BindView(R.id.cancel)
    Button cancel;
   @BindView(R.id.next)
   Button next;


    Context context;
    Dialog progressDialog;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    IPProductDetails ipProductDetails;
    String access_token = "", user_id = "", product_id = "", product_status = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_product_details);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(ProductDetailsActivity.this, R.color.colorPrimaryDark));// set status background white
        }
        ButterKnife.bind(this);
        context = ProductDetailsActivity.this;
        access_token = AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        user_id = String.valueOf(AppControler.getInstance(context).getInt(AppControler.Key.ID, 0));

        product_id = getIntent().getStringExtra("value");
        ipProductDetails = new PProductDetails(this);
        initViews();
        if (product_id != null) {
            if (NetworkUtills.isNetworkConnectionAvailable(context)) {
                progressDialog = UtilsDialog.ShowDialog(context);
                //TODO STATIC ID SEND TO SERVER

                ipProductDetails.productDetails(access_token, product_id);

            }
        } else {
            Toast.makeText(context, "Invalid Product id", Toast.LENGTH_SHORT).show();
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductSheetDatabase db = Room.databaseBuilder(getApplicationContext(),
                        ProductSheetDatabase.class, "productsSheetdetail_db").build();

                        try {

                //ProductSheetDatabase productSheetDatabase=ProductSheetDatabase.getInstance(getApplicationContext());
               // ProductDetailResponseModel productDetailResponseModel=new ProductDetailResponseModel();

                SharedPreferences pDetail= getSharedPreferences("sheetdata",Context.MODE_PRIVATE);


                Log.e("check ples",   pDetail.getString("type",null));


                ProductSheetDatabaseModel productSheetDatabaseModel=new ProductSheetDatabaseModel(
                        pDetail.getInt("product_id",8),
                        pDetail.getString("type",null),
                        pDetail.getString("quality",null),
                        pDetail.getString("lotnum",null),
                       pDetail.getString("gsm",null),
                       pDetail.getString("size",null),
                        pDetail.getString("noofsheet",null),
                        pDetail.getString("reamweight",null),
                       pDetail.getString("reamunit",null),
                       pDetail.getString("palletunit",null),
                        pDetail.getString("palletweight",null),
                        pDetail.getString("status",null),
                        pDetail.getString("product_type",null)
                        );


                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("isinserted","yes??");
                                    db.prodSheetDIo().insertSheetData(productSheetDatabaseModel);
                                   // Person person = mDb.personDao().loadPersonById(mPersonId);
                                    populateUI();
                                }

                                private void populateUI() {


                                    //finish();
                                }
                            });


        } catch (Exception e) {
            e.printStackTrace();
        }






                AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailsActivity.this, R.style.DialogeTheme);
                builder.setCancelable(true);
                builder.setMessage("Do you want to scan again?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent i=new Intent(getApplicationContext(),CommonProductActivity.class);
                                startActivity(i);

                                // dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // ProductSheetDatabase productSheetDatabase=ProductSheetDatabase.getInstance(getApplicationContext());
               Intent i=new Intent(getApplicationContext(),CommonProductActivity.class);
               startActivity(i);
            }
        });

    }

    private void initViews() {

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

            type.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            quality.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            lotnumber.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            gsm.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            noofsheet.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            size.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            reamweight.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            noofreamunit.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            unitpallets.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            palletsweight.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));




    }

    @Override
    public void successResponseFromPresenter(ProductDetailResponseModel productDetailResponseModel) {
        progressDialog.dismiss();
        if (productDetailResponseModel != null) {
            imgNodata.setVisibility(View.GONE);
            layoutProduct.setVisibility(View.VISIBLE);
            String getStatus = productDetailResponseModel.getData().getOptions().getStatus();

            if (getStatus != null) {
                if (getStatus.equalsIgnoreCase("Approved")) {
                   // layouttvVerified.setVisibility(View.VISIBLE);
                    //layouttvVerifiy.setVisibility(View.GONE);
                } else {
                    //layouttvVerified.setVisibility(View.GONE);
                    //layouttvVerifiy.setVisibility(View.VISIBLE);
                }
            }

            SharedPreferences pDetail= getSharedPreferences("sheetdata",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=pDetail.edit();
                   editor.putInt("product_id",productDetailResponseModel.getData().getProductId());
                    editor.putString("type", productDetailResponseModel.getData().getOptions().getType());
                    editor.putString("quality",productDetailResponseModel.getData().getOptions().getQuality());
                    editor.putString("lotnum", productDetailResponseModel.getData().getOptions().getLotNumber());
                    editor.putString("gsm",productDetailResponseModel.getData().getOptions().getGSM());
                    editor.putString("size",productDetailResponseModel.getData().getOptions().getSize());
                    editor.putString("noofsheet",productDetailResponseModel.getData().getOptions().getNoOfSheet());
                    editor.putString("reamweight",productDetailResponseModel.getData().getOptions().getReamWeight());
                    editor.putString("reamunit",productDetailResponseModel.getData().getOptions().getNoOfReamUnit());
                    editor.putString("palletunit",productDetailResponseModel.getData().getOptions().getNoOfUnitPallets());
                    editor.putString("palletweight", productDetailResponseModel.getData().getOptions().getPalletWeight());
                    editor.putString("status",productDetailResponseModel.getData().getOptions().getStatus());
                    editor.putString("product_type", productDetailResponseModel.getData().getOptions().getProduct_type());
                    editor.commit();




            if (productDetailResponseModel.getData().getOptions().getType() != null) {
                tvtype.setText(productDetailResponseModel.getData().getOptions().getType());
            } else {
                tvtype.setText(R.string.na);
            }
            if (productDetailResponseModel.getData().getOptions().getQuality() != null) {
                tvquality.setText(productDetailResponseModel.getData().getOptions().getQuality());

            } else {
                tvquality.setText(R.string.na);
            }
            if (productDetailResponseModel.getData().getOptions().getLotNumber() != null) {
                tvlotnumber.setText(productDetailResponseModel.getData().getOptions().getLotNumber());

            } else {
                tvlotnumber.setText(R.string.na);

            }
            if (productDetailResponseModel.getData().getOptions().getGSM() != null) {
                tvgsm.setText(productDetailResponseModel.getData().getOptions().getGSM());

            } else {
                tvgsm.setText(R.string.na);

            }
            if (productDetailResponseModel.getData().getOptions().getNoOfSheet() != null) {
                tvnoofsheet.setText(productDetailResponseModel.getData().getOptions().getNoOfSheet());

            } else {
                tvnoofsheet.setText(R.string.na);

            }

            if (productDetailResponseModel.getData().getOptions().getNoOfSheet() != null) {
                tvsize.setText(productDetailResponseModel.getData().getOptions().getSize());

            } else {
                tvsize.setText(R.string.na);

            }
            if (productDetailResponseModel.getData().getOptions().getReamWeight() != null) {
                tvreamweight.setText(productDetailResponseModel.getData().getOptions().getReamWeight());

            } else {
                tvreamweight.setText(R.string.na);

            }

            if (productDetailResponseModel.getData().getOptions().getNoOfReamUnit() != null) {
                tvnoofreamunit.setText(productDetailResponseModel.getData().getOptions().getNoOfReamUnit());

            } else {
                tvnoofreamunit.setText(R.string.na);

            }
            if (productDetailResponseModel.getData().getOptions().getNoOfUnitPallets() != null) {
                tvunitpallets.setText(productDetailResponseModel.getData().getOptions().getNoOfUnitPallets());

            } else {
                tvunitpallets.setText(R.string.na);

            }
            if (productDetailResponseModel.getData().getOptions().getPalletWeight() != null) {
                tvpalletsweight.setText(productDetailResponseModel.getData().getOptions().getPalletWeight());

            } else {
                tvpalletsweight.setText(R.string.na);

            }

        } else {
            layoutProduct.setVisibility(View.GONE);
            imgNodata.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void failedResponseFromPresenter(String message) {
        Log.e("Check here",message);
        progressDialog.dismiss();
        imgNodata.setVisibility(View.VISIBLE);
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel) {
        progressDialog.dismiss();
        AppControler.getInstance(context).clear();
        Intent intent = new Intent(ProductDetailsActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void logoutFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void productStatusSuccessFromPresenter(ProductStatus productStatus) {
        progressDialog.dismiss();
        recreate();

    }

    @Override
    public void productStatusFailedFromPresenter(String message) {
        progressDialog.dismiss();

    }


    @OnClick({R.id.imgBack, R.id.img_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.img_menu:
                PopupMenu popupMenu = new PopupMenu(this, view);
                getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        logoutAccount();
                        return true;
                    }

                });
                popupMenu.show();
                break;
           /* case R.id.layouttvVerifiy:
                product_status = "yes";
                if (NetworkUtills.isNetworkConnectionAvailable(context)) {
                    progressDialog = UtilsDialog.ShowDialog(context);
                    ipProductDetails.productStatus(access_token, user_id, product_id, product_status);
                }
                break;*/
        }
    }

    private void logoutAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogeTheme);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (NetworkUtills.isNetworkConnectionAvailable(context)) {
                            progressDialog = UtilsDialog.ShowDialog(context);
                            ipProductDetails.logout(access_token, user_id);


                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}