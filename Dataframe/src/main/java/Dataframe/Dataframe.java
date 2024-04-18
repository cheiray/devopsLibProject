package Dataframe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


/**
 *
 * @author Rayen CHEIKH M'HAMED, L'HADI YOUSFI, Benjamin (???)
 */

public class Dataframe {
 
    private Map<String, List<Object>> data;
    private List<String> labels;
    private int numberOfRows;

    // Empty Default constructor
    public Dataframe() {
        data = new LinkedHashMap<>();
        labels = new ArrayList<>();
        numberOfRows = 0;
    }

    
    public Dataframe(Map<String, List<?>> map) throws BadArgumentException {
       

        int size = -1;
        for (List<?> list : map.values()) {
            if (size == -1) {
                size = list.size();
            } else if (list.size() != size) {
                throw new BadArgumentException("Columns have different sizes");
            }
        }

        data = new LinkedHashMap<>();
        labels = new ArrayList<>();
        for (Map.Entry<String, List<?>> entry : map.entrySet()) {
            String colName = entry.getKey();
            List<?> colData = entry.getValue();
            data.put(colName, new ArrayList<>(colData));
            labels.add(colName);
        }
        numberOfRows = size;
    }

    public Dataframe(String nomFichier) throws IOException {
        data = new LinkedHashMap<>();
        labels = new ArrayList<>();
        FileReader fileReader = new FileReader(nomFichier);
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);

        for (String label : csvParser.getHeaderNames()) {
            data.put(label.trim(), new ArrayList<>());
            labels.add(label.trim());
        }

        for (CSVRecord record : csvParser) {
            for (String label : csvParser.getHeaderNames()) {
                data.get(label.trim()).add(record.get(label));
            }
        }
        long numRecords = csvParser.getRecordNumber();
        numberOfRows = (int)numRecords;
    }


    public int getDataFrameSize() {
        return numberOfLines;
    }


    protected void displayFromTo(int debut, int fin) throws BadArgumentException{
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        
    }

    public void displayFromBegginingTo(int nbline) throws BadArgumentException {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }

    public void displayFromEndTo(int nbline) throws BadArgumentException {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }

    public void display() throws BadArgumentException{
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }



    public Dataframe selectLine(int debut ,int fin ){
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return null;
    }

    public Dataframe selectCol(ArrayList<String> l){
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return null;
    }


    public List<String> getligne(int index) {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return null;

    }


    public Column getcol(String label)  {

        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return null;
    }

    public void insertligne(String[] l) {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");       
    }

    public void insertCol(Column c) {
       System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }


    public double max(String label) throws BadArgumentException {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return -1;
    }
    

    public double sum(String label) throws BadArgumentException {
      System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return -1;
    }
 
    public double min(String label) throws BadArgumentException {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return -1;
    }
    
    
    public double mean(String label) throws BadArgumentException {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        return -1;
    }

    public void deleteRow(int index) {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }

    public void deleteColumn(String label) {
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }

}
   
