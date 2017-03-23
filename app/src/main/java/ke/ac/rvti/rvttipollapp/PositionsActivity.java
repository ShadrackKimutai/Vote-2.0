package ke.ac.rvti.rvttipollapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import static android.R.attr.password;
import static android.R.id.input;

/**
 * Created by Shady on 01-Mar-17.
 */

public class PositionsActivity extends Activity implements View.OnClickListener {

    public static  String Position="";
    private Button[] btn = new Button[15];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positions);

        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0 :
                setFocus(btn_unfocus, btn[0]);
                //Toast.makeText(getApplicationContext(),"chairman",2000).show();


                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","chairman");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }
                btn_unfocus = btn[0];
                break;

            case R.id.btn1 :
                setFocus(btn_unfocus, btn[1]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Vice chairman");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }


            btn_unfocus = btn[1];
                break;

            case R.id.btn2 :
                setFocus(btn_unfocus, btn[2]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","General Secretary");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[2];
                break;

            case R.id.btn3 :
                setFocus(btn_unfocus, btn[3]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Vice General Secretary");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[3];
                break;

            case R.id.btn4:
                setFocus(btn_unfocus, btn[4]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Finance");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[4];
                break;

            case R.id.btn5:
                setFocus(btn_unfocus, btn[5]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Catering");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[5];
                break;
            case R.id.btn6:
                setFocus(btn_unfocus, btn[6]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Vice Catering");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[6];
                break;
            case R.id.btn7:
                setFocus(btn_unfocus, btn[7]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Security and Accomodation");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[7];
                break;
            case R.id.btn8:
                setFocus(btn_unfocus, btn[8]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Education");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[8];
                break;
            case R.id.btn9:
                setFocus(btn_unfocus, btn[9]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Clubs and Societies");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }


                btn_unfocus = btn[9];
                break;
            case R.id.btn10:
                setFocus(btn_unfocus, btn[10]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Health");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[10];
                break;
            case R.id.btn11:
                setFocus(btn_unfocus, btn[11]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Entertainment");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[11];
                break;
            case R.id.btn12:
                setFocus(btn_unfocus, btn[12]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Environment");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[12];
                break;

            case R.id.btn13:
                setFocus(btn_unfocus, btn[13]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","Sports");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[13];
                break;

            case R.id.btn14:
                setFocus(btn_unfocus, btn[14]);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("seat","EAC Representative");

                    Intent intent = new Intent(getApplicationContext(), CandidatesActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // finish();
                }catch (Exception x){
                    System.out.println(x.getMessage());
                }

                btn_unfocus = btn[14];
                break;




        }
    }

    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(53, 52, 215));
    }
}


