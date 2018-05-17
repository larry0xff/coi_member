package zhongd.member.entity.DTO.mailbox;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiezd 2018-01-19 21:10
 */
public class MailDTO {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private Integer toOrgId;

    private Date replyTime;
    private String orgName;
    private String replyName;
    private String replyContent;
    private String isRead;
    private String createName;


    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getToOrgId() {
        return toOrgId;
    }

    public void setToOrgId(Integer toOrgId) {
        this.toOrgId = toOrgId;
    }
}
