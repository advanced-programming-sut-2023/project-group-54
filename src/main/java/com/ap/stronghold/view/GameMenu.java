package com.ap.stronghold.view;

import com.ap.stronghold.controller.*;
import com.ap.stronghold.model.Buildings.Building;
import com.ap.stronghold.model.Buildings.BuildingType;
import com.ap.stronghold.model.Buildings.ProducerBuilding;
import com.ap.stronghold.model.Buildings.ShopBuilding;
import com.ap.stronghold.model.Direction;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.MapType;
import com.ap.stronghold.model.User;
import com.ap.stronghold.model.units.State;
import com.ap.stronghold.model.units.Unit;
import com.ap.stronghold.model.units.UnitType;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.GameMenuMessage;
import com.ap.stronghold.view.enums.messages.MapMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;

public class GameMenu extends Application {
    public static GridPane gridePane;
    public static Pane pane;
    private static int tilesLength;
    private static int xOfMap;
    private static int yOfMap;
    private static MapType typeForTreeAndTexture = MapType.DEFAULT;
    private static Direction direction = Direction.F;
    private static HashMap<String, Image> images;
    private static ImageView[][] imageViews;
    private static ImageView[] faceN;
    private static ImageView[] faceP;
    private static ImageView[] faceM;
    private static int x;
    private static int y;
    private static Tooltip tooltip;
    private static Rectangle rectangle = new Rectangle();
    private static VBox rectangleVBox;
    private static VBox popularityVBox;
    private static VBox anotherVBox;
    private static VBox buttonVBox;
    private static VBox buildingVBox;
    private static VBox buttonVBox2;
    private static Slider slider = new Slider();
    private static int rectangleYIn = -1;
    private static int rectangleXIn = -1;
    private static int rectangleYOut = -1;
    private static int rectangleXOut = -1;
    private static ArrayList<Unit> unitsInRectangle = new ArrayList<>();
    private static HashSet<Building> buildingsInRectangle = new HashSet<>();
    private static Text numberOfUnitsText;
    private static Text avRateText;
    private static Text rateMinText;
    private static Text rateMaxText;
    private static Text foodText;
    private static Text taxText;
    private static Text fearText;
    private static Text religionText;
    private static Text rateText;
    private static Text popularityText;
    private static HBox foodHBox;
    private static HBox taxHBox;
    private static HBox fearHBox;
    private static HBox religionHBox;
    private static HBox rateHBox;
    private static HBox popularityHBox;
    private static Text treasuryText;
    private static Text populationText;


    private static ImageView[][] minimapImageViews;

