package model;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class ConnectionParams {

    private int requestId;
    private int postId;

    public ConnectionParams(int requestId) {
        this.postId = -1;
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getPostId() {
        postId++;
        return postId;
    }
}
