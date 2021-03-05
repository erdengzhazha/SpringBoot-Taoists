package com.ovopark.tao.java.tool.tree;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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




    public static LinkedList<MyTestTree> toSort(List<MyTestTree> list,
                                                LinkedList<MyTestTree> result, int father) {
        List<MyTestTree> temp = new ArrayList<MyTestTree>();
        // 最高层,临时存放
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId() == father) {
                temp.add(list.get(i));
            }
        }

        if (temp.size() < 1) {
            return result;
        } else {
            // 删除最高层
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getParentId() == father) {
                    list.remove(j);
                }
            }
            // 对最高层排序
            for (int i = 0; i < temp.size() - 1; i++) {
                for (int j = i + 1; j < temp.size(); j++) {
                    if (temp.get(i).getNo_order() > temp.get(j).getNo_order()) {
                        MyTestTree myTestTree = temp.get(i);
                        temp.set(i, temp.get(j));
                        temp.set(j, myTestTree);
                    }
                }
            }
            // 递归
            for (int i = 0; i < temp.size(); i++) {
                result.add(temp.get(i));
                toSort(list, result, temp.get(i).getId());
            }
            return result;
        }

    }

    public static void main(String[] args) {
        // id,pid(父节点),name(节点名称),sequence(同级节点排序依据)
        MyTestTree tree1 = new MyTestTree(1, "顶层节点1", 0, 1);
        MyTestTree tree2 = new MyTestTree(2, "顶层节点2", 0, 2);
        MyTestTree tree3 = new MyTestTree(3, "顶层节点3", 0, 3);

        MyTestTree tree4 = new MyTestTree(4, "二级节点4", 1, 1);
        MyTestTree tree5 = new MyTestTree(5, "二级节点5", 2, 2);
        MyTestTree tree6 = new MyTestTree(6, "二级节点6", 3, 3);

        MyTestTree tree7 = new MyTestTree(7, "三级节点7", 4, 1);
        MyTestTree tree8 = new MyTestTree(8, "三级节点8", 4, 2);
        MyTestTree tree9 = new MyTestTree(9, "三级节点9", 5, 1);
        MyTestTree tree10 = new MyTestTree(10, "三级节点10", 5, 2);
        MyTestTree tree11 = new MyTestTree(11, "三级节点11", 5, 10);
        MyTestTree tree12 = new MyTestTree(12, "三级节点12", 5, 16);
        MyTestTree tree13 = new MyTestTree(13, "三级节点13", 5, 100);
        MyTestTree tree14 = new MyTestTree(14, "三级节点14", 5, 0);

        List<MyTestTree> list = new ArrayList<MyTestTree>();
        list.add(tree1);
        list.add(tree2);
        list.add(tree3);
        list.add(tree4);
        list.add(tree5);
        list.add(tree6);
        list.add(tree7);
        list.add(tree8);
        list.add(tree9);
        list.add(tree10);
        list.add(tree11);
        list.add(tree12);
        list.add(tree13);
        list.add(tree14);

        LinkedList<MyTestTree> result = new LinkedList<MyTestTree>();

        LinkedList<MyTestTree> f = toSort(list, result, 0);

        List<MM> list1 = new ArrayList<>();


        for (int i = 0; i < f.size(); i++) {
            System.out.print(f.get(i).getId() + ",");
            System.out.print(f.get(i).getName() + ",");
            System.out.print(f.get(i).getParentId() + ",");
            System.out.println(f.get(i).getNo_order()+",");
            System.out.println(f.get(i).getChildrens());
            Map map = java2Map(f.get(i));
            MM mm = new MM();
            mm.putAll(map);
            list1.add(mm);
        }

        List<MM> treeMaps = ArrayToTreeDataSort(list1, "id", "parentId","no_order");
        System.out.println(JSON.toJSON(treeMaps));


    }
    /**
     * 将List 数组转换为Tree ,并且排序
     *
     * @param id
     *            ID 列
     * @param pid
     *            PID 列
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<MM> ArrayToTreeDataSort(List<MM> mydata, String id, String pid ,String sortString) {
        Map h = new HashMap();// 数据索引
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
                    // 对Children进行排序
                    Collections.sort(children, new Comparator<MM>() {
                        @Override
                        public int compare(MM o1, MM o2) {
                            int diff = o1.getInt(sortString) - o2.getInt(sortString);
                            if(diff>0){
                                return 1;
                            }else if(diff<0){
                                return -1;
                            }
                            return 0; //相等等于0
                        }
                    });
                    pitem.put("children", children);
                }
            }
        }
        return r;
    }


    /**
     * JavaBean对象转化成Map对象
     *
     * @param javaBean
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map java2Map(Object javaBean) {
        Map map = new HashMap();
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean, new Object[0]);
                        map.put(propertyName, propertyValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
