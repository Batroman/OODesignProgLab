package src.Model;

import java.util.ArrayList;

public class Storage<T extends Cars> {
    private final int maxLoad;
    private ArrayList<T> storageContents;
    public Storage(int maxLoadingCapacity) {
        storageContents = new ArrayList<>(maxLoadingCapacity);
        this.maxLoad = maxLoadingCapacity;
    }

    protected void loadStorage(T car) {
        if (storageContents.size() < maxLoad) {
            storageContents.add(car);
        }
    }

    protected void unloadStorage(){
        T unload = storageContents.getLast();
        unload.getPosition().setLocation(unload.getPosition().x -1, unload.getPosition().y-1);
        storageContents.removeLast();
        System.out.println(unload.getModelName() + " has been unloaded");
    }

    protected ArrayList<T> getContents(){
        return storageContents;
    }

}
