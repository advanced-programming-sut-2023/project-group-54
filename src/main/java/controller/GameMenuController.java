package controller;

import model.Building;
import model.Buildings.Building;
import model.Map;
import model.Unit;
import model.units.Unit;
import view.commands.outputs.GameMenuOutput;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenuController {
   private Building selectedBuilding;
   private ArrayList<Unit> selectedUnit;
   public Map showMap(int xCoordinate,int yCoordinate){

   }
   public String showPopularityFactors(){

   }
   public String showPopularity(){

   }
   public String showFoodList(){

   }
   public GameMenuOutput foodRate(int rateNumber){

   }
   public String foodRateShow(){

   }
   public GameMenuOutput taxRate(){

   }
   public String taxRateShow(){

   }
   public GameMenuOutput fearRate(){

   }
   public GameMenuOutput dropBuilding(int xCoordinate,int yCoordinate,String type){

   }
   public Building selectBuilding(int xCoordinate,int yCoordinate){

   }
   public GameMenuOutput createUnit(String type,int count){

   }
   public GameMenuOutput repair(){

   }
   public Unit selectUnit(int xCoordinate,int yCoordinate){

   }
   public GameMenuOutput moveUnit(int xCoordinate,int yCoordinate){

   }
   public GameMenuOutput patrolUnit(int xCoordinate1,int yCoordinate1,int xCoordinate2,int yCoordinate2){

   }
   public GameMenuOutput stopPatrolUnit(){

   }
   public GameMenuOutput set(int xCoordinate,int yCoordinate,String state){

   }
   public GameMenuOutput attackE(int enemyXCoordinate,int enemyYCoordinate){

   }
   public GameMenuOutput attackX(int xCoordinate,int yCoordinate){

   }
   public GameMenuOutput pourOil(String direction){

   }
   public GameMenuOutput digTunnel(int xCoordinate,int yCoordinate){

   }
   public GameMenuOutput buildEquipment(String equipmentName){

   }
   public GameMenuOutput disbandUnit(){

   }
   public GameMenuOutput setTexture(int x1Coordinate,int y1Coordinate, int x2Coordinate,int y2Coordinate){

   }
   public  GameMenuOutput clearBlock(int xCoordinate, int yCoordinate) {

   }

   public  GameMenuOutput dropBlock(int xCoordinate, int yCoordinate, String direction) {

   }

   public  GameMenuOutput dropTree(int xCoordinate, int yCoordinate, String treeName) {

   }
   public GameMenuOutput dropUnit(int xCoordinate,int yCoordinate,String type,int count ){

   }
   public GameMenuOutput nextTurn(){

   }

}
