package com.example.trabalhocidade;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewCertoOuErrado;
    private EditText respostaTextView;
    private Button buttonChutar;

    public int pontos = 0;
    private ArrayList<Integer> indiceImagemSorteadas = new ArrayList<>();
    private int indiceImagem = 0;
    private String[] listaNomeCidades = {"Barcelona", "Brasília", "Curitiba", "Las Vegas", "Montreal", "Paris", "Rio de Janeiro", "Salvador", "São Paulo", "Tóquio"};
    private String[] listaLinksImagens = {"http://31.220.51.31/ufpr/cidades/01_barcelona.jpg", "http://31.220.51.31/ufpr/cidades/02_brasilia.jpg", "http://31.220.51.31/ufpr/cidades/03_curitiba.jpg", "http://31.220.51.31/ufpr/cidades/04_lasvegas.jpg", "http://31.220.51.31/ufpr/cidades/05_montreal.jpg", "http://31.220.51.31/ufpr/cidades/06_paris.jpg", "http://31.220.51.31/ufpr/cidades/07_riodejaneiro.jpg", "http://31.220.51.31/ufpr/cidades/08_salvador.jpg", "http://31.220.51.31/ufpr/cidades/09_saopaulo.jpg", "http://31.220.51.31/ufpr/cidades/10_toquio.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textViewCertoOuErrado = findViewById(R.id.textViewCertoOuErrado);
        respostaTextView = findViewById(R.id.respostaTextView);
        buttonChutar = findViewById(R.id.buttonChutar);
        int pontos = 0;

        int[] indicesAleatorios = geraIndicesAleatorios();
        baixarImagem(indicesAleatorios[0]);

        buttonChutar.setOnClickListener(new View.OnClickListener() {
            int pontos = 0;
            int chances = 0;
            public void onClick(View v) {
                String resposta = respostaTextView.getText().toString();
                chances++;
                if(chances <= 4)
                {
                    if (resposta.equalsIgnoreCase(listaNomeCidades[indicesAleatorios[indiceImagem]])) {
                        pontos += 25;
                        textViewCertoOuErrado.setText("Certo. Pontos: " + pontos);
                        if(chances <= 3)
                        {
                            indiceImagem = (indiceImagem + 1) % 4;
                            baixarImagem(indicesAleatorios[indiceImagem]);
                        }
                        else
                        {
                            textViewCertoOuErrado.setText("fim de jogo, total de "+ pontos);
                            Button btn = findViewById(R.id.buttonChutar);
                            btn.setEnabled(false);
                        }
                    }
                    else {
                        textViewCertoOuErrado.setText("Errado");
                        indiceImagem = (indiceImagem + 1) % 4;
                        baixarImagem(indicesAleatorios[indiceImagem]);
                    }
                }
                else
                {
                    textViewCertoOuErrado.setText("fim de jogo, total de "+ pontos);
                }


            }
        });
    }

    private void baixarImagem(int indice) {
        Picasso.get().load(listaLinksImagens[indice]).into(imageView);

        Log.d("teste jogo", listaNomeCidades[indice]);
    }


    private int[] geraIndicesAleatorios() {
        int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < indices.length; i++) {
            int aleatorio = (int) (Math.random() * indices.length);
            int temp = indices[i];
            indices[i] = indices[aleatorio];
            indices[aleatorio] = temp;
        }
        return indices;

    }
}