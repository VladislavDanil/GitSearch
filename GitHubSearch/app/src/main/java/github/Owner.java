package github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Object model query result.
 * @author Danilov Vladislav
 */
@DatabaseTable(tableName = "Owner")
public class Owner {
    @DatabaseField(generatedId = true)
    private int idbase;
    @Expose
    @DatabaseField()
    public String login;
    @Expose
    @DatabaseField()
    public Integer id;
    @SerializedName("avatar_url")
    @Expose
    @DatabaseField()
    public String avatarUrl;
    @SerializedName("gravatar_id")
    @Expose
    @DatabaseField()
    public String url;
    @Expose
    @DatabaseField()
    public String type;
}
