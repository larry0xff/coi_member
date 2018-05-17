package zhongd.member.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhongd.member.controller.BaseController;
import zhongd.member.entity.DO.comment.IgComment;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.comment.IgCommentService;
import zhongd.member.utils.constant.ReturnCode;

/**
 * @author xiezd 2018-01-06 10:19
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Autowired
    IgCommentService igCommentService;

    /**
     * 评论列表
     * @param page 页码
     * @param pageSize 页面大小
     * @param isSelf 是否拿自己的
     * @return
     */
    @GetMapping("/list")
    public ReturnObj list(int page, int pageSize, String isSelf){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igCommentService.list(page, pageSize, isSelf.equals("Y")?getCurrentMember().getIgMember().getIgMemberId():0));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取评论列表失败");
        }
        return obj;
    }

    /**
     * 新增评论
     * @param comment
     * @return
     */
    @PostMapping("/save")
    public ReturnObj save(@RequestBody IgComment comment){
        ReturnObj obj = new ReturnObj();
        try{
            if(igCommentService.save(comment, getCurrentMember().getIgMember().getIgMemberId())){
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setMsg("评论失败");
                obj.setReturnCode(ReturnCode.FAIL);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("评论失败");
        }
        return obj;
    }

    /**
     * 点赞或踩
     * @param igCommentId
     * @param flag
     * @return
     */
    @GetMapping("/agree")
    public ReturnObj agree(Integer igCommentId, String flag){
        ReturnObj obj = new ReturnObj();
        try{
            if(igCommentService.agree(igCommentId,flag)){
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                obj.setMsg("失败");
                obj.setReturnCode(ReturnCode.FAIL);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("失败");
        }
        return obj;
    }

    /**
     * 获取未读评论数
     * @return
     */
    @GetMapping("/notifyCount")
    public ReturnObj notifyCount() {
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igCommentService.getNotifyCountById(getCurrentMember().getIgMember().getIgMemberId()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }

    @GetMapping("notifyList")
    public ReturnObj notifyList() {
        ReturnObj obj = new ReturnObj();
        try {
            obj.setData(igCommentService.notifyList(getCurrentMember().getIgMember().getIgMemberId()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
}
