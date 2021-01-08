package com.satia.productDetials;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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

import com.satia.productDetials.interfaces.IPProductDetailss;
import com.satia.productDetials.interfaces.IProductDetailss;
import com.satia.productDetials.model.ProductDetailResponseModelReel;
import com.satia.productDetials.model.ProductReelDatabase;
import com.satia.productDetials.model.ProductReelDatabaseModel;
import com.satia.productDetials.model.ProductSheetDatabase;
import com.satia.productDetials.model.ProductSheetDatabaseModel;
import com.satia.productDetials.presenter.PProductDetails;
import com.satia.productDetials.presenter.PProductDetailss;
import com.satia.productDetials.responseModel.LogoutResponseModel;
import com.satia.productDetials.responseModel.ProductStatus;
import com.satia.utils.AppControler;
import com.satia.utils.AppExecutors;
import com.satia.utils.UtilsDialog;
import com.satia.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivityReem extends BaseClass implements IProductDetailss {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgNodata)
    ImageView imgNodata;
    @BindView(R.id.tvTittle)
    TextView tvTittle;


   // @BindView(R.id.layouttvVerified)
   // RelativeLayout layouttvVerified;
    @BindView(R.id.layoutProduct)
    RelativeLayout layoutProduct;

    // second layouts bind
    @BindView(R.id.user_name)
    TextView username;
    @BindView(R.id.tvuser)
    TextView tv_username;

    @BindView(R.id.machinee)
    TextView machinee;
    @BindView(R.id.tvmachinee)
    TextView tvmachinee;
    @BindView(R.id.product)
    TextView product;
    @BindView(R.id.tvproduct)
    TextView tvproduct;

    @BindView(R.id.qualityy)
    TextView qualityy;
    @BindView(R.id.tvqualityy)
    TextView tvqualityy;

    @BindView(R.id.lotnumberr)
    TextView lotno;
    @BindView(R.id.tvlotnor)
    TextView tvlotno;

    @BindView(R.id.gsmm)
    TextView gsmm;
    @BindView(R.id.tvgsmm)
    TextView tvgsmm;

    @BindView(R.id.sizee)
    TextView sizee;
    @BindView(R.id.tvsizee)
    TextView tvsizee;
    @BindView(R.id.realnumber)
    TextView realno;
    @BindView(R.id.tvrealnumber)
    TextView tvrealno;
    @BindView(R.id.netweight)
    TextView netweight;
    @BindView(R.id.tvnetweight)
    TextView tvnetweight;
    @BindView(R.id.dateofman)
    TextView date_of_man;
    @BindView(R.id.tvdateofman)
    TextView tv_date_of_man;

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
    IPProductDetailss ipProductDetails;
    String access_token = "", user_id = "", product_id = "", product_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_product_details_reel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(ProductDetailsActivityReem.this, R.color.colorPrimaryDark));// set status background white
        }
        ButterKnife.bind(this);
        context = ProductDetailsActivityReem.this;
        access_token = AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        user_id = String.valueOf(AppControler.getInstance(context).getInt(AppControler.Key.ID, 0));

        product_id = getIntent().getStringExtra("value");
        ipProductDetails = new PProductDetailss(this);
        initViews();
        if (product_id != null) {
            if (NetworkUtills.isNetworkConnectionAvailable(context)) {
                progressDialog = UtilsDialog.ShowDialog(context);
                //TODO STATIC ID SEND TO SERVER

                    ipProductDetails.productDetailsReel(access_token, product_id);

            }
        } else {
            Toast.makeText(context, "Invalid Product id", Toast.LENGTH_SHORT).show();
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductReelDatabase db = Room.databaseBuilder(getApplicationContext(),
                        ProductReelDatabase.class, "productsReel_db").build();

                try {

                    //ProductSheetDatabase productSheetDatabase=ProductSheetDatabase.getInstance(getApplicationContext());
                    // ProductDetailResponseModel productDetailResponseModel=new ProductDetailResponseModel();

                    SharedPreferences pDetail= getSharedPreferences("reeldata",Context.MODE_PRIVATE);


                   // Log.e("check ples",   pDetail.getString("type",null));


                    ProductReelDatabaseModel productReelDatabaseModel=new ProductReelDatabaseModel(
                            pDetail.getInt("product_id",8),
                            pDetail.getString("user_name",null),
                            pDetail.getString("machine",null),
                            pDetail.getString("product",null),
                            pDetail.getString("quality",null),
                            pDetail.getString("lotno",null),
                            pDetail.getString("gsm",null),
                            pDetail.getString("size",null),
                            pDetail.getString("real_num",null),
                            pDetail.getString("net_weight",null),
                            pDetail.getString("manufacture_date",null),

                            pDetail.getString("product_type",null)
                    );


                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("isinserted","yes??");
                           // db.prodSheetDIo().insertSheetData(productSheetDatabaseModel);
                            db.prodReelDIo().insertReelData(productReelDatabaseModel);
                            // Person person = mDb.personDao().loadPersonById(mPersonId);
                            populateUI();
                        }

                        private void populateUI() {




                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }






                AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailsActivityReem.this, R.style.DialogeTheme);
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

                                Intent i=new Intent(getApplicationContext(),CommonReemActivity.class);
                                startActivity(i);

                                // dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                //finish()



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
                Intent i=new Intent(getApplicationContext(), CommonReemActivity.class);
                startActivity(i);


            }
        });

    }

    private void initViews() {

        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

            username.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            machinee.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            product.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            qualityy.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            lotno.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            gsmm.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            sizee.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            realno.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            netweight.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
            date_of_man.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));


    }



    @Override
    public void successResponseFromPresenterReel(ProductDetailResponseModelReel productDetailResponseModelReel) {
        progressDialog.dismiss();
        if (productDetailResponseModelReel != null) {
            imgNodata.setVisibility(View.GONE);
            layoutProduct.setVisibility(View.VISIBLE);
            String getStatus = productDetailResponseModelReel.getData().getOptions().getGSM();

            if (getStatus !=null) {
                if (getStatus.equalsIgnoreCase("Approved")) {
                    //layouttvVerified.setVisibility(View.VISIBLE);
                   // layouttvVerifiy.setVisibility(View.GONE);
                } else {
                   // layouttvVerified.setVisibility(View.GONE);
                    //layouttvVerifiy.setVisibility(View.VISIBLE);
                }
            }

            SharedPreferences pDetail= getSharedPreferences("reeldata",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=pDetail.edit();
            editor.putInt("product_id",productDetailResponseModelReel.getData().getProductId());
            editor.putString("user_name", productDetailResponseModelReel.getData().getOptions().getUserName());
            editor.putString("machine",productDetailResponseModelReel.getData().getOptions().getMachine());
            editor.putString("product", productDetailResponseModelReel.getData().getOptions().getLotNumber());
            editor.putString("quality",productDetailResponseModelReel.getData().getOptions().getQuality());
            editor.putString("lotno",productDetailResponseModelReel.getData().getOptions().getLotNumber());
            editor.putString("gsm",productDetailResponseModelReel.getData().getOptions().getGSM());
            editor.putString("size",productDetailResponseModelReel.getData().getOptions().getSize());
            editor.putString("real_num",productDetailResponseModelReel.getData().getOptions().getReelNumber());
            editor.putString("net_weight",productDetailResponseModelReel.getData().getOptions().getNetWeight());
            editor.putString("manufacture_date", productDetailResponseModelReel.getData().getOptions().getDateOfManufacture());

            editor.putString("product_type", productDetailResponseModelReel.getData().getOptions().getProduct_type());
            editor.commit();





            if (productDetailResponseModelReel.getData().getOptions().getUserName() != null) {
                tv_username.setText(productDetailResponseModelReel.getData().getOptions().getUserName());
            } else {
                tv_username.setText(R.string.na);
            }
            if (productDetailResponseModelReel.getData().getOptions().getMachine() != null) {
                tvmachinee.setText(productDetailResponseModelReel.getData().getOptions().getMachine());

            } else {
                tvmachinee.setText(R.string.na);
            }
            if (productDetailResponseModelReel.getData().getOptions().getProduct() != null) {
                tvproduct.setText(productDetailResponseModelReel.getData().getOptions().getProduct());

            } else {
                tvproduct.setText(R.string.na);

            }
            if (productDetailResponseModelReel.getData().getOptions().getQuality() != null) {
                tvqualityy.setText(productDetailResponseModelReel.getData().getOptions().getQuality());

            } else {
                tvqualityy.setText(R.string.na);

            }
            if (productDetailResponseModelReel.getData().getOptions().getReelNumber() != null) {
                tvrealno.setText(productDetailResponseModelReel.getData().getOptions().getReelNumber());

            } else {
                tvrealno.setText(R.string.na);

            }

            if (productDetailResponseModelReel.getData().getOptions().getLotNumber() != null) {
                tvlotno.setText(productDetailResponseModelReel.getData().getOptions().getLotNumber());

            } else {
                tvlotno.setText(R.string.na);

            }
            if (productDetailResponseModelReel.getData().getOptions().getGSM() != null) {
                tvgsmm.setText(productDetailResponseModelReel.getData().getOptions().getGSM());

            } else {
                tvgsmm.setText(R.string.na);

            }

            if (productDetailResponseModelReel.getData().getOptions().getSize() != null) {
                tvsizee.setText(productDetailResponseModelReel.getData().getOptions().getSize());

            } else {
                tvsizee.setText(R.string.na);

            }
            if (productDetailResponseModelReel.getData().getOptions().getNetWeight() != null) {
                tvnetweight.setText(productDetailResponseModelReel.getData().getOptions().getNetWeight());

            } else {
                tvnetweight.setText(R.string.na);

            }
            if (productDetailResponseModelReel.getData().getOptions().getDateOfManufacture() != null) {
                tv_date_of_man.setText(productDetailResponseModelReel.getData().getOptions().getDateOfManufacture());

            } else {
                tv_date_of_man.setText(R.string.na);

            }

        } else {
            layoutProduct.setVisibility(View.GONE);
            imgNodata.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void failedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        imgNodata.setVisibility(View.VISIBLE);
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel) {
        progressDialog.dismiss();
        AppControler.getInstance(context).clear();
        Intent intent = new Intent(ProductDetailsActivityReem.this, LoginActivity.class);
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
            /*case R.id.layouttvVerifiy:
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