package com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service;

import com.androidnetworking.error.ANError;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.ParentResponse;

public class ParentService {
    protected ServiceListener serviceListener;
    public static interface ServiceListener {
        public void onResponse(ParentResponse parentResponse);
        public void onError(ANError error);
    }

    public ServiceListener getServiceListener() {
        return serviceListener;
    }

    public void setServiceListener(ServiceListener serviceListener) {
        this.serviceListener = serviceListener;
    }
}
