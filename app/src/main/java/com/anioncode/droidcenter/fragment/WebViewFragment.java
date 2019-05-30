package com.anioncode.droidcenter.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anioncode.droidcenter.R;

@SuppressLint("ValidFragment")
public class WebViewFragment extends Fragment {
    View view;
    int a;
    ProgressBar progressBar;
    @SuppressLint("ValidFragment")
    public WebViewFragment(int a) {
        this.a = a;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.webview_layout, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        progressBar=view.findViewById(R.id.progress_horizontal);


        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.setWebViewClient(new WebViewClient());
        // mWebView.setInitialScale(90);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if(a==1){
            myWebView.loadUrl("https://facebook.com/");
        }
        if(a==2){
            myWebView.loadUrl("https://instagram.com/");
        }
        if(a==3){
            myWebView.loadUrl("https://play.google.com/store/apps/dev?id=5300491392807005874");
        }
        if(a==4){
            myWebView.loadUrl("https://github.com/Lukieoo");
        }
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setIndeterminate(true);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setIndeterminate(false);


            }


        });

//        String unencodedHtml =
//                "&lt;html&gt;&lt;body&gt;'%23' is the percent code for ‘#‘ &lt;/body&gt;&lt;/html&gt;";
//        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
//                Base64.NO_PADDING);
  //      myWebView.loadData(encodedHtml, "text/html", "base64");

        return view;
    }




}