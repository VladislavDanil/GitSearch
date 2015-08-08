package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nitrogenium.githubsearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * The realization object and methods of the adapter layout list_element,
 * for use in the list layout result.
 *
 * @author Danilov Vladislav
 */


public class AdapterRepositories extends RecyclerView.Adapter<AdapterRepositories.Repositories> {
    /**
     * array element object model contained adapter
     */
    ArrayList<RepositoriesElement> mObjects;

    /**
     * image downloading, transformation, and caching manager
     */
    Picasso mPicasso;
    /**
     * implements a class containing the elements of the adapter
     */
    RepositoriesElement mRepositoriesElement;

    public AdapterRepositories(ArrayList<RepositoriesElement> mObjects) {
        this.mObjects = mObjects;
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    /**
     * method initializes the elements of the adapter
     *
     * @param repositories current data repository
     * @param i            the position of an object
     */
    @Override
    public void onBindViewHolder(Repositories repositories, int i) {
        mRepositoriesElement = mObjects.get(i);
        final Context context = repositories.mAvatar.getContext();
        mPicasso.with(context).load(mRepositoriesElement.mAatarUrl).into(repositories.mAvatar);
        repositories.mLogin.setText("Логин: " + mRepositoriesElement.mLogin);
        repositories.mNameRepositories.setText("Название:" + "\n" + mRepositoriesElement.mNameRep);
        repositories.mStarRating.setText(mRepositoriesElement.mStargazersCount);
        repositories.mStarView.setImageResource(R.mipmap.ic_star);
        repositories.mURL = mRepositoriesElement.mURL;
        repositories.mListEl.setOnClickListener(new View.OnClickListener() {
            //позволяет переходить в браузер по нажатию на элемент
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepositoriesElement.mURL));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public Repositories onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_element, viewGroup, false);

        return new Repositories(itemView);
    }


    public static class Repositories extends RecyclerView.ViewHolder {
        /**
         * link to a website of the repository
         */
        String mURL;
        /**
         * provides access to the underlying application functions:
         * access to resources in the file system, call activity, etc
         */
        Context mcCtx;
        /**
         * it creates a copy of the layout XML file as the corresponding objects
         */
        LayoutInflater mInflater;


        public String getmURL() {
            return mURL;
        }

        TextView mNameRepositories;
        TextView mLogin;
        ImageView mAvatar;
        TextView mStarRating;
        ImageView mStarView;
        RelativeLayout mListEl;


        /**
         * constructor provides a brief list of the contents of the adapter
         *
         * @param v the current context
         */
        public Repositories(View v) {
            super(v);
            mNameRepositories = ((TextView) v.findViewById(R.id.name_repositories));
            mLogin = ((TextView) v.findViewById(R.id.login));
            mAvatar = (ImageView) v.findViewById(R.id.avatar);
            mStarRating = ((TextView) v.findViewById(R.id.star_rating));
            mStarView = ((ImageView) v.findViewById(R.id.starView));
            mListEl = (RelativeLayout) v.findViewById(R.id.list_el);
        }

    }
}

