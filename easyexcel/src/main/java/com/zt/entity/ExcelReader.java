package com.zt.entity;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReader {
	public synchronized static ExcelData readFile(FileInputStream file) throws IOException{
		POIFSFileSystem fs=new POIFSFileSystem(file); 
		HSSFEventFactory factory = new HSSFEventFactory();
		HSSFRequest request = new HSSFRequest();
		ExcelData data=new ExcelData();
		request.addListener(new BOFRecordListener(data), BOFRecord.sid);
		SSTRecordListener stringListener=new SSTRecordListener(data);
		request.addListener(stringListener, SSTRecord.sid);
		request.addListener(stringListener, LabelSSTRecord.sid);
		NumbericListener numberListener=new NumbericListener(data);
		FormatTrackingHSSFListener fl=new FormatTrackingHSSFListener(numberListener);
		numberListener.setFormatTrackingHSSFListener(fl);
		request.addListenerForAllRecords(fl);
		
		factory.processWorkbookEvents(request, fs);
		return data;
	}
	
}

class BOFRecordListener implements HSSFListener{
	private ExcelData data;
	BOFRecordListener(ExcelData data){
		this.data=data;
	}
	@Override
	public void processRecord(Record record) {
		if (BOFRecord.sid==record.getSid()){
			BOFRecord bofRecord=(BOFRecord)record;
			if(BOFRecord.TYPE_WORKBOOK==bofRecord.getType()){
//				工作簿开始
			}else if(BOFRecord.TYPE_WORKSHEET==bofRecord.getType()){
//				工作表开始
				data.addWorkSheet();
			}
		}
	}
}
class SSTRecordListener implements HSSFListener{
	private SSTRecord sstRecord;
	private ExcelData data;
	SSTRecordListener(ExcelData data){
		this.data=data;
	}
	@Override
	public void processRecord(Record record) {
		if (record.getSid()==SSTRecord.sid){
//			发现SST，表明xls文件中有文本存在，SST保存xls文件中唯一的String，各个String都是对应着SST记录的索引
			sstRecord=(SSTRecord) record;
		}else if (LabelSSTRecord.sid==record.getSid()){
			LabelSSTRecord lsrec = (LabelSSTRecord) record;
			data.addString(lsrec.getRow(),lsrec.getColumn(),sstRecord.getString(lsrec.getSSTIndex()).toString());
		}
	}
}

class NumbericListener implements HSSFListener{
	private FormatTrackingHSSFListener formatListener;
	private ExcelData data;
	
	NumbericListener(ExcelData data){
		this.data=data;
	}
	@Override
	public void processRecord(Record record) {
		String stringValue="";
		if (record.getSid()==FormulaRecord.sid){
			FormulaRecord frecord=(FormulaRecord)record;
			if(Double.isNaN( frecord.getValue() )) {
				stringValue=String.valueOf(frecord.getValue()).trim();
			} else {
				stringValue=formatListener.formatNumberDateCell(frecord).trim();
			}
			if (stringValue!=null){
				while (stringValue.length()>0 && stringValue.endsWith("_")){
					stringValue=stringValue.substring(0,stringValue.length()-1);
				}
			}
			data.addString(frecord.getRow(), frecord.getColumn(), stringValue);
		}else if(record.getSid()==NumberRecord.sid ){
			NumberRecord nRecord=(NumberRecord)record;
			stringValue=formatListener.formatNumberDateCell(nRecord).trim();
			if (stringValue!=null){
				while (stringValue.length()>0 && stringValue.endsWith("_")){
					stringValue=stringValue.substring(0,stringValue.length()-1);
				}
			}
			data.addString(nRecord.getRow(), nRecord.getColumn(), stringValue);
		}
	}
	
	public void setFormatTrackingHSSFListener(FormatTrackingHSSFListener listener) {
		formatListener=listener;
	}
}

