package com.javalemon.serialize.hessian.test;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.javalemon.serialize.bean.Dog;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author lemon
 * @date 2019-08-10
 * @desc
 */

public class HessianTest {
    public static <T> byte[] serialize(T obj) {
        byte[] bytes = null;
        // 1、创建字节输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();


        HessianOutput hessianOutput = new HessianOutput(bos);

        try {
            // 注意，obj 必须实现Serializable接口
            hessianOutput.writeObject(obj);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static <T> T deserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        // 1、将字节数组转换成字节输入流
        ByteArrayInputStream bis = new ByteArrayInputStream(data);

        HessianInput hessianInput = new HessianInput(bis);
        Object object = null;

        try {
            object = hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (T) object;
    }

    @Test
    public void testSize() {
        byte[] serialize = serialize(Dog.getSizeCompareBean());
        System.out.println(serialize.length);

        Dog dog = deserialize(serialize);
        System.out.println(JSON.toJSONString(dog));
    }
}
