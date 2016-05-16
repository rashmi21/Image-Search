package assignment.outlook.com.outlookassignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rashmi Deokar on 15-05-2016.
 */
public class ImageSearchRes {

    private String batchcomplete;
    @SerializedName("continue")
    @Expose
    private Continue _continue;
    private Query query;

    /**
     * @return The batchcomplete
     */
    public String getBatchcomplete() {
        return batchcomplete;
    }

    /**
     * @param batchcomplete The batchcomplete
     */
    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    /**
     * @return The _continue
     */
    public Continue getContinue() {
        return _continue;
    }

    /**
     * @param _continue The continue
     */
    public void setContinue(Continue _continue) {
        this._continue = _continue;
    }

    /**
     * @return The query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * @param query The query
     */
    public void setQuery(Query query) {
        this.query = query;
    }
}
