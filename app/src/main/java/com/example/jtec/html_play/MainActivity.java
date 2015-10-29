package com.example.jtec.html_play;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    CheckBox checkBox;
    CheckBox checkBox2;
    Button btn_test;
    Activity activi = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        checkBox =(CheckBox) findViewById(R.id.checkBox);
        checkBox2 =(CheckBox) findViewById(R.id.checkBox2);
        btn_test = (Button) findViewById(R.id.button);

        wv = (WebView) findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new WebAppInterface(this), "Android");
        //wv.setWebViewClient(new ourViewClient());
        //wv.addJavascriptInterface(new AppApi(this), "APP");
        wv.getSettings().setBuiltInZoomControls(true);
        wv.clearCache(true);
        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());
        wv.setLongClickable(false);
        wv.loadUrl("http://developer.j-tec.com.vn/projects/android/sach/menu.html");
        //wv.loadUrl("http://developer.j-tec.com.vn/projects/android/nhac/menu.html");
        //wv.loadUrl("http://developer.j-tec.com.vn/projects/android/nhac/menu.html");

        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return true; // then it is not handled by default action
            }
        });
        wv.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               return true;
           }
       });
//       wv.setOnLongClickListener(new View.OnLongClickListener() {
//           @Override
//           public boolean onLongClick(View v) {
//               Log.v("Lg", "button long pressed --> ");
//
//               WebView.HitTestResult hr = ((WebView)v).getHitTestResult();
//               checkBox.setText(hr.toString());
//               if(hr.getType() == 5){
//                   //contentView.loadUrl("javascript:startLinePress(\"ACTIVITY\")");
//                   checkBox.setText(hr.toString());
//               }
//               return true;
//           }
//       });
    }

    public void action_btn(final int songid) throws IOException {
//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
                try {
                    baihientai=songid;
                    music_play(daad[songid]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
//        });
                ///return a;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    public String daad[]=null;
    String aStooges[] = {"http://192.168.0.15/a/music/animals__martin.mp3",
            "http://192.168.0.15/a/music/because_of_you.mp3",
            "http://192.168.0.15/a/music/fur_elise_dubstep.mp3",
            "http://192.168.0.15/a/music/iphone_6_plus.mp3"};
    public int baihientai=1;
    private int list_play_baihientai=0;
    MediaPlayer mediaPlayer;
    private void music_play(final String url) throws IOException {

        killMediaPlay();

        mediaPlayer = new MediaPlayer();
       // mediaPlayer
        mediaPlayer.reset();
        //String url = "http://192.168.0.15/a/music/animals__martin.mp3"; // your URL here
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(url);

        mediaPlayer.prepare(); // might take long! (for buffering, etc)
        //Toast.makeText(MainActivity.this, "Dang Play:"+url, Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
       // mediaPlayer.release();

        //WebAppInterface infte = new WebAppInterface();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // your code here
                //WebAppInterface infte = new WebAppInterface();
//                infte.linksmp3
                //tv.setText("Here");
//                        try {
//                            //music_play(infte.linksmp3[1].toString());
//                            killMediaPlay();
//                            mediaPlayer = new MediaPlayer();
//                            mediaPlayer.reset();
//                            String url = "http://192.168.0.15/a/music/animals__martin.mp3"; // your URL here
//                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                            mediaPlayer.setDataSource(url);
//                            mediaPlayer.prepare(); // might take long! (for buffering, etc)
//                            mediaPlayer.start();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                //btn_test.performClick();
                //wv.loadUrl("javascript:show_alert()");
                mediaPlayer.release();
                if (is_play_list) {
                    list_play_baihientai++;
                    if (list_play_baihientai < i_last + 1) {
                        try {
                            music_play(daad[list_play_baihientai]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this, "Play_list:"+daad[list_play_baihientai], Toast.LENGTH_LONG).show();
                        wv.loadUrl("javascript:showAndroidToast11(" + (list_play_baihientai - 1) + ")");
                    } else {
                        //showGui("Hết đoạn");
                        list_play_baihientai = i_first;
                        wv.loadUrl("javascript:showAndroidToast11(" + (list_play_baihientai - 1) + ")");
                        try {
                            music_play(daad[i_first]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //
                        //music_play();
                        //is_play_list = false;
                    }
                    return;
                }

                if (checkBox.isChecked()) {
                    try {
                        baihientai = baihientai + 1;
                        //showGui();
                        if ((baihientai < (leng_mp3)) && baihientai > 0) {
                            music_play(daad[baihientai]);
                            //Toast.makeText(MainActivity.this, "Play:"+daad[baihientai], Toast.LENGTH_LONG).show();
                            wv.loadUrl("javascript:showAndroidToast0(" + (baihientai - 1) + ")");

                        } else {
                            //showGui("Hết all bài");
                            baihientai = 0;
                            killMediaPlay();
                            //showHetBai();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            //Toast.makeText(MainActivity.this,infte.linksmp3[1],Toast.LENGTH_LONG).show();
        });


        //return  "Het";
    }

            public void run(final String scriptSrc) {
                wv.post(new Runnable() {
                    @Override
                    public void run() {
                wv.loadUrl("javascript:" + scriptSrc);
            }
        });
    }


    public void showHetBai(){
        Toast.makeText(MainActivity.this,"Het bai",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
        menu.add(0, v.getId(), 0, "Action 3");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Action 1") {
            Toast.makeText(this, "Action 1 invoked", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Action 2") {
            Toast.makeText(this, "Action 2 invoked", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Action 3") {
            Toast.makeText(this, "Action 3 invoked", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }



    public void test_btn(View view){
       if(checkBox2.isChecked()){
           wv.loadUrl("http://192.168.0.15/a/sach/menu.html");
       } else {
               wv.loadUrl("http://developer.j-tec.com.vn/projects/android/sach/menu.html");
       }
    }
    public void clicknow(){
        //btn_test.performClick();
        try {
            music_play("http://192.168.0.15/a/music/animals__martin.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showGui(String msm){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(msm);
        alertDialog.setMessage("Are you sure?");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();
    }
    public void phatnhac(){
        try {
            //music_play(infte.linksmp3[1].toString());
            killMediaPlay();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            String url = "http://192.168.0.15/a/music/animals__martin.mp3"; // your URL here
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
           // mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToTextView(final String text) {
        /*this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) MainActivity.this.findViewById(R.id.textView)).append("Received " + text
                        + System.getProperty("line.separator"));
                btn_test.performClick();
            }
        });*/
    }

    public void killMediaPlay() {


        try {
            mediaPlayer.pause();
            mediaPlayer.release();
            mediaPlayer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btn_sTop(View v){
        wv.loadUrl("javascript:stopPlay()");
    }
    public void sTop(){
        killMediaPlay();
        is_play_list=false;
//        i_first=0;
//        i_last=0;
        //baihientai=0;
        list_play_baihientai=0;
    }
    public int leng_mp3=1;
    public void setValue_link(String data[]){
        daad=data;
        leng_mp3=daad.length;
        //showGui("test");
    }
    private int i_first=0;
    private int i_last=0;
    private boolean is_play_list=false;

    public void play_part_listmp3(int i1,int i2){
        i_first=i1;
        i_last=i2;
        list_play_baihientai=i1;
        is_play_list=true;
        try {
            music_play(daad[i_first]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onAttachedToWindow() {
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.listen) {
            Toast.makeText(getBaseContext(), "listen", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.cancel) {
            Toast.makeText(getBaseContext(), "cancel", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void test_show(){
       // showGui();
        //openOptionsMenu();
    }
}

class WebAppInterface {
    private final MainActivity activity;

    /**
     * Creates the API.
     *
     * @param activity
     *            the activity used by this API.
     */
    public WebAppInterface(final MainActivity activity) {
        this.activity = activity;
    }
    /**
     * Logs a message.
     *
     * @param text
     *            the text to log.
     */
    @JavascriptInterface
    public void log(final String text) {
        this.activity.appendToTextView(text);
    }

    public static String str_link = "null";
    public String linksmp3[]=null; //all mp3 from javascript
    //Context mContext;
    //MainActivity main = new MainActivity();
    //private  MainActivity activity;

    /**
     * Instantiate the interface and set the context
     */
//    WebAppInterface(Context c) {
//        mContext = c;
//    }

//    public WebAppInterface() {
//
//    }
    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(int songid_in) throws IOException {
        //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        //str_link = toast.toString();
        //Toast.makeText(mContext, str_link, Toast.LENGTH_SHORT).show();
        this.activity.action_btn(songid_in);
        //this.activity.appendToTextView(str_link);
       //main.clicknow();
    }



    @JavascriptInterface
    public void stopPlay() {
        //Toast.makeText(mContext, "Stop Play", Toast.LENGTH_SHORT).show();
        //Toast.makeText(mContext, str_link, Toast.LENGTH_SHORT).show();
        this.activity.sTop();
    }
    @JavascriptInterface
    public void setValue() {
       // Toast.makeText(mContext, "Set Value Link", Toast.LENGTH_SHORT).show();
        //Toast.makeText(mContext, str_link, Toast.LENGTH_SHORT).show();
        this.activity.setValue_link(linksmp3);
    }
    @JavascriptInterface
    public void value_linkmp3(String links_mp3[]){
        linksmp3=links_mp3;
       // Toast.makeText(mContext, "Da co files: 1= "+linksmp3[1], Toast.LENGTH_SHORT).show();
        setValue();
    }

    public void test_show(){
        this.activity.test_show();
    }

    public void play_part(int i1,int i2){
        this.activity.play_part_listmp3(i1,i2);
    }
}
