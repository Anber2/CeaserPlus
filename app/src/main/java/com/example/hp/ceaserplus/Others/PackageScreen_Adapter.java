package com.example.hp.ceaserplus.Others;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.Others.packageClass;
import com.example.hp.ceaserplus.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by HP on 4/13/2017.
 */

public class PackageScreen_Adapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    Button upgread, renew;


    ArrayList<packageClass> packageClassArrayList;

    public PackageScreen_Adapter(Activity activity, ArrayList<packageClass> packageClassArrayList) {
        this.activity = activity;
        this.packageClassArrayList = packageClassArrayList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return packageClassArrayList.size();
    }

    @Override
    public packageClass getItem(int position) {
        packageClass packageClass=packageClassArrayList.get(position);
        return packageClass;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.package_list_row, null);
        }

        TextView title_package = (TextView) vi.findViewById(R.id.title_package);
        TextView ActiveTo = (TextView) vi.findViewById(R.id.ActiveTo);
        TextView ActiveFrom = (TextView) vi.findViewById(R.id.ActiveFrom);
        TextView MembershipFee = (TextView) vi.findViewById(R.id.MembershipFee);
        upgread = (Button) vi.findViewById(R.id.upgread);
        renew = (Button) vi.findViewById(R.id.renew);



        packageClass packageClassobj = packageClassArrayList.get(position);

        title_package.setText(packageClassobj.getPackages());
        ActiveTo.setText(packageClassobj.getActiveTo());
        ActiveFrom.setText(packageClassobj.getActiveFrom());
        MembershipFee.setText(packageClassobj.getMembershipFee());


        return vi;
    }
}
