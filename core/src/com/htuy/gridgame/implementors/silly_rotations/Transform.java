package com.htuy.gridgame.implementors.silly_rotations;

public interface Transform {

    void perform(Ngon gon);

    class RotateTransform implements Transform {
        private int amount;

        public RotateTransform(int amount) {
            this.amount = amount;
        }

        public void perform(Ngon gon) {
            for (int x = 0; x < amount; x++) {
                gon.rotate(-1);
            }
        }

        public String toString() {
            return "Rotate: " + amount;
        }

    }

    class ReflectTransform implements Transform {
        public void perform(Ngon gon) {
            gon.reflect();
        }

        public String toString() {
            return "Flip";
        }
    }

}
