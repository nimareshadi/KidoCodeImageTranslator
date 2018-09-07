package com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service;

import android.provider.Contacts;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest.GetLanguagesRequest;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.GetLanguagesResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.ParentResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.TargetLanguage;
import com.reshadi.nima.kidocodeimagetranslator.Util.Constans;
import com.reshadi.nima.kidocodeimagetranslator.View.Activity.ParentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetLanguagesService extends ParentService {

    private String url = "https://translation.googleapis.com/language/translate/v2/languages";

    public void run(GetLanguagesRequest getLanguagesRequest){
        AndroidNetworking.post(url)
                .addBodyParameter("key",getLanguagesRequest.getKey())
                .addBodyParameter("target",getLanguagesRequest.getTarget())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        List<TargetLanguage> targetLanguageList = new ArrayList<>();
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray tempResultJsonArray = jsonObject.getJSONArray("languages");
                            for(int i = 0; i < tempResultJsonArray.length();i++){
                                TargetLanguage targetLanguage = new TargetLanguage();
                                targetLanguage.setLanguage(tempResultJsonArray.getJSONObject(i).getString("language"));
                                targetLanguage.setName(tempResultJsonArray.getJSONObject(i).getString("name"));
                                targetLanguageList.add(targetLanguage);
                            }
                            GetLanguagesResponse getLanguagesResponse = new GetLanguagesResponse(targetLanguageList);
                            serviceListener.onResponse(getLanguagesResponse);
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
