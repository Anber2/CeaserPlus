package com.example.hp.ceaserplus.Others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;

import java.util.ArrayList;

/**
 * Created by HP on 4/10/2017.
 */

public class MembershipAdapter extends BaseAdapter {

    Context context;
    ArrayList<Membership> membershipArrayList;

    public MembershipAdapter(Context context, ArrayList<Membership> membershipArrayList) {
        this.context = context;
        this.membershipArrayList = membershipArrayList;
    }

    @Override
    public int getCount() {
        return membershipArrayList.size();
    }

    @Override
    public Membership getItem(int position) {
        Membership membership = membershipArrayList.get(position);
        return membership;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.membership_benefit_spinner_item, null);
        }

        TextView membershipBenefitTxt = (TextView) view.findViewById(R.id.membership_benefit_txt);
        Membership membership = membershipArrayList.get(position);
        membershipBenefitTxt.setText(membership.getMembershipName());
        return view;
    }
}
