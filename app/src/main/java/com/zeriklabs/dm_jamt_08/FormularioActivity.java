package com.zeriklabs.dm_jamt_08;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.zeriklabs.dm_jamt_08.model.Empleado;
import com.zeriklabs.dm_jamt_08.model.RespuestaEntidad;
import com.zeriklabs.dm_jamt_08.network.EmpleadoApi;
import com.zeriklabs.dm_jamt_08.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNombre, etCorreo, etTelefono, etDireccion;
    private CheckBox cbRegistrado;
    private Button btnGuardar;
    private Long empleadoId = -1L;
    private EmpleadoApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);
        cbRegistrado = findViewById(R.id.cbRegistrado);
        btnGuardar = findViewById(R.id.btnGuardar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Añadir empleado");
        }

        api = RetrofitClient.getClient().create(EmpleadoApi.class);

        if (getIntent().hasExtra("EMPLEADO_ID")) {
            empleadoId = getIntent().getLongExtra("EMPLEADO_ID", -1L);
            if (empleadoId != -1L) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Editar empleado");
                }
                cargarDatosEmpleado(empleadoId);
            }
        }

        btnGuardar.setOnClickListener(v -> guardarEmpleado());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void cargarDatosEmpleado(Long id) {
        api.obtenerPorId(id).enqueue(new Callback<RespuestaEntidad>() {
            @Override
            public void onResponse(Call<RespuestaEntidad> call, Response<RespuestaEntidad> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getDatos() != null) {
                    Empleado empleado = response.body().getDatos();
                    etNombre.setText(empleado.getNombre());
                    etCorreo.setText(empleado.getCorreo());
                    etTelefono.setText(empleado.getTelefono());
                    etDireccion.setText(empleado.getDireccion());
                    cbRegistrado.setChecked(Boolean.TRUE.equals(empleado.getRegistrado()));
                } else {
                    Toast.makeText(FormularioActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaEntidad> call, Throwable t) {
                Toast.makeText(FormularioActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarEmpleado() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        boolean registrado = cbRegistrado.isChecked();

        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        Empleado empleado = new Empleado(null, nombre, correo, telefono, direccion, registrado);

        if (empleadoId != -1L) {
            empleado.setId(empleadoId);
            actualizarEmpleado(empleado);
        } else {
            registrarEmpleado(empleado);
        }
    }

    private void registrarEmpleado(Empleado empleado) {
        api.registrar(empleado).enqueue(new Callback<RespuestaEntidad>() {
            @Override
            public void onResponse(Call<RespuestaEntidad> call, Response<RespuestaEntidad> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FormularioActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(FormularioActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaEntidad> call, Throwable t) {
                Toast.makeText(FormularioActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarEmpleado(Empleado empleado) {
        api.actualizar(empleado.getId(), empleado).enqueue(new Callback<RespuestaEntidad>() {
            @Override
            public void onResponse(Call<RespuestaEntidad> call, Response<RespuestaEntidad> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FormularioActivity.this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(FormularioActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaEntidad> call, Throwable t) {
                Toast.makeText(FormularioActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}