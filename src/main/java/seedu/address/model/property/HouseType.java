package seedu.address.model.property;

import java.util.HashMap;

/**
 * All types of houses are listed here
 */
public enum HouseType {
    ANY("any") {
        @Override
        public String toString() {
            return "Any";
        }
    },
    APARTMENT("apartment") {
        @Override
        public String toString() {
            return "Apartment";
        }
    },
    BUNGALOW("bungalow") {
        @Override
        public String toString() {
            return "Bungalow";
        }
    },
    CONDOMINIUM("condominium", "condo") {
        @Override
        public String toString() {
            return "Condominium";
        }
    },
    COLONIA("colonia") {
        @Override
        public String toString() {
            return "Colonia";
        }
    },
    HDB_FLAT("hdb, hdb_flat") {
        @Override
        public String toString() {
            return "HDB Flat";
        }
    },
    SEMIDETACHED("semidetached", "semid", "semi-detached", "semi-d") {
        @Override
        public String toString() {
            return "SemiDetached";
        }
    },
    MANSION("mansion") {
        @Override
        public String toString() {
            return "Mansion";
        }
    },
    NULLHOUSETYPE("Nullhouse") {
        @Override
        public String toString() {
            return "";
        }
    };

    public static final String MESSAGE_CONSTRAINTS = "Only the following house types are available: "
            + HouseType.ANY + " "
            + HouseType.APARTMENT + " "
            + HouseType.BUNGALOW + " "
            + HouseType.COLONIA + " "
            + HouseType.CONDOMINIUM + " "
            + HouseType.HDB_FLAT + " "
            + HouseType.MANSION + " "
            + HouseType.SEMIDETACHED;

    /**
     * A HashMap to map a possible string interpretation of the HouseType to the actual HouseType.
     */
    private final HashMap<String, HouseType> houseMappings = new HashMap<>();

    /**
     * Constructor of the enum.
     *
     * @param houseTypes The set of possible HouseType interpretations.
     */
    HouseType(String... houseTypes) {
        for (String s : houseTypes) {
            houseMappings.put(s, this);
        }
    }

    /**
     * Checks if the given string matches any of the HouseTypes.
     *
     * @param house The string.
     * @return True if it matches, False otherwise.
     */
    public static boolean isValidHouseType(String house) {
        for (HouseType h : HouseType.values()) {
            HouseType houseType = h.houseMappings.get(house.toLowerCase());
            if (houseType != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the HouseType from a given string.
     *
     * @param house The string.
     * @return A HouseType.
     */
    public static HouseType getHouseType(String house) {
        for (HouseType h : HouseType.values()) {
            HouseType houseType = h.houseMappings.get(house.toLowerCase());
            if (houseType != null) {
                return houseType;
            }
        }
        return HouseType.ANY;
    }
}


