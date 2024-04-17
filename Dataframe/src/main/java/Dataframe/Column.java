package Dataframe;

import java.util.ArrayList;
import java.util.List;

public class Column <VALUE extends Object>{
    private String label;
    private String type;
    private List<VALUE> values;


    public Column(String label, String type){
        this.label = label;
        this.type = type;
        this.values = new ArrayList<VALUE>();
    }


    public Column(String label, String type, List<VALUE> values){
        this.label = label;
        this.type = type;
        this.values = values;
    }


    public int getsize(){
        return this.values.size();
    }

    public String getLabel() {
        return label;
    }
    
    public boolean contain (VALUE v){
        
        return values.contains(v);
        
    }

    public String getType() {
        return type;
    }
    

    public List<VALUE> getValues() {
        return values;
    }
    

    public void add(VALUE v){
        this.values.add(v);
    }

    public boolean isnumeric(){
        return( ( this.getType().equals("Integer")) || ( this.getType().equals("Double" )) || ( this.getType().equals("Float")));
    }
}
