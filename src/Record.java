/**
 * Created by benma on 4/23/2017.
 */
public class Record implements Comparable<Record>{
    public int x;
    public int y;
    public int color;
    public long timeStamp;

    public Record(int x, int y, long timeStamp, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString(){
        return "" + timeStamp + "," + x + "," + y + "," + color;
    }

    @Override
    public int compareTo(Record r) {
        if (r.timeStamp < timeStamp) return 1;
        if (r.timeStamp > timeStamp) return -1;
        return 0;
    }
}
