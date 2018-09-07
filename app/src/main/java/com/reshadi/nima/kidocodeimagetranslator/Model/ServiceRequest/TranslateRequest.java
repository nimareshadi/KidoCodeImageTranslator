package com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest;

public class TranslateRequest {

    private String q;
    private String target;
    private String source;
    private String key;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TranslateRequest(String q, String target, String source, String key) {
        this.q = q;
        this.target = target;
        this.source = source;
        this.key = key;
    }
}
