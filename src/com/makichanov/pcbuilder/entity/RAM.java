package com.makichanov.pcbuilder.entity;

import com.makichanov.pcbuilder.util.MyIterator;
import com.makichanov.pcbuilder.util.MyLinkedList;

import java.io.*;

//класс объектов типа оперативная память
public class RAM extends Part{
    //поля
    private String name;
    private double memory;
    private double price;
    private String memoryType;
    private double memoryClock;
    private String category;

    //список объектов
    public static MyLinkedList<RAM> ramList = new MyLinkedList<>();

    //конструктор
    public RAM(String name, double price, double memory, String memoryType, double memoryClock) {
        this.name = name;
        this.price = price;
        this.memory = memory;
        this.memoryType = memoryType;
        this.memoryClock = memoryClock;
        //определить ценовую категорию
        if (price >= 24 && price < 70) {
            category = "minPrice";
            return;
        }
        if (price >= 70 && price < 120) {
            category = "lowPrice";
            return;
        }
        if (price >= 120 && price < 170) {
            category = "averagePrice";
            return;
        }
        if (price >= 170 && price < 250) {
            category = "midPrice";
            return;
        }
        if (price >= 250 && price < 300) {
            category = "highPrice";
            return;
        }
        if (price >= 300) category = "superPrice";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //метод принимает строку-категорию, а возвращает список объектов, удовлетворяющих заданной категории
    public static MyLinkedList<RAM> sortByCategory(String requestCategory) {
        MyLinkedList<RAM> requestedList = new MyLinkedList<>();

        MyIterator<RAM> iter = new MyIterator<>(ramList);
        iter.reset();

        while(iter.getCurrent() != null) {
            RAM current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void saveRamList() {
        File ramFile = new File(ComputerBox.class.getResource("/components/ramList.txt").getFile());

        MyIterator<RAM> iter = new MyIterator<>(ramList);
        iter.reset();
        try(FileWriter fw = new FileWriter(ramFile, false)) {
            fw.write("id|name|price|");
            int i = 0;
            while (iter.getCurrent() != null) {
                RAM current = iter.getCurrent().item;
                fw.write("\n"+ i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getMemory()
                        + "|" + current.getMemoryType() + "|" + current.getMemoryClock() + "|");
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getRamFromFile() {
        File ramFile = new File(ComputerBox.class.getResource("/components/ramList.txt").getFile());

        MyLinkedList<String> result = new MyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ramFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.addLast(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        flag: for (int i = 1; i < result.size(); i++) {
            String line = result.get(i);
            String[] linesArr = new String[6];
            int index = 0;
            for (String temp : line.split("\\|")) {
                linesArr[index] = temp;
                index++;
            }
            String name = linesArr[1];
            double price = Double.parseDouble(linesArr[2]);
            double memory = Double.parseDouble(linesArr[3]);
            String memoryType = linesArr[4];
            double memoryClock = Double.parseDouble(linesArr[5]);

            MyIterator<RAM> iter = new MyIterator<>(ramList);
            iter.reset();

            while(iter.getCurrent() != null) {
                RAM current = iter.getCurrent().item;
                if(current.name.equals(name)) {
                    iter.nextNode();
                    continue flag;
                }
                iter.nextNode();
            }

            ramList.addLast(new RAM(name, price, memory, memoryType, memoryClock));

        }

    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public double getMemoryClock() {
        return memoryClock;
    }

    public void setMemoryClock(double memoryClock) {
        this.memoryClock = memoryClock;
    }
}
