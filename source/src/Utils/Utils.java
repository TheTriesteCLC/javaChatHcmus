package Utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Utils {
    public static JPanel setPadding(JPanel jPanel, int paddingY, int paddingX, boolean hasBorder, String titleBorder)
    {
        JPanel padY = new JPanel();
        padY.setLayout(new BoxLayout(padY,BoxLayout.PAGE_AXIS));

        padY.add(Box.createRigidArea(new Dimension(0,paddingY)));
        padY.add(jPanel);
        padY.add(Box.createRigidArea(new Dimension(0,paddingY)));

        JPanel padX = new JPanel();
        padX.setLayout(new BoxLayout(padX,BoxLayout.LINE_AXIS));

        padX.add(Box.createRigidArea(new Dimension(paddingX,0)));
        padX.add(padY);
        padX.add(Box.createRigidArea(new Dimension(paddingX,0)));

        if(hasBorder && !titleBorder.isBlank())
        {
            padX.setBorder(BorderFactory.createTitledBorder(titleBorder));
        }else if(hasBorder) {
            padX.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        }
        return padX;
    }
    public static void setBorderWithTitle(JPanel jPanel, String titleBorder) {
        if(!titleBorder.isBlank())
        {
            jPanel.setBorder(BorderFactory.createTitledBorder(titleBorder));
        }
    }
    public static String[][] convertHashMapToTableData(HashMap<String, String[]> rawData)
    {
        String[][] data = new String[rawData.keySet().size()][2];

        int i = 0;
        for(String key : rawData.keySet())
        {
            data[i][0] = key;
            data[i][1] = String.join(" | ",rawData.get(key));
            i++;
        }

        return data;
    }
    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }
}
