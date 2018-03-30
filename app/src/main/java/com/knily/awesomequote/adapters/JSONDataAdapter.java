package com.knily.awesomequote.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.knily.awesomequote.R;
import com.knily.awesomequote.helper.DateTimeFormat;
import com.knily.awesomequote.models.PostJSONData;

import java.util.ArrayList;
import java.util.List;

public class JSONDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<PostJSONData> postJSONData;
    private int FragmentNum = 0;
    /*private static HomeItemClickListener home;
    private static NewsItemClickListener news;
    private static CultureItemClickListener culture;
    private static EnterTainmentItemClickListener entertainment;
    private static MilitaryItemClickListener military;
    private static WomenItemClickListener women;
    private static NatureItemClickListener nature;
    private static FoodItemClickListener food;
    private static HistoryItemClickListener history;
    private static VideosItemClickListener videos;
    private static OpinionItemClickListener opinion;

    //Home
    public interface HomeItemClickListener{
        void onClickHome(int position);
    }
    public void setClickListener(HomeItemClickListener home){
        this.home=home;
    }
    //News
    public interface NewsItemClickListener{
        void onClickNews(int position);
    }
    public void setClickListener(NewsItemClickListener news){
        this.news=news;
    }
    //Culture
    public interface CultureItemClickListener{
        void onClickCulture(int position);
    }
    public void setClickListener(CultureItemClickListener culture){
        this.culture=culture;
    }
    //Entertainment
    public interface EnterTainmentItemClickListener{
        void onClickEntertainment(int position);
    }
    public void setClickListener(EnterTainmentItemClickListener entertainment){
        this.entertainment=entertainment;
    }
    //Military
    public interface MilitaryItemClickListener{
        void onClickMilitary(int position);
    }
    public void setClickListener(MilitaryItemClickListener military){
        this.military=military;
    }
    //Women
    public interface WomenItemClickListener{
        void onClickWomen(int position);
    }
    public void setClickListener(WomenItemClickListener women){
        this.women=women;
    }
    //Nature
    public interface NatureItemClickListener{
        void onClickNature(int position);
    }
    public void setClickListener(NatureItemClickListener nature){
        this.nature=nature;
    }
    //Food
    public interface FoodItemClickListener{
        void onClickFood(int position);
    }
    public void setClickListener(FoodItemClickListener food){
        this.food=food;
    }
    //History
    public interface HistoryItemClickListener{
        void onClickHistory(int position);
    }
    public void setClickListener(HistoryItemClickListener history){
        this.history=history;
    }
    //Videos
    public interface VideosItemClickListener{
        void onClickVideos(int position);
    }
    public void setClickListener(VideosItemClickListener videos){
        this.videos=videos;
    }
    //Opinion
    public interface OpinionItemClickListener{
        void onClickOpinion(int position);
    }
    public void setClickListener(OpinionItemClickListener opinion){
        this.opinion=opinion;
    }*/
    public static final int VIEW_PROG = 2;
    public static final int VIEW_FIRST = 0;
    public static final int VIEW_REST = 1;

    private ItemClickListener listener;

    public interface ItemClickListener {
        void onItemClick(PostJSONData data);
    }
