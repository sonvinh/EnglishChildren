package com.example.vinhpc.englishchildren;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account> {
    public  AccountAdapter(Context context, ArrayList<Account> accounts) {
        super(context, 0, accounts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Account account = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemaccount, parent, false);
        }

        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);

        tvUsername.setText(account.username);
        tvScore.setText(account.score);
        return convertView;
    }
}
