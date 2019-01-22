package com.zt.test;

import com.zt.entity.ExcelData;
import com.zt.entity.ExcelReader;
import com.zt.entity.School;
import com.zt.mapper.SchoolMapper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangtian
 * @date 2019/1/21
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PoiWriterTest {

    @Autowired
    private SchoolMapper schoolMapper;

    @Test
    public void readExcelServlet() {

        //excel文件路径
        String excelPath = "e:/files/word/school.xls";
        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            //判断文件是否存在
            if (excel.isFile() && excel.exists()) {
                //.是特殊字符，需要转义！！！！！
                String[] split = excel.getName().split("\\.");
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    //文件流对象
                    FileInputStream fis = new FileInputStream(excel);
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                //读取sheet 0
                Sheet sheet = wb.getSheetAt(0);

                //第一行是列名，所以不读
                int firstRowIndex = sheet.getFirstRowNum() + 1;
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: " + firstRowIndex);
                System.out.println("lastRowIndex: " + lastRowIndex);

                //遍历行
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    if (rIndex != 3) {
                        System.out.println("rIndex: " + rIndex);
                        Row row = sheet.getRow(rIndex);
                        if (row != null) {
                            int firstCellIndex = row.getFirstCellNum();
                            int lastCellIndex = row.getLastCellNum();
                            //遍历列
                            String value = "";
                            for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
                                Cell cell = row.getCell(cIndex);
                                if (cell != null) {
                                    switch (cell.getCellType()) {
                                        case HSSFCell.CELL_TYPE_FORMULA:
                                            break;
                                        case HSSFCell.CELL_TYPE_NUMERIC:
                                            value += cell.getNumericCellValue() + ",";
                                            break;
                                        case HSSFCell.CELL_TYPE_STRING:
                                            value += cell.getStringCellValue() + ",";
                                            break;
                                        default:
                                            value += "0";
                                            break;
                                    }

                                }
                            }
                            String[] val = value.split(",");
                            System.out.println(val);
                        }

                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test() throws Exception{
        // 新建一个对象,存错误信息
        StringBuffer err = null;
        ExcelData excelData = null;
        // 解析表格信息
        excelData = ExcelReader.readFile(new FileInputStream("e:/files/word/school.xls"));
        // 获取第一个表格信息
        Map<Integer, Map> map = excelData.getSheets().get(0);
        // 转为对应的List
        List<School> list = mapList(map);
        if (list == null) {
            System.out.println("参数不可以为空");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                try {
                    //业务逻辑
                    schoolMapper.insert(list.get(i));
                } catch (Exception e) {
                    // 如果校验失败， 冗余错误行信息
                    int errorRowNum = i + 1 + 1;// 第一行为标题，所以是i+2
                    if (err == null) {
                        err = new StringBuffer("请注意第").append(errorRowNum)
                                .append("行数据：").append(e.getMessage())
                                .append("");
                    } else {
                        err.append("\n").append("请注意第").append(errorRowNum)
                                .append("行数据：").append(e.getMessage())
                                .append("");
                    }
                }

            }
        }
    }

    private List<School> mapList(Map<Integer, Map> map) {
        if (map == null) {
            System.out.println("参数不可以为空");
        }
        List<School> list = new ArrayList<>();
        School school = null;
        for (Integer key : map.keySet()) {
            if (key.intValue() == 0) {
                continue;
            }
            Map<Integer, String> clumMap = map.get(key);
            if (clumMap == null) {
                continue;
            }
            school = new School();
            school.setId(Integer.valueOf(clumMap.get(0)));
            school.setSchoolName(clumMap.get(1));
            list.add(school);
        }
        return list;
    }

}
