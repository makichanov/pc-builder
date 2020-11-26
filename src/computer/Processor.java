package computer;

import list.MyIterator;
import list.MyLinkedList;

import java.io.*;

//класс объектов типа процессор
public class Processor extends Part{
    //поля
    private String name;
    private double clock;
    private double price;
    private String architecture;
    private int cores;
    private int cache;
    private int techProcess;
    public String category;

    //список
    public static MyLinkedList<Processor> processorList = new MyLinkedList<>();

    //конструктор
    public Processor(String name, double price, double clock, String architecture, int cores, int cache, int techProcess) {
        this.name = name;
        this.price = price;
        this.clock = clock;
        this.cores = cores;
        this.cache = cache;
        this.techProcess = techProcess;
        this.architecture = architecture;
        //определить ценовую категорию
        if (price >= 50 && price < 120) {
            category = "minPrice";
            return;
        }
        if (price >= 120 && price < 250) {
            category = "lowPrice";
            return;
        }
        if (price >= 250 && price < 350) {
            category = "averagePrice";
            return;
        }
        if (price >= 350 && price < 450) {
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

    public void setName(String name) {
        this.name = name;
    }

    public double getClock() {
        return clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }


    /**
     * Первый этап сортировки - отбираем комплектующие по категории(низкая, средняя и высокая ценовая категория)
     *
     * @param requestCategory принимает категорию
     * @return список комплектующих, удовлетворяющих заданной категории
     */
    public static MyLinkedList<Processor> sortByCategory(String requestCategory) {
        MyLinkedList<Processor> requestedList = new MyLinkedList<>();

        MyIterator<Processor> iter = new MyIterator<>(processorList);
        iter.reset();

        while (iter.getCurrent() != null) {
            Processor current = iter.getCurrent().item;

            if (current.category.equals(requestCategory)) requestedList.addLast(current);
            iter.nextNode();
        }

        return requestedList;
    }

    //сохранить список в файл
    public static void saveProcessorList() throws IOException {
        MyIterator<Processor> iter = new MyIterator<>(processorList);
        iter.reset();

        File processorFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "processorList.txt");
        try (FileWriter fw = new FileWriter(processorFile, false)) {
            fw.write("id|name|price|clock|architecture|");
            int i = 0;
            while (iter.getCurrent() != null) {
                Processor current = iter.getCurrent().item;
                fw.write("\n" + i + "|" + current.getName() + "|" + current.getPrice() + "|" + current.getClock()
                        + "|" + current.getArchitecture() + "|" + current.getCores() + "|" + current.getCache() + "|"
                        + current.getTechProcess() + "|");
                i++;
                iter.nextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузить список из файла
    public static void getProcessorsFromFile() throws IOException {
        File processorFile = new File("C://Users//User//IdeaProjects//PCComp//src//files", "processorList.txt");

        MyLinkedList<String> result = new MyLinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(processorFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.addLast(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < result.size(); i++) {
            String line = result.get(i);
            String[] linesArr = new String[8];
            int index = 0;
            for (String temp : line.split("\\|")) {
                linesArr[index] = temp;
                index++;
            }
            String name = linesArr[1];
            double price = Double.parseDouble(linesArr[2]);
            double clock = Double.parseDouble(linesArr[3]);
            String architecture = linesArr[4];
            int cores = Integer.parseInt(linesArr[5]);
            int cache = Integer.parseInt(linesArr[6]);
            int techProcess = Integer.parseInt(linesArr[7]);

            processorList.addLast(new Processor(name, price, clock, architecture, cores, cache, techProcess));
        }

    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getTechProcess() {
        return techProcess;
    }

    public void setTechProcess(int techProcess) {
        this.techProcess = techProcess;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }
}
