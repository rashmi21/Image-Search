package assignment.outlook.com.outlookassignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rashmi Deokar on 15-05-2016.
 */
public class Page implements Comparable<Page> {
    @SerializedName("pageid")
    @Expose
    private Integer pageid;
    @SerializedName("ns")
    @Expose
    private Integer ns;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    /**
     * @return The pageid
     */
    public Integer getPageid() {
        return pageid;
    }

    /**
     * @param pageid The pageid
     */
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    /**
     * @return The ns
     */
    public Integer getNs() {
        return ns;
    }

    /**
     * @param ns The ns
     */
    public void setNs(Integer ns) {
        this.ns = ns;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index The index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return The thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail The thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int compareTo(Page another) {
        return Integer.compare(index, another.getIndex());
    }
}