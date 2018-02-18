package com.example.maziyank.itbooks;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.itbooks)
                .setDescription("This app can be used to search IT ebooks by utilizing the API provided by www.it-ebooks.info. " +
                        "This is not published application and only used for learning purpose (on IAK Study Group). " +
                        "Please, use this application wisely and do not use this app to violate the law.")
                .addGroup("Connect with developer")
                .addEmail("maziyank@gmail.com")
                .addGitHub("maziyank")
                .addWebsite("maziyank.wordpress.com")
                .addInstagram("maziyank")
                .create();


        aboutPage.setBackgroundColor(Color.argb(1,238,238,238));

        setContentView(aboutPage);
    }
}
