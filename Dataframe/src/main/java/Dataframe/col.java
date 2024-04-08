
package Dataframe;

import java.util.ArrayList;
import java.util.List;

public class col <VALUE extends Object> {
  private String label;
    private String type;
    private List<VALUE> values;
    


public col(String label, String type, List<VALUE> values){
    this.label = label;
    this.type = type;
    this.values = values;
}
public col(String label, String type){
    this.label = label;
    this.type = type;
    this.values = new ArrayList<VALUE>();
}
public int getsize(){
    return this.values.size();
}
public String getLabel() {
    return label;
}
public String getType() {
    return type;
}
}