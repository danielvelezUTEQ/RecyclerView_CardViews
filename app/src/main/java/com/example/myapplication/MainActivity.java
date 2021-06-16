package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Adapter.UsuarioAdapter;
import com.example.myapplication.Model.Usuario;
import com.example.myapplication.WebService.Asynchtask;
import com.example.myapplication.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerView;
    Button btnLista;
    EditText txtJournalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btnLista = findViewById(R.id.btnListar);
        txtJournalID = findViewById(R.id.txtJournalID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Busqueda(txtJournalID.getText().toString());
            }
        });

    }

    private void Busqueda (String codigo)
    {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+codigo,
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

        try {
            processFinish(datos.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Usuario> lstUsuarios = new ArrayList<Usuario> ();
        //JSONArray jsonArray = new JSONArray(result);
        JSONArray JSONlista =  new JSONArray(result);
        try {
            //JSONArray JSONlistaUsuarios=  JSONlista.getJSONArray("");

            lstUsuarios = Usuario.JsonObjectsBuild(JSONlista);

            UsuarioAdapter adapatorUsuario = new UsuarioAdapter(this, lstUsuarios);

            int resId = R.anim.layout_animation_down_to_up;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                    resId);
            recyclerView.setLayoutAnimation(animation);

            recyclerView.setAdapter(adapatorUsuario);



        }catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }

/* for(int i = 0; i< JSONlista.length(); i++ )
            {
                Toast.makeText(MainActivity.this,JSONlista.getString("cover"), Toast.LENGTH_SHORT).show();
            }*/
    }
}