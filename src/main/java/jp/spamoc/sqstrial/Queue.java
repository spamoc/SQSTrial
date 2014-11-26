package jp.spamoc.sqstrial;

import java.util.HashMap;
import java.util.Map;


public enum Queue {
    DEFAULT("Apocalypse");
    
    private String path;
    private static Map<String, Queue> map = new HashMap<String, Queue>();

    static {
        for (Queue queue : Queue.values()) {
            map.put(queue.path, queue);
        }
    }
    
    private Queue(final String path){
        this.path = path;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public static Queue valueOf(int key){
        return map.get(key);
    }
}
