package com.Week05.mybaemin.Controller;

import com.Week05.mybaemin.Entity.bregister;
import com.Week05.mybaemin.Entity.food;
import com.Week05.mybaemin.Entity.myorder;
import com.Week05.mybaemin.Repository.bregisterRepository;
import com.Week05.mybaemin.Repository.foodRepository;
import com.Week05.mybaemin.Repository.orderRepository;
import com.Week05.mybaemin.model.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class registerController {

    @Autowired
    private bregisterRepository bregisterrepository;
    @Autowired
    private foodRepository foodrepository;
    @Autowired
    private orderRepository orderrepository;

    @ResponseBody
    @GetMapping("/restaurants")
    public List<bregister> bregisters(){
        List<bregister> bregisterList= bregisterrepository.findAll();
        System.out.println(bregisterList);
        return bregisterList;

    }
    @ResponseBody
    @PostMapping("/restaurant/register")
    public bregister writebregister(@RequestBody bregisterdto bregister2){

        bregister bregister1=new bregister(bregister2);
//        System.out.println(bregister1.getName());
        if(bregister1.getMinOrderPrice()%100!=0)
            throw new IllegalArgumentException("100원 단위로 입력하세요");
        if((bregister1.getMinOrderPrice()<1000)||(bregister1.getMinOrderPrice()>100000))
            throw new IllegalArgumentException("1000~100000사이로 입력하세요");
        if(bregister1.getDeliveryfee()%500!=0)
            throw new IllegalArgumentException("500원 단위로 입력하세요");
        if((bregister1.getDeliveryfee()<0)||(bregister1.getDeliveryfee()>10000))
            throw new IllegalArgumentException("0~10000원 사이로 입력하세요");

        return bregisterrepository.save(bregister1);


    }
    @ResponseBody
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public List<food> writefood(@PathVariable("restaurantId") Integer rid, @RequestBody List<food> foods){
        for(food food : foods){
            food.setRid(rid);
            if((food.getPrice()<100)||(food.getPrice()>1000000))
                throw new IllegalArgumentException("100~1000000사이로 입력하세요");

            foodrepository.save(food);
        }

        return foods;
    }
    @ResponseBody
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<food> menulist(@PathVariable("restaurantId") Integer rid){
        List<food> menu=foodrepository.findAllByRid(rid);
//        for(food a : menu)
//        {
//
//        }
        return menu;

    }
    @ResponseBody
    @PostMapping("/order/request")
    public Responsemodel order(@RequestBody postmodel postmodel){
        Responsemodel responsemodel=new Responsemodel();

        Optional<bregister> bregister=bregisterrepository.findById(postmodel.getRestaurantId());
        bregister aa=bregister.get();

        foodmodel[] fooda=postmodel.getFoods();
        foodmodel2[] foodb =new foodmodel2[fooda.length];
        for(int i=0;i<fooda.length;i++)
            foodb[i]=new foodmodel2();

//        foodb[0].setQunatity(2);
//        System.out.println(fooda.g);
        int sum=0;
        for(int i=0;i<fooda.length;i++) {
            Optional<food> food = foodrepository.findById(fooda[i].getId());
            food bb = food.get();
//            System.out.println(bb.getId());
//            System.out.println(bb.getPrice());
//            System.out.println(bb.getName());
            foodb[i].setName(bb.getName());
            foodb[i].setPrice(bb.getPrice());
            foodb[i].setQunatity(fooda[i].getQuantity());
            sum+=foodb[i].getPrice()*foodb[i].getQunatity();
        }

//        fooda[0].getId();
//        bregister bregister=new bregister();
        responsemodel.setRestaurantName(aa.getName());
        responsemodel.setDeliveryFee(aa.getDeliveryfee());
        responsemodel.setFoods(foodb);
        sum+=responsemodel.getDeliveryFee();
        responsemodel.setTotalPrice(sum);
//        responsemodel.setRestaurantName(bregister);
//        System.out.println(responsemodel);
        Gson gson= new Gson();
        String jsss=gson.toJson(responsemodel);
//        System.out.println(jsss);
        myorder jj= new myorder();
//        jj.setId(1);
        jj.setPrice(jsss);
//        System.out.println(jj);
        orderrepository.save(jj);

        return responsemodel;
    }









}
