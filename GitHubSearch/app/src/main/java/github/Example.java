package github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Object model query result.
 * @author Danilov Vladislav
 */
public class Example {

    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    public Boolean incompleteResults;
    @Expose
    public ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
