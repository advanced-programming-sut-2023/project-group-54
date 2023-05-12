package model;

import model.Buildings.Building;
import model.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Building building;
    private MapType tree;
    private ArrayList<Unit> unit;
    private MapType mapType;
    public Map(MapType mapType) {
        this.mapType = mapType;
        this.unit = new ArrayList<>();
        this.tree = null;
        this.building = null;
    }
    public Map() {
        this.mapType = MapType.EARTH;
        this.unit = new ArrayList<>();
        this.tree = null;
        this.building = null;
    }
    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setTree(MapType tree) {
        this.tree = tree;
    }

    public void addUnit(Unit unit) {
        this.unit.add(unit);
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    public Building getBuilding() {
        return building;
    }

    public MapType getTree() {
        return tree;
    }

    public ArrayList<Unit> getUnit() {
        return unit;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setUnit() {
        this.unit = new ArrayList<>();
    }

    /** Find the path from start to goal using A-Star search
     *
     * @param start The starting location
     * @param goal The goal location
     * @return The list of intersections that form the shortest path from
     *   start to goal (including both start and goal).
     */
//    public static List<Map> aStarSearch(Map start,
//                                        Map goal)
//    {
//
//        MapNode startNode = pointNodeMap.get(start);
//        MapNode endNode = pointNodeMap.get(goal);
//
//        // setup for A*
//        HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>();
//        HashSet<MapNode> visited = new HashSet<MapNode>();
//        Map<MapNode, Double> distances = initializeAllToInfinity();
//
//        Queue<MapNode> priorityQueue = initQueue();
//
//        //  enque StartNode, with distance 0
//        startNode.setDistanceToStart(new Double(0));
//        distances.put(startNode, new Double(0));
//        priorityQueue.add(startNode);
//        MapNode current = null;
//
//        while (!priorityQueue.isEmpty()) {
//            current = priorityQueue.remove();
//
//            if (!visited.contains(current) ){
//                visited.add(current);
//                // if last element in PQ reached
//                if (current.equals(endNode)) return reconstructPath(parentMap, startNode, endNode, 0);
//
//                Set<MapNode> neighbors = getNeighbors(current);
//                for (MapNode neighbor : neighbors) {
//                    if (!visited.contains(neighbor) ){
//
//                        // calculate predicted distance to the end node
//                        double predictedDistance = neighbor.getLocation().distance(endNode.getLocation());
//
//                        // 1. calculate distance to neighbor. 2. calculate dist from start node
//                        double neighborDistance = current.calculateDistance(neighbor);
//                        double totalDistance = current.getDistanceToStart() + neighborDistance + predictedDistance;
//
//                        // check if distance smaller
//                        if(totalDistance < distances.get(neighbor) ){
//                            // update n's distance
//                            distances.put(neighbor, totalDistance);
//                            // used for PriorityQueue
//                            neighbor.setDistanceToStart(totalDistance);
//                            neighbor.setPredictedDistance(predictedDistance);
//                            // set parent
//                            parentMap.put(neighbor, current);
//                            // enqueue
//                            priorityQueue.add(neighbor);
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }

}
