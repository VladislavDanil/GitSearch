package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
public class AdapterRepositories extends BaseAdapter {

    /**
     * provides access to the underlying application functions:
     * access to resources in the file system, call activity, etc
     */
    Context mcCtx;
    /**
     * it creates a copy of the layout XML file as the corresponding objects
     */
    LayoutInflater mInflater;
    /**
     * image downloading, transformation, and caching manager
     */
    Picasso mPicasso;
    /**
     * array element object model contained adapter
     */
    ArrayList<RepositoriesElement> mObjects;
    /**
     * object representation of the contents of the adapter {@link RepositoriesElement}
     */
    RepositoriesElement mRepositoriesElement;

    /**
     * constructor retrieve a LayoutInflater for inflating layout resources in this context,
     * adding into the context Picasso,
     * object initialization {@link AdapterRepositories#mObjects}
     *
     * @param context      the current context
     * @param repositories an array of objects
     */
    public AdapterRepositories(Context context, ArrayList<RepositoriesElement> repositories) {
        mcCtx = context;
        mObjects = repositories;
        mInflater = (LayoutInflater) mcCtx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPicasso = Picasso.with(context);
    }

    /**
     * the number of elements in the array {@link AdapterRepositories#mObjects}
     *
     * @return format number integer
     */
    @Override
    public int getCount() {
        return mObjects.size();
    }

    /**
     * receiving element position and returns object the position value
     *
     * @param position position of the element in the array {@link AdapterRepositories#mObjects}
     * @return size {@link AdapterRepositories#mObjects}
     */
    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    /**
     * receives a position of an element and returns its index
     *
     * @param position position of the element in the array {@link AdapterRepositories#mObjects}
     * @return returns index {@link AdapterRepositories#mObjects}
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * creates adapter View
     * looking element of the position in {@link AdapterRepositories#mRepositoriesElement},
     * fills in the object model adapter
     *
     * @param position    position of the element in the array {@link AdapterRepositories#mObjects}
     * @param convertView the basic building block for user interface components
     * @param parent      is a special view that can contain other views (called children.)
     * @return viwe model adapter
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.list_element, parent, false);
        }

        mRepositoriesElement = getRepositoriesElement(position);
        ((TextView) view.findViewById(R.id.name_repositories)).setText("Название:" + "\n" + mRepositoriesElement.mNameRep);
        ((TextView) view.findViewById(R.id.login)).setText("Логин: " + mRepositoriesElement.mLogin);

        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        mPicasso.load(mRepositoriesElement.mAatarUrl).into(avatar);
        ((TextView) view.findViewById(R.id.star_rating)).setText(mRepositoriesElement.mStargazersCount);
        ((ImageView) view.findViewById(R.id.starView)).setImageResource(R.mipmap.ic_star);
        return view;
    }

    /**
     * return element for the position
     *
     * @param position position of the element in the array {@link AdapterRepositories#mObjects}
     * @return element for the position of {@link AdapterRepositories#mObjects}
     */
    RepositoriesElement getRepositoriesElement(int position) {
        return ((RepositoriesElement) getItem(position));
    }
}

