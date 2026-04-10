package com.makichanov.pcbuilder.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.makichanov.pcbuilder.entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.makichanov.pcbuilder.util.MyIterator;
import com.makichanov.pcbuilder.util.MyLinkedList;

//контроллер окна добавления
public class AddPartController {

    //объект Stage данного окна
    private Stage dialogStage;

    @FXML
    private ComboBox<String> partType;

    @FXML
    private Button cancelBtn;

    @FXML
    private GridPane processorAddPane;

    @FXML
    private TextField newProcessorName;

    @FXML
    private TextField newProcessorPrice;

    @FXML
    private Button confirmProcessor;

    @FXML
    private TextField newProcessorClock;

    @FXML
    private TextField newProcessorCores;

    @FXML
    private TextField newProcessorCache;

    @FXML
    private TextField newProcessorTechProcess;

    @FXML
    private TextField newProcessorArchitecture;


    @FXML
    private GridPane videocardAddPane;

    @FXML
    private TextField newVideocardName;

    @FXML
    private TextField newVideocardPrice;

    @FXML
    private Button confirmVideocard;

    @FXML
    private TextField newVideocardInterface;

    @FXML
    private TextField newVideocardMemory;

    @FXML
    private TextField newVideocardClock;

    @FXML
    private TextField newVideocardFans;

    @FXML
    private GridPane motherboardAddPane;

    @FXML
    private TextField newMotherboardName;

    @FXML
    private TextField newMotherboardPrice;

    @FXML
    private Button confirmMotherboard;

    @FXML
    private TextField newMotherboardSocket;

    @FXML
    private TextField newMotherboardMemoryType;

    @FXML
    private TextField newMotherboardMaxMemory;

    @FXML
    private TextField newMotherboardUSB;

    @FXML
    private GridPane ramAddPane;

    @FXML
    private TextField newRamName;

    @FXML
    private TextField newRamPrice;

    @FXML
    private Button confirmRam;

    @FXML
    private TextField newRamMemory;

    @FXML
    private TextField newRamMemoryType;

    @FXML
    private TextField newRamMemoryClock;

    @FXML
    private GridPane psuAddPane;

    @FXML
    private TextField newPsuName;

    @FXML
    private TextField newPsuPrice;

    @FXML
    private Button confirmPsu;

    @FXML
    private TextField newPsuCapacity;

    @FXML
    private TextField newPsuEfficiency;

    @FXML
    private TextField newPsuFanSize;

    @FXML
    private TextField newPsuMaxAmperage;

    @FXML
    private GridPane dataStorageAddPane;

    @FXML
    private TextField newDataStorageName;

    @FXML
    private TextField newDataStoragePrice;

    @FXML
    private Button confirmDataStorage;

    @FXML
    private TextField newDataStorageType;

    @FXML
    private TextField newDataStorageCapacity;

    @FXML
    private TextField newDataStorageInterface;

    @FXML
    private TextField newDataStorageBuffer;

    @FXML
    private GridPane computerBoxAddPane;

    @FXML
    private TextField newComputerBoxName;

    @FXML
    private TextField newComputerBoxPrice;

    @FXML
    private Button confirmComputerBox;

    @FXML
    private TextField newComputerBoxType;

    //Список содержит меню добавления
    MyLinkedList<GridPane> addPanes = new MyLinkedList<>();

    @FXML
    void initialize() {

        //добавляем все меню добавления в список
        addPanes.addLast(processorAddPane);
        addPanes.addLast(videocardAddPane);
        addPanes.addLast(motherboardAddPane);
        addPanes.addLast(ramAddPane);
        addPanes.addLast(psuAddPane);
        addPanes.addLast(computerBoxAddPane);
        addPanes.addLast(dataStorageAddPane);

        //ни один элемент не отображается
        setVisibility(null);

        //заполнить элемент ComboBox элементами
        partType.getItems().addAll("Процессор", "Видеокарта", "Материнская плата", "Оперативная память", "Блок питания", "Запоминающее устройство", "Корпус ПК");
        //инициализировать ComboBox
        partType.setValue("Выберите тип комплектующего");

        //слушатель изменений для ComboBox
        //при выборе определенного варианта, устанавливаем видимость для соответствующей панели
        partType.setOnAction(event -> {

            switch (partType.getValue()) {
                case "Процессор":
                    setVisibility(processorAddPane);
                    break;
                case "Видеокарта":
                    setVisibility(videocardAddPane);
                    break;
                case "Материнская плата":
                    setVisibility(motherboardAddPane);
                    break;
                case "Блок питания":
                    setVisibility(psuAddPane);
                    break;
                case "Оперативная память":
                    setVisibility(ramAddPane);
                    break;
                case "Запоминающее устройство":
                    setVisibility(dataStorageAddPane);
                    break;
                case "Корпус ПК":
                    setVisibility(computerBoxAddPane);
                    break;
            }
        });

    }

