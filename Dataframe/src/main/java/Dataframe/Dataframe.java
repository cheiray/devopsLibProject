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
        if (map == null || map.isEmpty()) {
            throw new BadArgumentException("Data map is null or empty");
        }

        int size = -1;
        for (List<?> list : map.values()) {
            if (list == null) {
                throw new BadArgumentException("The dataframe contains a null list");
            } else if (size == -1) {
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
        return numberOfRows;
    }

    public List<String> getLabels() {
        return labels;
    }


    public void displayFromTo(int start, int end) throws BadArgumentException {
        if (start < 0 || start > numberOfRows || end < 0 || end > numberOfRows || start > end) {
            throw new BadArgumentException("Invalid start or end index");
        }

        AsciiTableRenderer.render(data, labels, start, end);
    }

    public void displayFromBegginingTo(int numberOfLines) throws BadArgumentException {
        displayFromTo(0, numberOfLines);
    }

    public void displayFromEndTo(int numberOfLines) throws BadArgumentException {
        displayFromTo(getDataFrameSize() - numberOfLines, getDataFrameSize());
    }

    public void display() throws BadArgumentException {
        displayFromTo(0, getDataFrameSize());
    }



    public Dataframe selectLine(int start, int end) throws BadArgumentException {
        if (start < 0 || start >= numberOfRows || end < 0 || end >= numberOfRows || start > end) {
            throw new BadArgumentException("Invalid start or end index");
        }

        Dataframe dataframe = new Dataframe();
        for (String label : labels) {
            dataframe.insertCol(label, data.get(label).subList(start, end + 1));
        }
        return dataframe;
    }

    public Dataframe selectCol(ArrayList<String> selectedLabels) {
        Dataframe dataframe = new Dataframe();
        for (String label : selectedLabels) {
            dataframe.insertCol(label, data.get(label));
        }
        return dataframe;
    }


    public List<Object> getLine(int index) {
        List<Object> line = new ArrayList<>();
        for (String label : labels) {
            line.add(data.get(label).get(index));
        }
        return line;
    }


    public List<Object> getcol(String label) {
        return data.get(label);
    }

    public void insertLine(List<Object> line) throws BadArgumentException {
        if (line.size() != labels.size()) {
            throw new BadArgumentException("Line size does not match number of columns");
        }
        for (int i = 0; i < labels.size(); i++) {
            data.get(labels.get(i)).add(line.get(i));
        }
        numberOfRows++;
    }

    public void insertCol(String label, List<?> colData) {
        data.put(label, new ArrayList<>(colData));
        labels.add(label);
        if (colData.size() > numberOfRows) {
            numberOfRows = colData.size();
        }
    }

    public double max(String label) throws BadArgumentException {
        List<Object> column = data.get(label);
        if (column == null || column.isEmpty()) {
            throw new BadArgumentException("Column does not exist or is empty");
        }
        double max = Double.MIN_VALUE;
        for (Object value : column) {
            double currentValue;
            try {
                currentValue = Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                throw new BadArgumentException("Column contains non-numeric values");
            }
            if (currentValue > max) {
                max = currentValue;
            }
        }
        return max;
    }
    

    public double sum(String label) throws BadArgumentException {
        List<Object> column = data.get(label);
        if (column == null || column.isEmpty()) {
            throw new BadArgumentException("Column does not exist or is empty");
        }
        double sum = 0;
        for (Object value : column) {
            double currentValue;
            try {
                currentValue = Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                throw new BadArgumentException("Column contains non-numeric values");
            }
            sum += currentValue;
        }
        return sum;
    }
    public double min(String label) throws BadArgumentException {
        List<Object> column = data.get(label);
        if (column == null || column.isEmpty()) {
            throw new BadArgumentException("Column does not exist or is empty");
        }
        double min = Double.MAX_VALUE;
        for (Object value : column) {
            double currentValue;
            try {
                currentValue = Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                throw new BadArgumentException("Column contains non-numeric values");
            }
            if (currentValue < min) {
                min = currentValue;
            }
        }
        return min;
    }

    
    
    public double mean(String label) throws BadArgumentException {
        List<Object> column = data.get(label);
        if (column == null || column.isEmpty()) {
            throw new BadArgumentException("Column does not exist or is empty");
        }
        double sum = 0;
        for (Object value : column) {
            double currentValue;
            try {
                currentValue = Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                throw new BadArgumentException("Column contains non-numeric values");
            }
            sum += currentValue;
        }
        return sum / column.size();
    }

    public void deleteRow(int index) {
        for (List<Object> colData : data.values()) {
            colData.remove(index);
        }
        numberOfRows--;
    }

    public void deleteColumn(String label) {
        if (data.containsKey(label)) {
            data.remove(label);
            labels.remove(label);
        }
    }

}
   
