package com.zt.test;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zt.entity.School;
import com.zt.entity.SchoolExample;
import com.zt.mapper.SchoolMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian
 * @date 2019/1/21
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EasyExcelWriterTest {

    @Autowired
    private SchoolMapper schoolMapper;

    @org.junit.Test
    public void findSchool(){
        SchoolExample schoolExample = new SchoolExample();
        List<School> schoolList = schoolMapper.selectByExample(schoolExample);
    }

    //从数据库读取并写成excel
    @org.junit.Test
    public void readEasyExcel() throws IOException {
        try(OutputStream outputStream = new FileOutputStream("e:/files/word/school.xls")) {
            ExcelWriter writer = new ExcelWriter(outputStream,ExcelTypeEnum.XLS);
            Sheet sheet = new Sheet(1, 0, ExcelIndelModel.class);
            sheet.setSheetName("sheet1");
            List<ExcelIndelModel> data = new ArrayList<>();

            SchoolExample schoolExample = new SchoolExample();
            List<School> schoolList = schoolMapper.selectByExample(schoolExample);
            for(School school : schoolList){
                ExcelIndelModel item = new ExcelIndelModel();
                    item.id = school.getId();
                    item.schoolName = school.getSchoolName();
                    data.add(item);
            }
            writer.write(data, sheet);
            writer.finish();
        }
    }

    public static class ExcelIndelModel extends BaseRowModel {

        @ExcelProperty(value = "ID", index=0)
        private Integer id;

        @ExcelProperty(value = "名称", index = 1)
        private String schoolName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
    }

}
