package com.ankit.empowerher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // First training course
        TextView productName1 = findViewById(R.id.product_name_1);
        TextView category1 = findViewById(R.id.category_1);
        TextView price1 = findViewById(R.id.price_1);
        TextView duration1 = findViewById(R.id.duration_1);
        TextView material1 = findViewById(R.id.material_1);
        TextView link1 = findViewById(R.id.link_1);

        link1.setOnClickListener(v -> openYouTubeLink("https://youtube.com/playlist?list=PLV01m1I3r5MKojpOE9EoZ39RFg3KP3Bqz&si=qwgG6mhDbpd07PXo"));

        // Second training course
        TextView productName2 = findViewById(R.id.product_name_2);
        TextView category2 = findViewById(R.id.category_2);
        TextView price2 = findViewById(R.id.price_2);
        TextView duration2 = findViewById(R.id.duration_2);
        TextView material2 = findViewById(R.id.material_2);
        TextView link2 = findViewById(R.id.link_2);

        link2.setOnClickListener(v -> openYouTubeLink("https://youtu.be/TLKyolNrRIQ?si=WKD1spfe3Bq6Za5Y"));

        // Third training course
        TextView productName3 = findViewById(R.id.product_name_3);
        TextView category3 = findViewById(R.id.category_3);
        TextView price3 = findViewById(R.id.price_3);
        TextView duration3 = findViewById(R.id.duration_3);
        TextView material3 = findViewById(R.id.material_3);
        TextView link3 = findViewById(R.id.link_3);

        link3.setOnClickListener(v -> openYouTubeLink("https://youtube.com/playlist?list=PL9AvBy0wWtV9xwkn6n1c37p14NTNnrUFw&si=avP3tjm11tDoyQhE"));

        // Fourth training course
        TextView productNameShampoo = findViewById(R.id.product_name_shampoo);
        TextView categoryShampoo = findViewById(R.id.category_shampoo);
        TextView priceShampoo = findViewById(R.id.price_shampoo);
        TextView durationShampoo = findViewById(R.id.duration_shampoo);
        TextView materialShampoo = findViewById(R.id.material_shampoo);
        TextView linkShampoo = findViewById(R.id.link_shampoo);

        linkShampoo.setOnClickListener(v -> openYouTubeLink("https://youtu.be/RIzFYjdC76s?si=4Lntx-RMa4myHgOQ"));

        // Fifth training course
        TextView productNameScrubs = findViewById(R.id.product_name_scrubs);
        TextView categoryScrubs = findViewById(R.id.category_scrubs);
        TextView priceScrubs = findViewById(R.id.price_scrubs);
        TextView durationScrubs = findViewById(R.id.duration_scrubs);
        TextView materialScrubs = findViewById(R.id.material_scrubs);
        TextView linkScrubs = findViewById(R.id.link_scrubs);

        linkScrubs.setOnClickListener(v -> openYouTubeLink("https://youtu.be/Nmpohm5x0uo?si=IZumtdLScxNdpqFh"));

        // Sixth training course
        TextView productNameDeodorants = findViewById(R.id.product_name_deodrants);
        TextView categoryDeodorants = findViewById(R.id.category_deodrants);
        TextView priceDeodorants = findViewById(R.id.price_deodrants);
        TextView durationDeodorants = findViewById(R.id.duration_deodrants);
        TextView materialDeodorants = findViewById(R.id.material_deodrants);
        TextView linkDeodorants = findViewById(R.id.link_deodrants);

        linkDeodorants.setOnClickListener(v -> openYouTubeLink("https://youtu.be/LPcGNHVO5A0?si=IPeapu2iIwHasHDl"));

        // For Handmade Cards/Books
        TextView productNameHandmadeCards = findViewById(R.id.product_name_handmade_cards);
        TextView categoryHandmadeCards = findViewById(R.id.category_handmade_cards);
        TextView priceHandmadeCards = findViewById(R.id.price_handmade_cards);
        TextView durationHandmadeCards = findViewById(R.id.duration_handmade_cards);
        TextView materialHandmadeCards = findViewById(R.id.material_handmade_cards);
        TextView linkHandmadeCards = findViewById(R.id.link_handmade_cards);

        linkHandmadeCards.setOnClickListener(v -> openYouTubeLink("https://youtube.com/playlist?list=PL1rZ38lgA5s91XQwyDG-QIUTSJS5kIBn7&si=PgYVpcJXUFK8E4LC"));

// For Homemade Jams/Sauces/Jellies
        TextView productNameJams = findViewById(R.id.product_name_jams);
        TextView categoryJams = findViewById(R.id.category_jams);
        TextView priceJams = findViewById(R.id.price_jams);
        TextView durationJams = findViewById(R.id.duration_jams);
        TextView materialJams = findViewById(R.id.material_jams);
        TextView linkJams = findViewById(R.id.link_jams);

        linkJams.setOnClickListener(v -> openYouTubeLink("https://youtube.com/playlist?list=PL59WQaa5qGyjrMFiRRWKI3MgxmAVIsj_9&si=hAqdguOb8Oz-sWJQ"));

// For Homemade Pickles/Chutney
        TextView productNamePickles = findViewById(R.id.product_name_pickles);
        TextView categoryPickles = findViewById(R.id.category_pickles);
        TextView pricePickles = findViewById(R.id.price_pickles);
        TextView durationPickles = findViewById(R.id.duration_pickles);
        TextView materialPickles = findViewById(R.id.material_pickles);
        TextView linkPickles = findViewById(R.id.link_pickles);

        linkPickles.setOnClickListener(v -> openYouTubeLink("https://youtube.com/playlist?list=PLglwZJBsJ5MUCL0kAt7Z9u9JaoI6-9ImV&si=wrajuIHLKJMQZkCu"));

    }






    private void openYouTubeLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setPackage("com.google.android.youtube"); // Opens the link in YouTube app if installed
        startActivity(intent);
    }
}
