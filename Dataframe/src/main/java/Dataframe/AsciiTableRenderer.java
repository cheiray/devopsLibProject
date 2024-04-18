package Dataframe;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;
import java.util.Map;
import java.util.*;

public class AsciiTableRenderer {

    public static void render(Map<String, List<Object>> data, List<String> labels, int start, int end) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(labels);
        at.addRule();

        for (int i = start; i < end; i++) {
            List<String> row = new ArrayList<>();
            for (String label : labels) {
                row.add(data.get(label).get(i).toString());
            }
            at.addRow(row);
        }

        at.addRule();
        at.setTextAlignment(TextAlignment.CENTER);
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        System.out.println(at.render());
    }
}
