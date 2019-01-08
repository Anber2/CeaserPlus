package com.example.hp.ceaserplus.Others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;

import java.util.ArrayList;

/**
 * Created by HP on 4/10/2017.
 */

public class MembershipBenefitAdapter extends BaseAdapter {

    Context context ;
    ArrayList<MembershipBenefit> membershipBenefitArrayList;

    public MembershipBenefitAdapter(Context context , ArrayList<MembershipBenefit> membershipBenefitArrayList){
        this.context=context;
        this.membershipBenefitArrayList=membershipBenefitArrayList;
    }

    @Override
    public int getCount() {
        return membershipBenefitArrayList.size();
    }

    @Override
    public MembershipBenefit getItem(int position) {
        MembershipBenefit membershipBenefit=membershipBenefitArrayList.get(position);
        return membershipBenefit;
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
        TextView membershipBenefitTxt= (TextView) view.findViewById(R.id.membership_benefit_txt);
        MembershipBenefit membershipBenefit=membershipBenefitArrayList.get(position);
        membershipBenefitTxt.setText(membershipBenefit.getMembershipBenefitName());

        return view;
    }
}
