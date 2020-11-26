package view;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import computer.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import list.MyIterator;
import list.MyLinkedList;

//контроллер для главного окна программы
public class Controller {

    double price;//цена, прочитання с textfield
    double totalPrice;//суммарная цена ПК

    @FXML
    public Button goToManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField priceField;

    @FXML
    private Button confirmBtn;

    @FXML
    private ListView<Videocard> possibleVideocards;

    @FXML
    private ListView<PSU> possiblePsu;

    @FXML
    private ListView<RAM> possibleRam;

    @FXML
    private ListView<ComputerBox> possibleComputerBox;

    @FXML
    private ListView<DataStorage> possibleDataStorage;

    @FXML
    private ListView<Processor> possibleProcessors;

    @FXML
    private ListView<Motherboard> possibleMotherboards;

    @FXML
    private GridPane processorInfoPane;

    @FXML
    private Label processorNameLabel;

    @FXML
    private Label processorPriceLabel;

    @FXML
    private Label processorClockLabel;

    @FXML
    private Label processorArchitectLabel;

    @FXML
    private Label processorCores;

    @FXML
    private Label processorCache;

    @FXML
    private Label processorTechProcess;

    @FXML
    private Label processorIntegratedGraphics;

    @FXML
    private GridPane videocardInfoPane;

    @FXML
    private Label videocardNameLabel;

    @FXML
    private Label videocardPriceLabel;

    @FXML
    private Label videocardInterface;

    @FXML
    private Label videocardMemory;

    @FXML
    private Label videocardClock;

    @FXML
    private Label videocardFans;

    @FXML
    private GridPane motherboardInfoPane;

    @FXML
    private Label motherboardNameLabel;

    @FXML
    private Label motherboardPriceLabel;

    @FXML
    private Label motherboardSocket;

    @FXML
    private Label motherboardMemoryType;

    @FXML
    private Label motherboardMaxMemory;

    @FXML
    private Label motherboardUSB;

    @FXML
    private GridPane ramInfoPane;

    @FXML
    private Label ramNameLabel;

    @FXML
    private Label ramPriceLabel;

    @FXML
    private Label ramMemory;

    @FXML
    private Label ramMemoryType;

    @FXML
    private Label ramMemoryClock;

    @FXML
    private GridPane psuInfoPane;

    @FXML
    private Label psuNameLabel;

    @FXML
    private Label psuPriceLabel;

    @FXML
    private Label psuCapacity;

    @FXML
    private Label psuEfficiency;

    @FXML
    private Label psuFanSize;

    @FXML
    private Label psuMaxAmperage;

    @FXML
    private GridPane dataStorageInfoPane;

    @FXML
    private Label dataStorageNameLabel;

    @FXML
    private Label dataStoragePriceLabel;

    @FXML
    private Label dataStorageType;

    @FXML
    private Label dataStorageCapacity;

    @FXML
    private Label dataStorageInterface;

    @FXML
    private Label dataStorageBuffer;

    @FXML
    private GridPane computerBoxInfoPane;

    @FXML
    private Label computerBoxNameLabel;

    @FXML
    private Label computerBoxPriceLabel;

    @FXML
    private Label computerBoxType;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label currentComputerCategory;

    //ObservableList-ы для элементов ListView
    ObservableList<Processor> observableProcessorList = FXCollections.observableArrayList();
    ObservableList<Videocard> observableVideocardList = FXCollections.observableArrayList();
    ObservableList<Motherboard> observableMotherboardList = FXCollections.observableArrayList();
    ObservableList<RAM> observableRamList = FXCollections.observableArrayList();
    ObservableList<PSU> observablePsuList = FXCollections.observableArrayList();
    ObservableList<DataStorage> observableDataStorageList = FXCollections.observableArrayList();
    ObservableList<ComputerBox> observableComputerBoxList = FXCollections.observableArrayList();

    //текущие выбранные объекты
    private Processor currentProcessor;
    private Videocard currentVideocard;
    private Motherboard currentMotherboard;
    private RAM currentRam;
    private PSU currentPsu;
    private DataStorage currentDataStorage;
    private ComputerBox currentComputerBox;

