package venkat.com.kayakapp;

/**
 * Created by venkatgonuguntala on 3/20/16.
 */
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import venkat.com.kayakapp.Util.Constant;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = DetailActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView nameTextView;
    private TextView urlTextView;
    private TextView phoneNumberTextView;
    private ImageView airlineImageView;
    private String url;
    private String phoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Airline Details");

        //for having the back button enabled on this activity
        nameTextView = (TextView) findViewById(R.id.airline_name);
        urlTextView = (TextView) findViewById(R.id.url);
        urlTextView.setPaintFlags(urlTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        phoneNumberTextView = (TextView) findViewById(R.id.phone_number);
        airlineImageView = (ImageView) findViewById(R.id.airline_image);

        Intent intent = getIntent();
        String airlineName = intent.getStringExtra(Constant.NAME);
        url = intent.getStringExtra(Constant.WEBSITE);
        phoneNumber = intent.getStringExtra(Constant.PHONE_NUMBER);
        String imageReference = intent.getStringExtra(Constant.LOGO);

        nameTextView.setText(airlineName);
        urlTextView.setText(url);
        urlTextView.setOnClickListener(this);
        phoneNumberTextView.setText(phoneNumber);
        phoneNumberTextView.setOnClickListener(this);
        Picasso.with(this).load(Constant.BASE_URL + imageReference).into(airlineImageView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if( id == android.R.id.home) {
//            NavUtils.navigateUpFromSameTask(this);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.url :
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
             break;

            case R.id.phone_number :
                StringBuilder stringBuilder = new StringBuilder("tel:");
                stringBuilder.append(phoneNumber);
                Intent dailIntent = new Intent(Intent.ACTION_DIAL);
                dailIntent.setData(Uri.parse(stringBuilder.toString()));
                startActivity(dailIntent);
             break;
        }
    }

}
