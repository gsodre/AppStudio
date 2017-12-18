package com.example.sodr.appstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VoteActivity extends AppCompatActivity {

    private ImageView imagePhoto;
    private int photo_id;
    private TextView textAuthor;
    private TextView textTitle;
    private TextView textLocal;
    private TextView textVotes;
    private EditText editName;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        imagePhoto = (ImageView) findViewById(R.id.imagePhoto);
        textAuthor = (TextView) findViewById(R.id.textAuthor);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textLocal = (TextView) findViewById(R.id.textLocal);
        textVotes = (TextView) findViewById(R.id.textVotes);
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);

        Intent intent = getIntent();
        photo_id = intent.getIntExtra("photo_id", -1);
        String author = intent.getStringExtra("author");
        String title = intent.getStringExtra("title");
        String local = intent.getStringExtra("local");
        int votes = intent.getIntExtra("votes", -1);

        Picasso.with(this).load("http://127.0.0.1/photo_studio/pictures/" + photo_id + ".jpg").into(imagePhoto);
        textAuthor.setText("Autor: " + author);
        textTitle.setText("Título: " + title);
        textLocal.setText("Local: " + local);
        textVotes.setText("Votos: " + String.valueOf(votes));
    }

    public void insertVote(View view) {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Informe um nome", Toast.LENGTH_SHORT).show();
            editName.requestFocus();
        } else if (email.trim().isEmpty()) {
            Toast.makeText(this, "Informe um email", Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.0.1/photo_studio/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PhotoService service = retrofit.create(PhotoService.class);

            Call<Vote> insertVote = service.insertVote(name, email, photo_id);

            insertVote.enqueue(new Callback<Vote>() {
                @Override
                public void onResponse(Call<Vote> call, Response<Vote> response) {
                    Toast.makeText(getApplicationContext(), "Voto salvo com sucesso com o código: " + response.body().getId(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Vote> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao salvar dados.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
