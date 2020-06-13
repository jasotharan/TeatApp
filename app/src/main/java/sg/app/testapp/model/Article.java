package sg.app.testapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Article implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("last_update")
    private String last_update;
    @SerializedName("short_description")
    private String short_description;
    @SerializedName("avatar")
    private String avatar;

    public Article(Integer id, String title, String avatar) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
