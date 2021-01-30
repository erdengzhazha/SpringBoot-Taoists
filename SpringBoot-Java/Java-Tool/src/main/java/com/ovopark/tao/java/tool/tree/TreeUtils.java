package com.ovopark.tao.java.tool.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Tree  工具
 */
public class TreeUtils {


    /**
     * 将List 数组转换为Tree 所需要的数据(循环方式)
     *
     * @param id
     *            ID 列
     * @param pid
     *            PID 列
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<MM> ArrayToTreeData(List<MM> mydata, String id, String pid) {
        Map<Object, Object> h = new HashMap<Object, Object>();// 数据索引
        List<MM> r = new ArrayList<MM>();// 数据池，返回的数据

        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            } else {
                h.put(item.get(id), item);
            }
        }
        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            }else{
                List<MM> children = new ArrayList<MM>();
                item.put("children",children);
            }


            if (!item.containsKey(pid) || (item.get(pid) == null) || !h.containsKey(item.get(pid))) {
                r.add(item);
            } else {
                Map<String, List<MM>> pitem = (Map<String, List<MM>>) h.get(item.get(pid));
                if (!pitem.containsKey("children")) {
                    List<MM> children = new ArrayList<MM>();
                    children.add(item);
                    pitem.put("children", children);
                } else {
                    List<MM> children = pitem.get("children");
                    children.add(item);
                    pitem.put("children", children);
                }
            }
        }
        return r;
    }
    /**
     *
     * @Title: ArrayToTreeData
     * @Description: TODO(List转tree结构)
     * @param @param mydata
     * @param @param id
     * @param @param pid
     * @param @return    参数
     * @return List<MM>    返回类型
     * @throws
     */
    public static List<MM> ArrayToTreeData2(List<MM> mydata, String id, String pid) {
        Map<Object, Object> h = new HashMap<Object, Object>();// 数据索引
        List<MM> r = new ArrayList<MM>();// 数据池，返回的数据

        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            } else {
                h.put(item.get(id), item);
            }
        }
        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            }
            if (!item.containsKey(pid) || (item.get(pid) == null) || !h.containsKey(item.get(pid))) {
                r.add(item);
            } else {
                Map<String, List<MM>> pitem = (Map<String, List<MM>>) h.get(item.get(pid));
                if (!pitem.containsKey("children")) {
                    List<MM> children = new ArrayList<MM>();
                    children.add(item);
                    pitem.put("children", children);
                } else {
                    List<MM> children = pitem.get("children");
                    children.add(item);
                    pitem.put("children", children);
                }
            }
        }
        for (MM mm : r) {
            if(mm.containsKey("children")&&mm.getLong("pid")==0){
                List<MM> list=(List<MM>)mm.get("children");
                for (MM mm2 : list) {
                    if(!mm2.containsKey("children")){
                        mm2.put("children", new ArrayList<MM>());
                    }
                }
            }
        }
        return r;
    }


    /**
     * 同级的也许是叶子节点
     * @param mydata
     * @param id
     * @param pid
     * @return
     */
    public static List<MM> ArrayToTreeData3(List<MM> mydata, String id, String pid,Object helpDocumentService) {
        Map<Object, Object> h = new HashMap<Object, Object>();// 数据索引
        List<MM> r = new ArrayList<MM>();// 数据池，返回的数据

        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            } else {
                h.put(item.get(id), item);
            }
        }
        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            }else{
                List<MM> children = new ArrayList<MM>();
                // ***********   查询叶子 ****************
                List<MM> docs = new ArrayList<>();
                for(MM d:docs){
                    children.add(d);
                }
                item.put("children",children);
            }
            if (!item.containsKey(pid) || (item.get(pid) == null) || !h.containsKey(item.get(pid))) {
                r.add(item);
            } else {
                Map<String, List<MM>> pitem = (Map<String, List<MM>>) h.get(item.get(pid));
                if (!pitem.containsKey("children")) {
                    List<MM> children = new ArrayList<MM>();
                    children.add(item);
                    pitem.put("children", children);
                } else {
                    List<MM> children = pitem.get("children");
                    children.add(item);
                    pitem.put("children", children);
                }
            }
        }
        return r;
    }

    /**
     * 同级的也许是叶子节点
     * @param mydata
     * @param id
     * @param pid
     * @return
     */
    public static List<MM> ArrayToTreeData4(List<MM> mydata, String id, String pid,String label,String title) {
        Map<Object, Object> h = new HashMap<Object, Object>();// 数据索引
        List<MM> r = new ArrayList<MM>();// 数据池，返回的数据

        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            } else {
                h.put(item.get(id), item);
            }
        }
        for (MM item : mydata) {
            if (!item.containsKey(id)) {
                continue;
            }else{
                List<MM> children = new ArrayList<MM>();
                item.put("value",item.get("id"));
                item.put("label",item.get(label));
                item.put("title",item.get(title));
                item.put("children",children);
            }
            if (!item.containsKey(pid) || (item.get(pid) == null) || !h.containsKey(item.get(pid))) {
                item.put("value",item.get("id"));
                item.put("label",item.get(label));
                r.add(item);
            } else {
                Map<String, List<MM>> pitem = (Map<String, List<MM>>) h.get(item.get(pid));
                if (!pitem.containsKey("children")) {
                    List<MM> children = new ArrayList<MM>();
                    children.add(item);
                    pitem.put("children", children);
                } else {
                    List<MM> children = pitem.get("children");
                    children.add(item);
                    pitem.put("children", children);
                }
            }
        }
        return r;
    }
}
