package com.ivg.banktest.controllers;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ivg.banktest.R;
import com.ivg.banktest.models.cardsUsers;
import com.ivg.banktest.models.userBalance;
import com.ivg.banktest.views.DashboardUser;

import java.util.List;

public class AdapterCards extends RecyclerView.Adapter<AdapterCards.BalanceViewHold>  {

    private List<cardsUsers> listhomeBalance;
    private Context mContext;
    private DashboardUser mListener;



    public AdapterCards(Context context, List<cardsUsers> listhomeBalance) {
        this.listhomeBalance = listhomeBalance;
        this.mContext = context;
    }

    @NonNull
    @Override
    public BalanceViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cards, parent, false);
        return new BalanceViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHold holder, int position) {


        cardsUsers balancehelper = listhomeBalance.get(position);
        holder.textCardStatus.setText(balancehelper.getStatusCard());
        holder.textCardnumber.setText(balancehelper.getNumberCard());
        holder.textSaldoCard.setText("$ "+balancehelper.getSaldo());
        holder.textNameCard.setText(balancehelper.getNamerCard());
        holder.textTypeCards.setText(balancehelper.getTypeUser());

        if (balancehelper.getStatusCard().equals("activa")){
            holder.imgcardstatus.setImageResource(R.drawable.ic_card_active);
        }else if (balancehelper.getStatusCard().equals("desactivada")){
            holder.imgcardstatus.setImageResource(R.drawable.ic_card_inactive);
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

        TextView textCardStatus;
        TextView textCardnumber;
        TextView textNameCard;
        TextView textSaldoCard;
        TextView textTypeCards;
        ImageView imgcardstatus;
        LinearLayout lyhome;


        public BalanceViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks

            textCardStatus = itemView.findViewById(R.id.status_card);
            textCardnumber = itemView.findViewById(R.id.number_card);
            textNameCard = itemView.findViewById(R.id.card_name);
            textSaldoCard = itemView.findViewById(R.id.money_user);
            imgcardstatus = itemView.findViewById(R.id.card_status_img);
            textTypeCards = itemView.findViewById(R.id.user_type_card);
            lyhome=itemView.findViewById(R.id.user_cards_bank);

        }

        @Override
        public void onClick(View view) {
            Log.i("balance" ,"balance");
        }
    }

}