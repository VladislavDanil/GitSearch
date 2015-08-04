package github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Object model query result.
 * @author Danilov Vladislav
 */
@DatabaseTable(tableName = "Item")
public class Item {
    @DatabaseField(generatedId = true)
    public int idbase;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Example example;
    @Expose
    @DatabaseField
    public Integer id;
    @Expose
    @DatabaseField
    public String name;
    @Expose
    @DatabaseField(foreign = true)
    public Owner owner;
    @SerializedName("html_url")
    @Expose
    @DatabaseField
    public String htmlUrl;
    @Expose
    @DatabaseField
    public String url;
    @Expose
    @DatabaseField
    public Integer size;
    @SerializedName("stargazers_count")
    @Expose
    @DatabaseField
    public Integer stargazersCount;
    @Expose
    @DatabaseField
    public Object language;
}
