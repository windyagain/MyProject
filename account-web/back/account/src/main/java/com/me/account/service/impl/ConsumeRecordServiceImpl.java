package com.me.account.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.account.dao.ConsumeRecordMapper;
import com.me.account.entity.ConsumeRecord;
import com.me.account.entity.sys.ComboPager;
import com.me.account.entity.sys.Criteria;
import com.me.account.entity.sys.Pager;
import com.me.account.service.ConsumeRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service("ConsumeRecordService")
@Transactional(rollbackFor = Exception.class)
public class ConsumeRecordServiceImpl implements ConsumeRecordService {
    @Resource
    private ConsumeRecordMapper consumeRecordMapper;

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> recordInfos = consumeRecordMapper.selectMaps(new EntityWrapper<ConsumeRecord>());
        return recordInfos;
    }

    @Override
    public List<ConsumeRecord> getConsumeRecordByUserPkId(BigInteger tsysUserId) {
        List<ConsumeRecord> recordInfos = consumeRecordMapper.getConsumeRecordByUserPkId(tsysUserId);
        return recordInfos;
    }

    @Override
    public Integer addConsumeRecord(ConsumeRecord cr) throws Exception {
        Integer rows = 0;
        Long consumeRecordId = consumeRecordMapper.seqConsumeRecord();
        cr.setConsumeRecordId(consumeRecordId);
        try {
            rows = consumeRecordMapper.insert(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 分页查询
     */
    @Override
    public ComboPager queryListByPager(Criteria example, Pager pager)
            throws Exception {

        int totalRows = consumeRecordMapper.countByExample(example);
        pager.setTotalRows(totalRows);
        example.setStartRow((pager.getCurrentPage()-1)*pager.getPageSize());
        example.setEndRow(pager.getPageSize());
        // 需要拿 搜索的 总的数据来进行计算 总花费，而不是当前页。
        List allRows = consumeRecordMapper.selectByCondition(example);
        List rs = consumeRecordMapper.selectByExample(example);
        BigDecimal totalConsume = new BigDecimal("0");
        // 循环一遍所有的数据，计算拿到数据的总开销。
        for (Iterator iterator = allRows.iterator(); iterator.hasNext();) {
            ConsumeRecord cr = (ConsumeRecord) iterator.next();
            totalConsume = totalConsume.add(cr.getConsumeAmount());
        }
        Map temp_mp = new HashMap();
        temp_mp.put("totalConsume",totalConsume);
        ComboPager comboPager = new ComboPager();
        comboPager.setPager(pager);
        comboPager.setRs(rs);
        comboPager.setMp(temp_mp);
        return comboPager;

    }
    /*
        用于月份数据获取
    */
    @Override
    public Map queryList(Criteria example)
            throws Exception {

        List<ConsumeRecord> allRows = consumeRecordMapper.selectByCondition(example);
        List data_title = new ArrayList();
        List data = new ArrayList();
        List consume_type = new ArrayList();
        BigDecimal totalConsume = new BigDecimal("0");
        // 循环一遍所有的数据，计算拿到数据的总开销。
        for (Iterator iterator = allRows.iterator(); iterator.hasNext();) {
            ConsumeRecord cr = (ConsumeRecord) iterator.next();
            totalConsume = totalConsume.add(cr.getConsumeAmount());
            if(!consume_type.contains(cr.getConsumeTypeIdFk())){
                consume_type.add(cr.getConsumeTypeIdFk());
            }
        }
        for(int i=0; i < consume_type.size(); i++ ){
            String t_data_title = "";
            BigDecimal bigDecimal = new BigDecimal("0");
            for(int j=0; j< allRows.size();j++){
                ConsumeRecord tempConsumeRecord = new ConsumeRecord();
                tempConsumeRecord = allRows.get(j);
                if(consume_type.get(i).equals(tempConsumeRecord.getConsumeTypeIdFk())){
                    bigDecimal = bigDecimal.add(tempConsumeRecord.getConsumeAmount());
                    t_data_title = tempConsumeRecord.getConsumeType();
                }
            }
            data_title.add(t_data_title);
            data.add(bigDecimal);
        }
        Map temp_mp = new HashMap();
        temp_mp.put("totalConsume",totalConsume);
        temp_mp.put("data_title", data_title);
        temp_mp.put("data", data);
        return temp_mp;

    }


    /*
        获取某一年12个月的数据
    */
    @Override
    public Map queryListAccordingConsumeMonth(Criteria example)
            throws Exception {

        List<ConsumeRecord> allRows = consumeRecordMapper.selectByCondition(example);
        List data_title = new ArrayList();
        List data = new ArrayList();
        BigDecimal totalConsume = new BigDecimal("0");
        // 初始化十二个月的耗费开销为0.
        BigDecimal janTotalConsume = new BigDecimal("0");
        BigDecimal febTotalConsume = new BigDecimal("0");
        BigDecimal marTotalConsume = new BigDecimal("0");
        BigDecimal aprTotalConsume = new BigDecimal("0");
        BigDecimal mayTotalConsume = new BigDecimal("0");
        BigDecimal juneTotalConsume = new BigDecimal("0");
        BigDecimal julyTotalConsume = new BigDecimal("0");
        BigDecimal augTotalConsume = new BigDecimal("0");
        BigDecimal sepTotalConsume = new BigDecimal("0");
        BigDecimal octTotalConsume = new BigDecimal("0");
        BigDecimal novTotalConsume = new BigDecimal("0");
        BigDecimal decTotalConsume = new BigDecimal("0");

        for(int j=0; j< allRows.size();j++){
            ConsumeRecord tempConsumeRecord = new ConsumeRecord();
            tempConsumeRecord = allRows.get(j);
            // 累加
            totalConsume = totalConsume.add(tempConsumeRecord.getConsumeAmount());
            Calendar cal = Calendar.getInstance();
            cal.setTime(tempConsumeRecord.getConsumeTime());
            int month = cal.get(Calendar.MONTH)+1;

            if(month == 1){
                janTotalConsume = janTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }else if(month == 2){
                febTotalConsume = febTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }else if(month == 3){
                marTotalConsume = marTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }else if(month == 4){
                aprTotalConsume = aprTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            } else if(month ==5){
                mayTotalConsume = mayTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }else if(month == 6){
                juneTotalConsume = juneTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            } else if(month == 7){
                julyTotalConsume = julyTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            } else if(month == 8){
                augTotalConsume = augTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            } else if(month == 9){
                sepTotalConsume = sepTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }else if(month == 10){
                octTotalConsume = octTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }else if(month == 11){
                novTotalConsume = novTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            } else if(month == 12){
                decTotalConsume = decTotalConsume.add(tempConsumeRecord.getConsumeAmount());
            }
        }
        data_title.add("一月");
        data_title.add("二月");
        data_title.add("三月");
        data_title.add("四月");
        data_title.add("五月");
        data_title.add("六月");
        data_title.add("七月");
        data_title.add("八月");
        data_title.add("九月");
        data_title.add("十月");
        data_title.add("十一月");
        data_title.add("十二月");

        data.add(janTotalConsume);
        data.add(febTotalConsume);
        data.add(marTotalConsume);
        data.add(aprTotalConsume);
        data.add(mayTotalConsume); //5月
        data.add(juneTotalConsume);
        data.add(julyTotalConsume);
        data.add(augTotalConsume);
        data.add(sepTotalConsume);
        data.add(octTotalConsume);// 10月
        data.add(novTotalConsume);
        data.add(decTotalConsume);
        Map temp_mp = new HashMap();
        temp_mp.put("totalConsume",totalConsume);
        temp_mp.put("data_title", data_title);
        temp_mp.put("data", data);
        return temp_mp;

    }
}
