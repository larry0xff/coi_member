package zhongd.member.entity.DTO.mailbox;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
