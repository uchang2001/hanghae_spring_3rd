package com.Week05.mybaemin.Controller;

import com.Week05.mybaemin.Entity.myorder;
import com.Week05.mybaemin.Repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class orderController {
    @Autowired
    private orderRepository orderrepository;

    @ResponseBody
    @GetMapping("/orders")
    public String getorderss(){
        List<myorder> myorders=orderrepository.findAll();
        String result="";
        for(myorder i : myorders){
            result+=i.getPrice();
            result+=",";

        }
        result=result.substring(0,result.length()-1);
        String realresult="["+result+"]";
//        System.out.println(realresult);
        return realresult;
    }


}
