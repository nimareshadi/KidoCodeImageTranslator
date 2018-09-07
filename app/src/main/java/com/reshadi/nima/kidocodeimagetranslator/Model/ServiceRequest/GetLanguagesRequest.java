package com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest;

public class GetLanguagesRequest {
    private String key;
    private String target;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public GetLanguagesRequest(String key, String target) {
        this.key = key;
        this.target = target;
    }
}
