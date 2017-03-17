package com.zdh.alphathink.imh.News;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zdh.alphathink.imh.R;


public class HealthNewsDetail extends Activity
{

	private WebView webview;  
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_news_detail);
		Intent intent = getIntent();
		String url = intent.getExtras().getString("url");
		webview = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本  
		webSettings.setJavaScriptEnabled(true);  
		//设置可以访问文件
        webSettings.setAllowFileAccess(true);
         //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页  
        webview.loadUrl(url);  
        //设置Web视图  
        webview.setWebViewClient(new webViewClient ());  
		
	}
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override 
    //设置回退  
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {  
            webview.goBack(); //goBack()表示返回WebView的上一页面

        }
        finish();//结束退出程序
        return false;
    }  
      
    //Web视图  
    private class webViewClient extends WebViewClient {  
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        }  
    }  

}