/*
    //Return position.
    public PostJSONData getSelectedData(int position) {
        return postJSONData.get(position);
    }*/

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView title, subTitle;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.image_title);
            subTitle = (TextView) view.findViewById(R.id.image_subTitle);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public static class MyViewHolderForTitleImage extends RecyclerView.ViewHolder {
        private ImageView backdrop;
     //   private TextView title2, date;

        public MyViewHolderForTitleImage(View view) {
            super(view);
            backdrop = (ImageView) view.findViewById(R.id.backdrop);
       //     date = (TextView) view.findViewById(R.id.image_date);
        //    title2 = (TextView) view.findViewById(R.id.image_title2);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       // Log.d("Test viewType", "viewType ="+viewType);



       if (viewType == VIEW_REST || viewType == VIEW_FIRST) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row, parent, false);
            MyViewHolder holder = new MyViewHolder(itemView);
            setUpClickListener(holder);
            return holder;
        } else if (viewType == VIEW_PROG) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_dialog, parent, false);
            LoadingViewHolder holder2 = new LoadingViewHolder(itemView);
            return holder2;
        }


        throw new IllegalArgumentException("This viewType doesn't exist");
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
    public int getItemViewType(int position) {
        if (postJSONData.get(position) != null) {
            if (position == 0) {
                return VIEW_FIRST;
            }
            return VIEW_REST;
        } else
            return VIEW_PROG;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.title.setText(postJSONData.get(position).getTitle().getRendered().replaceAll("(&#8230;)|(&#8216;)|(&#038;)|(<i>)|(</i>)|(&#8217;)|(&#8221;)|(&#8220;)|(&#8211;)", "\'"));
            String newDate2 = DateTimeFormat.DateTimeFormat(postJSONData.get(position).getDate());
            myViewHolder.subTitle.setText(newDate2);

            //Log.d("ABCD", postJSONData.get(position).getId() + "");

            //Loading image using Glide library
            //Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
            //OR
            //Glide.with(myContext).load(data.getContent().getRendered()).into(holder.thumbnail);
            //OR
        /*Glide.with(context).load("https://github.com/pramod-locio/Images/blob/master/2015-08-07_14.52.214-200x200.jpg" + current.fishImage)
        .placeholder(R.drawable.ic_img_error)
                .error(R.drawable.ic_img_error)
                .into(myHolder.ivFish);*/
            //OR
            try {
        //        Glide.with(myViewHolder.thumbnail.getContext()).load(postJSONData.get(position)
       //                 .getBetterFeaturedImage().getMediaDetails().getSizes().getMediumThumb().getSourceUrl())
      //                  .into(myViewHolder.thumbnail);

                Glide.with(myViewHolder.thumbnail.getContext()).load(postJSONData.get(position)
                .getFeaturedImgURL()).into(myViewHolder.thumbnail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (holder instanceof MyViewHolderForTitleImage) {
            MyViewHolderForTitleImage myViewHolderForTitleImage = (MyViewHolderForTitleImage) holder;
           // StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) myViewHolderForTitleImage.itemView.getLayoutParams();
         //   params.setFullSpan(true);
            String newDate1 = DateTimeFormat.DateTimeFormat(postJSONData.get(position).getDate());
         //   myViewHolderForTitleImage.title2.setText(postJSONData.get(position).getTitle().getRendered().replaceAll("(&#8230;)|(&#8216;)|(&#8217;)|(&#8221;)|(&#8220;)|(&#8211;)|(<i>)|(</i>)", "\'"));
        //    myViewHolderForTitleImage.date.setText(newDate1);
            try {

                Glide.with(myViewHolderForTitleImage.backdrop.getContext()).load(postJSONData.get(position)
                        .getFeaturedImgURL()).into(myViewHolderForTitleImage.backdrop);
            //    Glide.with(myViewHolderForTitleImage.backdrop.getContext()).load(postJSONData.get(position)
            //            .getBetterFeaturedImage().getMediaDetails().getSizes().getMediumThumb().getSourceUrl())
             //           .into(myViewHolderForTitleImage.backdrop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder viewHolder = (LoadingViewHolder) holder;
        //    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
        //    params.setFullSpan(true);
            viewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return postJSONData.size();
    }

    public JSONDataAdapter(List<PostJSONData> postJSONData, ItemClickListener listener) {
        this.postJSONData = postJSONData;
        this.listener = listener;
    }

    public void addAllData(List<PostJSONData> dataList) {
        postJSONData.addAll(dataList);
        notifyDataSetChanged(); //화면 갱신
    }

    public void flash() {
        postJSONData.clear();
        notifyDataSetChanged();
    }

    public void addProgressDialog() {
        postJSONData.add(null);
    }

    public void removeProgressDialog() {
        postJSONData.remove(null);
        notifyDataSetChanged();
    }
    /*public void printData(String tag) {
        Log.d(tag, "printData: " + getItemCount());
    }*/

    public void setFilter(List<PostJSONData> newList) {

        postJSONData = new ArrayList<>();
        postJSONData.addAll(newList);
        notifyDataSetChanged();
    }
}