package com.makichanov.pcbuilder.entity;

import com.makichanov.pcbuilder.util.MyIterator;
import com.makichanov.pcbuilder.util.MyLinkedList;

import java.io.*;

//класс объектов типа блок питания
public class PSU {
    //поля
    private String name;
    private double price;
    private int capacity;
    private int efficiency;
    private double fanSize;
    private double maxAmperage;
    private String category;

    //список объектов
    public static MyLinkedList<PSU> psuList = new MyLinkedList<>();

    //конструктор
    public PSU(String name, double price, int capacity, int efficiency, double fanSize, double maxAmperage) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.efficiency = efficiency;
        this.fanSize = fanSize;
        this.maxAmperage = maxAmperage;
        //определить ценовую категорию
        if (price >= 23 && price < 90) {
            category = "minPrice";
            return;
        }
        if (price >= 90 && price < 130) {
            category = "lowPrice";
            return;
        }
        if (price >= 130 && price < 180) {
            category = "averagePrice";
            return;
        }
        if (price >= 180 && price < 250) {
            category = "midPrice";
            return;
        }
        if (price >= 250 && price < 320) {
            category = "highPrice";
            return;
        }
        if (price >= 320) category = "superPrice";
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
    public static MyLinkedList<PSU> sortByCategory(String requestCategory) {
        MyLinkedList<PSU> requestedList = new MyLinkedList<>();

        MyIterator<PSU> iter = new MyIterator<>(psuList);
        iter.reset();

        while(iter.getCurrent() != null) {
            PSU current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void savePsuList() {
        File psuFile = new File(ComputerBox.class.getResource("/components/psuList.txt").getFile());

        MyIterator<PSU> iter = new MyIterator<>(psuList);
        iter.reset();
        try(FileWriter fw = new FileWriter(psuFile, false)) {
            fw.write("id|name|price|capacity|efficiency|fanSize|maxAmperage|");
            int i = 0;
            while (iter.getCurrent() != null) {
                PSU current = iter.getCurrent().item;
                fw.write("\n"+ i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getCapacity()
                        + "|" + current.getEfficiency() + "|" + current.getFanSize() + "|" + current.getMaxAmperage() + "|");
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getPsuFromFile() {
        File psuFile = new File(ComputerBox.class.getResource("/components/psuList.txt").getFile());

        MyLinkedList<String> result = new MyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(psuFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.addLast(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        flag: for (int i = 1; i < result.size(); i++) {
            String line = result.get(i);
            String[] linesArr = new String[7];
            int index = 0;
            for (String temp : line.split("\\|")) {
                linesArr[index] = temp;
                index++;
            }
            String name = linesArr[1];
            double price = Double.parseDouble(linesArr[2]);
            int capacity = Integer.parseInt(linesArr[3]);
            int efficiency = Integer.parseInt(linesArr[4]);
            double fanSize = Double.parseDouble(linesArr[5]);
            double maxAmperage = Double.parseDouble(linesArr[6]);

            MyIterator<PSU> iter = new MyIterator<>(psuList);
            iter.reset();

            while(iter.getCurrent() != null) {
                PSU current = iter.getCurrent().item;
                if(current.name.equals(name)) {
                    iter.nextNode();
                    continue flag;
                }
                iter.nextNode();
            }

            psuList.addLast(new PSU(name, price, capacity, efficiency, fanSize, maxAmperage));

        }

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public double getFanSize() {
        return fanSize;
    }

    public void setFanSize(double fanSize) {
        this.fanSize = fanSize;
    }

    public double getMaxAmperage() {
        return maxAmperage;
    }

    public void setMaxAmperage(double maxAmperage) {
        this.maxAmperage = maxAmperage;
    }
}
