package zhongd.member.controller.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.member.controller.BaseController;
import zhongd.member.entity.DTO.mailbox.MailDTO;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.mailbox.IgMailboxService;
import zhongd.member.utils.constant.ReturnCode;

/**
 * @author xiezd 2018-01-19 21:06
 */
@RestController
@RequestMapping("/mail")
public class MailboxController extends BaseController{
    @Autowired
    private IgMailboxService mailboxService;

    @PostMapping("/send")
    public ReturnObj sendMail(@RequestBody MailDTO mail){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(mailboxService.save(mail, getCurrentMember().getIgMember().getIgMemberId()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;

    }
}
