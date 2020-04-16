package com.example.fcmjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.iid.FirebaseInstanceId;


import org.json.JSONException;
import org.json.JSONObject;;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText et;
    Button btn;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAfKEvSno:APA91bGaJa_ST6v4svf1IOEagLLDwzeAJGrmLw74jj4A5BZ_Z2dlvbboksMT-BJdMlVcui0_Yuat3rNxcbHHkmz6yPmklz5oa-38Uv1ybHnbftT_OdEs18BJKONw2XqRc-rhmy9L5vZI";
    final private String contentType = "application/json";
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "onCreate: " + token);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = et.getText().toString();
                String token = "eoU1Tgc06Lg:APA91bFUvoKOfK4I-3sKzG6CUezqXP0JiGe2_m1R6jRD63AotBF3_mw_M7jYoCI9CkK82Rz2A9htusZ-VqIAXpEDGlY_V8HhIcWYSnohfcEu_exXZOUAT32c_5CHOvRR83RiI0wMvVuN";
                PostModel postModel = new PostModel();
                Data data = new Data();
                data.setMessage("dummy Message");
                data.setTitle(message);
                postModel.setData(data);
                postModel.setTo(token);

                Retrofit retro = new Retrofit.Builder()
                        .baseUrl("https://fcm.googleapis.com/fcm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retro.create(Api.class);

                Call<PostModel> call = api.sendNotification(postModel);

                call.enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        Log.e(TAG, "onResponse: " + response.body());
                    }
                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });

    }
}







