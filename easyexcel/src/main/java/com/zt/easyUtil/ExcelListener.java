package com.zt.easyUtil;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.zt.entity.School;
import com.zt.entity.SchoolExample;
import com.zt.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian
 * @date 2019/1/22
 */

public class ExcelListener extends AnalysisEventListener {

    @Autowired
    private SchoolMapper schoolMapper;

    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    @Override
    public void invoke(Object object, AnalysisContext context) {
        System.out.println("当前行："+ context.getCurrentRowNum());
        System.out.println("object-->"+ object);
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(object);
        System.out.println("datas--->"+ datas);
        //根据自己业务做处理
        doSomething(datas);


        //schoolMapper.batchInsert(datas);
        /*SchoolExample schoolExample = new SchoolExample();
        List<School> schoolList = schoolMapper.selectByExample(schoolExample);
        System.out.println(schoolList);
*/
    }

    private void doSomething(List<Object> lists) {
        System.out.println("lists--->" + lists);

        //schoolMapper.batchInsert(lists);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }
    public List<Object> getDatas() {
        return datas;
    }
    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
