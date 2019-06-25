package co.nf.axesoft.consumindourljson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.nf.axesoft.consumindourljson.activity.FichaPeople;
import co.nf.axesoft.consumindourljson.model.People;

public class MainActivity extends AppCompatActivity {
    private String idRecuperado;
    private ListView listView;

    private PeopleSWAdapter adapter;
    private List<People> peopleSwList;
    private int itemClicado;

    private Button btnPrevious,btnNext;
    private ProgressBar progressBar;

    private final String _HOST = "https://swapi.co/api/people";
    private String pageNext ="";
    private String pagePrevious ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView    = findViewById(R.id.listView);
        btnNext     = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        progressBar = findViewById(R.id.progress_circular);

        //preparando a listview
        peopleSwList = new ArrayList<People>();
        adapter = new PeopleSWAdapter(MainActivity.this,peopleSwList);
        listView.setAdapter(adapter);

        //listarContatos
        //if(btnNext.isClickable()){
            //listarContatos(pageNext);

        //}else if(btnPrevious.isClickable()){
           // listarContatos(pagePrevious);

       // }else {
            listarContatos(_HOST);
       // }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarContatos(pageNext);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarContatos(pagePrevious);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                People contato =  (People) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(MainActivity.this, FichaPeople.class);

                String id     = String.valueOf(i);
                String nome   = contato.getName();
                String altura = String.valueOf(contato.getHeight());
                String peso   = String.valueOf(contato.getMass());

                intent.putExtra("ID", id);
                intent.putExtra("NOME", nome);
                intent.putExtra("ALTURA", altura);
                intent.putExtra("PESO", peso);

                startActivity(intent);
            }
        });


    }
    private void listarContatos(String page){


        String url = page;

        Ion.with(getApplicationContext())
                .load(url)
                .progressBar(progressBar)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        peopleSwList.clear();

                        if(!result.get("next").isJsonNull())
                            pageNext = result.get("next").getAsString();

                        if(!result.get("previous").isJsonNull())
                            pagePrevious = result.get("previous").getAsString();

                        JsonArray peopleJson = result.getAsJsonObject().getAsJsonArray("results");


                        for(int i=0; i < peopleJson.size(); i++){

                            JsonObject object = peopleJson.get(i).getAsJsonObject();

                            People c = new People();

                            c.setId(i);
                            c.setName(object.get("name").getAsString());
                            c.setMass(object.get("mass").getAsInt());
                            c.setHeight(object.get("height").getAsInt());

                            peopleSwList.add(c);

                        }
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

}