    //собрать ПК
    public void createPC() {

        if (!Pattern.matches("\\d+", priceField.getText()) || Double.parseDouble(priceField.getText()) < 0 || Double.parseDouble(priceField.getText()) > 1000000000) {
            alert(Alert.AlertType.WARNING, "Ошибка ввода", "Введена некорректная строка.", "Строка может сожержать только цифровые символы, \nот 0 или больше.");
            priceField.clear();
            return;
        }

        if (Processor.processorList.isEmpty() || Videocard.videocardList.isEmpty() || Motherboard.motherboardList.isEmpty() || RAM.ramList.isEmpty() || PSU.psuList.isEmpty() || DataStorage.dataStorageList.isEmpty() || ComputerBox.computerBoxList.isEmpty()) {
            alert(Alert.AlertType.WARNING, "Невозможно собрать ПК!", "Недостаточно комплектующих в библиотеке...", "Попробуйте добавить комплектующие в библиотеку.");
            priceField.clear();
            return;
        }

        //считать цену с поля
        price = Integer.parseInt(priceField.getText());

        //определить категорию ПК
        String computerCategory = checkCategory(price);

        currentComputerCategory.setText(computerCategory);

        //вызвать методы для подбора комплектующих в зависимости от полученной категории
        observableProcessorList = selectProcessors(computerCategory);
        observableVideocardList = selectVideocards(computerCategory);
        observableMotherboardList = selectMotherboards(computerCategory);
        observableRamList = selectRam(computerCategory);
        observablePsuList = selectPsu(computerCategory);
        observableDataStorageList = selectDataStorage(computerCategory);
        observableComputerBoxList = selectComputerBox(computerCategory);

        //заполнить элементы ListView
        possibleProcessors.setItems(observableProcessorList);
        possibleVideocards.setItems(observableVideocardList);
        possibleMotherboards.setItems(observableMotherboardList);
        possibleRam.setItems(observableRamList);
        possiblePsu.setItems(observablePsuList);
        possibleDataStorage.setItems(observableDataStorageList);
        possibleComputerBox.setItems(observableComputerBoxList);

        //задать внешний вид элементов в элементе ListView
        processorFactory();
        videocardFactory();
        motherboardFactory();
        ramFactory();
        psuFactory();
        dataStorageFactory();
        computerBoxFactory();

        //инициализировать значение текущей стоимости ПК
        totalPrice = 0;
        totalPriceLabel.setText("Итоговая цена: " + totalPrice + " б.р.");
    }

    //к менеджеру
    public void toManager() {
        Main.showManager();
    }

    //список панелей компоновки
    MyLinkedList<GridPane> infoPanes = new MyLinkedList<>();

