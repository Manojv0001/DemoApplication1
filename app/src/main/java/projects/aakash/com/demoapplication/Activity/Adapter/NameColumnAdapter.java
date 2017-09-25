package projects.aakash.com.demoapplication.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import projects.aakash.com.demoapplication.Activity.Application.Constants;
import projects.aakash.com.demoapplication.Activity.Models.User;
import projects.aakash.com.demoapplication.R;

/**
 * Created by NG on 20-Jul-2017.
 */

public class NameColumnAdapter extends RecyclerView.Adapter<NameColumnAdapter.MyViewHolder> {
    private List<User> userList;
    private Context context;
    public OnItemClickListener onItemClickListener;
    public NameColumnAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList=userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.namecolumnadapter, parent, false);
        return new MyViewHolder(itemView);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User userdata = userList.get(position);
        if(!TextUtils.isEmpty(userdata.getName())){
            holder.tvName.setText(userdata.getName());
        }else{
            holder.tvName.setText(Constants.EMPTY);
        }
        if(!TextUtils.isEmpty(userdata.getEmail())){
            holder.tvEmail.setText(userdata.getEmail());
        }else{
            holder.tvEmail.setText(Constants.EMPTY);
        }
        if(!TextUtils.isEmpty(userdata.getMobile())){
            holder.tvMobile.setText(userdata.getMobile());
        }else{
            holder.tvMobile.setText(Constants.EMPTY);
        }
        holder.itemView.setTag(userdata);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvEmail;
        TextView tvName;
        TextView tvMobile;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvMobile = (TextView) itemView.findViewById(R.id.tvMobile);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, (User) v.getTag());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, User position);
    }
}

