package com.example.projecturl;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private WebView webView1;
    private EditText url_Login;
    private String address;
    private ImageView img_bookmark;
    private Button goBtn;
    private boolean isGoBtnClicked;

    private SharedPreferences sharedPreferences;
    private boolean isBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView1 = findViewById(R.id.webView1);
        url_Login = findViewById(R.id.url_Login);
        goBtn = findViewById(R.id.bt1);
        img_bookmark = findViewById(R.id.imgBookMark);

        sharedPreferences = getSharedPreferences(Constants.MyPreferences, Context.MODE_PRIVATE);

        url_Login.setImeOptions(EditorInfo.IME_ACTION_DONE);
        url_Login.setOnEditorActionListener(onEditorActionListener);

        img_bookmark.setOnClickListener(bookMarkListener);

        goBtn.setOnClickListener(onClickListener);
        url_Login.addTextChangedListener(urlTextWatcher);

        String address = getIntents();

        if (!TextUtils.isEmpty(address)) {
            url_Login.setText(address);
            if (address.contains("www")) {
                Utils.goToUrl(webView1, address);
            } else {
                Utils.search(webView1, address);
            }
        }
    }

    private String getIntents() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            assert extras != null;
            return extras.getString("Url");
        }
        return null;
    }

    private TextWatcher urlTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            isGoBtnClicked = false;
            showStar(false);
            if (isBookmarked) {
                img_bookmark.setBackgroundResource(R.drawable.ic_star_border_filled);
                isBookmarked = false;
            }

            for(String str: Utils.getAllSharedPrefList(MainActivity.this) ){
                if(str.equalsIgnoreCase(charSequence.toString())){
                    showStar(true);
                    img_bookmark.setBackgroundResource(R.drawable.ic_star_filled);
                }
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private EditText.OnEditorActionListener onEditorActionListener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callType();
                return true;
            }
            return false;
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            callType();
        }
    };

    private void callType() {
        String url = url_Login.getText().toString();
        isGoBtnClicked = true;
        showStar(true);

        if (url.contains("www")) {
            Utils.goToUrl(webView1, url);
        } else {
            Utils.search(webView1, url);
        }
    }

    private void showStar(boolean toShow) {
        if (toShow) {
            img_bookmark.setVisibility(View.VISIBLE);
        } else {
            img_bookmark.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener bookMarkListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (isGoBtnClicked) {
                String url = url_Login.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(url, url);
                editor.apply();
                Toast.makeText(getApplicationContext(), "BookMarked : " + url, Toast.LENGTH_LONG).show();
                img_bookmark.setBackgroundResource(R.drawable.ic_star_filled);
                isBookmarked = true;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.book_Mark) {

            Intent intent = new Intent(this, BookmarkList.class);
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


