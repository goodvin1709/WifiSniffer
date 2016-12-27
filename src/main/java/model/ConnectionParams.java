package model;

/**
 * Created by [Goodvin1709] on 27.12.2016.
 */
public class ConnectionParams {

    private String requsetId;
    private String activePage;
    private String postId;

    public ConnectionParams(String requsetId, String activePage, String postId) {
        this.requsetId = requsetId;
        this.activePage = activePage;
        this.postId = postId;
    }

    public String getRequsetId() {
        return requsetId;
    }

    public void setRequsetId(String requsetId) {
        this.requsetId = requsetId;
    }

    public String getActivePage() {
        return activePage;
    }

    public void setActivePage(String activePage) {
        this.activePage = activePage;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
