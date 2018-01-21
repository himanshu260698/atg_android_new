package com.ATG.World.utilities;

import android.content.Intent;

import com.ATG.World.models.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prate on 20-01-2018.
 */

public class GroupSelectionSingleton {
    private List<Integer> groups=new ArrayList<>();
    private static GroupSelectionSingleton groupSelectionSingleton=null;

    public static GroupSelectionSingleton getInstance(){
        if(groupSelectionSingleton==null){
            groupSelectionSingleton= new GroupSelectionSingleton();}
        return groupSelectionSingleton;
    }
    public void addGroup(Integer id){
        groups.add(id);
    }
    public void removeGroup(Integer id){
        groups.remove(id);
    }
    public List<Integer> addedGroup(){
        return groups;
    }
}
