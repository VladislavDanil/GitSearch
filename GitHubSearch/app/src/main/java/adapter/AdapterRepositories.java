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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nitrogenium on 21.07.15.
 */
public class AdapterRepositories  extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    Picasso mPicasso;
    ArrayList<RepositoriesElement> objects;
    RepositoriesElement repositories;
    public AdapterRepositories(Context context, ArrayList<RepositoriesElement> repositories) {
        ctx = context;
        objects = repositories;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPicasso = Picasso.with(context);
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
        ((TextView) view.findViewById(R.id.name_repositories)).setText("Название:" + "\n" + repositories.name_rep);
        ((TextView) view.findViewById(R.id.login)).setText("Логин: " + repositories.login);

        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        mPicasso.load(repositories.avatar_url).into(avatar);
        ((TextView) view.findViewById(R.id.star_rating)).setText(repositories.stargazers_count);
        ((ImageView) view.findViewById(R.id.starView)).setImageResource(R.drawable.star);
        return view;
    }
    RepositoriesElement getRepositoriesElement(int position) {
        return ((RepositoriesElement) getItem(position));
    }
}

