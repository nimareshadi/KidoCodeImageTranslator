package com.reshadi.nima.kidocodeimagetranslator.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest.GetLanguagesRequest;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest.TranslateRequest;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.GetLanguagesResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.ParentResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.TranslateResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.TargetLanguage;
import com.reshadi.nima.kidocodeimagetranslator.R;
import com.reshadi.nima.kidocodeimagetranslator.Util.Constans;
import com.reshadi.nima.kidocodeimagetranslator.View.Adapter.TargetLanguageAdapter;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service.GetLanguagesService;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service.ParentService;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service.TranslateService;

import java.util.List;


public class MainActivityViewModel extends ViewModel {

    private DrawerLayout drawerLayout;
    private Spinner targetLanguageSpinner;
    private TextView readedTextView;
    private Context context;
    public MainActivityViewModel(DrawerLayout drawerLayout,Spinner targetLanguageSpinner,TextView readedTextView,Context context) {
        this.drawerLayout = drawerLayout;
        this.targetLanguageSpinner = targetLanguageSpinner;
        this.readedTextView = readedTextView;

        this.context = context;
        getTargetLanguages();
    }
    public void drawerIconClicked(){ drawerLayout.openDrawer(Gravity.LEFT); }

    public void contentCopyClicked(){
        if(readedTextView.getText().length() > 0){
            setClipboard(context,readedTextView.getText().toString());
        }

    }

    public void shareClicked() {
        if (readedTextView.getText().length() > 0) {
            shareText(readedTextView.getText().toString());
        }
    }

    public void saveClicked(){ drawerLayout.openDrawer(Gravity.LEFT); }

    public void translateClicked(){
        TargetLanguageAdapter targetLanguageAdapter = (TargetLanguageAdapter) targetLanguageSpinner.getAdapter();
        if(targetLanguageAdapter != null) {
            if (targetLanguageAdapter.getItems().size() > 0) {
                Constans.selectedLanguage = targetLanguageAdapter.getItem(targetLanguageSpinner.getSelectedItemPosition());
                if (readedTextView.getText().length() > 0) {
                    TranslateService translateService = new TranslateService();
                    translateService.setServiceListener(new ParentService.ServiceListener() {
                        @Override
                        public void onResponse(ParentResponse parentResponse) {
                            TranslateResponse translateResponse = (TranslateResponse) parentResponse;
                            if(translateResponse.getTranslatedTexts().size()>0){
                                readedTextView.setText(translateResponse.getTranslatedTexts().get(0));
                            }
                        }

                        @Override
                        public void onError(ANError error) {

                        }
                    });

                    TranslateRequest translateRequest = new TranslateRequest(readedTextView.getText().toString(), Constans.selectedLanguage.getLanguage(),
                            "en", context.getResources().getString(R.string.api_key));
                    translateService.run(translateRequest);
                }
            }
        }

    }

    private void getTargetLanguages() {
        GetLanguagesService getLanguagesService = new GetLanguagesService();
        getLanguagesService.setServiceListener(new GetLanguagesService.ServiceListener() {
            @Override
            public void onResponse(ParentResponse parentResponse) {
                GetLanguagesResponse getLanguagesResponse = (GetLanguagesResponse) parentResponse;
                TargetLanguageAdapter targetLanguageAdapter = new TargetLanguageAdapter(context, R.layout.target_language_row,getLanguagesResponse.getTargetLanguageList());
                targetLanguageSpinner.setAdapter(targetLanguageAdapter);
            }

            @Override
            public void onError(ANError error) {

            }
        });
        GetLanguagesRequest getLanguagesRequest = new GetLanguagesRequest(context.getResources().getString(R.string.api_key),"en");
        getLanguagesService.run(getLanguagesRequest);
    }


    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,context.getResources().getString(R.string.text_copied),Toast.LENGTH_SHORT).show();
        }
    }

    public void shareText(String body) {
        Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
        txtIntent .setType("text/plain");
        txtIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name));
        txtIntent .putExtra(android.content.Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(txtIntent ,"Share"));
    }
}
