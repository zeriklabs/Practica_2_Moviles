package com.zeriklabs.dm_jamt_08;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zeriklabs.dm_jamt_08.model.Empleado;
import com.zeriklabs.dm_jamt_08.model.RespuestaLista;
import com.zeriklabs.dm_jamt_08.network.EmpleadoApi;
import com.zeriklabs.dm_jamt_08.network.RetrofitClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmpleadoAdapter adapter;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewEmpleados);
        fabAgregar = findViewById(R.id.fabAgregar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmpleadoAdapter(new ArrayList<>(), empleado -> {
            Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
            intent.putExtra("EMPLEADO_ID", empleado.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        fabAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        EmpleadoApi api = RetrofitClient.getClient().create(EmpleadoApi.class);
        Call<RespuestaLista> call = api.obtenerTodos();

        call.enqueue(new Callback<RespuestaLista>() {
            @Override
            public void onResponse(Call<RespuestaLista> call, Response<RespuestaLista> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Empleado> lista = new ArrayList<>();
                    if (response.body().getDatos() != null && response.body().getDatos().getContent() != null) {
                        lista = response.body().getDatos().getContent();
                    }
                    adapter.setEmpleados(lista);
                } else {
                    Toast.makeText(MainActivity.this, "Error al cargar empleados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaLista> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "Error", t);
            }
        });
    }
}