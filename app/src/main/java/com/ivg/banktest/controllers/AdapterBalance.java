package com.ivg.banktest.controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivg.banktest.R;
import com.ivg.banktest.models.userBalance;
import com.ivg.banktest.views.DashboardUser;

import java.util.ArrayList;
import java.util.List;

public class AdapterBalance extends RecyclerView.Adapter<AdapterBalance.BalanceViewHold>  {

    private List<userBalance> listhomeBalance;
    private Context mContext;
    private DashboardUser mListener;



    public AdapterBalance(Context context, List<userBalance> listhomeBalance) {
        this.listhomeBalance = listhomeBalance;
        this.mContext = context;
    }

    @NonNull
    @Override
    public BalanceViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balance_card, parent, false);
        return new BalanceViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHold holder, int position) {


        userBalance balancehelper = listhomeBalance.get(position);
        holder.textSaldoGeneral.setText("Saldo General: $"+ balancehelper.getGeneralBalance());
        holder.textIngresos.setText("Ingresos: $"+balancehelper.getUserEarnings());
        holder.textgastos.setText("Gastos: $"+balancehelper.getUserBills());

    }

    @Override
    public int getItemCount() {
        return listhomeBalance.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class BalanceViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textSaldoGeneral;
        TextView textIngresos;
        TextView textgastos;
        CardView cardhome;


        public BalanceViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks

            textSaldoGeneral = itemView.findViewById(R.id.tv_saldo_general);
            textIngresos = itemView.findViewById(R.id.tv_ingreso);
            textgastos = itemView.findViewById(R.id.tv_gastos);
            cardhome=itemView.findViewById(R.id.list_directorio_balances);

        }

        @Override
        public void onClick(View view) {
            Log.i("balance" ,"balance");
        }
    }

}