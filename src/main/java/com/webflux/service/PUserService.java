package com.webflux.service;

import com.webflux.bean.PUser;
import com.webflux.dao.PUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@Service
public class PUserService {

    @Autowired
    private PUserDao userDao;

    public PUser findUserById(Long id){
        Optional<PUser> optional = userDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public List<PUser> findUserAll(){
        return userDao.findAll();
    }

    public Long addUser(PUser bean) {
        userDao.save(bean);
        return bean.getId();
    }

    public Long updateUser(PUser bean) {
        PUser target = findUserById(bean.getId());
        convertBean(bean,target);
        userDao.saveAndFlush(target);
        return 1L;
    }
    public Long deleteUser(Long id) {
        userDao.deleteById(id);
        return 1L;
    }
    //实体类赋值转换
    public <T> void convertBean(T source, T target) {
        Field[] sourceField = target.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < sourceField.length; i++) {
                String fieldName = sourceField[i].getName();
                String fieldName2 = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method mSet = target.getClass().getDeclaredMethod("set" + fieldName2, sourceField[i].getType());
                Method mGet = target.getClass().getDeclaredMethod("get" + fieldName2);

                Object value = mGet.invoke(source);
                if(value!=null){
                    // System.out.println("fieldName= " + fieldName + ", value= " + value);
                    mSet.invoke(target, value);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