    static {
        tilesLength = 50;

        images = new HashMap<>();
        images.put("EARTH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/earth.png").toExternalForm()));
        images.put("EARTH_AND_STONE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/earth_and_stone.png").toExternalForm()));
        images.put("BOULDERS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/boulders.png").toExternalForm()));
        images.put("ROCK_N", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_n.png").toExternalForm()));
        images.put("ROCK_S", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_s.png").toExternalForm()));
        images.put("ROCK_E", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_e.png").toExternalForm()));
        images.put("ROCK_W", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_w.png").toExternalForm()));
        images.put("IRON", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/iron.png").toExternalForm()));
        images.put("OASIS_GRASS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/oasis_grass.png").toExternalForm()));
        images.put("SCRUB", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/scrub.png").toExternalForm()));
        images.put("THICK_SCRUB", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/thick_scrub.png").toExternalForm()));
        images.put("OIL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/oil.png").toExternalForm()));
        images.put("SHALLOW_WATER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/shallow_water.png").toExternalForm()));
        images.put("RIVER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/river.png").toExternalForm()));
        images.put("SMALL_POND", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/small_pond.png").toExternalForm()));
        images.put("BIG_POUND", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/big_pound.png").toExternalForm()));
        images.put("BEACH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/beach.png").toExternalForm()));
        images.put("SEA", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/sea.png").toExternalForm()));

        images.put("WALL_STAIRS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/WALL_STAIRS.png").toExternalForm()));
        images.put("LOW_WALL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/LOW_WALL.png").toExternalForm()));
        images.put("STONE_WALL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/STONE_WALL.png").toExternalForm()));
        images.put("SMALL_STONE_GATE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/SMALL_STONE_GATE.png").toExternalForm()));
        images.put("LARGE_STONE_GATE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/LARGE_STONE_GATE.png").toExternalForm()));
        images.put("DRAW_BRIDGE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/DRAW_BRIDGE.png").toExternalForm()));
        images.put("LOOK_OUT_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/LOOK_OUT_TOWER.png").toExternalForm()));
        images.put("PERIMETER_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/PERIMETER_TOWER.png").toExternalForm()));
        images.put("DEFENCE_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/DEFENCE_TOWER.png").toExternalForm()));
        images.put("SQUARE_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/SQUARE_TOWER.png").toExternalForm()));
        images.put("ROUND_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ROUND_TOWER.png").toExternalForm()));
        images.put("ARMOURY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ARMOURY.png").toExternalForm()));
        images.put("BARRACKS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BARRACKS.png").toExternalForm()));
        images.put("MERCENARY_POST", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MERCENARY_POST.png").toExternalForm()));
        images.put("ENGINEERS_GUILD", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ENGINEERS_GUILD.png").toExternalForm()));
        images.put("KILLING_PIT", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/KILLING_PIT.png").toExternalForm()));
        images.put("OIL_SMELTER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/OIL_SMELTER.png").toExternalForm()));
        images.put("PITCH_DITCH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/PITCH_DITCH.png").toExternalForm()));
        images.put("CAGED_WAR_DOGS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/CAGED_WAR_DOGS.png").toExternalForm()));
        images.put("SIEGE_TENT", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/SIEGE_TENT.png").toExternalForm()));
        images.put("APPLE_ORCHARD", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/APPLE_ORCHARD.png").toExternalForm()));
        images.put("DIARY_FARMER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/DIARY_FARMER.png").toExternalForm()));
        images.put("HOPS_FARMER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/HOPS_FARMER.png").toExternalForm()));
        images.put("WHEAT_FARMER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/WHEAT_FARMER.png").toExternalForm()));
        images.put("HUNTER_POST", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/HUNTER_POST.png").toExternalForm()));
        images.put("BAKERY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BAKERY.png").toExternalForm()));
        images.put("BREWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BREWER.png").toExternalForm()));
        images.put("GRANARY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/GRANARY.png").toExternalForm()));
        images.put("INN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/INN.png").toExternalForm()));
        images.put("MILL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MILL.png").toExternalForm()));
        images.put("IRON_MINE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/IRON_MINE.png").toExternalForm()));
        images.put("MARKET", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MARKET.png").toExternalForm()));
        images.put("OX_TETHER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/OX_TETHER.png").toExternalForm()));
        images.put("PITCH_RIG", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/PITCH_RIG.png").toExternalForm()));
        images.put("QUARRY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/QUARRY.png").toExternalForm()));
        images.put("STOCK_PILE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/STOCK_PILE.png").toExternalForm()));
        images.put("WOODCUTTER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/WOODCUTTER.png").toExternalForm()));
        images.put("HOVEL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/HOVEL.png").toExternalForm()));
        images.put("CHURCH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/CHURCH.png").toExternalForm()));
        images.put("CATHEDRAL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/CATHEDRAL.png").toExternalForm()));
        images.put("ARMOURER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ARMOURER.png").toExternalForm()));
        images.put("BLACKSMITH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BLACKSMITH.png").toExternalForm()));
        images.put("FLETCHER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/FLETCHER.png").toExternalForm()));
        images.put("POLETURNER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/POLETURNER.png").toExternalForm()));
        images.put("MAIN_HOUSE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MAIN_HOUSE.png").toExternalForm()));

        images.put("ARABIAN_BOW", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/ARABIAN_BOW.png").toExternalForm()));
        images.put("ARABIAN_SWORDSMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/ARABIAN_SWORDSMAN.png").toExternalForm()));
        images.put("ARCHER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/ARCHER.png").toExternalForm()));
        images.put("ASSASSIN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/ASSASSIN.png").toExternalForm()));
        images.put("BLACK_MONK", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/BLACK_MONK.png").toExternalForm()));
        images.put("CROSSBOWMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/CROSSBOWMAN.png").toExternalForm()));
        images.put("ENGINEER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/ENGINEER.png").toExternalForm()));
        images.put("FIRE_THROWERS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/FIRE_THROWERS.png").toExternalForm()));
        images.put("HORSE_ARCHER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/HORSE_ARCHER.png").toExternalForm()));
        images.put("KNIGHT", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/KNIGHT.png").toExternalForm()));
        images.put("LADDERMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/LADDERMAN.png").toExternalForm()));
        images.put("MACEMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/MACEMAN.png").toExternalForm()));
        images.put("PIKEMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/PIKEMAN.png").toExternalForm()));
        images.put("SLAVE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/SLAVE.png").toExternalForm()));
        images.put("SLINGER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/SLINGER.png").toExternalForm()));
        images.put("SPEARMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/SPEARMAN.png").toExternalForm()));
        images.put("SWORDSMAN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/SWORDSMAN.png").toExternalForm()));
        images.put("TUNNELER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Units/TUNNELER.png").toExternalForm()));

        faceN = new ImageView[6];
        faceP = new ImageView[6];
        faceM = new ImageView[6];

        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Face/n.png").toExternalForm()));
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            faceN[i] = imageView;
        }
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Face/p.png").toExternalForm()));
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            faceP[i] = imageView;
        }
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Face/m.png").toExternalForm()));
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            faceM[i] = imageView;
        }

        imageViews = new ImageView[180][70];
        for (int i = 0; i < 180; i++) {
            for (int j = 0; j < 70; j++) {
                imageViews[i][j] = new ImageView();
            }
        }

        minimapImageViews = new ImageView[Game.getX()][Game.getY()];
        for (int i = 0; i < Game.getX(); i++) {
            for (int j = 0; j < Game.getY(); j++) {
                minimapImageViews[i][j] = new ImageView(images.get(Game.getGameMap()[j][i].getMapType().name()));
            }
        }

        numberOfUnitsText = new Text();
        avRateText = new Text();
        rateMinText = new Text();
        rateMaxText = new Text();

        foodText = new Text();
        taxText = new Text();
        fearText = new Text();
        religionText = new Text();
        rateText = new Text();
        popularityText = new Text();
        foodHBox = new HBox();
        taxHBox = new HBox();
        fearHBox = new HBox();
        religionHBox = new HBox();
        rateHBox = new HBox();
        popularityHBox = new HBox();

        slider.setMin(-5);
        slider.setMax(5);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        treasuryText = new Text();
        populationText = new Text();
    }
    private Scene scene;
    private double startDragX = -1;
    private double startDragY = -1;
    private static GridPane miniMap;
    private static ScrollPane scrollPane1;
    private static ScrollPane scrollPane2;
    private static ImageView buildingDragged;

    private void nextTurn() {
        if (Game.getUsers().indexOf(Game.getCurrentUser()) == Game.getUsers().size() - 1) {
            GameMenuController.doGameInEachTurn();
        }
        GameMenuController.setNextUser();
        reload();
        showMap();
        showRectAngle();
    }

    private void reload() {
        x = ((900 / tilesLength) - (200 / tilesLength));
        y = (1800 / tilesLength);

        xOfMap = Math.max(xOfMap, x / 2);
        xOfMap = Math.min(xOfMap, (Game.getX() - x / 2));
        yOfMap = Math.max(yOfMap, y / 2);
        yOfMap = Math.min(yOfMap, (Game.getY() - y / 2));

        ArrayList<Node> nodesToRemove = new ArrayList<>();
        for (Node child : gridePane.getChildren()) {
            if (child instanceof ImageView) {
                nodesToRemove.add(child);
            }
        }
        gridePane.getChildren().removeAll(nodesToRemove);
//        gridePane.getChildren().remove(miniMap);
//        gridePane.getChildren().clear();
        setImageViews();
//        miniMap.getChildren().clear();
//        for(int i = 0; i < Game.getX(); i++){
//            for (int j = 0; j < Game.getY(); j++){
//                minimapImageViews[i][j].setFitHeight((double) ((400 / tilesLength) * tilesLength) / Game.getX());
//                minimapImageViews[i][j].setFitWidth((double) ((400 / tilesLength) * tilesLength) / Game.getY());
//                miniMap.add(minimapImageViews[i][j], i, j);
//            }
//        }
//        gridePane.add(miniMap, 0, 0, (400 / tilesLength), (400 / tilesLength));
        rectangleVBoxShow();
        popularityVBoxShow();
        anotherVBoxShow();
        buildingVBoxShow();
    }

    private void anotherVBoxShow() {
        slider.setValue(Game.getCurrentUser().getGovernment().getFearRate());
        treasuryText.setText("Treasury: " + Game.getCurrentUser().getGovernment().getGold());
        populationText.setText("Population: " + Game.getCurrentUser().getGovernment().getPopulation() +
                "/" + Game.getCurrentUser().getGovernment().getMaxPopulation());
    }

    private void setImageViews() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                imageViews[i][j].setFitWidth(tilesLength);
                imageViews[i][j].setFitHeight(tilesLength);
                gridePane.add(imageViews[i][j], i, j);
            }
        }
    }

    private void rectangleVBoxShow() {
        int numberOfUnit = unitsInRectangle.size();
        double avRate = 0;
        double rateSize = 0;
        double rateMax = 0;
        double rateMin = 0;
        for (Building building : buildingsInRectangle) {
            if (building instanceof ProducerBuilding) {
                avRate += ((ProducerBuilding) building).getProductionRate();
                rateSize++;
                if (rateMax < ((ProducerBuilding) building).getProductionRate()) {
                    rateMax = ((ProducerBuilding) building).getProductionRate();
                }
                if (rateMin > ((ProducerBuilding) building).getProductionRate() || rateMin == 0) {
                    rateMin = ((ProducerBuilding) building).getProductionRate();
                }
            }
        }
        avRate = avRate == 0 ? 0 : avRate / rateSize;

        numberOfUnitsText.setText("units: " + numberOfUnit);
        avRateText.setText("avRate: " + avRate);
        rateMaxText.setText("rateMax: " + rateMax);
        rateMinText.setText("rateMin: " + rateMin);
    }

    private void popularityVBoxShow() {
        int foodPop = GameMenuController.getPopularityFromFood();
        int taxPop = GameMenuController.getPopularityFromTax();
        int fearPop = GameMenuController.getPopularityFromFear();
        int religionPop = GameMenuController.getPopularityFromReligion();
        int popularity = GameMenuController.getPopularity();

        foodHBox.getChildren().clear();
        taxHBox.getChildren().clear();
        fearHBox.getChildren().clear();
        religionHBox.getChildren().clear();
        popularityHBox.getChildren().clear();
        rateHBox.getChildren().clear();

        foodText.setText("Food: " + foodPop);
        foodHBox.getChildren().add(foodText);
        if (foodPop > 0) {
            foodText.setFill(Color.GREEN);
            foodHBox.getChildren().add(faceP[0]);
        } else if (foodPop < 0) {
            foodText.setFill(Color.RED);
            foodHBox.getChildren().add(faceN[0]);
        } else {
            foodText.setFill(Color.BLACK);
            foodHBox.getChildren().add(faceM[0]);
        }

        taxText.setText("Tax: " + taxPop);
        taxHBox.getChildren().add(taxText);
        if (taxPop > 0) {
            taxText.setFill(Color.GREEN);
            taxHBox.getChildren().add(faceP[1]);
        } else if (taxPop < 0) {
            taxText.setFill(Color.RED);
            taxHBox.getChildren().add(faceN[1]);
        } else {
            taxText.setFill(Color.BLACK);
            taxHBox.getChildren().add(faceM[1]);
        }

        fearText.setText("Fear: " + fearPop);
        fearHBox.getChildren().add(fearText);
        if (fearPop > 0) {
            fearText.setFill(Color.GREEN);
            fearHBox.getChildren().add(faceP[2]);
        } else if (fearPop < 0) {
            fearText.setFill(Color.RED);
            fearHBox.getChildren().add(faceN[2]);
        } else {
            fearText.setFill(Color.BLACK);
            fearHBox.getChildren().add(faceM[2]);
        }

        religionText.setText("Religion: " + religionPop);
        religionHBox.getChildren().add(religionText);
        if (religionPop > 0) {
            religionText.setFill(Color.GREEN);
            religionHBox.getChildren().add(faceP[3]);
        } else if (religionPop < 0) {
            religionText.setFill(Color.RED);
            religionHBox.getChildren().add(faceN[3]);
        } else {
            religionText.setFill(Color.BLACK);
            religionHBox.getChildren().add(faceM[3]);
        }

        rateText.setText("Rate: " + (foodPop + taxPop + fearPop + religionPop));
        rateHBox.getChildren().add(rateText);
        if ((foodPop + taxPop + fearPop + religionPop) > 0) {
            rateText.setFill(Color.GREEN);
            rateHBox.getChildren().add(faceP[4]);
        } else if ((foodPop + taxPop + fearPop + religionPop) < 0) {
            rateText.setFill(Color.RED);
            rateHBox.getChildren().add(faceN[4]);
        } else {
            rateText.setFill(Color.BLACK);
            rateHBox.getChildren().add(faceM[4]);
        }

        popularityText.setText("Popularity: " + popularity + "/100");
        popularityHBox.getChildren().add(popularityText);
        if (popularity > 66) {
            popularityText.setFill(Color.GREEN);
            popularityHBox.getChildren().add(faceP[5]);
        } else if (popularity < 66 && popularity > 33) {
            popularityText.setFill(Color.RED);
            popularityHBox.getChildren().add(faceM[5]);
        } else {
            populationText.setFill(Color.BLACK);
            popularityHBox.getChildren().add(faceN[5]);
        }
    }

    private void buildingVBoxShow() {
        scrollPane1.setPrefSize((800 / tilesLength) * tilesLength, (double) ((200 / tilesLength) * tilesLength)/2);
        scrollPane2.setPrefSize((800 / tilesLength) * tilesLength, (double) ((200 / tilesLength) * tilesLength)/2);
    }

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(Menu.class.getResource("/com/ap/stronghold/FXML/gameMenu.fxml"));
        gridePane = (GridPane) pane.getChildren().get(0);

        rectangleVBox = new VBox();
        rectangleVBox.setAlignment(Pos.CENTER);
        rectangleVBox.setSpacing(10);
        rectangleVBox.getChildren().add(new Text("Rectangle Info"));
        rectangleVBox.getChildren().add(numberOfUnitsText);
        rectangleVBox.getChildren().add(avRateText);
        rectangleVBox.getChildren().add(rateMaxText);
        rectangleVBox.getChildren().add(rateMinText);

        popularityVBox = new VBox();
        popularityVBox.setAlignment(Pos.CENTER);
        popularityVBox.setSpacing(10);
        popularityVBox.getChildren().add(new Text("Popularity Info"));
        popularityVBox.getChildren().add(foodHBox);
        popularityVBox.getChildren().add(taxHBox);
        popularityVBox.getChildren().add(fearHBox);
        popularityVBox.getChildren().add(religionHBox);
        popularityVBox.getChildren().add(rateHBox);
        popularityVBox.getChildren().add(popularityHBox);

        anotherVBox = new VBox();
        anotherVBox.setAlignment(Pos.CENTER);
        anotherVBox.setSpacing(10);
        anotherVBox.getChildren().add(new Text("Fear rate"));
        anotherVBox.getChildren().add(slider);
        slider.valueProperty().addListener(
                (observableValue, number, t1) -> {
                    GameMenuController.fearRateSet(t1.intValue());
                    popularityVBoxShow();
                }
        );
        anotherVBox.getChildren().add(treasuryText);
        anotherVBox.getChildren().add(populationText);
        Button shopMenu = new Button("Shop Menu");
        anotherVBox.getChildren().add(shopMenu);
        shopMenu.setOnAction(actionEvent -> {
            boolean inMarket = false;
            for (Building building : buildingsInRectangle) {
                if(building instanceof ShopBuilding){
                    inMarket = true;
                    break;
                }
            }
            if(inMarket) {
                try {
                    Stage shopStage = new Stage();
                    shopStage.initModality(Modality.APPLICATION_MODAL);
                    (new ShopMenu()).start(shopStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonVBox = new VBox();
        buttonVBox.setAlignment(Pos.CENTER);
        buttonVBox.setSpacing(10);
        Button briefing = new Button("Briefing");
        briefing.getStyleClass().add("buttonSmall");
        buttonVBox.getChildren().add(briefing);
        briefing.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Briefing");
            alert.setHeaderText("Briefings");
            String s = "";
            for (User user : Game.getUsers()) {
                s += "username: " + user.getUsername() + " | Gold: " + user.getGovernment().getGold() + "\n";
            }
            alert.setContentText(s);

            alert.showAndWait();
        });

        Button delete = new Button("Delete");
        delete.getStyleClass().add("buttonSmall");
        buttonVBox.getChildren().add(delete);
        delete.setOnAction(actionEvent -> {
            for (Building building : buildingsInRectangle) {
                if (building != null)
                    MapMenuController.clearBlock(building.getX1Position(), building.getY1Position(), true);
            }
            reload();
            showRectAngle();
            showMap();
        });

        Button option = new Button("Option");
        option.getStyleClass().add("buttonSmall");
        buttonVBox.getChildren().add(option);
        option.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Options");
            alert.setHeaderText("exit");
            alert.setContentText("Are you sure about exit?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //TODO: exit
            } else {

            }

        });

        Button foodRate = new Button("Food Rate");
        foodRate.getStyleClass().add("buttonSmall");
        buttonVBox.getChildren().add(foodRate);
        foodRate.setOnAction(actionEvent -> {
            int foodRate2 = 0;
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("food rate");
            dialog.setHeaderText("set food rate");
            dialog.setContentText("set food rate between -2 and 2");
            Optional<String> result = dialog.showAndWait();

            while (result.isPresent()) {
                try {
                    foodRate2 = Integer.parseInt(result.get());
                    if (foodRate2 < -2 || foodRate2 > 2) {
                        dialog.setHeaderText("food rate must between -2 and 2");
                        result = dialog.showAndWait();
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    dialog.setHeaderText("food rate must be a number");
                    result = dialog.showAndWait();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            for (Building building : buildingsInRectangle) {
                BuildingMenuController.setSelectedBuilding(building);
                BuildingMenuController.foodRateSet(foodRate2);
                reload();
                showMap();
                showRectAngle();
            }
        });

        Button taxRate = new Button("Tax Rate");
        taxRate.getStyleClass().add("buttonSmall");
        buttonVBox.getChildren().add(taxRate);
        taxRate.setOnAction(actionEvent -> {
            int taxRate2 = 0;
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("tax rate");
            dialog.setHeaderText("set tax rate");
            dialog.setContentText("set tax rate between -3 and 8");
            Optional<String> result = dialog.showAndWait();

            while (result.isPresent()) {
                try {
                    taxRate2 = Integer.parseInt(result.get());
                    if (taxRate2 < -3 || taxRate2 > 8) {
                        dialog.setHeaderText("tax rate must between -3 and 8");
                        result = dialog.showAndWait();
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    dialog.setHeaderText("tax rate must be a number");
                    result = dialog.showAndWait();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            for (Building building : buildingsInRectangle) {
                BuildingMenuController.setSelectedBuilding(building);
                BuildingMenuController.taxRateSet(taxRate2);
                reload();
                showMap();
                showRectAngle();
            }
        });

        buildingVBox = new VBox();
        scrollPane1 = new ScrollPane();
        scrollPane2 = new ScrollPane();
        HBox building1HBox = new HBox();
        HBox building2HBox = new HBox();
        buildingVBox.setAlignment(Pos.CENTER);
        buildingVBox.getChildren().add(scrollPane1);
        buildingVBox.getChildren().add(scrollPane2);
        scrollPane1.setPrefSize((800 / tilesLength) * tilesLength, (double) ((200 / tilesLength) * tilesLength)/2);
        scrollPane2.setPrefSize((800 / tilesLength) * tilesLength, (double) ((200 / tilesLength) * tilesLength)/2);
        scrollPane1.setContent(building1HBox);
        scrollPane2.setContent(building2HBox);
        boolean toggle = false;
        for (BuildingType value : BuildingType.values()) {
            ImageView imageView = new ImageView(images.get(value.name()));
            imageView.setFitHeight(75);
            imageView.setFitWidth(75);

            imageView.setOnDragDetected(dragEvent -> {
                buildingDragged = (ImageView) dragEvent.getSource();
            });
            if(toggle) {
                building1HBox.getChildren().add(imageView);
                toggle = false;
            } else {
                building2HBox.getChildren().add(imageView);
                toggle = true;
            }
        }

        buttonVBox2 = new VBox();
        buttonVBox2.setAlignment(Pos.CENTER);
        buttonVBox2.setSpacing(10);
        Button createUnit = new Button("createUnit");
        buttonVBox2.getChildren().add(createUnit);
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("create unit");
        dialog.setHeaderText("select unit");

        ChoiceBox<String> order = new ChoiceBox<String>();
        for (UnitType value : UnitType.values()) {
            order.getItems().add(value.getType());
        }
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(new Label("select unit to add"), order);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return order.getValue();
            }
            return null;
        });
        createUnit.setOnAction(actionEvent -> {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                for (Building building : buildingsInRectangle) {
                    BuildingMenuController.setSelectedBuilding(building);
                    BuildingMenuController.createUnit(order.getValue(), 1);
                }
                reload();
                showRectAngle();
                showMap();
            }
        });

        Button nextTurn = new Button("Next Turn");
        buttonVBox2.getChildren().add(nextTurn);
        nextTurn.setOnAction(actionEvent -> {
            nextTurn();
        });


//        miniMap = new GridPane();

        showRectAngle();

        reload();

        xOfMap = x / 2;
        yOfMap = y / 2;

        pane.getChildren().add(rectangle);

        showMap();

        gridePane.add(rectangleVBox, (200 / tilesLength) * 0, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(popularityVBox, (200 / tilesLength) * 1, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(anotherVBox, (200 / tilesLength) * 2, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(buttonVBox, (200 / tilesLength) * 3, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(buildingVBox, (200 / tilesLength) * 4, x, (800 / tilesLength), (200 / tilesLength));
        gridePane.add(buttonVBox2, (200 / tilesLength) * 4 + (800 / tilesLength), x, (200 / tilesLength), (200 / tilesLength));

        scene = new Scene(pane);

        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            switch (keyCode) {
                case M -> moveSelectedUnit();
                case S -> setStateSelectedUnit();
                case A -> attackSelectedUnit();
                case D -> disbandSelectedUnit();
                case R -> repairSelectedBuilding();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void repairSelectedBuilding() {
        if (buildingsInRectangle.size() < 1) {
            return;
        }
        for (Building building : buildingsInRectangle) {
            BuildingMenuController.setSelectedBuilding(building);
            BuildingMenuController.repair();
        }
    }

    private void disbandSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        UnitMenuController.setSelectedUnit(unitsInRectangle);
        UnitMenuController.disbandUnit();
        reload();
        showMap();
        showRectAngle();
    }

    private void attackSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        int destinationX = 0;
        int destinationY = 0;

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("attack to");
        dialog.setHeaderText("attack coordinates");
        dialog.setContentText("what is X coordinates of attack?");
        Optional<String> result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int x = Integer.parseInt(result.get());
                if (x < 0 || x > Game.getX()) {
                    dialog.setHeaderText("X must lower than " + Game.getX());
                    result = dialog.showAndWait();
                } else {
                    destinationX = x;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("X must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        dialog.setHeaderText("attack coordinates");
        dialog.setContentText("what is Y coordinates of attack?");
        result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int y = Integer.parseInt(result.get());
                if (y < 0 || y > Game.getY()) {
                    dialog.setHeaderText("Y must lower than " + Game.getY());
                    result = dialog.showAndWait();
                } else {
                    destinationY = y;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("Y must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        UnitMenuController.setSelectedUnit(unitsInRectangle);
        UnitMenuController.attack(destinationX, destinationY);
    }

    private void setStateSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("state");
        dialog.setHeaderText("select unit state");

        ChoiceBox<String> order = new ChoiceBox<String>();
        order.getItems().add("standing");
        order.getItems().add("defensive");
        order.getItems().add("offensive");
        order.setValue("standing");
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(new Label("select units state: "), order);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return order.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            State state = null;
            switch (result.get()) {
                case "standing":
                    state = State.STATIC;
                    break;
                case "defensive":
                    state = State.DEFENSIVE;
                    break;
                case "offensive":
                    state = State.AGGRESSIVE;
                    break;
            }
            UnitMenuController.setSelectedUnit(unitsInRectangle);
            UnitMenuController.setState(state);
        }
    }

    private void moveSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        int destinationX = 0;
        int destinationY = 0;

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("move to");
        dialog.setHeaderText("destination coordinates");
        dialog.setContentText("what is X coordinates of destination?");
        Optional<String> result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int x = Integer.parseInt(result.get());
                if (x < 0 || x > Game.getX()) {
                    dialog.setHeaderText("X must lower than " + Game.getX());
                    result = dialog.showAndWait();
                } else {
                    destinationX = x;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("X must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        dialog.setHeaderText("destination coordinates");
        dialog.setContentText("what is Y coordinates of destination?");
        result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int y = Integer.parseInt(result.get());
                if (y < 0 || y > Game.getY()) {
                    dialog.setHeaderText("Y must lower than " + Game.getY());
                    result = dialog.showAndWait();
                } else {
                    destinationY = y;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("Y must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        UnitMenuController.setSelectedUnit(unitsInRectangle);
//        System.out.println(unitsInRectangle);
        UnitMenuController.moveUnit(destinationX, destinationY);
    }

    public void press(MouseEvent mouseEvent) {
        if (mouseEvent.getY() <= (900 - (200 / tilesLength) * tilesLength)) {
            startDragX = mouseEvent.getSceneX();
            startDragY = mouseEvent.getSceneY();
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                rectangleXOut = rectangleXIn = -1;
                rectangleYOut = rectangleYIn = -1;
                rectangle.setWidth(0);
                rectangle.setHeight(0);
                showRectAngle();
            }
        } else {
            startDragX = -1;
            startDragY = -1;
        }
    }

    public void drag(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            if (startDragY != -1 && event.getSceneY() <= (900 - (200 / tilesLength) * tilesLength)) {
                boolean moved = false;
                if ((int) (startDragX - event.getSceneX()) / tilesLength > 0) {
                    yOfMap += (startDragX - event.getSceneX()) / tilesLength;
                    moved = true;
                } else if ((int) (event.getSceneX() - startDragX) / tilesLength > 0) {
                    yOfMap -= (event.getSceneX() - startDragX) / tilesLength;
                    moved = true;
                }
                if ((int) (startDragY - event.getSceneY()) / tilesLength > 0) {
                    xOfMap += (startDragY - event.getSceneY()) / tilesLength;
                    moved = true;
                } else if ((int) (event.getSceneY() - startDragY) / tilesLength > 0) {
                    xOfMap -= (event.getSceneY() - startDragY) / tilesLength;
                    moved = true;
                }
                if (moved) {
                    startDragX = event.getSceneX();
                    startDragY = event.getSceneY();
                    reload();
                    showRectAngle();
                    showMap();
                }
            }
        } else if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (startDragY != -1 && event.getSceneY() <= (900 - (200 / tilesLength) * tilesLength)) {
                rectangleYIn = (int) ((startDragX / 1800) * y) + yOfMap - y / 2;
                rectangleXIn = (int) ((startDragY / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
                rectangleYOut = (int) ((event.getX() / 1800) * y) + yOfMap - y / 2;
                rectangleXOut = (int) ((event.getY() / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
                if (rectangleYIn > rectangleYOut) {
                    int tmp = rectangleYIn;
                    rectangleYIn = rectangleYOut;
                    rectangleYOut = tmp;
                }
                if (rectangleXIn > rectangleXOut) {
                    int tmp = rectangleXIn;
                    rectangleXIn = rectangleXOut;
                    rectangleXOut = tmp;
                }
                reload();
                showRectAngle();
            }
        }
    }

    private void showRectAngle() {
        unitsInRectangle.clear();
        buildingsInRectangle.clear();
        if (rectangleYIn != -1 && rectangleXIn != -1) {
            int xMin = xOfMap < x / 2 ? 0 : xOfMap - x / 2;
            int yMin = yOfMap < y / 2 ? 0 : yOfMap - y / 2;
            rectangle.setX((rectangleYIn - yMin) * tilesLength);
            rectangle.setY((rectangleXIn - xMin) * tilesLength);
            rectangle.setWidth((rectangleYOut - rectangleYIn + 1) * tilesLength);
            rectangle.setHeight((rectangleXOut - rectangleXIn + 1) * tilesLength);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.RED);
            for (int i = rectangleXIn; i < rectangleXOut; i++) {
                for (int j = rectangleYIn; j < rectangleYOut; j++) {
                    for (Unit unit : Game.getGameMap()[i][j].getUnit()) {
                        if (unit.getOwner().equals(Game.getCurrentUser().getGovernment())) {
                            unitsInRectangle.add(unit);
                        }
                    }
                    if (Game.getGameMap()[i][j].getBuilding() != null && Game.getGameMap()[i][j].getBuilding().getOwner()
                            .equals(Game.getCurrentUser().getGovernment()))
                        buildingsInRectangle.add(Game.getGameMap()[i][j].getBuilding());
                }
            }
        }
    }

    public void scroll(ScrollEvent scrollEvent) {
        double deltaY = scrollEvent.getDeltaY();
        if (deltaY < 0) {
            tilesLength -= 1;
            tilesLength = Math.max(tilesLength, 10);
        } else {
            tilesLength += 1;
            tilesLength = Math.min(tilesLength, 60);
        }
        reload();
        gridePaneReload();
        showRectAngle();
        showMap();
    }

    private void gridePaneReload() {
        gridePane.getChildren().clear();
        setImageViews();
        gridePane.add(rectangleVBox, (200 / tilesLength) * 0, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(popularityVBox, (200 / tilesLength) * 1, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(anotherVBox, (200 / tilesLength) * 2, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(buttonVBox, (200 / tilesLength) * 3, x, (200 / tilesLength), (200 / tilesLength));
        gridePane.add(buildingVBox, (200 / tilesLength) * 4, x, (800 / tilesLength), (200 / tilesLength));
        gridePane.add(buttonVBox2, (200 / tilesLength) * 4 + (800 / tilesLength), x, (200 / tilesLength), (200 / tilesLength));
    }

    public void showMap() {
        int xMin = xOfMap < x / 2 ? 0 : xOfMap - x / 2;
        int xMax = xOfMap < x / 2 ? x : xOfMap + x / 2;
        int yMin = yOfMap < y / 2 ? 0 : yOfMap - y / 2;
        int yMax = yOfMap < y / 2 ? y : yOfMap + y / 2;
        if (x % 2 == 1) {
            xMax++;
        }
        if (y % 2 == 1) {
            yMax++;
        }
        yMax = Math.min(yMax, Game.getY());
        xMax = Math.min(xMax, Game.getX());
        for (int i = yMin; i < yMax; i++) {
            for (int j = xMin; j < xMax; j++) {
                imageViews[i - yMin][j - xMin].setImage(images.get(Game.getGameMap()[j][i].getMapType().name()));
            }
        }
        Node node1 = pane.getChildren().get(0);
        Node node2 = pane.getChildren().get(1);
        pane.getChildren().clear();
        pane.getChildren().add(node1);
        pane.getChildren().add(node2);
        for (Building building : Building.getBuildings()) {
            if (building.getX1Position() >= xMin && building.getX2Position() <= xMax) {
                if (building.getY1Position() >= yMin && building.getY2Position() <= yMax) {
                    ImageView imageView = new ImageView(images.get(building.getBuildingType().name()));
                    imageView.setFitWidth((building.getY2Position() - building.getY1Position()) * tilesLength);
                    imageView.setFitHeight((building.getX2Position() - building.getX1Position()) * tilesLength);
                    imageView.setLayoutX((building.getY1Position() - yMin) * tilesLength);
                    imageView.setLayoutY((building.getX1Position() - xMin) * tilesLength);
                    pane.getChildren().add(imageView);
                }
            }
        }
        for (Unit unit : Unit.getUnits()) {
            if (unit.getxPosition() >= xMin && unit.getxPosition() <= xMax) {
                if (unit.getyPosition() >= yMin && unit.getyPosition() <= yMax) {
                    ImageView imageView = new ImageView(images.get(unit.getUnitType().name()));
                    imageView.setFitWidth(tilesLength);
                    imageView.setFitHeight(2 * tilesLength);
                    imageView.setLayoutX((unit.getyPosition() - yMin) * tilesLength);
                    imageView.setLayoutY((unit.getxPosition() - xMin) * tilesLength);
                    pane.getChildren().add(imageView);
                }
            }
        }
    }

    public void hover(MouseEvent mouseEvent) {
        if (mouseEvent.getY() <= (900 - (200 / tilesLength) * tilesLength)) {
            int yIn = (int) ((mouseEvent.getX() / 1800) * y) + yOfMap - y / 2;
            int xIn = (int) ((mouseEvent.getY() / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
            String unitData = "x: " + xIn + " y: " + yIn + "\n" + MapMenuController.showMapDetails(xIn, yIn);
            for (Unit unit : Game.getGameMap()[xIn][yIn].getUnit()) {
                unitData += "\n" + (unit.getxTarget() == -1 ? "" : "attacking...") + "type: " + unit.getUnitType().name() + " health: " + unit.getHp() + " damage: " + unit.getUnitType().getDamage();
            }
            tooltip = new Tooltip(unitData);
            tooltip.setShowDelay(Duration.seconds(1));
            tooltip.setShowDuration(Duration.seconds(4));
            tooltip.setWrapText(true);
            Tooltip.install(pane, tooltip);
        } else {
            Tooltip.uninstall(pane, tooltip);
        }
    }

    public void released(MouseEvent mouseEvent) {
        if(buildingDragged != null){
            int releasedX = (int) ((mouseEvent.getY() / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
            int releasedY = (int) ((mouseEvent.getX() / 1800) * y) + yOfMap - y / 2;
            String type = "";
            for (String s : images.keySet()) {
                if(images.get(s).equals(buildingDragged.getImage())){
                    type = s;
                    break;
                }
            }
            for (BuildingType value : BuildingType.values()) {
                if(value.name().equals(type)){
                    type = value.getName();
                    break;
                }
            }
            String message = "";
            MapMenuMessage result = null;
            if(!type.equals("main house")){
                result = MapMenuController.dropBuilding(releasedX, releasedY, type, Game.getCurrentUser().getGovernment());
            }else {
                message = "can not drop main house";
            }
            switch (result){
                case NOT_VALID_TYPE_FOR_DROP_BUILDING:
                    message = "enter a valid type for building";
                    break;
                case HOUSE_IS_FILED_WITH_BUILDING:
                    message = "house or houses around that that this building needs them are filled with buildings";
                    break;
                case FORBIDDEN_DROP_BUILDING_ON_ROCK:
                    message = "you can not drop building on a rock";
                    break;
                case CAN_NOT_PLACE_THAT_THING_ON_IT:
                    message = "you can place anything on oi,sea,shallow water,river,big pound and sea";
                    break;
                case MANGONEL_AND_BALLIASTE_MUST_ON_SQUARE_OR_ROUND_TOWER:
                    message = "mangonel and balistae must be place on square or round tower";
                    break;
                case FARMS_NEED_TO_BE_ON_THICK_SCRUB_OR_OASIS_GRASS:
                    message = "apple orchard,diary farmer,hops farmer,wheat farmer should be place on thick scrub or oasis grass";
                    break;
                case IRON_MINE_MUST_BE_ON_IRON:
                    message = "iron mine must be placed on iron";
                    break;
                case PITCH_RIG_ON_SMALL_POUND:
                    message = "pitch rig must be place on small pound";
                    break;
                case QUARRY_ON_BOULDERS:
                    message = "quarry must be placed on boulders";
                    break;
                case ONLY_MANGONEL_BALLIASTE_ON_SQUARE_AND_ROUND:
                    message = "you can only drop mangonel and balistae in square or round tower";
                    break;
                case ONLY_IRON_MINE_ON_IRON:
                    message = "only iron mine can be placed on iron";
                    break;
                case ONLY_PITCH_RIG_ON_SMALL_POUND:
                    message = "only pitch rig can be placed on small pound";
                    break;
                case ONLY_QUARRY_ON_BOULDERS:
                    message = "only quarry can be placed on boulders";
                    break;
                case PUT_STORAGE_NEXT_TO_EACH_OTHER:
                    message = "you have to put granary storages next to each other";
                    break;
                case NOT_ENOUGH_RESOURCE:
                    message = "you dont have enough resources to build that building";
                    break;
                case NOT_ENOUGH_WORKERS:
                    message = "you dont have enough workers to work on that building";
                    break;
                case NOT_ENOUGH_ENGINEER:
                    message = "you dont have enough engineer to work on that building";
                    break;
                case NOT_ENOUGH_MONEY:
                    message = "you dont have enough gold to build that building";
                    break;
                case SUCCESS:
                    message = "you successfully dropped your building on position x " + releasedX + " y " + releasedY;
                    break;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("building");
            alert.setHeaderText("drop building");
            alert.setContentText(message);

            alert.showAndWait();
            reload();
            showMap();
        }
        buildingDragged = null;
    }
}