package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import computer.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import list.MyIterator;
import list.MyLinkedList;

//контроллер для окна менеджера
public class PartsManagerController extends Controller {

    //ссылка на объект Stage окна менеджера
    private Stage dialogStage;

    //текущий выбранный объект
    private Processor currentProcessor;
    private Videocard currentVideocard;
    private Motherboard currentMotherboard;
    private RAM currentRam;
    private PSU currentPsu;
    private DataStorage currentDataStorage;
    private ComputerBox currentComputerBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private StackPane partsPane;

    @FXML
    private Button changeButton;

    @FXML
    protected ListView<Processor> processorList;
    ObservableList<Processor> observableProcessorList = FXCollections.observableArrayList();

    @FXML
    protected ListView<Videocard> videocardList;
    ObservableList<Videocard> observableVideocardList = FXCollections.observableArrayList();

    @FXML
    private ListView<Motherboard> motherboardList;
    ObservableList<Motherboard> observableMotherboardList = FXCollections.observableArrayList();

    @FXML
    private ListView<RAM> ramList;
    ObservableList<RAM> observableRamList = FXCollections.observableArrayList();

    @FXML
    private ListView<PSU> psuList;
    ObservableList<PSU> observablePsuList = FXCollections.observableArrayList();

    @FXML
    private ListView<DataStorage> dataStorageList;
    ObservableList<DataStorage> observableDataStorageList = FXCollections.observableArrayList();

    @FXML
    private ListView<ComputerBox> computerBoxList;
    ObservableList<ComputerBox> observableComputerBoxList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> switchPartType;

    @FXML
    private GridPane processorChangePane;

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
    private GridPane videocardInfoPane;

    @FXML
    private Label videocardNameLabel;

    @FXML
    private Label videocardPriceLabel;

    @FXML
    private Label videocardMemoryLabel;

    @FXML
    private Label videocardClockLabel;

    @FXML
    private Label videocardFansLabel;

    @FXML
    private Label videocardInterfaceLabel;

    @FXML
    private GridPane videocardChangePane;

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
    private GridPane motherboardChangePane;

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
    private GridPane ramChangePane;

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
    private GridPane psuChangePane;

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
    private GridPane dataStorageChangePane;

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
    private GridPane computerBoxInfoPane;

    @FXML
    private Label computerBoxNameLabel;

    @FXML
    private Label computerBoxPriceLabel;

    @FXML
    private Label computerBoxType;

    @FXML
    private GridPane computerBoxChangePane;

    @FXML
    private TextField newComputerBoxName;

    @FXML
    private TextField newComputerBoxPrice;

    @FXML
    private Button confirmComputerBox;

    @FXML
    private TextField newComputerBoxType;

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
    private Label processorCoreLabel;

    @FXML
    private Label processorCacheLabel;

    @FXML
    private Label processorTechProcessLabel;

    @FXML
    private Button loadButton;

    @FXML
    private Button saveButton;

    @FXML
    private AnchorPane emptyPane;

    //список панелей компоновки
    MyLinkedList<GridPane> infoPanes = new MyLinkedList<>();

