package assignment.outlook.com.outlookassignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rashmi Deokar on 15-05-2016.
 */
public class Continue {
    private Integer gpsoffset;

    @SerializedName("continue")
    @Expose
    private String _continue;

    /**
     * @return The gpsoffset
     */
    public Integer getGpsoffset() {
        return gpsoffset;
    }

    /**
     * @param gpsoffset The gpsoffset
     */
    public void setGpsoffset(Integer gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    /**
     * @return The _continue
     */
    public String getContinue() {
        return _continue;
    }

    /**
     * @param _continue The continue
     */
    public void setContinue(String _continue) {
        this._continue = _continue;
    }
}
