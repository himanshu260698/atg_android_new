package com.ATG.World.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.MarkNotificationResponse;
import com.ATG.World.models.Notification;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.utilities.DownloadImageTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 1/4/2018.
 */

public class NotificationAdapter extends BaseAdapter<Notification> implements EventsAdapter.OnItemClickListener{


    Context context;
    private FooterViewHolder footerViewHolder;

    public NotificationAdapter(List<Notification> list, Context context){
        super();
        items=list;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        return ( isLastPosition(position) &&  isFooterAdded ) ? FOOTER : ITEM ;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_all_layout, parent, false);

        final NotificationViewHolder holder = new NotificationViewHolder(view);


        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_footer, parent, false);

        final FooterViewHolder holder = new FooterViewHolder(view);
        holder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadClickListener != null) {
                    onReloadClickListener.onReloadClick();
                }
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                bindHeaderViewHolder(viewHolder);
                break;
            case ITEM:
                bindItemViewHolder(viewHolder, position);
                break;
            case FOOTER:
                bindFooterViewHolder(viewHolder);
                break;
            default:
                break;
        }
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final NotificationViewHolder holder = (NotificationViewHolder) viewHolder;

        final Notification noti = getItem(position);
        if (noti != null){
            holder.bind(noti);
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;
    }

    @Override
    protected void displayLoadMoreFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        add(new Notification());
    }
    @Override
    public void onItemClick(int position, View view) {
        Retrofit retrofit=new AtgClient().getClient();
        AtgService atg=retrofit.create(AtgService.class);
        Call<MarkNotificationResponse> call=atg.markNotificationRead(items.get(position).getUser_id());
        call.enqueue(new Callback<MarkNotificationResponse>() {
            @Override
            public void onResponse(Call<MarkNotificationResponse> call, Response<MarkNotificationResponse> response) {
                MarkNotificationResponse mark=response.body();
                Toast.makeText(context,mark.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MarkNotificationResponse> call, Throwable t) {
                Toast.makeText(context,"There seems to be an error!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.noti_message)
        TextView nmess;
        @BindView(R.id.noti_subject)
        TextView nsub;
        @BindView(R.id.user_name)
        TextView nuid;
        @BindView(R.id.noti_time)
        TextView ntime;
        @BindView(R.id.user_image)
        ImageView uImage;
        public NotificationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        private void bind(Notification article) {
            setupUserId(nuid, article);
            setupUserImage(uImage, article);
            setupMessage(nmess, article);
            setupSubject(nsub, article);
            setupTime(ntime, article);
            int adapterPos = getAdapterPosition();
        }
        private void setupUserId(TextView t,Notification article)
        {
            t.setText(article.getUser_id());
        }
        private void setupMessage(TextView t,Notification article)
        {
            t.setText(article.getMessage());
        }
        private void setupSubject(TextView t,Notification article)
        {
            t.setText(article.getSubject());
        }
        private void setupTime(TextView t,Notification article)
        {
            t.setText(article.getCreated_at());
        }
        private void setupUserImage(ImageView i,Notification  article)
        {
            //new DownloadImageTask(i).execute(article.getProfile_picture());
        }


    }
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.loading_layout)
        ProgressBar progressBar;
        @BindView(R.id.reload_btn)
        Button reloadButton;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
