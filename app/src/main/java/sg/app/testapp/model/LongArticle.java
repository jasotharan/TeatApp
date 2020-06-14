package sg.app.testapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LongArticle implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("text")
    private String text;


    public LongArticle(Integer id, String title) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
