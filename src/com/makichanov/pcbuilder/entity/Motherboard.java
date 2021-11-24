package com.makichanov.pcbuilder.entity;

import com.makichanov.pcbuilder.util.MyIterator;
import com.makichanov.pcbuilder.util.MyLinkedList;

import java.io.*;

//класс объектов типа материнская плата
public class Motherboard {
    //поля
    private String name;
    private double price;
    private String socket;
    private String memoryType;
    private int maxMemory;
    private int usb;
    private String category;

    //список объектов
    public static MyLinkedList<Motherboard> motherboardList = new MyLinkedList<>();

    //конструктор
    public Motherboard(String name, double price, String socket, String memoryType, int maxMemory, int usb) {
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.memoryType = memoryType;
        this.maxMemory = maxMemory;
        this.usb = usb;

        //определить ценовую категорию
        if (price >= 58 && price < 100) {
            category = "minPrice";
            return;
        }
        if (price >= 100 && price < 170) {
            category = "lowPrice";
            return;
        }
        if (price >= 170 && price < 300) {
            category = "averagePrice";
            return;
        }
        if (price >= 300 && price < 450) {
            category = "midPrice";
            return;
        }
        if (price >= 450 && price < 600) {
            category = "highPrice";
            return;
        }
        if (price >= 600) category = "superPrice";
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
    public static MyLinkedList<Motherboard> sortByCategory(String requestCategory) {
        MyLinkedList<Motherboard> requestedList = new MyLinkedList<>();

        MyIterator<Motherboard> iter = new MyIterator<>(motherboardList);
        iter.reset();

        while(iter.getCurrent() != null) {
            Motherboard current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void saveMotherboardList() {
        File motherboardFile = new File(ComputerBox.class.getResource("/components/motherboardList.txt").getFile());

        MyIterator<Motherboard> iter = new MyIterator<>(motherboardList);
        iter.reset();
        try(FileWriter fw = new FileWriter(motherboardFile, false)) {
            fw.write("id|name|price|socket|memoryType|maxMemory|usb|");
            int i = 0;
            while (iter.getCurrent() != null) {
                Motherboard current = iter.getCurrent().item;
                fw.write("\n"+ i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getSocket()
                        + "|" + current.getMemoryType() + "|" + + current.getMaxMemory() + "|" + current.getUsb() + "|");
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getMotherboardsFromFile() {
        File motherboardFile = new File(ComputerBox.class.getResource("/components/motherboardList.txt").getFile());
        MyLinkedList<String> result = new MyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(motherboardFile))) {

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
            String socket = linesArr[3];
            String memoryType = linesArr[4];
            int maxMemory = Integer.parseInt(linesArr[5]);
            int usb = Integer.parseInt(linesArr[6]);

            MyIterator<Motherboard> iter = new MyIterator<>(motherboardList);
            iter.reset();

            while(iter.getCurrent() != null) {
                Motherboard current = iter.getCurrent().item;
                if(current.name.equals(name)) {
                    iter.nextNode();
                    continue flag;
                }
                iter.nextNode();
            }

            motherboardList.addLast(new Motherboard(name, price, socket, memoryType, maxMemory, usb));

        }

    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public int getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    public int getUsb() {
        return usb;
    }

    public void setUsb(int usb) {
        this.usb = usb;
    }
}
