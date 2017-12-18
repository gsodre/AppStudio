package com.example.sodr.appstudio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPhotos = (ListView)findViewById(R.id.listPhotos);

        listPhotos.setOnItemClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1/photo_studio/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoService service = retrofit.create(PhotoService.class);

        Call<List<Photo>> photos = service.getPhotos();

        photos.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    List<Photo> photos = response.body();
                    PhotosAdapter adapter = new PhotosAdapter(getApplicationContext(), photos, "http://127.0.0.1/photo_studio/pictures/");
                    listPhotos.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao carregar dados.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Photo photo = (Photo) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, VoteActivity.class);
        intent.putExtra("photo_id", photo.getId());
        intent.putExtra("author", photo.getAuthor());
        intent.putExtra("title", photo.getTitle());
        intent.putExtra("local", photo.getLocal());
        intent.putExtra("votes", photo.getVotes());
        startActivity(intent);

    }
}
