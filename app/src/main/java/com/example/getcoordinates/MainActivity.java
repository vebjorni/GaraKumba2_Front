package com.example.getcoordinates;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnMuestraCoordenada;
    EditText edtDireccion;
    TextView txtCoordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMuestraCoordenada = (Button)findViewById(R.id.btnMuestraCoordenada);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        txtCoordenadas = (TextView)findViewById(R.id.txtCoordenadas);

        btnMuestraCoordenada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetCoordinates().execute(edtDireccion.getText().toString().replace(" ","+"));
            }
        });
    }

    private class GetCoordinates extends AsyncTask<String,Void,String> {




        @Override
        protected String doInBackground(String... strings) {
            String response;
            try {
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&sensor=true_or_false&key=AIzaSyC53SFiczNZ-U7Gs0OXdvk_cgFSzQQnZSY");
                response = http.getHTTPData(url);
                return response;
            } catch (Exception ex) {

            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {

          try{
              JSONObject jsonObject = new JSONObject(s);
              String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
              String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();
            txtCoordenadas.setText(String.format("Coordenadas : %s / %s ",lat,lng));

          } catch (JSONException e) {
              e.printStackTrace();
          }
        }
    }



}