package com.diebuc.hicxsimplefileparser.tokencounter;

import java.util.*;

public class TokenStatsResult {

    Map<String, Long> stats = new HashMap<>();

    public void setStatistic(String name, Long value){
        stats.put(name,value);
    }

    public Long getStatistic(String name){return stats.get(name);}

    public List<String> getAllStatistics(){
        return new ArrayList<>(stats.keySet());
    }

}
