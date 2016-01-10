package com.LuckyBlock.Inventory;

public class AttributeName {

    public static enum AttributeType {

        MAX_HEALTH("maxHealth"),

        ATTACK_DAMAGE("attackDamage"),

        MOVEMENT_SPEED("movementSpeed"),

        FOLLOW_RANGE("followRange");

        private String name;

        private AttributeType(String name) {
            this.name = name;
        }

        public static AttributeType getByName(String name) {
            AttributeType t = null;
            for (AttributeType a : values()) {
                if (a.getName().equalsIgnoreCase(name)) {
                    t = a;
                }
            }
            return t;
        }

        public String getName() {
            return name;
        }

    }

    public static enum OperationType {

        ZERO(0),

        ONE(1);

        private int id;

        private OperationType(int id) {
            this.id = id;
        }

        public static OperationType getById(int id) {
            OperationType t = null;
            for (OperationType o : values()) {
                if (o.getId() == id) {
                    t = o;
                }
            }
            return t;
        }

        public int getId() {
            return id;
        }

    }


}
