package computer;

import list.MyIterator;
import list.MyLinkedList;

import javax.xml.crypto.Data;
import java.io.*;

//класс объектов типа запоминающее устройство
public class DataStorage {
    //поля
    private String name;
    private double price;
    private int capacity;
    private String dataStorageInterface;
    private int buffer;
    private String type;
    private String category;

    //список объектов
    public static MyLinkedList<DataStorage> dataStorageList = new MyLinkedList<>();

    //конструктор
    public DataStorage(String name, double price, String type, int capacity, String dataStorageInterface, int buffer) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.capacity = capacity;
        this.dataStorageInterface = dataStorageInterface;
        this.buffer = buffer;
        //определить ценовую категорию
        if (type.equals("HDD")) {
            if (price >= 35 && price < 80) {
                category = "minPrice";
                return;
            }
            if (price >= 80 && price < 130) {
                category = "lowPrice";
                return;
            }
            if (price >= 130 && price < 200) {
                category = "averagePrice";
                return;
            }
            if (price >= 200 && price < 310) {
                category = "midPrice";
                return;
            }
            if (price >= 310 && price < 400) {
                category = "highPrice";
                return;
            }
            if (price >= 400) category = "superPrice";
        }
        if (type.equals("SSD")) {
            if (price >= 50 && price < 90) {
                category = "minPrice";
                return;
            }
            if (price >= 90 && price < 150) {
                category = "lowPrice";
                return;
            }
            if (price >= 150 && price < 230) {
                category = "averagePrice";
                return;
            }
            if (price >= 230 && price < 350) {
                category = "midPrice";
                return;
            }
            if (price >= 350 && price < 500) {
                category = "highPrice";
                return;
            }
            if (price >= 500) category = "superPrice";
        }
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
    public static MyLinkedList<DataStorage> sortByCategory(String requestCategory) {
        MyLinkedList<DataStorage> requestedList = new MyLinkedList<>();

        MyIterator<DataStorage> iter = new MyIterator<>(dataStorageList);
        iter.reset();

        while(iter.getCurrent() != null) {
            DataStorage current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void saveDataStorageList() {
        File dataStorageFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "dataStorageList.txt");

        MyIterator<DataStorage> iter = new MyIterator<>(dataStorageList);
        iter.reset();
        try(FileWriter fw = new FileWriter(dataStorageFile, false)) {
            fw.write("id|name|price|type|capacity|interface|buffer|");
            int i = 0;
            while (iter.getCurrent() != null) {
                DataStorage current = iter.getCurrent().item;
                fw.write("\n"+ i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getType() + "|"
                + current.getCapacity() + "|" + current.getDataStorageInterface() + "|" + current.getBuffer() + "|");
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getDataStorageFromFile() {
        File dataStorageFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "dataStorageList.txt");

        MyLinkedList<String> result = new MyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dataStorageFile))) {

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
            String type = linesArr[3];
            int capacity = Integer.parseInt(linesArr[4]);
            String dataStorageInterface = linesArr[5];
            int buffer = Integer.parseInt(linesArr[6]);

            MyIterator<DataStorage> iter = new MyIterator<>(dataStorageList);
            iter.reset();

            while(iter.getCurrent() != null) {
                DataStorage current = iter.getCurrent().item;
                if(current.name.equals(name)) {
                    iter.nextNode();
                    continue flag;
                }
                iter.nextNode();
            }

            dataStorageList.addLast(new DataStorage(name, price, type, capacity, dataStorageInterface, buffer));
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDataStorageInterface() {
        return dataStorageInterface;
    }

    public void setDataStorageInterface(String dataStorageInterface) {
        this.dataStorageInterface = dataStorageInterface;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }
}
