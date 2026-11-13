package com.zeriklabs.dm_jamt_08;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.zeriklabs.dm_jamt_08.model.Empleado;
import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.ViewHolder> {

    private List<Empleado> empleados;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Empleado empleado);
    }

    public EmpleadoAdapter(List<Empleado> empleados, OnItemClickListener listener) {
        this.empleados = empleados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empleado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Empleado empleado = empleados.get(position);
        holder.tvNombre.setText(empleado.getNombre());
        holder.tvCorreo.setText(empleado.getCorreo());
        holder.tvTelefono.setText(empleado.getTelefono());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(empleado));
    }

    @Override
    public int getItemCount() {
        return empleados == null ? 0 : empleados.size();
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre;
        public TextView tvCorreo;
        public TextView tvTelefono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
        }
    }
}