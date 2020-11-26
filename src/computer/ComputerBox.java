package computer;

import list.MyIterator;
import list.MyLinkedList;

import java.io.*;

//класс объектов типа корпус ПК
public class ComputerBox extends Part{
    //поля
    private String name;
    private double price;
    private String type;
    private String category;

    //список объектов
    public static MyLinkedList<ComputerBox> computerBoxList = new MyLinkedList<>();

    //конструктор
    public ComputerBox(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        //определяем ценовую категорию комплектующего
        if (price >= 30 && price < 60) {
            category = "minPrice";
            return;
        }
        if (price >= 60 && price < 100) {
            category = "lowPrice";
            return;
        }
        if (price >= 100 && price < 150) {
            category = "averagePrice";
            return;
        }
        if (price >= 150 && price < 210) {
            category = "midPrice";
            return;
        }
        if (price >= 210 && price < 300) {
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
    public static MyLinkedList<ComputerBox> sortByCategory(String requestCategory) {
        MyLinkedList<ComputerBox> requestedList = new MyLinkedList<>();

        MyIterator<ComputerBox> iter = new MyIterator<>(computerBoxList);
        iter.reset();

        while(iter.getCurrent() != null) {
            ComputerBox current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void saveComputerBoxList() {
        File computerBoxFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "computerBoxList.txt");

        MyIterator<ComputerBox> iter = new MyIterator<>(computerBoxList);
        iter.reset();
        try(FileWriter fw = new FileWriter(computerBoxFile, false)) {
            fw.write("id|name|price|type|");
            int i = 0;
            while (iter.getCurrent() != null) {
                ComputerBox current = iter.getCurrent().item;
                fw.write("\n"+ i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getType() + "|");
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getComputerBoxFromFile() {
        File computerBoxFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "computerBoxList.txt");

        MyLinkedList<String> result = new MyLinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(computerBoxFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.addLast(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        flag: for (int i = 1; i < result.size(); i++) {
            String line = result.get(i);
            String[] linesArr = new String[4];
            int index = 0;
            for (String temp : line.split("\\|")) {
                linesArr[index] = temp;
                index++;
            }
            String name = linesArr[1];
            double price = Double.parseDouble(linesArr[2]);
            String type = linesArr[3];

            MyIterator<ComputerBox> iter = new MyIterator<>(computerBoxList);
            iter.reset();

            while(iter.getCurrent() != null) {
                ComputerBox current = iter.getCurrent().item;
                if(current.name.equals(name)) {
                    iter.nextNode();
                    continue flag;
                }
                iter.nextNode();
            }

            computerBoxList.addLast(new ComputerBox(name, price, type));

        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
