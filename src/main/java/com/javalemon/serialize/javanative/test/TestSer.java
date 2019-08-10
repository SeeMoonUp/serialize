package com.javalemon.serialize.javanative.test;

import com.alibaba.fastjson.JSON;
import com.javalemon.serialize.bean.Dog;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lemon
 * @date 2019-08-04
 * @desc
 */

public class TestSer {

    @Test
    public void deserializeList() throws IOException{
        ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("/Users/lvxiran/dev/input/dog.text")
                )
        );
        try {

            Object o = inputStream.readObject();

            System.out.println(JSON.toJSONString(o));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    @Test
    public void serializeList() {
        try {

            List<Dog> dogs = new ArrayList<Dog>();
            Dog dog = new Dog();
            dog.name = "123";
            dogs.add(dog);
            Dog dog2 = new Dog();
            dog2.name = "234";
            dogs.add(dog2);

            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream("/Users/lvxiran/dev/input/dog.text"))
            );

            outputStream.writeObject(dogs);

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserialize()  throws IOException{
        ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("/Users/lvxiran/dev/input/dog.text")
                )
        );
        try {

            int size = inputStream.readInt();
            List<Dog> dogs = new ArrayList<Dog>(size);
            for (int i = 0; i < size; i++) {
                try {
                    dogs.add((Dog) inputStream.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(JSON.toJSONString(dogs));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    @Test
    public void serialize() {
        try {

            List<Dog> dogs = new ArrayList<Dog>();
            Dog dog = new Dog();
            dog.name = "123";
            dogs.add(dog);
            Dog dog2 = new Dog();
            dog2.name = "234";
            dogs.add(dog2);

            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream("/Users/lvxiran/dev/input/dog.text"))
            );

            outputStream.writeInt(dogs.size());
            for (Dog oneDog : dogs) {
                outputStream.writeObject(oneDog);
            }

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSize() throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(
                bos
        );
        outputStream.writeObject(Dog.getSizeCompareBean());
        byte[] bytes = bos.toByteArray();
        System.out.println(bytes.length);
        System.out.println(JSON.toJSONString(Dog.getSizeCompareBean()));

    }
}
