package com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse;

import com.reshadi.nima.kidocodeimagetranslator.Model.TargetLanguage;

import java.util.List;

public class GetLanguagesResponse extends ParentResponse{
    private List<TargetLanguage> targetLanguageList;

    public GetLanguagesResponse(List<TargetLanguage> targetLanguageList) {
        this.targetLanguageList = targetLanguageList;
    }

    public List<TargetLanguage> getTargetLanguageList() {
        return targetLanguageList;
    }

    public void setTargetLanguageList(List<TargetLanguage> targetLanguageList) {
        this.targetLanguageList = targetLanguageList;
    }
}
