package ControllerFiles;

import ClassFiles.CosmicBody;
import ClassFiles.Planet;
import ClassFiles.Satellits;
import ClassFiles.Stars;
import ModalWindow.ErrorWindow;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;

public class MainController {

    @FXML //ГЛАВНЫЙ КОМПОНЕНТ
    private TreeView TreePlanet;
    @FXML
    private ImageView imageHeader, imageClose;
    @FXML
    private Button btAdd, btRestore, btSearch, btEdint, brRemove, btStore;
    @FXML
    private Pane inputPanel, paneCloseBlur, paneCloseBlur2,
            showInfoPane, paneHeader4, paneHeader5, paneHeader6,
            panelFindInTreeView;
    @FXML
    private AnchorPane windowMain, anchOane;
    @FXML
    private Label LabelPaneAdd, label1, label2, label3, label4, label5, label6,
            labeltext1, labeltext2, labeltext3, labeltext5, labeltext4, labeltext6,
            header1, header2, header3, header4, header5, header6,
            infoPaneLabel;
    @FXML
    private TextField textField1, textField2, textField3, textField4, textField5, textField6,
            textFieldFind;
    @FXML
    private Line line1, line2, line3;
    //вспомагательные переменные
    private static String namePanel;
    private static double panePosition = 0.0;
    private static String typePanel;
    //буфер
    private static Stars st;
    private static Planet pl;
    private static Satellits sl;
    private static TreeItem pr;

    private static TreeItem spaceOffice;
    private TreeItem tempNode = null;

