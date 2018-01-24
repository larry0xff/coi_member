package zhongd.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhongd.member.dao.movie.MovieMapper;
import zhongd.member.entity.ReturnObj;
import zhongd.member.utils.constant.ReturnCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-01-24 12:52
 */
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieMapper movieMapper;
    @PostMapping("/search")
    public ReturnObj search(String condition){
        ReturnObj obj = new ReturnObj();
        try{
            Map<String, Object> data = new HashMap<>();
            List<Map<String, Object>> dataList = movieMapper.search("%" + condition + "%");
            data.put("dataList", dataList);
            data.put("count", dataList.size());
            obj.setData(data);
        } catch (Exception e){
            obj.setReturnCode(ReturnCode.FAIL);
        }
        return obj;
    }
}
