package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Usuario;
import com.example.myapplication.R;

import java.util.List;


public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private Context Ctx;
    private List<Usuario> lstUsuarios;

    public UsuarioAdapter(Context mCtx, List<Usuario> usuarios) {
        this.lstUsuarios = usuarios;
        Ctx=mCtx;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_usuario, null);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {

        Usuario usuario = lstUsuarios.get(position);

        holder.textViewIssue.setText(usuario.getIssue_id());
        holder.textViewVolume.setText(usuario.getVolume());
        holder.textViewNumber.setText(usuario.getNumber());
        holder.textViewYear.setText(usuario.getYear());
        holder.textViewDatePub.setText(usuario.getDate_published());
        holder.textViewTitle.setText(usuario.getTitle());
        holder.textViewDOI.setText(usuario.getDoi());
        Glide.with(Ctx)
                .load(usuario.getCover())
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return lstUsuarios.size();
    }


    class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIssue, textViewVolume, textViewNumber, textViewYear, textViewDatePub, textViewTitle, textViewDOI;
        ImageView imageView;

        public UsuarioViewHolder(View itemView) {
            super(itemView);

            textViewIssue= itemView.findViewById(R.id.txtIssue);
            textViewVolume = itemView.findViewById(R.id.txtVolume);
            textViewNumber = itemView.findViewById(R.id.txtNumber);
            textViewYear = itemView.findViewById(R.id.txtYear);
            textViewDatePub = itemView.findViewById(R.id.txtFP);
            textViewTitle = itemView.findViewById(R.id.txtTitulo);
            textViewDOI = itemView.findViewById(R.id.txtDOI);
            imageView = itemView.findViewById(R.id.imgAvatar);
        }
    }

}
