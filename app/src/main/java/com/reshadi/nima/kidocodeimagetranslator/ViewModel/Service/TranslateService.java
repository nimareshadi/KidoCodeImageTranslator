package com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest.TranslateRequest;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.TranslateResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TranslateService extends ParentService {

    private String url = "https://translation.googleapis.com/language/translate/v2";
    public void run(TranslateRequest translateRequest){
        AndroidNetworking.post(url)
                .addBodyParameter("key",translateRequest.getKey())
                .addBodyParameter("target",translateRequest.getTarget())
                .addBodyParameter("q",translateRequest.getQ())
                .addBodyParameter("source",translateRequest.getSource())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        List<String> translatedTexts = new ArrayList<>();
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray tempResultJsonArray = jsonObject.getJSONArray("translations");
                            for(int i = 0; i < tempResultJsonArray.length();i++){

                                translatedTexts.add(tempResultJsonArray.getJSONObject(i).getString("translatedText"));
                            }
                            TranslateResponse translateResponse = new TranslateResponse(translatedTexts);
                            serviceListener.onResponse(translateResponse);
                        } catch (JSONException e) {
                            serviceListener.onError(null);
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        serviceListener.onError(error);
                    }
                });
    }


}
