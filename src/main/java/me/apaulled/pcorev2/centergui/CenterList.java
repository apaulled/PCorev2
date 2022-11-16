package me.apaulled.pcorev2.centergui;

import java.util.ArrayList;

public class CenterList {
    public static ArrayList<Integer> slots(int numRows, int itemCount) {
        ArrayList<Integer> slotList = new ArrayList<>();
        int size = numRows * 9;
        int center = size / 2;

        if (numRows % 2 == 0) {
            center -= 5;
        }
        int centerModifier = 0;
        int centerModifierIncrementSign = -1;
        while (itemCount > 0) {
            double slotAdditive = -0.5;
            int incrementSign = -1;

            if (itemCount < 9) {
                if (itemCount % 2 != 0) {
                    slotList.add(center);
                }
                for (int i = 1; i <= itemCount - (itemCount % 2); i++) {
                    incrementSign *= -1;
                    slotAdditive = (-1 * slotAdditive) + (0.5 * incrementSign);
                    slotList.add(center + (int) slotAdditive);
                }
            } else {
                slotList.add(center);
                for (int i = 0; i < 9; i++) {
                    slotList.add((center - 4) + i);
                }
            }

            itemCount -= 9;
            centerModifierIncrementSign *= -1;
            centerModifier = (-1 * centerModifier) + (9 * centerModifierIncrementSign);
            center += centerModifier;
        }
        return slotList;
    }
}
