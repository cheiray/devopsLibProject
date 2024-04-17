package Dataframe;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Rayen CHEIKH M'HAMED, L'HADI YOUSFI, Benjamin
 */

public class Dataframe {
 
    private ArrayList<String> labels;
    private List<Column> data;
    private int numberOfLines;

    // Empty Default constructor
    public Dataframe() {
        
        labels=new ArrayList<String>();
        data =new ArrayList<Column>();
        numberOfLines=0;

    }

    
    public Dataframe( Map<String, List<?>> map) throws BadArgumentException{
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
    }

    public Dataframe(String nomFichier) throws FileNotFoundException, IOException{
        System.out.println("TO IMPLEMENT: This method needs to be implemented");
        
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
   
