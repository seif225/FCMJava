package com.example.fcmjava;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAfKEvSno:APA91bGaJa_ST6v4svf1IOEagLLDwzeAJGrmLw74jj4A5BZ_Z2dlvbboksMT-BJdMlVcui0_Yuat3rNxcbHHkmz6yPmklz5oa-38Uv1ybHnbftT_OdEs18BJKONw2XqRc-rhmy9L5vZI"
    })
    @POST("send")
    Call<ResponseBody> sendNotification(
            @Field("token") String token,
            @Field("title") String title,
            @Field("body") String body
    );

}
