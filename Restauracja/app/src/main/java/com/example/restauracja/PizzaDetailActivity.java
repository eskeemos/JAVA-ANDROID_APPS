package com.example.restauracja;

import androidx.annotation.NonNull;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class PizzaDetailActivity extends Activity {

    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_PIZZA_NO = "pizzaNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        int pizzaNo = getIntent().getExtras().getInt(EXTRA_PIZZA_NO);

        String pizzaName = Pizza.pizzas[pizzaNo].getName();
        TextView tvPizzaName = findViewById(R.id.tvPizzaName);
        tvPizzaName.setText(pizzaName);

        int pizzaImage = Pizza.pizzas[pizzaNo].getImageId();
        ImageView ivImage = findViewById(R.id.ivImage);
        ivImage.setImageResource(pizzaImage);
        ivImage.setContentDescription(pizzaName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        TextView tvPizzaName = findViewById(R.id.tvPizzaName);
        String pizzaName = (String) tvPizzaName.getText();

        MenuItem iShare = menu.findItem(R.id.action_send_message);
        shareActionProvider = (ShareActionProvider) iShare.getActionProvider();
        setIntent(pizzaName);

        return super.onCreateOptionsMenu(menu);
    }

    private void setIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}