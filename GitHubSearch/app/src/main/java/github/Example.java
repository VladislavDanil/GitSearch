package github;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Object model query result.
 * @author Danilov Vladislav
 */
@DatabaseTable (tableName = "Example")
public class Example {
    @DatabaseField(generatedId = true)
    public int idbase;
    @DatabaseField
    public int id;
    @ForeignCollectionField(eager = true)
    public Collection<Item> item;
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
