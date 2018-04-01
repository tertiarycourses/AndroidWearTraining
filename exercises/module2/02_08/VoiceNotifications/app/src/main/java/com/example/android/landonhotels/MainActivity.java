package com.example.android.landonhotels;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Hotel> hotels = DataProvider.hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(android.R.id.list);
        ArrayAdapter<Hotel> adapter =
                new HotelAdapter(this, R.layout.list_item_location, hotels);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                Hotel location = hotels.get(position);
                intent.putExtra("city", location.getCity());
                startActivity(intent);
            }

        });

        sendNotification();

    }

    protected void sendNotification() {

        Intent replyIntent = new Intent(this, DetailActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notify)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("Landon Hotels, your best hotel!")
                        .setContentIntent(pendingIntent)
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
    }

}
