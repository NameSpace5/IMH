package com.zdh.alphathink.imh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Panda on 2016/9/13.
 */
public class InitValue {
    public static int page=1;

    /**
     * 模拟数据分页加载，
     * @param pageStart  起始数
     * @param pageSize   每页显示数目
     * @return
     */
    public static List<Map<String,Object>> initValue(int pageStart, int pageSize){
        Map<String,Object> map;
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for(int i=0;i<pageSize;i++){
            map=new HashMap<String,Object>();
            map.put("text", "zhangkai281发表文章");
            map.put("title", page+"_ListView分页显示");
            ++page;
            list.add(map);
        }
        return list;
    }
}
