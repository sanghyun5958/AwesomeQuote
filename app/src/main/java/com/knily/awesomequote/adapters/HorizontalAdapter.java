package com.knily.awesomequote.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knily.awesomequote.R;
import com.knily.awesomequote.helper.DateTimeFormat;
import com.knily.awesomequote.models.PostJSONData;

import java.util.List;

/**
 * Created by takusemba on 2017/08/03.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {


    private List<PostJSONData> postJSONData;
    private JSONDataAdapter.ItemClickListener listener;
    private String[] titles;

    public HorizontalAdapter(List<PostJSONData> postJSONData, JSONDataAdapter.ItemClickListener listener) {
        this.postJSONData = postJSONData;
        this.listener = listener;
    }

    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horizontal, viewGroup, false);
        return new HorizontalAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final HorizontalAdapter.ViewHolder holder, int position) {
//        holder.title.setText(postJSONData.get(position).getTitle().getRendered().replaceAll("(&#8230;)|(&#8216;)|(&#038;)|(<i>)|(</i>)|(&#8217;)|(&#8221;)|(&#8220;)|(&#8211;)", "\'"));

        setUpClickListener(holder);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                PostJSONData data = postJSONData.get(position);
                listener.onItemClick(data);
            }
        });
        try {

            Glide.with(holder.img.getContext()).load(postJSONData.get(position)
                    .getFeaturedImgURL()).into(holder.img);
            String newDate2 = DateTimeFormat.DateTimeFormat(postJSONData.get(position).getDate());
            holder.title.setText(newDate2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setUpClickListener(final RecyclerView.ViewHolder holder) {



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                PostJSONData data = postJSONData.get(position);
                Log.d("ppppppppppppp", position + "");
                listener.onItemClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postJSONData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView img;
        private Button btn;

        ViewHolder(final View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.text_date);
            this.img = (ImageView)itemView.findViewById(R.id.imageview);
            this.btn = (Button)itemView.findViewById(R.id.click_button);

        }
    }

    public void addAllData(List<PostJSONData> dataList) {
        postJSONData.addAll(dataList);
        notifyDataSetChanged(); //화면 갱신
    }
}