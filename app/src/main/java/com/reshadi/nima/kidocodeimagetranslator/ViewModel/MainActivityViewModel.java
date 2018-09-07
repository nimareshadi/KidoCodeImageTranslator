package com.reshadi.nima.kidocodeimagetranslator.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.reshadi.nima.kidocodeimagetranslator.Model.Database.Initializer.DBIntializer;
import com.reshadi.nima.kidocodeimagetranslator.Model.Database.Model.SavedText;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest.GetLanguagesRequest;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceRequest.TranslateRequest;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.GetLanguagesResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.ParentResponse;
import com.reshadi.nima.kidocodeimagetranslator.Model.ServiceResponse.TranslateResponse;
import com.reshadi.nima.kidocodeimagetranslator.R;
import com.reshadi.nima.kidocodeimagetranslator.Util.Constans;
import com.reshadi.nima.kidocodeimagetranslator.View.Adapter.SavedTextAdapter;
import com.reshadi.nima.kidocodeimagetranslator.View.Adapter.TargetLanguageAdapter;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service.GetLanguagesService;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service.ParentService;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.Service.TranslateService;

import java.util.List;

import io.objectbox.Box;


public class MainActivityViewModel extends ViewModel {

    private DrawerLayout drawerLayout;
    private Spinner targetLanguageSpinner;
    private TextView readedTextView;
    private ListView savedListView;
    private Context context;
    public MainActivityViewModel(DrawerLayout drawerLayout,Spinner targetLanguageSpinner,TextView readedTextView,
                                 ListView savedListView,Context context) {
        this.drawerLayout = drawerLayout;
        this.targetLanguageSpinner = targetLanguageSpinner;
        this.readedTextView = readedTextView;
        this.savedListView = savedListView;

        this.context = context;

        readedTextView.setMovementMethod(new ScrollingMovementMethod());

        getTargetLanguages();

        getSavedTexts();
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

    public void saveClicked(){
        Box<SavedText> savedTextBox = DBIntializer.getBoxStore().boxFor(SavedText.class);
        SavedText savedText = new SavedText();
        TargetLanguageAdapter targetLanguageAdapter = (TargetLanguageAdapter) targetLanguageSpinner.getAdapter();
        if(targetLanguageAdapter != null) {
            if (targetLanguageAdapter.getItems().size() > 0) {
                Constans.selectedLanguage = targetLanguageAdapter.getItem(targetLanguageSpinner.getSelectedItemPosition());
                if (readedTextView.getText().length() > 0) {
                    savedText.setLanguage(Constans.selectedLanguage.getLanguage());
                    savedText.setLanguageName(Constans.selectedLanguage.getName());
                    savedText.setContent(readedTextView.getText().toString());
                    savedTextBox.put(savedText);
                    Toast.makeText(context, context.getResources().getString(R.string.text_saved), Toast.LENGTH_SHORT).show();
                    getSavedTexts();
                }
            }
        }


    }

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

    private void shareText(String body) {
        Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
        txtIntent .setType("text/plain");
        txtIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name));
        txtIntent .putExtra(android.content.Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(txtIntent ,"Share"));
    }

    private void getSavedTexts(){
        Box<SavedText> englishClassBox = DBIntializer.getBoxStore().boxFor(SavedText.class);
        final List<SavedText> savedTextList = englishClassBox.query().build().find();

        savedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                readedTextView.setText(savedTextList.get(position).getContent());
            }
        });
        SavedTextAdapter savedTextAdapter = new SavedTextAdapter(context,savedTextList);
        savedListView.setAdapter(savedTextAdapter);
    }

}