    @FXML
    public void initialize(){
        System.out.println("LOAD MAIN CONTROLLER");
        try {
            testStartBranch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        btStore.setVisible(false);
        btRestore.setVisible(false);
    }
    //СТАРТОВАЯ ИНИЦИАЛИЗАЦИЯ
    private void testStartBranch() throws Exception {
        Stars star = new Stars("СОЛНЦЕ","СОЛНЕЧНАЯ СИСТЕМА", 100000000, 10000.0, 8);
        Planet planet = new Planet("ЗЕМЛЯ", 30000000.0, 700000000, 0.80, 40.5, 1);
        Satellits satellit = new Satellits("ЛУНА", 55000000, 20.0);
        spaceOffice = new TreeItem<>("SPACE OFFICE", getImageInTreeItem("starSystem"));
        TreeItem stars = new TreeItem<>(star, getImageInTreeItem("star"));
        TreeItem planets = new TreeItem<>(planet, getImageInTreeItem("planet"));
        TreeItem satellits = new TreeItem<>(satellit, getImageInTreeItem("satellites"));
        spaceOffice.getChildren().add(stars);
        stars.getChildren().add(planets);
        planets.getChildren().add(satellits);
        TreePlanet.setRoot(spaceOffice);
    }

    //РАБОТА С ИЗОБРАЖЕНИЯМИ
    private ImageView getImageInTreeItem(String name){
        ImageView image = null;
        switch (name){
            case "star":
                image = new ImageView(new Image((getClass().getResourceAsStream("/Image/star.png"))));
                break;
            case"planet":
                image = new ImageView(new Image((getClass().getResourceAsStream("/Image/planet.png"))));
                break;
            case"satellites":
                image = new ImageView(new Image((getClass().getResourceAsStream("/Image/satellites.png"))));
            break;
            case"starSystem":
                image = new ImageView(new Image((getClass().getResourceAsStream("/Image/starSystem.png"))));
                image.setFitHeight(30.0);
                image.setFitWidth(30.0);
                return image;
        }
        image.setFitHeight(20.0);
        image.setFitWidth(20.0);
        return image;
    }
    //Проверка выбраного элемента TreeView на принадлежность к классу с заменой изображения
    public void imageUpdateInThePanel(TreeItem item){
        if(item.getValue() instanceof  Stars){
            imageHeader.setImage(new Image((getClass().getResourceAsStream("/Image/star.png"))));
            namePanel = "addPlanet";
        } else if (item.getValue() instanceof  Planet){
            imageHeader.setImage(new Image((getClass().getResourceAsStream("/Image/planet.png"))));
            namePanel = "addSatellites";
        }else if (item.getValue() instanceof  Satellits){
            imageHeader.setImage(new Image((getClass().getResourceAsStream("/Image/satellites.png"))));
            namePanel = "last";
        } else{
            imageHeader.setImage(new Image((getClass().getResourceAsStream("/Image/starSystem.png"))));
            namePanel = "addStar";
        }
    }
    //МЕТОД ПРОВЕРКИ ВЫБОРА КОМПОНЕНТА TREEVIEW
    private void getEnterTreeItem() throws IOException {
        if(treeViewIsSelected()){ }else{ inputPanelSelection(namePanel); }
    }

    //ОБРАБОТЧИК НАЖАТИЯ МИШИ
    public void mouseClick(MouseEvent mouseEvent){
        try{//реакция на двойное нажатие
        if(mouseEvent.getClickCount() == 2){
            showInformInTree();
        }
        //реакция на одинарное нажатие
        if(mouseEvent.getClickCount() == 1){
            TreeItem item = (TreeItem) TreePlanet.getSelectionModel().getSelectedItem();
            System.out.println(item.getValue());
            System.out.println("name => " + item.getValue().getClass());
            imageUpdateInThePanel(item);
        }
        }catch (Exception e){}
    }

    //ОТОБРАЖЕНИЕ ПАНЕЛИ ВВОДА ДАНННЫХ
    private void inputPanelSelection(String namePanel) {
        if(typePanel.equals("add")){
            if(namePanel.equals("last")) try { new ErrorWindow("Not selected in the directory"); } catch (IOException e) {}
            switch (namePanel) {
                case "addStar": viewPaneStar("NEW STAR");break;
                case "addPlanet": viewPanePlanet("NEW PLANET");break;
                case "addSatellites": viewPaneSatellites("NEW SATELLITE");break;
            }
        }else if (typePanel.equals("edit")) {
            switch (namePanel) {
                case "addPlanet": viewPaneStar("EDIT STAR");getStarInTreeView();break;
                case "addSatellites": viewPanePlanet("EDIT PLANET");getPlanetInTreeView();break;
                case "last": viewPaneSatellites("EDIT SATELLITE");getSatelitesInTreeView();break;
            }
        }
    }
    private void viewPaneStar(String textLabel){
        sitingsPane(textLabel,420.0);
        label1.setText("name star");
        label2.setText("the weight");
        label3.setText("name star system");
        label4.setText("number of planets");
        label5.setText("average temperature");
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(false);
        textField5.setVisible(true);
        textField4.setVisible(true);
        textField6.setVisible(false);
        line1.setVisible(true);
        line2.setVisible(true);
        line3.setVisible(false);
        clearTextField();
        translatePanel("down", inputPanel);
    }
    private void viewPanePlanet(String textLabel){
        sitingsPane(textLabel,420.0);
        label1.setText("name planet");
        label2.setText("the weight");
        label3.setText("population");
        label4.setText("number satellites");
        label5.setText("average temperature");
        label6.setText("oxygen level");
        label6.setVisible(true);
        label5.setVisible(true);
        label4.setVisible(true);
        textField6.setVisible(true);
        textField5.setVisible(true);
        textField4.setVisible(true);
        line1.setVisible(true);
        line2.setVisible(true);
        line3.setVisible(true);
        clearTextField();
        translatePanel("down", inputPanel);
    }
    private void viewPaneSatellites(String textLabel){
        sitingsPane(textLabel,420.0);
        label1.setText("name satellites");
        label2.setText("the weight");
        label3.setText("average temperature");
        label6.setVisible(false);
        label5.setVisible(false);
        label4.setVisible(false);
        textField6.setVisible(false);
        textField5.setVisible(false);
        textField4.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        clearTextField();
        translatePanel("down", inputPanel);
    }

    //АНИМИРОВАНИЕ ПОЯВЛЕНИЯ ПАНЕЛИ ВВОДА ЗНАЧЕНИЯ
    private void translatePanel(String pos, Pane pane){
        panePosition = pane.getHeight() + 20;
        TranslateTransition trP = new TranslateTransition(Duration.seconds(0.3f), pane);
        if (pos.equals("down")){
            paneCloseBlur.setPrefHeight(windowMain.getPrefHeight());
            trP.setToY(panePosition);
        }else if (pos.equals("up")){
            trP.setToY(0);
            paneCloseBlur.setPrefHeight(0);
        } else if(pos.equals("up2")){
            paneCloseBlur2.setPrefHeight(windowMain.getPrefHeight());
            trP.setToY(-panePosition);
        } else if(pos.equals("down2")){
            trP.setToY(0);
            paneCloseBlur2.setPrefHeight(0);
        }
        trP.play();
    }
    //МЕТОД НАСТРОЙКИ ПАНЕЛИ
    private void sitingsPane(String headerLabel ,double heightPane){
        LabelPaneAdd.setText(headerLabel);
        inputPanel.setPrefHeight(heightPane);
        anchOane.setPrefHeight(heightPane);
    }
    //ПОКАЗАТЬ ПАНЕЛЬ
    public void closeOpenPane(){
        translatePanel("up", inputPanel);
    }

    //ДОБАВИТЬ НОВУЮ ВЕТКУ С ЗАПИСЬЮ
    public void addNewSpaceOffice(ActionEvent actionEvent) {
        switch (typePanel){
            case"add":
                if(textFieldIsEmpty(namePanel)){
                    try { new ErrorWindow("TextField is empty in " + namePanel); } catch (IOException e) { }
                } else{
                    switch (namePanel){
                        case"addStar": addNewStarInTreeView(); break;
                        case"addPlanet": addNewPlanetInTreeView(); break;
                        case"addSatellites": addNewSatelliteInTreeView(); break;
                    }
                }
                break;
            case"edit":
                if(textFieldIsEmpty(namePanel)){
                    try { new ErrorWindow("TextField is empty"); } catch (IOException e) { }
                } else{
                    switch (namePanel){
                        case"addPlanet": System.out.println("EDIT STAR"); addNewStarInTreeView(); break;
                        case"addSatellites": System.out.println("EDIT PLANET"); addNewPlanetInTreeView(); break;
                        case"last": System.out.println("EDIT SATELLITE"); addNewSatelliteInTreeView(); break;
                    }
                }
                break;
        }
    }
    //ДОБАВЛЕНИЯ ЗВЕЗДЫ
    private void addNewStarInTreeView(){
        try {
            Stars star = new Stars(textField1.getText(),textField3.getText(), Double.parseDouble(textField2.getText()),
                    Double.parseDouble(textField5.getText()), Integer.parseInt(textField4.getText()));
            addNewTreeView(star, "star", typePanel);
        } catch (Exception e) { try { new ErrorWindow(e.toString()); } catch (IOException e1) { } }
    }
    //ДОБАВЛЕНИЕ ПЛАНЕТЫ
    private void addNewPlanetInTreeView(){
        try {
            Planet planet = new Planet(textField1.getText(),  Double.parseDouble(textField2.getText()),
                    Integer.parseInt(textField3.getText()), Double.parseDouble(textField6.getText()),
                    Double.parseDouble(textField5.getText()), Integer.parseInt(textField4.getText()));
            addNewTreeView(planet, "planet", typePanel);
        } catch (Exception e) { try { new ErrorWindow(e.toString()); } catch (IOException e1) { } }
    }
    //ДОБАВЛЕНИЕ СПУТНИКА
    private void addNewSatelliteInTreeView(){
        try {
            Satellits satellit = new Satellits(textField1.getText(), Double.parseDouble(textField2.getText()),
                    Double.parseDouble(textField3.getText()));
            addNewTreeView(satellit, "satellites", typePanel);
        } catch (Exception e) { try { new ErrorWindow(e.toString()); } catch (IOException e1) { } }
    }
    //ДОБАВЛЕНИЕ УЗЛА В ВЕТКУ
    private void addNewTreeView(CosmicBody newBody, String image, String typePanel){
        switch (typePanel){
            case"add":
                TreeItem parent = getSelectedTreeNode();
                TreeItem newTree = new TreeItem<>(newBody, getImageInTreeItem(image));
                parent.getChildren().add(newTree);
                break;
            case"edit":
                pr.setValue(newBody);
                break;
        }
    }
    //ПОЛУЧЕНИЕ ЗВЕЗДЫ
    private void getStarInTreeView(){
        pr = getSelectedTreeNode();
        Stars stars = (Stars) pr.getValue();
        textField1.setText(stars.getName());
        textField2.setText(String.valueOf(stars.getMass()));
        textField3.setText(stars.getStarSystem());
        textField4.setText(String.valueOf(stars.getNumberPlanets()));
        textField5.setText(String.valueOf(stars.getAverageTemperature()));
        st = stars;
    }

    //ПОЛУЧЕНИЕ ПЛАНЕТЫ
    private void getPlanetInTreeView(){
        pr = getSelectedTreeNode();
        Planet planet = (Planet) pr.getValue();
        textField1.setText(planet.getName());
        textField2.setText(String.valueOf(planet.getMass()));
        textField3.setText(String.valueOf(planet.getPopulation()));
        textField4.setText(String.valueOf(planet.getNumberSatellites()));
        textField5.setText(String.valueOf(planet.getAverageTemperature()));
        textField6.setText(String.valueOf(planet.getOxygenLevel()));
        pl = planet;
    }
    //ПОЛУЧЕНИЕ СПУТНИКА
    private void getSatelitesInTreeView(){
        pr = getSelectedTreeNode();
        Satellits satellits = (Satellits) pr.getValue();
        textField1.setText(satellits.getName());
        textField2.setText(String.valueOf(satellits.getMass()));
        textField3.setText(String.valueOf(satellits.getAverageTemperature()));
        sl = satellits;
    }
    //МЕТОД УДАЛЕНИЯ ВЕТКИ
    private void removeBrunch(){
        TreeItem tree =  getSelectedTreeNode();
        if(tree == null || (tree.getValue().equals("SPACE OFFICE"))) return;
        tree.getParent().getChildren().remove(tree);
        namePanel = "";
    }
    //ПОЛУЧИТЬ УЗЕЛ
    private TreeItem getSelectedTreeNode(){
        Object tree =  TreePlanet.getSelectionModel().getSelectedItem();
        if(tree == null) return null;
        return (TreeItem) tree;
    }

    //МЕТОД ОЧИСТКИ ПОЛЕЙ
    private void clearTextField(){
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }
    //ОТОБРАЖЕНИЕ СООБЩЕНИЯ В СЛУЧАЕ ПУСТОГО ПОЛЯ
    private boolean textFieldIsEmpty(String panel){
        if(typePanel.equals("add")) {
            switch (panel) {
                case "addStar": if(isEmptyTextFieldAddStar()) return true;break;
                case "addPlanet": if(isEmptyTextFieldAddPlanet()) return true;break;
                case "addSatellites": if(isEmptyTextFieldAddSatellites()) return true;break;
            }
        } else if(typePanel.equals("edit")){
            switch (panel) {
                case "addPlanet": if(isEmptyTextFieldAddStar()) return true;break;
                case "addSatellites": if(isEmptyTextFieldAddPlanet()) return true;break;
                case "last":  if(isEmptyTextFieldAddSatellites()) return true;break;
            }
        }
        return false;
    }
    //ВСПОМАГАТЕЛЬНЫЕ МЕТОДЫ ДЛЯ ПРОВЕРКИ ПОЛЕЙ
    private boolean isEmptyTextFieldAddStar(){
        if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") ||
                textField4.getText().equals("") || textField5.getText().equals("")) {
            return true;
        } return false;
    }
    private boolean isEmptyTextFieldAddPlanet(){
        if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") ||
                textField4.getText().equals("") || textField5.getText().equals("") || textField6.getText().equals("")) {
            return true;
        } return false;
    }
    private boolean isEmptyTextFieldAddSatellites(){
        if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("")) {
            return true;
        } return false;
    }
    //ВЫБРАНА ЛИ ВЕТКА
    private boolean treeViewIsSelected(){
        TreeItem tree =  getSelectedTreeNode();
        if((tree == null)) {
            namePanel = "";
            try{ new ErrorWindow("Not selected in the directory"); } catch (IOException e) { }
            return true;
        }
        return false;
    }

    //СОХРАНЕНИЕ В ФАЙЛ
    private void saveInFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("SPACE OFFICE");
        try{
            TreeItem tempTree;
            File file = fileChooser.showSaveDialog(null);
            FileOutputStream fileStream = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            for(Object star : spaceOffice.getChildren()){
                out.writeObject((Stars) ((TreeItem) star).getValue());
                for(Object planet: ( (TreeItem)star).getChildren() ){
                    out.writeObject((Planet) ((TreeItem) planet).getValue());
                    //поиск по списку спутников
                    for(Object satellite: ((TreeItem)planet).getChildren() ){
                        out.writeObject((Satellits) ((TreeItem) satellite).getValue());
                    }
                }
            }
            out.close();
        }catch (Exception e){
            System.out.println("ERRRRRRRRRRRRROOOOOOOOOOOOORRRRRRR");
        }

    }
    //ОТКРЫТИЕ ФАЙЛА
    private void openFile(){
        try{
            FileChooser fileChooser = new FileChooser();
            File file;
            fileChooser.setTitle("OPEN SPACE OFFICE");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
            file = fileChooser.showOpenDialog(null);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()));
            spaceOffice.getChildren().addAll((TreeItem) in.readObject());
            TreePlanet.setRoot(spaceOffice);
            in.close();
        }catch (Exception e){
            System.out.println("Error open file");
        }
        for(Object star : spaceOffice.getChildren()){
            System.out.println(((TreeItem)star).getValue().toString());
        }
    }

    //ОБРАБОТЧИКИ НАЖАТИЯ КЛАВИШИ
    public void ADD(ActionEvent actionEvent)throws IOException {
        typePanel = "add";
        getEnterTreeItem();
    }
    public void REMOVE(ActionEvent actionEvent)throws IOException {
        if(treeViewIsSelected()){} else{ removeBrunch(); }
    }
    public void EDIT(ActionEvent actionEvent)throws IOException {
        TreeItem tree =  getSelectedTreeNode();
        typePanel = "edit";
        if(tree.getValue().equals("SPACE OFFICE")){
            namePanel = "";
            try{ new ErrorWindow("Not selected in the directory (stare, " +
                    "planet, satellite)"); } catch (IOException e) { }
        } else{
            getEnterTreeItem();
        }
    }
    public void STORE(ActionEvent actionEvent)throws IOException {
        if(spaceOfficeIsEmpty()) return;
        saveInFile();
    }
    public void RESTORE(ActionEvent actionEvent)throws IOException {
        openFile();
    }

    //ОТОБРАЖЕНИЕ ПОЛНОЙ ИНФОРМАЦИИ
    private void showInformInTree(){
        TreeItem tree =  getSelectedTreeNode();
        if(tree.getValue().equals("SPACE OFFICE")) {}
        else {
            showAndDownPaneBlur();
            switch (namePanel){
                case"addPlanet":
                    getStarInfo(tempNode);
                    break;
                case"addSatellites":
                    getPlanetInfo(tempNode);
                    break;
                case"last":
                    getSatellitInfo(tempNode);
                    break;
            }
        }
    }
    private void showAndDownPaneBlur(){
        paneCloseBlur.setPrefHeight(windowMain.getPrefHeight());
        transformshowInfoPane("right");
    }
    private void getStarInfo(TreeItem tempNode){
        infoPaneLabel.setText("INFO STAR");
        paneHeader4.setVisible(true);
        paneHeader5.setVisible(true);
        paneHeader6.setVisible(false);
        Stars stars;
        if(tempNode == null) stars = (Stars) getSelectedTreeNode().getValue();
        else stars = ((Stars) tempNode.getValue());
        header1.setText("NAME STAR:"); labeltext1.setText(stars.getName());
        header2.setText("THE WEIGHT:"); labeltext2.setText(String.valueOf(stars.getMass()));
        header3.setText("NAME STAR SYSTEM:"); labeltext3.setText(stars.getStarSystem());
        header4.setText("NUMBER OF PLANET:"); labeltext4.setText(String.valueOf(stars.getNumberPlanets()));
        header5.setText("AVERAGE TEMPERATURE:"); labeltext5.setText(String.valueOf(stars.getAverageTemperature())+" C");
    }
    private void getPlanetInfo(TreeItem tempNode){
        infoPaneLabel.setText("INFO PLANET");
        paneHeader4.setVisible(true);
        paneHeader5.setVisible(true);
        paneHeader6.setVisible(true);
        Planet planet;
        if(tempNode == null) planet = (Planet) getSelectedTreeNode().getValue();
        else planet = ((Planet) tempNode.getValue());
        header1.setText("NAME PLANET:"); labeltext1.setText(planet.getName());
        header2.setText("THE WEIGHT:"); labeltext2.setText(String.valueOf(planet.getMass()));
        header3.setText("POPULATION:"); labeltext3.setText(String.valueOf(planet.getPopulation()) + " млрд.");
        header4.setText("NUMBER SATELLITE:"); labeltext4.setText(String.valueOf(planet.getNumberSatellites()));
        header5.setText("AVERAGE TEMPERATURE:"); labeltext5.setText(String.valueOf(planet.getAverageTemperature())+" C");
        header6.setText("OXYGEN LEVEL:"); labeltext6.setText(String.valueOf(planet.getOxygenLevel()) + " %");
    }
    private void getSatellitInfo(TreeItem tempNode){
        infoPaneLabel.setText("INFO SATELLITE");
        paneHeader4.setVisible(false);
        paneHeader5.setVisible(false);
        paneHeader6.setVisible(false);
        Satellits satellits;
        if(tempNode == null) satellits = (Satellits) getSelectedTreeNode().getValue();
        else satellits = ((Satellits) tempNode.getValue());
        label1.setText("NAME SATELLITE:"); labeltext1.setText(satellits.getName());
        label2.setText("THE WEIGHT:"); labeltext2.setText(String.valueOf(satellits.getMass()));
        label3.setText("AVERAGE TEMPERATURE:"); labeltext3.setText(String.valueOf(satellits.getAverageTemperature()) + " C");
    }
    private void transformshowInfoPane(String leftRight){
        double panePoz = showInfoPane.getWidth() + 10;
        TranslateTransition trP = new TranslateTransition(Duration.seconds(0.3f), showInfoPane);
        if(leftRight.equals("left")){ trP.setToX(0); }
        else if(leftRight.equals("right")){ trP.setToX(panePoz); }
        trP.play();
    }
    public void closeLabel(MouseEvent mouseEvent) {
        paneCloseBlur.setPrefHeight(0);
        transformshowInfoPane("left");
    }

    //Вызов панели поиска
    public void SEARCH(ActionEvent actionEvent) {
        if(spaceOfficeIsEmpty()) return;
        translatePanel("up2", panelFindInTreeView);
    }
    public void closeFindPane(MouseEvent mouseEvent) {
        translatePanel("down2", panelFindInTreeView);
    }
    //поиск по значению
    public void findIconOnButton(MouseEvent mouseEvent) {
        if(textFieldFind.getText().equals("")) try { new ErrorWindow("No request," +
                " enter query!"); return; } catch (IOException e) { }
        findNameNodeTreeView();
    }
    //ПОИСК СООТВЕТСТВИЙ ВВОДИМОГО ЗНАЧЕНИЯ В ВЕТКЕ
    private void findNameNodeTreeView(){
        TreeItem tempTree;
        //поиск по списку звезд
        for(Object star : spaceOffice.getChildren()){
            tempTree = ((TreeItem)star);
            if(equalsText(tempTree)) { getStarInfo(tempTree); showAndDownPaneBlur(); return;}
            for(Object planet: ( (TreeItem)star).getChildren() ){
                tempTree = ((TreeItem)planet);
                if(equalsText(tempTree)) { getPlanetInfo(tempTree); showAndDownPaneBlur(); return;}
                //поиск по списку спутников
                for(Object satellite: ((TreeItem)planet).getChildren() ){
                    tempTree = ((TreeItem)satellite);
                    if(equalsText(tempTree)) { getSatellitInfo(tempTree); showAndDownPaneBlur(); return;}
                }
            }
        }
        try { new ErrorWindow("Nothing found on request!"); return; } catch (IOException e) { }
    }
    private boolean equalsText(TreeItem tempTree){
        if(tempTree.getValue().toString().toLowerCase().equals(textFieldFind.getText().toLowerCase().toString()))
            return true;
        return false;
    }

    //проверка на наличие дерева
    private boolean spaceOfficeIsEmpty(){
        if(spaceOffice.getChildren().isEmpty()){
            try { new ErrorWindow("SPACE IS EMPTY!\n" +
                    "Add a new star, planet, or satellite."); } catch (IOException e) {}
        return true;
        }
        return false;
    }
}
