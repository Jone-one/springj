package example.controller;

import example.pojo.Book;
import example.pojo.RedisBean;
import example.service.BookService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/home")
public class index {
    @Resource
    BookService bookService;
    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public @ResponseBody
    String indexx() {
        return "index";
    }

    @RequestMapping("/test")
    public @ResponseBody
    List<Book> hello() {
        List<Book> Books = bookService.queryList();

        return Books;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public @ResponseBody
    String json() {
        String str = "{{name:liuyi,age:22},{sex:wwwman}}";
        return str;
    }

    @RequestMapping(value = "/setRedis", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> redis(@RequestBody RedisBean redisBean) throws UnsupportedEncodingException {
        System.out.println("post数据传递**********：" + redisBean.getKey() + ":" + redisBean.getValue());
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (valueOperations == null) {
            System.out.println("null+++++++++++++");
        }
        valueOperations.set(redisBean.getKey(), redisBean.getValue());
        System.out.println("存储redis后取值*****：" + valueOperations.get(redisBean.getKey()));
        Map<String, String> map = new HashMap<>();
        map.put("info", "success");
        return map;
    }

    @RequestMapping("/getRedis")
    public @ResponseBody HashMap<String,Object> getRedis(){
        Set<String> stringSet = redisTemplate.keys("*");
        HashMap<String,Object> hashMap = new HashMap<>();
        for (String key : stringSet){
            Object value = redisTemplate.opsForValue().get(key);
            hashMap.put(key,value);
            System.out.println("key:"+key+"   value:"+value);
        }
        return hashMap;
    }

    @RequestMapping("/redis")
    public ModelAndView toHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
