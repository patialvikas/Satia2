package com.satia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class SucessActivity extends AppCompatActivity {
File path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent i = getIntent();
        String isopen = i.getStringExtra("openpdf");

        if (isopen.matches("yes")) {
            Log.e("yes",i.getStringExtra("screen"));
            if(i.getStringExtra("screen").matches("reem")){
                path= new File(i.getStringExtra("path"));
                File filee = new File(path + "/Real.pdf");
                if (filee.exists()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.fromFile(filee);
                    Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName()
                            + ".provider", filee);
                    intent.setDataAndType(uri, "application/pdf");
                    Intent chooser = intent.createChooser(intent, "ChooseApp To View Pdf");
                    chooser.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    //chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    try {
                        startActivity(chooser);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(SucessActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
                    }
                }
            }
            else {
                path = new File(i.getStringExtra("path"));
                File file = new File(path + "/Sheet.pdf");
                if (file.exists()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.fromFile(file);
                    Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName()
                            + ".provider", file);
                    intent.setDataAndType(uri, "application/pdf");
                    Intent chooser = intent.createChooser(intent, "ChooseApp To View Pdf");
                    chooser.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    try {
                        startActivity(chooser);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(SucessActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
                    }
                }
            }

        }



    }


}