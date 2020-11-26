package computer;

import list.MyIterator;
import list.MyLinkedList;

import java.io.*;

//класс объектов типа видеокарта
public class Videocard {
    //поля
    private double memory;
    private String name;
    private double clock;
    private int fans;
    private String videocardInterface;
    private double price;
    private String category;

    //список
    public static MyLinkedList<Videocard> videocardList = new MyLinkedList<>();

    //конструктор
    public Videocard(String name, double price, double memory, double clock, int fans, String videocardInterface) {
        this.name = name;
        this.price = price;
        this.memory = memory;
        this.clock = clock;
        this.fans = fans;
        this.videocardInterface = videocardInterface;
        //определить ценовую категорию
        if (price >= 70 && price < 140) {
            category = "minPrice";
            return;
        }
        if (price >= 140 && price < 200) {
            category = "lowPrice";
            return;
        }
        if (price >= 200 && price < 300) {
            category = "averagePrice";
            return;
        }
        if (price >= 300 && price < 400) {
            category = "midPrice";
            return;
        }
        if (price >= 400 && price < 650) {
            category = "highPrice";
            return;
        }
        if (price >= 650) category = "superPrice";
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getClock() {
        return clock;
    }

    public void setClock(double memoryClock) {
        this.clock = memoryClock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //метод принимает строку-категорию, а возвращает список объектов, удовлетворяющих заданной категории
    public static MyLinkedList<Videocard> sortByCategory(String requestCategory) {
        MyLinkedList<Videocard> requestedList = new MyLinkedList<>();

        MyIterator<Videocard> iter = new MyIterator<>(videocardList);
        iter.reset();

        while(iter.getCurrent() != null) {
            Videocard current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void saveVideocardList() {
        File videocardFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "videocardList.txt");


        MyIterator<Videocard> iter = new MyIterator<>(videocardList);
        iter.reset();
        try(FileWriter fw = new FileWriter(videocardFile, false)) {
            fw.write("id|name|price|");
            int i = 0;
            while (iter.getCurrent() != null) {
                Videocard current = iter.getCurrent().item;
                fw.write("\n"+ i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getMemory() + "|"
                        + current.getClock() + "|" + current.getFans() + "|" + current.getVideocardInterface() + "|");
                i++;
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getVideocardsFromFile() {
        File videocardFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "videocardList.txt");


        MyLinkedList<String> result = new MyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(videocardFile))) {

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
            double memory = Double.parseDouble(linesArr[3]);
            double clock = Double.parseDouble(linesArr[4]);
            int fans = Integer.parseInt(linesArr[5]);
            String videocardInterface = linesArr[6];

            MyIterator<Videocard> iter = new MyIterator<>(videocardList);
            iter.reset();

            while(iter.getCurrent() != null) {
                Videocard current = iter.getCurrent().item;
                if(current.name.equals(name)) {
                    iter.nextNode();
                    continue flag;
                }
                iter.nextNode();
            }

            videocardList.addLast(new Videocard(name, price, memory, clock, fans, videocardInterface));
        }

    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getVideocardInterface() {
        return videocardInterface;
    }

    public void setVideocardInterface(String videocardInterface) {
        this.videocardInterface = videocardInterface;
    }
}