    @FXML
    void initialize() {

        //добавить панели в список
        infoPanes.addLast(processorInfoPane);
        infoPanes.addLast(videocardInfoPane);
        infoPanes.addLast(motherboardInfoPane);
        infoPanes.addLast(ramInfoPane);
        infoPanes.addLast(psuInfoPane);
        infoPanes.addLast(dataStorageInfoPane);
        infoPanes.addLast(computerBoxInfoPane);
        infoPanes.addLast(processorChangePane);
        infoPanes.addLast(videocardChangePane);
        infoPanes.addLast(motherboardChangePane);
        infoPanes.addLast(ramChangePane);
        infoPanes.addLast(psuChangePane);
        infoPanes.addLast(dataStorageChangePane);
        infoPanes.addLast(computerBoxChangePane);

        //изначально ничего не отображается
        setVisibility(null);

        //заполнить элементы ComboBox
        switchPartType.getItems().addAll("Процессор", "Видеокарта", "Материнская плата", "Оперативная память", "Блок питания", "Запоминающее устройство", "Корпус ПК");
        //инициализировать элемент ComboBox
        switchPartType.setValue("Тип комплектующего");

        //обновить списки
        refreshLists();

        //задать внешний вид объектов в ListView
        processorFactory();
        videocardFactory();
        motherboardFactory();
        ramFactory();
        psuFactory();
        dataStorageFactory();
        computerBoxFactory();

        //слушатель изменений для ComboBox
        switchPartType.setOnAction(event -> {

            switch (switchPartType.getValue()) {
                case "Процессор":
                    processorList.toFront();
                    setVisibility(processorInfoPane);
                    break;
                case "Видеокарта":
                    videocardList.toFront();
                    setVisibility(videocardInfoPane);
                    break;
                case "Материнская плата":
                    motherboardList.toFront();
                    setVisibility(motherboardInfoPane);
                    break;
                case "Блок питания":
                    psuList.toFront();
                    setVisibility(psuInfoPane);
                    break;
                case "Оперативная память":
                    ramList.toFront();
                    setVisibility(ramInfoPane);
                    break;
                case "Запоминающее устройство":
                    dataStorageList.toFront();
                    setVisibility(dataStorageInfoPane);
                    break;
                case "Корпус ПК":
                    computerBoxList.toFront();
                    setVisibility(computerBoxInfoPane);
                    break;
            }
        });

        //слушатели выбора в элементах ListView
        MultipleSelectionModel<Processor> processorMSM = processorList.getSelectionModel();
        processorMSM.selectedItemProperty().addListener(
                new ChangeListener<Processor>() {
                    @Override
                    public void changed(ObservableValue<? extends Processor> observableValue, Processor oldValue, Processor newValue) {
                        if (newValue == null) return;
                        setVisibility(processorInfoPane);
                        processorNameLabel.setText(newValue.getName());
                        processorPriceLabel.setText(newValue.getPrice() + " б.р.");
                        processorClockLabel.setText(newValue.getClock() + " ГГц");
                        processorCoreLabel.setText(Integer.toString(newValue.getCores()));
                        processorCacheLabel.setText(newValue.getCache() + " Мб");
                        processorArchitectLabel.setText(newValue.getArchitecture());
                        processorTechProcessLabel.setText(newValue.getTechProcess() + " нм");
                        currentProcessor = newValue;
                    }
                }
        );

        MultipleSelectionModel<Videocard> videocardMSM = videocardList.getSelectionModel();
        videocardMSM.selectedItemProperty().addListener(
                new ChangeListener<Videocard>() {
                    @Override
                    public void changed(ObservableValue<? extends Videocard> observableValue, Videocard oldValue, Videocard newValue) {
                        if (newValue == null) return;
                        setVisibility(videocardInfoPane);
                        videocardNameLabel.setText(newValue.getName());
                        videocardPriceLabel.setText(newValue.getPrice() + " б.р.");
                        videocardClockLabel.setText(newValue.getClock() + " МГц");
                        videocardFansLabel.setText(Integer.toString(newValue.getFans()));
                        videocardInterfaceLabel.setText(newValue.getVideocardInterface());
                        videocardMemoryLabel.setText(newValue.getMemory() + " Мб");
                        currentVideocard = newValue;
                    }
                }
        );

        MultipleSelectionModel<Motherboard> motherboardMSM = motherboardList.getSelectionModel();
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
                    }
                }
        );

        MultipleSelectionModel<RAM> ramMSM = ramList.getSelectionModel();
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
                    }
                }
        );

        MultipleSelectionModel<PSU> psuMSM = psuList.getSelectionModel();
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
                    }
                }
        );

        MultipleSelectionModel<DataStorage> dataStorageMSM = dataStorageList.getSelectionModel();
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
                    }
                }
        );

        MultipleSelectionModel<ComputerBox> computerBoxMSM = computerBoxList.getSelectionModel();
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
                    }
                }
        );

        //задать функционал кнопке загрузки
        loadButton.setOnAction(actionEvent -> {
            handleLoad();
            emptyPane.setVisible(false);
            refreshLists();
        });

        //задать функционал кнопке сохранения
        saveButton.setOnAction(actionEvent -> {
            handleSave();
            alert(Alert.AlertType.INFORMATION, "Загружено!", "Списки загружены в файл!", "");
        });

    }

    //обновить списки
    private void refreshLists() {

        observableProcessorList.clear();
        observableVideocardList.clear();
        observableMotherboardList.clear();
        observableRamList.clear();
        observablePsuList.clear();
        observableDataStorageList.clear();
        observableComputerBoxList.clear();

        MyIterator<Processor> iterProc = new MyIterator<>(Processor.processorList);
        iterProc.reset();

        while (iterProc.getCurrent() != null) {
            Processor current = iterProc.getCurrent().item;
            observableProcessorList.add(current);
            iterProc.nextNode();
        }

        MyIterator<Videocard> iterVcard = new MyIterator<>(Videocard.videocardList);
        iterVcard.reset();

        while (iterVcard.getCurrent() != null) {
            Videocard current = iterVcard.getCurrent().item;
            observableVideocardList.add(current);
            iterVcard.nextNode();
        }

        MyIterator<Motherboard> iterMboard = new MyIterator<>(Motherboard.motherboardList);
        iterMboard.reset();

        while (iterMboard.getCurrent() != null) {
            Motherboard current = iterMboard.getCurrent().item;
            observableMotherboardList.add(current);
            iterMboard.nextNode();
        }

        MyIterator<RAM> iterRam = new MyIterator<>(RAM.ramList);
        iterRam.reset();

        while (iterRam.getCurrent() != null) {
            RAM current = iterRam.getCurrent().item;
            observableRamList.add(current);
            iterRam.nextNode();
        }

        MyIterator<PSU> iterPsu = new MyIterator<>(PSU.psuList);
        iterPsu.reset();

        while (iterPsu.getCurrent() != null) {
            PSU current = iterPsu.getCurrent().item;
            observablePsuList.add(current);
            iterPsu.nextNode();
        }

        MyIterator<DataStorage> iterDStor = new MyIterator<>(DataStorage.dataStorageList);
        iterDStor.reset();

        while (iterDStor.getCurrent() != null) {
            DataStorage current = iterDStor.getCurrent().item;
            observableDataStorageList.add(current);
            iterDStor.nextNode();
        }
        MyIterator<ComputerBox> iterCompBox = new MyIterator<>(ComputerBox.computerBoxList);
        iterCompBox.reset();

        while (iterCompBox.getCurrent() != null) {
            ComputerBox current = iterCompBox.getCurrent().item;
            observableComputerBoxList.add(current);
            iterCompBox.nextNode();
        }

        processorList.setItems(observableProcessorList);
        videocardList.setItems(observableVideocardList);
        motherboardList.setItems(observableMotherboardList);
        ramList.setItems(observableRamList);
        psuList.setItems(observablePsuList);
        dataStorageList.setItems(observableDataStorageList);
        computerBoxList.setItems(observableComputerBoxList);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void handleAdd() {
        Main.addPartWindow();
        refreshLists();
    }

    public void handleEdit(Part part) {
        
    }

    //задать внешний вид элементов ListView
    private void processorFactory() {
        processorList.setCellFactory(param -> new ListCell<Processor>() {
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
        videocardList.setCellFactory(param -> new ListCell<Videocard>() {
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
        motherboardList.setCellFactory(param -> new ListCell<Motherboard>() {
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
        ramList.setCellFactory(param -> new ListCell<RAM>() {
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
        psuList.setCellFactory(param -> new ListCell<PSU>() {
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
        dataStorageList.setCellFactory(param -> new ListCell<DataStorage>() {
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
        computerBoxList.setCellFactory(param -> new ListCell<ComputerBox>() {
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

    //устанавливает видимость для панели gp, а у остальных видимость убирает
    private void setVisibility(GridPane gp) {

        MyIterator<GridPane> iter = new MyIterator<>(infoPanes);

        while (iter.getCurrent() != null) {
            if (iter.getCurrent().item == gp) {
                iter.getCurrent().item.setVisible(true);
            } else iter.getCurrent().item.setVisible(false);
            iter.nextNode();
        }
    }

    //метод вызываются, когда пользователь кликает по кнопке изменить
    @FXML
    private void handleChange() {
        switch (switchPartType.getValue()) {
            case "Процессор":
                if (currentProcessor == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(processorChangePane);
                newProcessorName.setText(currentProcessor.getName());
                newProcessorPrice.setText(Double.toString(currentProcessor.getPrice()));
                newProcessorClock.setText(Double.toString(currentProcessor.getClock()));
                newProcessorCache.setText(Integer.toString(currentProcessor.getCache()));
                newProcessorCores.setText(Integer.toString(currentProcessor.getCores()));
                newProcessorTechProcess.setText(Integer.toString(currentProcessor.getTechProcess()));
                newProcessorArchitecture.setText(currentProcessor.getArchitecture());
                break;
            case "Видеокарта":
                if (currentVideocard == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(videocardChangePane);
                newVideocardName.setText(currentVideocard.getName());
                newVideocardPrice.setText(Double.toString(currentVideocard.getPrice()));
                newVideocardMemory.setText(Double.toString(currentVideocard.getMemory()));
                newVideocardClock.setText(Double.toString(currentVideocard.getClock()));
                newVideocardFans.setText(Integer.toString(currentVideocard.getFans()));
                newVideocardInterface.setText(currentVideocard.getVideocardInterface());
                break;
            case "Материнская плата":
                if (currentMotherboard == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(motherboardChangePane);
                newMotherboardName.setText(currentMotherboard.getName());
                newMotherboardPrice.setText(Double.toString(currentMotherboard.getPrice()));
                newMotherboardMemoryType.setText(currentMotherboard.getMemoryType());
                newMotherboardMaxMemory.setText(Integer.toString(currentMotherboard.getMaxMemory()));
                newMotherboardSocket.setText(currentMotherboard.getSocket());
                newMotherboardUSB.setText(Integer.toString(currentMotherboard.getUsb()));
                break;
            case "Блок питания":
                if (currentPsu == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(psuChangePane);
                newPsuName.setText(currentPsu.getName());
                newPsuPrice.setText(Double.toString(currentPsu.getPrice()));
                newPsuCapacity.setText(Integer.toString(currentPsu.getCapacity()));
                newPsuEfficiency.setText(Integer.toString(currentPsu.getEfficiency()));
                newPsuFanSize.setText(Double.toString(currentPsu.getFanSize()));
                newPsuMaxAmperage.setText(Double.toString(currentPsu.getMaxAmperage()));
                break;
            case "Оперативная память":
                if (currentRam == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(ramChangePane);
                newRamName.setText(currentRam.getName());
                newRamPrice.setText(Double.toString(currentRam.getPrice()));
                newRamMemory.setText(Double.toString(currentRam.getMemory()));
                newRamMemoryClock.setText(Double.toString(currentRam.getMemoryClock()));
                newRamMemoryType.setText(currentRam.getMemoryType());
                break;
            case "Запоминающее устройство":
                if (currentDataStorage == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(dataStorageChangePane);
                newDataStorageName.setText(currentDataStorage.getName());
                newDataStoragePrice.setText(Double.toString(currentDataStorage.getPrice()));
                newDataStorageCapacity.setText(Integer.toString(currentDataStorage.getCapacity()));
                newDataStorageType.setText(currentDataStorage.getType());
                newDataStorageInterface.setText(currentDataStorage.getDataStorageInterface());
                newDataStorageBuffer.setText(Integer.toString(currentDataStorage.getBuffer()));
                break;
            case "Корпус ПК":
                if (currentComputerBox == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                setVisibility(computerBoxChangePane);
                newComputerBoxName.setText(currentComputerBox.getName());
                newComputerBoxPrice.setText(Double.toString(currentComputerBox.getPrice()));
                newComputerBoxType.setText(currentComputerBox.getType());
                break;
        }
    }

    //методы вызываются, когда пользователь кликает по кнопке подтвердить изменения
    @FXML
    private void changeProcessor() {

        if (newProcessorName.getText().isEmpty() || newProcessorPrice.getText().isEmpty() || newProcessorClock.getText().isEmpty()
                || newProcessorCores.getText().isEmpty() || newProcessorCache.getText().isEmpty() || newProcessorArchitecture.getText().isEmpty()
                || newProcessorTechProcess.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newProcessorName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено имя", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newProcessorPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newProcessorClock.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена частота", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[a-zA-Z][0-9][\\s][\\+]]", newProcessorArchitecture.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена архитектура", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newProcessorCores.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено кол-во ядер", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("^[0-9][\\.]", newProcessorCache.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен размер кэша", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("^[0-9][\\.]", newProcessorTechProcess.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено значение техпроцесса", "Повторите ввод");
            return;
        }

        String name = newProcessorName.getText();
        double price = Double.parseDouble(newProcessorPrice.getText());
        double clock = Double.parseDouble(newProcessorClock.getText());
        String architecture = newProcessorArchitecture.getText();
        int cores = Integer.parseInt(newProcessorCores.getText());
        int cache = Integer.parseInt(newProcessorCache.getText());
        int techProcess = Integer.parseInt(newProcessorTechProcess.getText());

        currentProcessor.setName(name);
        currentProcessor.setPrice(price);
        currentProcessor.setClock(clock);
        currentProcessor.setArchitecture(architecture);
        currentProcessor.setCores(cores);
        currentProcessor.setCache(cache);
        currentProcessor.setTechProcess(techProcess);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Процессор успешно изменен", "");
        setVisibility(null);
    }

    @FXML
    private void changeVideocard() {

        if (newVideocardName.getText().isEmpty() || newVideocardPrice.getText().isEmpty() || newVideocardClock.getText().isEmpty()
                || newVideocardMemory.getText().isEmpty() || newVideocardFans.getText().isEmpty() || newVideocardInterface.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newVideocardName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено имя", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newVideocardPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newVideocardClock.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена частота", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newVideocardMemory.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена память", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9]]", newVideocardFans.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено кол-во вентиляторов", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-][\\.]]", newVideocardInterface.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен интерфейс", "Повторите ввод");
            return;
        }

        String name = newVideocardName.getText();
        double price = Double.parseDouble(newVideocardPrice.getText());
        double memory = Double.parseDouble(newVideocardMemory.getText());
        double clock = Double.parseDouble(newVideocardClock.getText());
        int fans = Integer.parseInt(newVideocardFans.getText());
        String videocardInterface = newVideocardInterface.getText();

        currentVideocard.setName(name);
        currentVideocard.setPrice(price);
        currentVideocard.setMemory(memory);
        currentVideocard.setClock(clock);
        currentVideocard.setFans(fans);
        currentVideocard.setVideocardInterface(videocardInterface);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Видеокарта успешно изменена", "");
        setVisibility(null);
    }

    @FXML
    private void changeMotherboard() {

        if (newMotherboardName.getText().isEmpty() || newMotherboardPrice.getText().isEmpty() || newMotherboardMemoryType.getText().isEmpty()
                || newMotherboardMaxMemory.getText().isEmpty() || newMotherboardSocket.getText().isEmpty() || newMotherboardUSB.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newMotherboardName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newMotherboardPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newMotherboardSocket.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен сокет", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newMotherboardMemoryType.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип памяти", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newMotherboardMaxMemory.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена максимальная память", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9]]", newMotherboardUSB.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено количество USB", "Повторите ввод");
            return;
        }

        String name = newMotherboardName.getText();
        double price = Double.parseDouble(newMotherboardPrice.getText());
        String socket = newMotherboardSocket.getText();
        String memoryType = newMotherboardMemoryType.getText();
        int maxMemory = Integer.parseInt(newMotherboardMaxMemory.getText());
        int usb = Integer.parseInt(newMotherboardUSB.getText());

        currentMotherboard.setName(name);
        currentMotherboard.setPrice(price);
        currentMotherboard.setSocket(socket);
        currentMotherboard.setMemoryType(memoryType);
        currentMotherboard.setMaxMemory(maxMemory);
        currentMotherboard.setUsb(usb);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Материнская плата успешно изменена", "");
        setVisibility(null);
    }

    @FXML
    private void changeRam() {

        if (newRamName.getText().isEmpty() || newRamPrice.getText().isEmpty() || newRamMemory.getText().isEmpty()
                || newRamMemoryClock.getText().isEmpty() || newRamMemoryType.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newRamName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newRamPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newRamMemory.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен объем памяти", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newRamMemoryType.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип памяти", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newRamMemoryClock.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена частота памяти", "Повторите ввод");
            return;
        }

        String name = newRamName.getText();
        double price = Double.parseDouble(newRamPrice.getText());
        double memory = Double.parseDouble(newRamMemory.getText());
        String memoryType = newRamMemoryType.getText();
        double memoryClock = Double.parseDouble(newRamMemoryClock.getText());

        currentRam.setName(name);
        currentRam.setPrice(price);
        currentRam.setMemory(memory);
        currentRam.setMemoryType(memoryType);
        currentRam.setMemoryClock(memoryClock);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Оперативная память успешно изменена", "");
        setVisibility(null);
    }

    @FXML
    private void changePsu() {

        if (newPsuName.getText().isEmpty() || newPsuPrice.getText().isEmpty() || newPsuCapacity.getText().isEmpty()
                || newPsuEfficiency.getText().isEmpty() || newPsuFanSize.getText().isEmpty() || newPsuMaxAmperage.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newPsuName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newPsuPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newPsuCapacity.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена мощность", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9]]", newPsuEfficiency.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено КПД", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newPsuFanSize.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен размер вентилятора", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newPsuMaxAmperage.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена максимальная сила тока", "Повторите ввод");
            return;
        }

        String name = newPsuName.getText();
        double price = Double.parseDouble(newPsuPrice.getText());
        int capacity = Integer.parseInt(newPsuCapacity.getText());
        int efficiency = Integer.parseInt(newPsuEfficiency.getText());
        double fanSize = Double.parseDouble(newPsuFanSize.getText());
        double maxAmperage = Double.parseDouble(newPsuMaxAmperage.getText());

        currentPsu.setName(name);
        currentPsu.setPrice(price);
        currentPsu.setCapacity(capacity);
        currentPsu.setEfficiency(efficiency);
        currentPsu.setFanSize(fanSize);
        currentPsu.setMaxAmperage(maxAmperage);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Блок питания успешно изменен", "");
        setVisibility(null);
    }

    @FXML
    private void changeDataStorage() {

        if (newDataStorageName.getText().isEmpty() || newDataStoragePrice.getText().isEmpty() || newDataStorageCapacity.getText().isEmpty()
                || newDataStorageType.getText().isEmpty() || newDataStorageInterface.getText().isEmpty() || newDataStorageBuffer.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newDataStorageName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено название", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newDataStoragePrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена стоимость", "Повторите ввод");
            return;
        }
        if (!(newDataStorageType.getText().trim().equals("HDD") || newDataStorageType.getText().trim().equals("SSD"))) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип\nВозможные типы: SSD и HDD.", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newDataStorageCapacity.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен объем памяти", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newDataStorageInterface.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен интерфейс", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newDataStorageBuffer.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введено значение буфера", "Повторите ввод");
            return;
        }

        String name = newDataStorageName.getText();
        double price = Double.parseDouble(newDataStoragePrice.getText());
        String type = newDataStorageType.getText();
        int capacity = Integer.parseInt(newDataStorageCapacity.getText());
        String dataStorageInterface = newDataStorageInterface.getText();
        int buffer = Integer.parseInt(newDataStorageBuffer.getText());

        currentDataStorage.setName(name);
        currentDataStorage.setPrice(price);
        currentDataStorage.setType(type);
        currentDataStorage.setCapacity(capacity);
        currentDataStorage.setDataStorageInterface(dataStorageInterface);
        currentDataStorage.setBuffer(buffer);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Запоминающее устройство успешно изменено", "");
        setVisibility(null);
    }

    @FXML
    private void changeComputerBox() {

        if (newComputerBoxName.getText().isEmpty() || newComputerBoxPrice.getText().isEmpty() || newComputerBoxType.getText().isEmpty()) {
            alert(Alert.AlertType.ERROR, "Error", "Одно или несколько полей пусты", "Необходимо заполнить все поля");
            return;
        }

        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newComputerBoxName.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип памяти", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^[0-9][\\.]]", newComputerBoxPrice.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введена максимальная память", "Повторите ввод");
            return;
        }
        if (isInputIncorrect("[^A-Za-z[0-9][\\s][\\-]]", newComputerBoxType.getText().trim())) {
            alert(Alert.AlertType.WARNING, "Некорректный ввод", "Некорректно введен тип", "Повторите ввод");
            return;
        }

        String name = newComputerBoxName.getText();
        double price = Double.parseDouble(newComputerBoxPrice.getText());
        String type = newComputerBoxType.getText();

        currentComputerBox.setName(name);
        currentComputerBox.setPrice(price);
        currentComputerBox.setType(type);

        alert(Alert.AlertType.INFORMATION, "Успешно", "Корпус ПК успешно изменен", "");
        setVisibility(null);
    }

    //удалить выбранный объект
    @FXML
    private void handleRemove() {
        switch (switchPartType.getValue()) {
            case "Процессор":
                if (currentProcessor == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                Processor.processorList.deleteKey(currentProcessor);
                observableProcessorList.remove(currentProcessor);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Процессор успешно удален", "");
                break;
            case "Видеокарта":
                if (currentVideocard == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                Videocard.videocardList.deleteKey(currentVideocard);
                observableVideocardList.remove(currentVideocard);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Видеокарта успешно удалена", "");
                break;
            case "Материнская плата":
                if (currentMotherboard == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                Motherboard.motherboardList.deleteKey(currentMotherboard);
                observableMotherboardList.remove(currentMotherboard);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Материнская плата успешно изменена", "");
                break;
            case "Блок питания":
                if (currentPsu == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                PSU.psuList.deleteKey(currentPsu);
                observablePsuList.remove(currentPsu);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Блок питания успешно изменена", "");
                break;
            case "Оперативная память":
                if (currentRam == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                RAM.ramList.deleteKey(currentRam);
                observableRamList.remove(currentRam);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Оперативная память успешно изменена", "");
                break;
            case "Запоминающее устройство":
                if (currentDataStorage == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                DataStorage.dataStorageList.deleteKey(currentDataStorage);
                observableDataStorageList.remove(currentDataStorage);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Запоминающее устройство успешно изменено", "");
                break;
            case "Корпус ПК":
                if (currentComputerBox == null) {
                    alert(Alert.AlertType.WARNING, "Ошибка", "Необходимо выбрать объект", "");
                    return;
                }
                ComputerBox.computerBoxList.deleteKey(currentComputerBox);
                observableComputerBoxList.remove(currentComputerBox);
                alert(Alert.AlertType.INFORMATION, "Успешно", "Корпус ПК успешно изменен", "");
                break;
        }
    }

    private static void alert(Alert.AlertType alertType, String titleText, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private boolean isInputIncorrect(String pat, String str) {
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(str);
        return m.find();
    }

    //загрузить списки
    @FXML
    private static void handleLoad() {
        if (!(Processor.processorList.isEmpty() || Videocard.videocardList.isEmpty() || Motherboard.motherboardList.isEmpty() || RAM.ramList.isEmpty() || PSU.psuList.isEmpty() || DataStorage.dataStorageList.isEmpty() || ComputerBox.computerBoxList.isEmpty())) {
            alert(Alert.AlertType.ERROR, "Ошибка", "Невозможно загрузить данные", "В данный момент списки содержат элементы,\n загрузка невозможна");
           return;
        }
        try {
            Processor.getProcessorsFromFile();
            Videocard.getVideocardsFromFile();
            Motherboard.getMotherboardsFromFile();
            RAM.getRamFromFile();
            PSU.getPsuFromFile();
            DataStorage.getDataStorageFromFile();
            ComputerBox.getComputerBoxFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        alert(Alert.AlertType.INFORMATION, "Загружено!", "Списки загружены из файла!", "");
    }

    //сохранить списки
    @FXML
    private static void handleSave() {
        try {
            Processor.saveProcessorList();
            Videocard.saveVideocardList();
            Motherboard.saveMotherboardList();
            RAM.saveRamList();
            PSU.savePsuList();
            DataStorage.saveDataStorageList();
            ComputerBox.saveComputerBoxList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
