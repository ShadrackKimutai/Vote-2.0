package ke.ac.rvti.rvttipollapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;
import ke.ac.rvti.rvttipollapp.util.Util;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private Boolean flag;
    private Boolean registered = false;
    private String password = "";
    private Context context;
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Button btnStart = (Button) findViewById(R.id.startButton);
        btnStart.setOnClickListener(new View.OnClickListener() {
            //Context

            public void onClick(View v) {
                progressBar = new ProgressDialog(context);
                progressBar.setCancelable(false);
                progressBar.setMessage("Authenticating ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                progressBarStatus = 0;

                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {

                             imei = getImei();


                            try {
                                HttpClient client = new DefaultHttpClient();
                                String postURL = "http://192.168.10.1/m-vote/index.php";
                                HttpPost post = new HttpPost(postURL);
                                List<NameValuePair> params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("imei", imei));

                                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                                post.setEntity(ent);
                                HttpResponse responsePOST = client.execute(post);
                                HttpEntity resEntity = responsePOST.getEntity();
                                Thread.sleep(4000);
                                if (resEntity != null) {
                                    String Result = EntityUtils.toString(resEntity);
                                    Result = Result.trim();
                                    // System.out.println("--->"+Result);
                                    //System.out.println("")
                                    if (Result.equals("0")) {
                                        flag = false;
                                        progressBarStatus = 100;
                                    } else if (Result.equals("1")) {
                                        flag = true;
                                        progressBarStatus = 100;

                                    }

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            progressBarbHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }


                        progressBar.dismiss();


                        System.out.println("Flag-->" + flag.toString());
                        if (flag.equals(false)) {
                            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            if (Looper.myLooper() == null) {
                                Looper.prepare();
                            }
                            Handler handle = new Handler();
                            final String[] x = new String[1];
                            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                            View view = layoutInflater.inflate(R.layout.dialog_prompt, null);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setView(view);

                            final EditText userInput = (EditText) view.findViewById(R.id.input_password_main);
                            alertDialogBuilder.setCancelable(false).setPositiveButton("Login",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            password = userInput.getText().toString();
                                            System.out.println(password);
                                            System.out.println(imei);
                                            String digestPassword= Util.encryptPassword(password);
                                            System.out.println(digestPassword);

                                            if(Util.checkExist(imei,digestPassword)==true){
                                                Intent i = new Intent(getApplicationContext(), PositionsActivity.class);
                                                startActivity(i);
                                                finish();
                                            }else{

                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle("Access Denied")
                                                        .setMessage("Wrong Password. Try again ")
                                                        .setCancelable(false)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        })
                                                        .setIcon(android.R.drawable.stat_notify_error)
                                                        .show();

                                            }
                                        }
                                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });


                            AlertDialog ald = alertDialogBuilder.create();
                            ald.show();


                            Looper.loop();

                        }
                    }


                }).start();
            }
        });


    }


    public String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimSerialNumber();
    }


}
