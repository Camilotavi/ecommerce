package com.example.demo.service;


import com.example.demo.entity.Coupon;
import com.example.demo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CouponService {

    @Autowired
    private CouponRepository repository;

    @Autowired
    private UserService userService;


    public Coupon getCouponById(int id){
        return this.repository.findCouponByuser(id);
    }

    public Coupon denyCoupon(String name) {
        Coupon coupon = this.repository.findCouponByuser(this.userService.getIdByEmail(name));
        coupon.setUsed(1);

        return repository.save(coupon);
    }

    public void createCoupon(String email){
        int userId = userService.getIdByEmail(email);

        Coupon coupon = new Coupon();

        coupon.setCode(generateRandomString());
        coupon.setUser(userId);
        coupon.setDiscount(50000);
        coupon.setUsed(0);

        this.repository.save(coupon);
    }

    public static String generateRandomString() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(uppercaseLetters.length());
            result.append(uppercaseLetters.charAt(index));
        }

        return result.toString();
    }
}
