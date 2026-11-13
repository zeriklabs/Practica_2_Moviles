package com.zeriklabs.dm_jamt_08;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.zeriklabs.dm_jamt_08.model.Empleado;
import com.zeriklabs.dm_jamt_08.model.RespuestaEntidad;
import com.zeriklabs.dm_jamt_08.model.RespuestaVacia;
import com.zeriklabs.dm_jamt_08.network.EmpleadoApi;
import com.zeriklabs.dm_jamt_08.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvNombre, tvCorreo, tvTelefono, tvDireccion, tvRegistrado;
    private Button btnEditar, btnEliminar;
    private Long empleadoId;
    private EmpleadoApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvNombre = findViewById(R.id.tvDetalleNombre);
        tvCorreo = findViewById(R.id.tvDetalleCorreo);
        tvTelefono = findViewById(R.id.tvDetalleTelefono);
        tvDireccion = findViewById(R.id.tvDetalleDireccion);
        tvRegistrado = findViewById(R.id.tvDetalleRegistrado);

        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalles del empleado");
        }

        api = RetrofitClient.getClient().create(EmpleadoApi.class);

        empleadoId = getIntent().getLongExtra("EMPLEADO_ID", -1L);
        if (empleadoId == -1L) {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleActivity.this, FormularioActivity.class);
            intent.putExtra("EMPLEADO_ID", empleadoId);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(v -> mostrarDialogoConfirmacion());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (empleadoId != -1L) {
            cargarDetalles();
        }
    }

    private void cargarDetalles() {
        api.obtenerPorId(empleadoId).enqueue(new Callback<RespuestaEntidad>() {
            @Override
            public void onResponse(Call<RespuestaEntidad> call, Response<RespuestaEntidad> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getDatos() != null) {
                    Empleado empleado = response.body().getDatos();
                    tvNombre.setText("Nombre: " + empleado.getNombre());
                    tvCorreo.setText("Correo: " + empleado.getCorreo());
                    tvTelefono.setText("Teléfono: " + empleado.getTelefono());
                    tvDireccion.setText("Dirección: " + empleado.getDireccion());
                    tvRegistrado.setText("Registrado: " + (Boolean.TRUE.equals(empleado.getRegistrado()) ? "Sí" : "No"));
                } else {
                    Toast.makeText(DetalleActivity.this, "Error al cargar detalles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaEntidad> call, Throwable t) {
                Toast.makeText(DetalleActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarDialogoConfirmacion() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar empleado")
                .setMessage("¿Estás seguro de que deseas eliminar este empleado?")
                .setPositiveButton("Sí", (dialog, which) -> eliminarEmpleado())
                .setNegativeButton("No", null)
                .show();
    }

    private void eliminarEmpleado() {
        api.eliminar(empleadoId).enqueue(new Callback<RespuestaVacia>() {
            @Override
            public void onResponse(Call<RespuestaVacia> call, Response<RespuestaVacia> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetalleActivity.this, "Empleado eliminado", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetalleActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaVacia> call, Throwable t) {
                Toast.makeText(DetalleActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}