    @FXML
    void initialize() {

        //добавляем панели в список
        infoPanes.addLast(processorInfoPane);
        infoPanes.addLast(videocardInfoPane);
        infoPanes.addLast(motherboardInfoPane);
        infoPanes.addLast(ramInfoPane);
        infoPanes.addLast(psuInfoPane);
        infoPanes.addLast(dataStorageInfoPane);
        infoPanes.addLast(computerBoxInfoPane);

        //ни одна панель не отображается
        setVisibility(null);

        //слушатели выбора в элементах ListView
        MultipleSelectionModel<Processor> processorMSM = possibleProcessors.getSelectionModel();
        processorMSM.selectedItemProperty().addListener(
                new ChangeListener<Processor>() {
                    @Override
                    public void changed(ObservableValue<? extends Processor> observableValue, Processor oldValue, Processor newValue) {
                        if (newValue == null) return;
                        setVisibility(processorInfoPane);
                        processorNameLabel.setText(newValue.getName());
                        processorPriceLabel.setText(newValue.getPrice() + " б.р.");
                        processorClockLabel.setText(newValue.getClock() + " ГГц");
                        processorCores.setText(Integer.toString(newValue.getCores()));
                        processorCache.setText(newValue.getCache() + " Мб");
                        processorArchitectLabel.setText(newValue.getArchitecture());
                        processorTechProcess.setText(newValue.getTechProcess() + " нм");
                        currentProcessor = newValue;
                        if(oldValue == null) totalPrice += currentProcessor.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentProcessor.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

        MultipleSelectionModel<Videocard> videocardMSM = possibleVideocards.getSelectionModel();
        videocardMSM.selectedItemProperty().addListener(
                new ChangeListener<Videocard>() {
                    @Override
                    public void changed(ObservableValue<? extends Videocard> observableValue, Videocard oldValue, Videocard newValue) {
                        if (newValue == null) return;
                        setVisibility(videocardInfoPane);
                        videocardNameLabel.setText(newValue.getName());
                        videocardPriceLabel.setText(newValue.getPrice() + " б.р.");
                        videocardClock.setText(newValue.getClock() + " МГц");
                        videocardFans.setText(Integer.toString(newValue.getFans()));
                        videocardInterface.setText(newValue.getVideocardInterface());
                        videocardMemory.setText(newValue.getMemory() + " Мб");
                        currentVideocard = newValue;
                        if(oldValue == null) totalPrice += currentVideocard.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentVideocard.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

        MultipleSelectionModel<Motherboard> motherboardMSM = possibleMotherboards.getSelectionModel();
        motherboardMSM.selectedItemProperty().addListener(
                new ChangeListener<Motherboard>() {
                    @Override
                    public void changed(ObservableValue<? extends Motherboard> observableValue, Motherboard oldValue, Motherboard newValue) {
                        if (newValue == null) return;
                        setVisibility(motherboardInfoPane);
                        motherboardNameLabel.setText(newValue.getName());
                        motherboardPriceLabel.setText(newValue.getPrice() + " б.р.");
                        motherboardSocket.setText(newValue.getSocket());
                        motherboardMemoryType.setText(newValue.getMemoryType());
                        motherboardMaxMemory.setText(newValue.getMaxMemory() + " Гб");
                        motherboardUSB.setText(Integer.toString(newValue.getUsb()));
                        currentMotherboard = newValue;
                        if(oldValue == null) totalPrice += currentMotherboard.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentMotherboard.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

        MultipleSelectionModel<RAM> ramMSM = possibleRam.getSelectionModel();
        ramMSM.selectedItemProperty().addListener(
                new ChangeListener<RAM>() {
                    @Override
                    public void changed(ObservableValue<? extends RAM> observableValue, RAM oldValue, RAM newValue) {
                        if (newValue == null) return;
                        setVisibility(ramInfoPane);
                        ramNameLabel.setText(newValue.getName());
                        ramPriceLabel.setText(newValue.getPrice() + " б.р.");
                        ramMemory.setText(newValue.getMemory() + " Мб");
                        ramMemoryType.setText(newValue.getMemoryType());
                        ramMemoryClock.setText(newValue.getMemoryClock() + " Мгц");
                        currentRam = newValue;
                        if(oldValue == null) totalPrice += currentRam.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentRam.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

        MultipleSelectionModel<PSU> psuMSM = possiblePsu.getSelectionModel();
        psuMSM.selectedItemProperty().addListener(
                new ChangeListener<PSU>() {
                    @Override
                    public void changed(ObservableValue<? extends PSU> observableValue, PSU oldValue, PSU newValue) {
                        if (newValue == null) return;
                        setVisibility(psuInfoPane);
                        psuNameLabel.setText(newValue.getName());
                        psuPriceLabel.setText(newValue.getPrice() + " б.р.");
                        psuCapacity.setText(newValue.getCapacity() + " Вт");
                        psuEfficiency.setText(newValue.getEfficiency() + "%");
                        psuFanSize.setText(newValue.getFanSize() + " мм");
                        psuMaxAmperage.setText(newValue.getMaxAmperage() + " А");
                        currentPsu = newValue;
                        if(oldValue == null) totalPrice += currentPsu.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentPsu.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

        MultipleSelectionModel<DataStorage> dataStorageMSM = possibleDataStorage.getSelectionModel();
        dataStorageMSM.selectedItemProperty().addListener(
                new ChangeListener<DataStorage>() {
                    @Override
                    public void changed(ObservableValue<? extends DataStorage> observableValue, DataStorage oldValue, DataStorage newValue) {
                        if (newValue == null) return;
                        setVisibility(dataStorageInfoPane);
                        dataStorageNameLabel.setText(newValue.getName());
                        dataStoragePriceLabel.setText(newValue.getPrice() + " б.р.");
                        dataStorageType.setText(newValue.getType());
                        dataStorageCapacity.setText(newValue.getCapacity() + " Гб");
                        dataStorageInterface.setText(newValue.getDataStorageInterface());
                        dataStorageBuffer.setText(newValue.getBuffer() + " Мб");
                        currentDataStorage = newValue;
                        if(oldValue == null) totalPrice += currentDataStorage.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentDataStorage.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

        MultipleSelectionModel<ComputerBox> computerBoxMSM = possibleComputerBox.getSelectionModel();
        computerBoxMSM.selectedItemProperty().addListener(
                new ChangeListener<ComputerBox>() {
                    @Override
                    public void changed(ObservableValue<? extends ComputerBox> observableValue, ComputerBox oldValue, ComputerBox newValue) {
                        if (newValue == null) return;
                        setVisibility(computerBoxInfoPane);
                        computerBoxNameLabel.setText(newValue.getName());
                        computerBoxPriceLabel.setText(newValue.getPrice() + " б.р.");
                        computerBoxType.setText(newValue.getType());
                        currentComputerBox = newValue;
                        if(oldValue == null) totalPrice += currentComputerBox.getPrice();
                        else totalPrice = totalPrice - oldValue.getPrice() + currentComputerBox.getPrice();
                        totalPriceLabel.setText(Double.toString(totalPrice));
                    }
                }
        );

    }

    //устанавливает видимость для элемента gp, а у остальных видимость выключает
    private void setVisibility(GridPane gp) {

        MyIterator<GridPane> iter = new MyIterator<>(infoPanes);

        while (iter.getCurrent() != null) {
            if (iter.getCurrent().item == gp) {
                iter.getCurrent().item.setVisible(true);
            } else iter.getCurrent().item.setVisible(false);
            iter.nextNode();
        }
    }

    //задать внешний вид элементов в ListView
    private void processorFactory() {
        possibleProcessors.setCellFactory(param -> new ListCell<Processor>() {
            @Override
            protected void updateItem(Processor item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void videocardFactory() {
        possibleVideocards.setCellFactory(param -> new ListCell<Videocard>() {
            @Override
            protected void updateItem(Videocard item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void motherboardFactory() {
        possibleMotherboards.setCellFactory(param -> new ListCell<Motherboard>() {
            @Override
            protected void updateItem(Motherboard item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void ramFactory() {
        possibleRam.setCellFactory(param -> new ListCell<RAM>() {
            @Override
            protected void updateItem(RAM item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void psuFactory() {
        possiblePsu.setCellFactory(param -> new ListCell<PSU>() {
            @Override
            protected void updateItem(PSU item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void dataStorageFactory() {
        possibleDataStorage.setCellFactory(param -> new ListCell<DataStorage>() {
            @Override
            protected void updateItem(DataStorage item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void computerBoxFactory() {
        possibleComputerBox.setCellFactory(param -> new ListCell<ComputerBox>() {
            @Override
            protected void updateItem(ComputerBox item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    //определить категорию ПК
    private static String checkCategory(double price) {
        String category = " ";
        if (price >= 305 && price < 670) {
            category = "minPrice";
            return category;
        }
        if (price >= 670 && price < 1120) {
            category = "lowPrice";
            return category;
        }
        if (price >= 1120 && price < 1680) {
            category = "averagePrice";
            return category;
        }
        if (price >= 1680 && price < 2360) {
            category = "midPrice";
            return category;
        }
        if (price >= 2360 && price < 3270) {
            category = "highPrice";
            return category;
        }
        if (price >= 3270) category = "superPrice";
        return category;
    }

    //выбрать комплектующие в зависимости от категории
    private ObservableList<Processor> selectProcessors(String computerCategory) {

        ObservableList<Processor> observablePossibleProcessors = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<Processor> selectedProcessors = Processor.sortByCategory(computerCategory);

        MyIterator<Processor> iter = new MyIterator<>(selectedProcessors);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossibleProcessors.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossibleProcessors;
    }

    private ObservableList<Videocard> selectVideocards(String computerCategory) {

        ObservableList<Videocard> observablePossibleVideocards = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<Videocard> selectedVideocards = Videocard.sortByCategory(computerCategory);

        MyIterator<Videocard> iter = new MyIterator<>(selectedVideocards);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossibleVideocards.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossibleVideocards;
    }

    private ObservableList<Motherboard> selectMotherboards(String computerCategory) {

        ObservableList<Motherboard> observablePossibleMotherboards = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<Motherboard> selectedMotherboards = Motherboard.sortByCategory(computerCategory);

        MyIterator<Motherboard> iter = new MyIterator<>(selectedMotherboards);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossibleMotherboards.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossibleMotherboards;
    }

    private ObservableList<RAM> selectRam(String computerCategory) {

        ObservableList<RAM> observablePossibleRam = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<RAM> selectedRAM = RAM.sortByCategory(computerCategory);

        MyIterator<RAM> iter = new MyIterator<>(selectedRAM);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossibleRam.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossibleRam;
    }

    private ObservableList<PSU> selectPsu(String computerCategory) {

        ObservableList<PSU> observablePossiblePsu = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<PSU> selectedPsu = PSU.sortByCategory(computerCategory);

        MyIterator<PSU> iter = new MyIterator<>(selectedPsu);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossiblePsu.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossiblePsu;
    }

    private ObservableList<DataStorage> selectDataStorage(String computerCategory) {

        ObservableList<DataStorage> observablePossibleDataStorage = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<DataStorage> selectedDataStorage = DataStorage.sortByCategory(computerCategory);

        MyIterator<DataStorage> iter = new MyIterator<>(selectedDataStorage);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossibleDataStorage.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossibleDataStorage;
    }

    private ObservableList<ComputerBox> selectComputerBox(String computerCategory) {

        ObservableList<ComputerBox> observablePossibleComputerBox = FXCollections.observableArrayList();
        price = Integer.parseInt(priceField.getText());

        MyLinkedList<ComputerBox> selectedComputerBox = ComputerBox.sortByCategory(computerCategory);

        MyIterator<ComputerBox> iter = new MyIterator<>(selectedComputerBox);
        iter.reset();

        while (iter.getCurrent() != null) {
            observablePossibleComputerBox.add(iter.getCurrent().item);
            iter.nextNode();
        }

        return observablePossibleComputerBox;
    }

    //сохранить компьютер в файл
    @FXML
    private void saveComputer() {

        if (currentProcessor == null || currentVideocard == null || currentMotherboard == null || currentRam == null || currentPsu == null || currentDataStorage == null || currentComputerBox == null) {
            alert(Alert.AlertType.WARNING, "Ошибка", "Невозможно сохранить ПК", "Необходимо выбрать каждый тип комплектующего");
            return;
        }

        try (FileWriter fw = new FileWriter("currentPC.txt", false)) {
            fw.write("Процессор: " + currentProcessor.getName() + "\n");
            fw.write("Видеокарта: " + currentVideocard.getName() + "\n");
            fw.write("Материнская плата: " + currentMotherboard.getName() + "\n");
            fw.write("Оперативная память: " + currentRam.getName() + "\n");
            fw.write("Блок питания: " + currentPsu.getName() + "\n");
            fw.write("Запоминающее устройство: " + currentDataStorage.getName() + "\n");
            fw.write("Корпус ПК: " + currentComputerBox.getName() + "\n");
            fw.write("\nИтоговая цена:" + totalPrice + " б.р.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        alert(Alert.AlertType.INFORMATION, "Успешно!", "Ваш компьютер сохранен в файл", "");
    }

    private void alert(Alert.AlertType alertType, String titleText, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

}



