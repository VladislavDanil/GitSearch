package github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
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
    private int id;
    @SerializedName("total_count")
    @Expose
    @DatabaseField
    public Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    @DatabaseField
    public Boolean incompleteResults;
    @Expose
    @ForeignCollectionField(eager = true)
    public Collection<Item> item;
}
