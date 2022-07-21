package com.ivg.banktest.controllers;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivg.banktest.R;
import com.ivg.banktest.models.cardTransactions;
import com.ivg.banktest.models.userBalance;
import com.ivg.banktest.views.DashboardUser;

import java.util.List;

public class AdapterMovimientos extends RecyclerView.Adapter<AdapterMovimientos.BalanceViewHold>  {

    private List<cardTransactions> listhomeBalance;
    private Context mContext;
    private DashboardUser mListener;



    public AdapterMovimientos(Context context, List<cardTransactions> listhomeBalance) {
        this.listhomeBalance = listhomeBalance;
        this.mContext = context;
    }

    @NonNull
    @Override
    public BalanceViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_movimientos, parent, false);
        return new BalanceViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHold holder, int position) {


        cardTransactions balancehelper = listhomeBalance.get(position);
        holder.textMovimientonombre.setText(balancehelper.getDescripctionTranscation());
        holder.textDateMovimineto.setText(balancehelper.getDateTransaction());
        holder.textQuantyMov.setText("$"+balancehelper.getMonto());

        if (balancehelper.getTipo_transaction().equals("abono") ){
            holder.textQuantyMov.setTextColor(Color.GREEN);

        }else if (balancehelper.getTipo_transaction().equals("cargo")){
            holder.textQuantyMov.setTextColor(Color.RED);
        }



    }

    @Override
    public int getItemCount() {
        return listhomeBalance.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class BalanceViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textMovimientonombre;
        TextView textDateMovimineto;
        TextView textQuantyMov;
        LinearLayout cardhome;


        public BalanceViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks

            textMovimientonombre = itemView.findViewById(R.id.name_mov);
            textDateMovimineto = itemView.findViewById(R.id.date_mov);
            textQuantyMov = itemView.findViewById(R.id.quanty_mov);
            cardhome=itemView.findViewById(R.id.lys_movimientos);

        }

        @Override
        public void onClick(View view) {
            Log.i("balance" ,"balance");
        }
    }

}