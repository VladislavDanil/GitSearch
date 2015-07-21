package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nitrogenium.githubsearch.R;

import java.util.ArrayList;

/**
 * Created by Nitrogenium on 21.07.15.
 */
public class AdapterRepositories  extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<RepositoriesElement> objects;
    RepositoriesElement repositories;
    public AdapterRepositories(Context context, ArrayList<RepositoriesElement> repositories) {
        ctx = context;
        objects = repositories;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_element, parent, false);
        }

        repositories= getRepositoriesElement(position);
        ((TextView) view.findViewById(R.id.name_repositories)).setText(repositories.name_rep);
        ((TextView) view.findViewById(R.id.login)).setText(repositories.login);
        ((ImageView) view.findViewById(R.id.avatar)).setImageResource(repositories.avatar_url);
        final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                // TODO Auto-generated method stub
                ratingBar.setRating(repositories.stargazers_count);
                ratingBar.isIndicator();
            }

        });
        return view;
    }
    RepositoriesElement getRepositoriesElement(int position) {
        return ((RepositoriesElement) getItem(position));
    }
}

