package model;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class ConnectionParams {

    private int requsetId;
    private int postId;

    public ConnectionParams(int requsetId) {
        this.postId = 0;
        this.requsetId = requsetId;
    }

    public int getRequsetId() {
        return requsetId;
    }

    public void setRequsetId(int requsetId) {
        this.requsetId = requsetId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
