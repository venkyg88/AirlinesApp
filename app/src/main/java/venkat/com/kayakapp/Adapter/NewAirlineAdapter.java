package venkat.com.kayakapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import venkat.com.kayakapp.DetailActivity;
import venkat.com.kayakapp.R;
import venkat.com.kayakapp.Util.Constant;
import venkat.com.kayakapp.model.Airlines;

/**
 * Created by venkatgonuguntala on 3/19/16.
 */
public class NewAirlineAdapter extends RecyclerView.Adapter<NewAirlineAdapter.NewAirlineViewHolder> {

    private List<Airlines> mAirlinesListItems;
    private Context mContext;
    private String name;
    private String url;
    private String phoneNumber;
    private String website;
    public static final int INDEX = -100;
    private Boolean isSwitched;


    public NewAirlineAdapter(List<Airlines> airlinesListItems, Context context){
        this.mContext = context;
        this.mAirlinesListItems = new CopyOnWriteArrayList<>();
        this.mAirlinesListItems.addAll(airlinesListItems);
    }

    @Override
    public NewAirlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airlines_list_item, parent, false);

        isSwitched = false;
        NewAirlineViewHolder airlinesViewHolder = new NewAirlineViewHolder(view);
        return airlinesViewHolder;
    }

    @Override
    public void onBindViewHolder(NewAirlineViewHolder holder, int position) {
        //holder.bindAirlines(mAirlinesListItems.get(position));

        Airlines airlines = mAirlinesListItems.get(position);

            holder.mAirlineLabel.setText(airlines.getName());
            Picasso.with(mContext).load(Constant.BASE_URL + airlines.getLogoURL()).into(holder.mImageView);

            if(!isSwitched) {
                holder.mCheckBox.setVisibility(View.VISIBLE);
                holder.mCheckBox.setTag(position);
                holder.mCheckBox.setTag(INDEX, position);
                holder.mCheckBox.setChecked(airlines.isChecked());
                holder.mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
            } else {
                holder.mCheckBox.setVisibility(View.GONE);
            }

            name = airlines.getName();
            url = airlines.getLogoURL();
            phoneNumber = airlines.getPhone();
            website = airlines.getSite();
    }

    @Override
    public int getItemCount() {
        return mAirlinesListItems.size();
    }

    public List<Airlines> getList() {
        return mAirlinesListItems;
    }

    public class NewAirlineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mAirlineLabel;
        public ImageView mImageView;
        public CheckBox mCheckBox;

        public NewAirlineViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
            mAirlineLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkEnable);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(Constant.NAME, name);
            intent.putExtra(Constant.LOGO, url);
            intent.putExtra(Constant.PHONE_NUMBER, phoneNumber);
            intent.putExtra(Constant.WEBSITE, website);
            mContext.startActivity(intent);
        }
    }

    CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int selectedIndex = (Integer)buttonView.getTag();
            Airlines airline = mAirlinesListItems.get(selectedIndex);
            airline.setChecked(isChecked);
            mAirlinesListItems.set(selectedIndex,airline);
        }
    };



    public void setData(List<Airlines> newList,boolean isSwitched){

        synchronized (this.isSwitched) {
            this.isSwitched = isSwitched;
        }

        synchronized (mAirlinesListItems) {
            mAirlinesListItems.clear();
            mAirlinesListItems.addAll(newList);
        }
        notifyDataSetChanged();
    }
}
