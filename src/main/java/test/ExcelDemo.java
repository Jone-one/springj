package test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExcelDemo {

    public static void main(String[] args) throws IOException {
        CopyOnWriteArrayList<String> listE ;
        CopyOnWriteArrayList<String> listV ;

        List<String> successE = new ArrayList<>();
        List<String> successV = new ArrayList<>();

        List<String> listNo1 = new ArrayList<>();
        Set<String> setV = new HashSet<>();
        Set<String> setE = new HashSet<>();
        //二级对比成功键值对集合
        Map<String,String> mapNo2 = new HashMap<>();
        Map<String,String> mapE2 = new HashMap<>();
        //二级对比失败vx集合
        List<String> listNo2 = new ArrayList<>();

        List<String> listE1 = new ArrayList<>();
        List<String> listE2 = new ArrayList<>();

        ExcelDemo excelDemo = new ExcelDemo();
        listV = excelDemo.readFile02();
        listE = excelDemo.getExcel();

        int tempp = 0;
        int vxtemp = 1;
        //每次只进入一个vx名
        for (String strV : listV){
            //如果此vx名中在表格中完全不同
           if (!listE.contains(strV)){
               //添加到一级对比集合中
               listNo1.add(strV);
               //拆分此vx名到set集合中
                for (int i = 0 ; i < strV.length(); i++){
                    setV.add(strV.substring(i,i+1));
                }
                //循环表格中所有游戏名
                for (int j = 0;j<listE.size();j++){
                    String strE = listE.get(j);
                    //拆分每个游戏名到set集合中
                    for (int i = 0; i < strE.length(); i++){
                        setE.add(strE.substring(i,i+1));
                    }
                    //对比交集
                    setE.retainAll(setV);
                    //如果交集大于2，符合准确度
                    if (setE.size()>=2) {
                        if (mapNo2.containsKey(strV)){
                            mapNo2.put(strV,mapNo2.get(strV)+"   "+strE);
                        }
                        else {
                            mapNo2.put(strV, strE);
                        }
                        if (!listNo2.contains(strV)){
                            listNo2.add(strV);
                        }
                    }
                    setE.clear();
                }
                setV.clear();
           }
           else {
               //将完全匹配的名字添加到成功集合中
               successE.add(strV);
               successV.add(strV);
               //在原始数据集合中删除对比成功的数据
                listE.remove(strV);
                listV.remove(strV);
                ++tempp;
           }
        }


        setE.clear();
        setV.clear();


        //每个游戏名与微信名对比
        for (String strE : listE){
            listE1.add(strE);
            for (int i = 0 ; i <strE.length() ; i++){
                setE.add(strE.substring(i,i+1));
            }
            for (String strV : listV){
                for (int j = 0 ; j < strV.length() ; j++){
                    setV.add(strV.substring(j,j+1));
                }
                setV.retainAll(setE);
                if (setV.size()>=2) {
                    if (mapE2.containsKey(strE)){
                        mapE2.put(strE,mapE2.get(strE)+"   "+strV);
                    }
                    else {
                        mapE2.put(strE, strV);
                    }
                    if (!listE2.contains(strE)){
                        listE2.add(strE);
                    }
                }
                setV.clear();
            }
            setE.clear();
        }
        System.out.println("对比分类：\n1.两边名字中有两个字以上的相同\n2.两边名字只有一个字或者没有相同的");
      System.out.println("------------微信名精确对比失败："+listNo1.size());


        System.out.println("------------剩下数据不匹配数据：微信【"+listV.size()+"】:游戏【"+listE.size()+"】");


        System.out.println("------------剩下数据互相对比，以两个字符的相似度进行分类--------------");
        System.out.println("-----------------微信名对比游戏名--------------");
        System.out.println("粗对比："+mapNo2.size()+"个微信名有相似的游戏名");
        Iterator iterator = mapNo2.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = mapNo2.get(key);
            System.out.println("【微信名】："+key+"     "+"【游戏名】："+value);
        }
        System.out.println();
        listNo1.removeAll(listNo2);
        System.out.println("二级对比："+listNo1.size()+"个不合格（没有部分匹配游戏名）[不在游戏中]");
        for (String str : listNo1){
            System.out.println(str);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-----------------游戏名对比微信名--------------");
        System.out.println("粗对比："+mapE2.size()+"个游戏名有相似的微信名");
        Iterator iterator1 = mapE2.keySet().iterator();
        while (iterator1.hasNext()){
            String key = (String)iterator1.next();
            String value = mapE2.get(key);
            System.out.println("【游戏名】："+key+"     【微信名】："+value);
        }
        listE1.removeAll(listE2);
        System.out.println();
        System.out.println("二级对比："+listE1.size()+"个不合格（没有部分匹配微信名）[未进群]");
        for (String str : listE1){
            System.out.println(str);
        }




    }

    public List<String> getStr(){
        List<String> VXstr = new ArrayList<>();
        String str = "@Lucifer、@脑呱疼@托尼蟹@Jeremy\uD83D\uDC23@MS丶@Colin@Whit.Brian@G.O.@\uD83E\uDD17 \uD83E\uDD17 \uD83E\uDD17 \uD83E\uDD17 2.個人de世界@Feel@杰迪@水色放逐@阳子@Hugo@瀦醜寶@L@走在冷风中@Yan亚强@9527.besides@丶Ruto@融众_房贷_车贷:家福15959595505@我们一起私奔@繁华过后的是孤独@自由随风@开着火车，唱着歌(^_-)@阿里哥哥@JingGG@Skywalker@翟继尧@　\uD83D\uDC1C　　　@军无戏言@张小宇@Yoochin ²⁰¹⁸@噹噹-丁丁@byte@\uD83D\uDC3E狼扒子@A老叶床上用品叶建斌13651288399@Xue Sven@愚味@張威@石头@刘大爷@传祺@欢@小呆@皓月当空@雨过天晴@意随随意@官晓峰\uD83D\uDE0Eི@有乐恬@W.先生@Mr.〢、低調@王梓岩@陈矢渝（雨桐猫咪咖啡）@啦啦@李疆@晴天咚@永嘉农商行郑秦晓@JunKai@被遗忘的柴犬大人@  @ty@小星星@ClearOu@月下美人@斌得开@低调奢华@#甚℉麽@風吹雲散@Shao S1bó@小萌@赵恺@꯭夏꯭至꯭、꯭未꯭至꯭@Delta carinids@胡甲正@A～房产经纪人~森@勇哥@Ada@徐小龙@突然记得忘记@、No.1李先生\uD83C\uDF97@Yi@戒烟不戒酒\uD83C\uDFC2@洋洋同學@yc晨@Ming@seen-@A华远欧洲-张越13810277973@皮一下\uD83D\uDC32@几何原理@诚者乃天之道也！@xiao磊@奋斗@Hasika@张纯展@天空@领悦智能·舒适生活体验馆·赵斌@ 快来找我买美瞳呀  \uD83D\uDC3E@波波仔@需要 氧气的@ི་ོ喵༄ @Tao@华东理工大学 左老师@林风@胖胖@、Lewis、@笑十一狼@Cowboy bone@Jenny@钊钊@endi云@宜客斯酷笃弥@Lv.X@陈淑浩@方龙柱@于耀钧@木云@麒麟仔@Silence@止止止疯。@j.k@Daling_Qiu@大辉辉@九离@AA李季@樊宇@老腊肉@茹@A 姚小妮10.29奔韩国@赵小玮@许涛@SilverFox@龙@黄尚@All saints人群恐惧症重症患者@ohh@啊志@C.C@HD@小小小小小…@艾呀呀@苏玥@乔奇@Leekin@木偶@A- 晴姐姐。\uD83D\uDE48@guo@老王@杨小布@荣哥@冯洋@宣圣臣@J.X.H@Leo Pan@AI灵。@JL@晓帆@圆@LT@徵@柴学洁℡ ¹³⁷⁵²²⁷³³⁴³@高级催乳师@Evil_Ye@Ekinb@A。@aspiRinnie@꧁꫞℡陆晓喵℡꫞꧂@一个人奔跑@星辰@精彩回忆@军哥威武@大貔貅";
        String[] strings =  str.split("@");
        System.out.println("VX数据条数"+strings.length);
        for (int i = 0;i<strings.length;i++){
//            System.out.println(strings[i]);
            VXstr.add(strings[i]);
        }
        return VXstr;
    }

    /**
     * 读取一个文本 一行一行读取
     *
     * @return
     * @throws IOException
     */
    public  CopyOnWriteArrayList<String> readFile02() throws IOException {
        String path = "G:\\vx.txt";
        // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        // 防止路径乱码   如果utf-8 乱码  改GBK     eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            // 如果 t x t文件里的路径 不包含---字符串       这里是对里面的内容进行一个筛选
            if (line.lastIndexOf("---") < 0) {
                line = line.trim();
                while (line.startsWith("　")) {//这里判断是不是全角空格
                    line = line.substring(1, line.length()).trim();
                }
                while (line.endsWith("　")) {
                    line = line.substring(0, line.length() - 1).trim();
                }
                list.add(line);
            }
        }
        br.close();
        isr.close();
        fis.close();
        System.out.println("微信群数据条数"+list.size());
        return list;
    }






    public CopyOnWriteArrayList<String> getExcel() throws IOException {

        String path = "G:\\aaa.xlsx";
        CopyOnWriteArrayList<String> ExcelStr = new CopyOnWriteArrayList<String>();
        InputStream iStream = new FileInputStream(path);
// .xlsx
        XSSFWorkbook workbook = new XSSFWorkbook(iStream);
//循环每一页，并处理当前页
        for (Sheet xssfSheet : workbook) {
//处理当前页循环读取每一行
            if (xssfSheet == null) {
                continue;
            }
            for (int runNum = 0; runNum < xssfSheet.getLastRowNum(); runNum++) {
                XSSFRow row = (XSSFRow) xssfSheet.getRow(runNum);
                int minColIx = row.getFirstCellNum();
                int maxColIx = row.getLastCellNum();
                //遍历该行，获取每个cell元素
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    XSSFCell cell = row.getCell(colIx);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    //获取指定的一列
                    if (cell.getStringCellValue().equals("3")) {
                       for (int runNum1 = 3; runNum1 <= xssfSheet.getLastRowNum(); runNum1++) {
                            XSSFRow row1 = (XSSFRow) xssfSheet.getRow(runNum1);
                            //遍历该行，获取每个cell元素
                            XSSFCell cell2 = row1.getCell(1);
                           String str = cell2.getStringCellValue();
                           str = str.trim();
                           while (str.startsWith("　")) {//这里判断是不是全角空格
                               str = str.substring(1, str.length()).trim();
                           }
                           while (str.endsWith("　")) {
                               str = str.substring(0, str.length() - 1).trim();
                           }
                           ExcelStr.add(str);
//                           System.out.println(str);
                        }
                        System.out.println("Excel数据条数:"+ExcelStr.size());
                        return ExcelStr;
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }


}
