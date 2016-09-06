package Bean;

/**
 * Created by 不一样的天空 on 2016/9/5.
 */
public class Conversation {
    private String title;
    private String lastContent;
    public String getLastContent() {
        return lastContent;
    }

    public String getTitle() {
        return title;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
