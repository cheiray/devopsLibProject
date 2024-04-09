package Dataframe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVReader;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
public class Dataframe{
 
    private List<col> data;
    private ArrayList<String> labels;
    private int nbligne ;
    public Dataframe() {
        data =new ArrayList<col>();
        labels=new ArrayList<String>();
        nbligne=0;

    } 
    public Dataframe(Map<String, List<?>> map){
    ArrayList<List<?>> columns = new ArrayList<List<?>>(map.values());
    int size = columns.get(0).size();
    
    data = new ArrayList<col>();
    labels = new ArrayList<String>();
    
    for (String colname : map.keySet()) {
        String type = map.get(colname).get(0).getClass().toString();
        type = type.substring(type.lastIndexOf('.') + 1);
        data.add(new col(colname,type,map.get(colname))); 
        labels.add(colname);
    }
    
    nbligne = size;
}
public Dataframe(String nomFichier) throws FileNotFoundException, IOException {
    data = new ArrayList<col>();
    labels = new ArrayList<String>();
    
   CSVReader csvReader = new CSVReader(new FileReader(nomFichier));
    String[] types = csvReader.readNext();
    String[] labels = csvReader.readNext();
    
    for (int i = 0; i < labels.length; i++) {
        this.data.add(new col(labels[i].trim(), types[i].trim(), new ArrayList()));
        this.labels.add(labels[i].trim());
    }
    
    List<String[]> lines = csvReader.readAll();
    
    for (String[] row : lines) {
        for (int i = 0; i < row.length; i++) {
            data.get(i).getValues().add(row[i]);
        }
    }
    
    nbligne = data.get(0).getsize();
}



protected void afficherdev(int debut, int fin) throws BadArgumentException {
    AsciiTable at = new AsciiTable();
    at.addRule();
    at.addRow(labels);
    for(int i = debut; i < fin; i++) {
        at.addRule();
        List<String> row = new ArrayList<String>();
        for(col column : data)
            row.add(column.getValues().get(i).toString());
        at.addRow(row);
    }
    at.addRule();
    at.setTextAlignment(TextAlignment.CENTER);
    at.getContext().setGrid(A7_Grids.minusBarPlusEquals());
    System.out.println(at.render());
}

public void afficherDebut(int nbline) {
    if(nbline < 0 || nbline > getNbligne())
    afficherdev(nbline, nbline);
}
public void afficherFin(int nbline) throws BadArgumentException {
    if(nbline < 0 || nbline > getNbligne())
        throw new BadArgumentException("number of lines");
   afficherdev(getNbligne()-nbline,getNbligne());
}


public void afficher(){
    afficherdev(0,getNbligne());
}
}