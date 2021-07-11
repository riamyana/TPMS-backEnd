package com.trasportManagement.transportservice.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private static final Integer EXPIRE_MINS = 10;
    private LoadingCache<String, Integer> otpCache;

    public OTPService(){
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    public int generateOTP(String userName){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(userName, otp);
        return otp;
    }

    public int getOtp(String userName){
        try{
            return otpCache.get(userName);
        }catch (Exception e){
            return 0;
        }
    }

    public void clearOTP(String userName){
        otpCache.invalidate(userName);
    }
}
