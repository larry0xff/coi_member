package zhongd.member.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.member.controller.BaseController;
import zhongd.member.entity.DTO.advice.IgAdviceCollectionDTO;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.advice.IgAdviceService;
import zhongd.member.utils.constant.ReturnCode;

/**
 * @author xiezd 2018-01-06 17:30
 */
@RestController
@RequestMapping("/advice")
public class AdviceController extends BaseController {
    @Autowired
    IgAdviceService igAdviceService;

    @GetMapping("/collection/list")
    public ReturnObj getCollections(){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igAdviceService.list(new IgAdviceCollectionDTO()));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取失败！");
        }
        return obj;
    }

    /**
     * 获取单个征集信息
     * @param igAdviceCollectionId
     * @return
     */
    @GetMapping("/collection/get")
    public ReturnObj getById(Integer igAdviceCollectionId){
        ReturnObj obj = new ReturnObj();
        try{
            obj.setData(igAdviceService.getById(igAdviceCollectionId));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取失败！");
        }
        return obj;
    }

    /**
     * 获取意见记录
     * @param igAdviceCollectionId
     * @return
     */
    @GetMapping("/getMemberAdviceById")
    public ReturnObj getMemberAdviceById(Integer igAdviceCollectionId){
        ReturnObj obj = new ReturnObj();
        try{
            Integer igMemberId = getCurrentMember().getIgMember().getIgMemberId();
            obj.setData(igAdviceService.getByMemberIdAndCollectionId(igMemberId, igAdviceCollectionId));
            obj.setReturnCode(ReturnCode.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg("获取失败！");
        }
        return obj;
    }

    /**
     * 保存用户征集意见
     * @param content 内容
     * @param igAdviceCollectionId 征集id
     * @return
     */
    @PostMapping("/save")
    public ReturnObj save(String content, Integer igAdviceCollectionId){
        ReturnObj obj = new ReturnObj();
        try{
            Integer memberId = getCurrentMember().getIgMember().getIgMemberId();
            obj.setData(igAdviceService.saveRecord(content, igAdviceCollectionId, memberId));
            obj.setReturnCode(ReturnCode.SUCCESS);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
}