    //сеттер для объекта Stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //вызывается, когда пользователь нажал добавить процессор
    @FXML
    private void handleAddProcessor() {

        if(newProcessorName.getText().isEmpty() || newProcessorPrice.getText().isEmpty() || newProcessorClock.getText().isEmpty()
                || newProcessorCores.getText().isEmpty() || newProcessorCache.getText().isEmpty() || newProcessorArchitecture.getText().isEmpty()
        || newProcessorTechProcess.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newProcessorName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено имя", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newProcessorPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newProcessorClock.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена частота", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[a-zA-Z][0-9][\\s][\\+]]", newProcessorArchitecture.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена архитектура", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newProcessorCores.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено кол-во ядер", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newProcessorCache.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен размер кэша", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9]]", newProcessorTechProcess.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено значение техпроцесса", "Повторите ввод");
            return;
        }

        String name = newProcessorName.getText();
        double price = Double.parseDouble(newProcessorPrice.getText().trim());
        double clock = Double.parseDouble(newProcessorClock.getText().trim());
        String architecture = newProcessorArchitecture.getText().trim();
        int cores = Integer.parseInt(newProcessorCores.getText().trim());
        int cache = Integer.parseInt(newProcessorCache.getText().trim());
        int techProcess = Integer.parseInt(newProcessorTechProcess.getText().trim());

        newProcessorName.clear();
        newProcessorPrice.clear();
        newProcessorClock.clear();
        newProcessorTechProcess.clear();
        newProcessorArchitecture.clear();
        newProcessorCache.clear();
        newProcessorCores.clear();

        Processor.processorList.addLast(new Processor(name, price, clock, architecture, cores, cache, techProcess));
        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Процессор успешно добавлен", " ");
    }

    //вызывается, когда пользователь нажал добавить видеокарту
    @FXML
    private void handleAddVideocard() {

        if(newVideocardName.getText().isEmpty() || newVideocardPrice.getText().isEmpty() || newVideocardClock.getText().isEmpty()
         || newVideocardMemory.getText().isEmpty() || newVideocardFans.getText().isEmpty() || newVideocardInterface.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newVideocardName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено имя", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newVideocardPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newVideocardClock.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена частота", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newVideocardMemory.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена память", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9]]", newVideocardFans.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено кол-во вентиляторов", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-][.]]", newVideocardInterface.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен интерфейс", "Повторите ввод");
            return;
        }

        String name = newVideocardName.getText();
        double price = Double.parseDouble(newVideocardPrice.getText());
        double clock = Double.parseDouble(newVideocardClock.getText());
        double memory = Double.parseDouble(newVideocardMemory.getText());
        int fans = Integer.parseInt(newVideocardFans.getText());
        String videocardInterface = newVideocardInterface.getText();

        newVideocardName.clear();
        newVideocardPrice.clear();
        newVideocardInterface.clear();
        newVideocardClock.clear();
        newVideocardFans.clear();
        newVideocardMemory.clear();

        Videocard.videocardList.addLast(new Videocard(name, price, memory, clock, fans, videocardInterface));

        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Видеокарта успешно добавлена", " ");
    }

    //вызывается, когда пользователь нажал добавить материнскую плату
    @FXML
    private void handleAddMotherboard() {

        if(newMotherboardName.getText().isEmpty() || newMotherboardPrice.getText().isEmpty() || newMotherboardMemoryType.getText().isEmpty()
                || newMotherboardMaxMemory.getText().isEmpty() || newMotherboardSocket.getText().isEmpty() || newMotherboardUSB.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-][\\.][\\+]", newMotherboardName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newMotherboardPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newMotherboardSocket.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен сокет", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newMotherboardMemoryType.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип памяти", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newMotherboardMaxMemory.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена максимальная память", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9]]", newMotherboardUSB.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено количество USB", "Повторите ввод");
            return;
        }

        String name = newMotherboardName.getText();
        double price = Double.parseDouble(newMotherboardPrice.getText());
        String socket = newMotherboardSocket.getText();
        String memoryType = newMotherboardMemoryType.getText();
        int maxMemory = Integer.parseInt(newMotherboardMaxMemory.getText());
        int usb = Integer.parseInt(newMotherboardUSB.getText());

        newMotherboardName.clear();
        newMotherboardPrice.clear();
        newMotherboardMaxMemory.clear();
        newMotherboardMemoryType.clear();
        newMotherboardSocket.clear();
        newMotherboardUSB.clear();

        Motherboard.motherboardList.addLast(new Motherboard(name, price, socket, memoryType, maxMemory, usb));
        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Материнская плата успешно добавлена", " ");
    }

    //вызывается, когда пользователь нажал добавить оперативную память
    @FXML
    private void handleAddRam() {

        if(newRamName.getText().isEmpty() || newRamPrice.getText().isEmpty() || newRamMemory.getText().isEmpty()
                || newRamMemoryClock.getText().isEmpty() || newRamMemoryType.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newRamName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newRamPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newRamMemory.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен объем памяти", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newRamMemoryType.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип памяти", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newRamMemoryClock.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена частота памяти", "Повторите ввод");
            return;
        }
        String name = newRamName.getText();
        double price = Double.parseDouble(newRamPrice.getText());
        double memory = Double.parseDouble(newRamMemory.getText());
        String memoryType = newRamMemoryType.getText();
        double memoryClock = Double.parseDouble(newRamMemoryClock.getText());

        newRamName.clear();
        newRamPrice.clear();
        newRamMemoryClock.clear();
        newRamMemory.clear();
        newRamMemoryType.clear();

        RAM.ramList.addLast(new RAM(name, price, memory, memoryType, memoryClock));
        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Оперативная память успешно добавлена", " ");
    }

    //вызывается, когда пользователь нажал добавить блок питания
    @FXML
    private void handleAddPsu() {

        if(newPsuName.getText().isEmpty() || newPsuPrice.getText().isEmpty() || newPsuCapacity.getText().isEmpty()
                || newPsuEfficiency.getText().isEmpty() || newPsuFanSize.getText().isEmpty() || newPsuMaxAmperage.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-][!]]", newPsuName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newPsuPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newPsuCapacity.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена мощность", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9]]", newPsuEfficiency.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено КПД", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newPsuFanSize.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен размер вентилятора", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newPsuMaxAmperage.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена максимальная сила тока", "Повторите ввод");
            return;
        }

        String name = newPsuName.getText();
        double price = Double.parseDouble(newPsuPrice.getText());
        int capacity = Integer.parseInt(newPsuCapacity.getText());
        int efficiency = Integer.parseInt(newPsuEfficiency.getText());
        double fanSize = Double.parseDouble(newPsuFanSize.getText());
        double maxAmperage = Double.parseDouble(newPsuMaxAmperage.getText());

        newPsuName.clear();
        newPsuPrice.clear();
        newPsuCapacity.clear();
        newPsuEfficiency.clear();
        newPsuFanSize.clear();
        newPsuMaxAmperage.clear();

        PSU.psuList.addLast(new PSU(name, price, capacity, efficiency, fanSize, maxAmperage));
        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Блок питания успешно добавлен", " ");

    }

    //вызывается, когда пользователь нажал добавить запоминающее устройство
    @FXML
    private void handleAddDataStorage() {

        if(newDataStorageName.getText().isEmpty() || newDataStoragePrice.getText().isEmpty() || newDataStorageCapacity.getText().isEmpty()
                || newDataStorageType.getText().isEmpty() || newDataStorageInterface.getText().isEmpty() || newDataStorageBuffer.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newDataStorageName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newDataStoragePrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(!(newDataStorageType.getText().trim().equals("HDD") || newDataStorageType.getText().trim().equals("SSD"))) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип\nВозможные типы: SSD и HDD.", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newDataStorageCapacity.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен объем памяти", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\.]]", newDataStorageInterface.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен интерфейс", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newDataStorageBuffer.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено значение буфера", "Повторите ввод");
            return;
        }

        String name = newDataStorageName.getText();
        double price = Double.parseDouble(newDataStoragePrice.getText());
        String type = newDataStorageType.getText();
        int capacity = Integer.parseInt(newDataStorageCapacity.getText());
        String dataStorageInterface = newDataStorageInterface.getText();
        int buffer = Integer.parseInt(newDataStorageBuffer.getText());

        newDataStorageName.clear();
        newDataStoragePrice.clear();
        newDataStorageInterface.clear();
        newDataStorageBuffer.clear();
        newDataStorageCapacity.clear();
        newDataStorageType.clear();

        DataStorage.dataStorageList.addLast(new DataStorage(name, price, type, capacity, dataStorageInterface, buffer));
        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Запоминающее устройство успешно добавлено", " ");
    }

    //вызывается, когда пользователь нажал добавить корпус ПК
    @FXML
    private void handleAddComputerBox() {

        if(newComputerBoxName.getText().isEmpty() || newComputerBoxPrice.getText().isEmpty() || newComputerBoxType.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newComputerBoxName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^[0-9][\\.]]", newComputerBoxPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if(isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newComputerBoxType.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип", "Повторите ввод");
            return;
        }

        String name = newComputerBoxName.getText();
        double price = Double.parseDouble(newComputerBoxPrice.getText());
        String type = newComputerBoxType.getText();

        newComputerBoxName.clear();
        newComputerBoxPrice.clear();
        newComputerBoxType.clear();

        ComputerBox.computerBoxList.addLast(new ComputerBox(name, price, type));
        alert(Alert.AlertType.INFORMATION, "Успешно добавлен", "Корпус ПК успешно добавлен", " ");
    }

    //устанавливает видимость для объекта gp, а у остальных отключает видимость
    private void setVisibility(GridPane gp) {

        MyIterator<GridPane> iter = new MyIterator<>(addPanes);

        while (iter.getCurrent() != null) {
            if(iter.getCurrent().item == gp) {
                iter.getCurrent().item.setVisible(true);
            } else iter.getCurrent().item.setVisible(false);
            iter.nextNode();
        }
    }

    //закрыть окно
    @FXML
    private void closeWindow() {
        dialogStage.close();
    }

    //вызывает окно с информацией об ошибке или предупреждении. Параметры задают свойства окна
    private void alert(Alert.AlertType alertType, String titleText, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    //проверка введенной строки на корректность
    private boolean isInputIncorrect(String pat, String str){
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
