package zhongd.member.controller.member;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhongd.member.controller.BaseController;
import zhongd.member.entity.ReturnObj;
import zhongd.member.service.member.IgMemberService;
import zhongd.member.utils.ImageUtil;
import zhongd.member.utils.constant.ReturnCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author xiezd 2018-01-04 10:50
 */
@RestController
public class PermissionController extends BaseController {

    @Autowired
    private IgMemberService igMemberService;
    @PostMapping("/login")
    public ReturnObj login(HttpServletRequest request){
        return igMemberService.login(request);
    }

    @RequestMapping(value = {"/logout", "/"})
    public void unlogin() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ReturnObj denied() {
        ReturnObj rtobj= new ReturnObj();
        rtobj.setReturnCode(ReturnCode.NO_PERMISSION);
        rtobj.setMsg("没有权限进行此操作");
        return rtobj;
    }

    /**
     * 生成二维码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/verifycode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String randomString = ImageUtil.getRandomString();
        request.getSession().setAttribute("loginVerifyCode",randomString);
        BufferedImage bufferedImage = ImageUtil.generateVerifyCode(randomString,60, 18);
        ServletOutputStream out = response.getOutputStream();
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //encoder.encode(bufferedImage);
        ImageIO.write(bufferedImage, "jpeg", out);
        out.close();
    }
}
