package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.Resource;
import com.ap.stronghold.model.units.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public enum ProducerType {
    BARRACKS(BuildingType.BARRACKS, null, true, new ArrayList<>(Arrays.asList()), false),
    MERCENARY_POST(BuildingType.MERCENARY_POST, null, true, new ArrayList<>(Arrays.asList()), false),
    ENGINEERS_GUILD(BuildingType.ENGINEERS_GUILD, null, true, new ArrayList<>(Arrays.asList()), false),
    STABLE(BuildingType.STABLE, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.HORSE, 1.0);
        }});
    }}, false, null, false),
    APPLE_ORCHARD(BuildingType.APPLE_ORCHARD, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.APPLE, 5.0);
        }});
    }}, false, null, false),
    DIARY_FARMER(BuildingType.DIARY_FARMER, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.CHEESE, 5.0);
            put(Resource.COW, 1.0);
        }});
    }}, false, null, false),
    HOPS_FARMER(BuildingType.HOPS_FARMER, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.HOPS, 3.0);
        }});
    }}, false, null, false),
    WHEAT_FARMER(BuildingType.WHEAT_FARMER, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.WHEAT, 2.0);
        }});
    }}, false, null, false),
    HUNTER_POST(BuildingType.HUNTER_POST, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.MEAT, 5.0);
        }});
    }}, false, null, false),
    BAKERY(BuildingType.BAKERY, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.FLOUR, 1.0);
        }}, new HashMap<>() {{
            put(Resource.BREAD, 8.0);
        }});
    }}, false, null, false),
    BREWER(BuildingType.BREWER, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.HOPS, 1.0);
        }}, new HashMap<>() {{
            put(Resource.ALE, 1.0);
        }});
    }}, false, null, false),
    INN(BuildingType.INN, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.ALE, 1.0);
        }}, null);
    }}, false, null, true),
    MILL(BuildingType.MILL, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.WHEAT, 3.0);
        }}, new HashMap<>() {{
            put(Resource.FLOUR, 3.0);
        }});
    }}, false, null, false),
    IRON_MINE(BuildingType.IRON_MINE, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.IRON, 1.0);
        }});
    }}, false, null, false),
    PITCH_RIG(BuildingType.PITCH_RIG, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.PITCH, 1.0);
        }});
    }}, false, null, false),
    QUARRY(BuildingType.QUARRY, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.STONE, 12.0);
        }});
    }}, false, null, false),
    WOODCUTTER(BuildingType.WOODCUTTER, new HashMap<>() {{
        put(null, new HashMap<>() {{
            put(Resource.WOOD, 18.0);
        }});
    }}, false, null, false),
    CHURCH(BuildingType.CHURCH, null, false, null, true),
    CATHEDRAL(BuildingType.CATHEDRAL, null, true, new ArrayList<>(Arrays.asList()), true),
    ARMOURER(BuildingType.ARMOURER, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.IRON, 1.0);
        }}, new HashMap<>() {{
            put(Resource.METAL_ARMOR, 1.0);
        }});
    }}, false, null, false),
    BLACKSMITH(BuildingType.BLACKSMITH, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.IRON, 1.0);
        }}, new HashMap<>() {{
            put(Resource.MACE, 1.0);
        }});
        put(new HashMap<>() {{
            put(Resource.IRON, 1.0);
        }},
        new HashMap<>() {{
            put(Resource.SWORDS, 1.0);
        }});
    }}, false, null, false),
    FLETCHER(BuildingType.FLETCHER, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.WOOD, 2.0);
        }}, new HashMap<>() {{
            put(Resource.BOW, 1.0);
        }});
        put(new HashMap<>() {{
            put(Resource.WOOD, 3.0);
        }},
        new HashMap<>() {{
            put(Resource.CROSSBOW, 1.0);
        }});
    }}, false, null, false),
    POLETURNER(BuildingType.POLETURNER, new HashMap<>() {{
        put(new HashMap<>() {{
            put(Resource.WOOD, 1.0);
        }}, new HashMap<>() {{
            put(Resource.SPEAR, 1.0);
        }});
        put(new HashMap<>() {{
            put(Resource.WOOD, 2.0);
        }},
        new HashMap<>() {{
            put(Resource.PIKE, 1.0);
        }});
    }}, false, null, false);


    private BuildingType buildingType;
    private HashMap<HashMap<Resource, Double>, HashMap<Resource, Double>> puts;
    private boolean isProduceUnit;
    private ArrayList<Unit> unitsCanProduce;
    private boolean isAddPopularity;

    ProducerType(BuildingType buildingType, HashMap<HashMap<Resource, Double>, HashMap<Resource, Double>> puts,
                 boolean isProduceUnit, ArrayList<Unit> unitsCanProduce, boolean isAddPopularity) {
        this.buildingType = buildingType;
        this.puts = puts;
        this.isProduceUnit = isProduceUnit;
        this.unitsCanProduce = unitsCanProduce;
        this.isAddPopularity = isAddPopularity;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public HashMap<HashMap<Resource, Double>, HashMap<Resource, Double>> getPuts() {
        return puts;
    }

    public boolean isProduceUnit() {
        return isProduceUnit;
    }

    public ArrayList<Unit> getUnitsCanProduce() {
        return unitsCanProduce;
    }

    public boolean isAddPopularity() {
        return isAddPopularity;
    }
}
