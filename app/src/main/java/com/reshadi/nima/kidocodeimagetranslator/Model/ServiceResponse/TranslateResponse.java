package com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse;

import com.reshadi.nima.kidocodeimagetranslator.Model.TargetLanguage;

import java.util.List;

public class TranslateResponse extends ParentResponse{
    private List<String> translatedTexts;

    public List<String> getTranslatedTexts() {
        return translatedTexts;
    }

    public void setTranslatedTexts(List<String> translatedTexts) {
        this.translatedTexts = translatedTexts;
    }

    public TranslateResponse(List<String> translatedTexts) {
        this.translatedTexts = translatedTexts;
    }
}
