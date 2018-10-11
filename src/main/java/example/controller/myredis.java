package example.controller;

import example.pojo.RedisBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/redis")
public class myredis {
    @Resource
    RedisTemplate<String, String> redisTemplate;
    /*
    访问redis操作页面
     */
    @RequestMapping("/index")
    public ModelAndView toHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*
    获取当前Redis所有键值对
     */
    @RequestMapping("/getRedis")
    public @ResponseBody Map<String,Object>  getRedis(){
        Set<String> keySet = redisTemplate.keys("*");
        Map<String,Object> dataMap = new HashMap<>();
        for(String key : keySet){
            Object value = redisTemplate.opsForValue().get(key);
            dataMap.put(key,value);
        }
        return dataMap;
    }
    /*
    当前Redis中添加键值对
     */
    @RequestMapping(value = "/addRedis",method = RequestMethod.POST)
    public @ResponseBody Boolean addRdis(@RequestBody RedisBean redisBean){
        redisTemplate.opsForValue().set(redisBean.getKey(),redisBean.getValue());
        if (redisTemplate.hasKey(redisBean.getKey())){
            return true;
        }
        return false;
    }


}
