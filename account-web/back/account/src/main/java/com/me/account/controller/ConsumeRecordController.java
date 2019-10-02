package com.me.account.controller;

import com.me.account.common.staticconst.WebConstants;
import com.me.account.common.utils.DateFormatUtil;
import com.me.account.common.utils.WebUtils;
import com.me.account.controller.sys.LoginController;
import com.me.account.entity.ConsumeRecord;
import com.me.account.entity.sys.ComboPager;
import com.me.account.entity.sys.Criteria;
import com.me.account.entity.sys.Pager;
import com.me.account.service.impl.ConsumeRecordServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping(value = "/admin/consumeRecord")
public class ConsumeRecordController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumeRecordController.class);
    private int pageSize = 10;
    private int currentPage = 1;
    @Resource
    private ConsumeRecordServiceImpl consumeRecordServiceImpl;
    /**
     * 分页查询列表
     */
    @RequestMapping(value = "/list")
    public String queryListByPager(@RequestBody Map map, HttpSession session) {
        try {
//            String sessionUserNo = (String)session.getAttribute(WebConstants.SESSION_USER_NO);
//            if(WebUtils.isNull(sessionUserNo)){
//                return WebUtils.getData(false,"登陆超时！",null);
//            }
            String pkUserId = map.get("token")==null?"":map.get("token").toString(); //用户主键
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            list = consumeRecordServiceImpl.getList();
            return WebUtils.getData(true, "", list);
        } catch (Exception e) {
            logger.error("NewsController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }

    @RequestMapping(value = "/getlist")
    public String getlist(@RequestBody Map map,HttpSession session) {
        try {
//            String sessionUserNo = (String)session.getAttribute(WebConstants.SESSION_USER_NO);
//            if(WebUtils.isNull(sessionUserNo)){
//                return WebUtils.getData(false,"登陆超时！",null);
//            }
            String pkUserId = map.get("token")==null?"":map.get("token").toString(); //用户主键
            if("".equals(pkUserId)){
                return WebUtils.getData(false,"非法访问！",null);
            }
            List<ConsumeRecord> list = new ArrayList<ConsumeRecord>();
            BigInteger bigInPkUserId = new BigInteger(pkUserId);
            list = consumeRecordServiceImpl.getConsumeRecordByUserPkId(bigInPkUserId);
            return WebUtils.getData(true, "", list);
        } catch (Exception e) {
            logger.error("ConsumeRecordController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }

    /**
     * 分页查询列表
     */
    @RequestMapping(value = "/getOnePage")
    public String queryListByPager(
                                   HttpSession session,
                                   @RequestBody Map map) {
        try {
            String pkUserId = map.get("token")==null?"":map.get("token").toString(); //用户主键
            String PAGE_SIZE = map.get("PAGE_SIZE")==null?"":map.get("PAGE_SIZE").toString();
            String CURRENT_PAGE = map.get("CURRENT_PAGE")==null?"":map.get("CURRENT_PAGE").toString();
            String consume_type_id = map.get("consume_type_id")==null?"":map.get("consume_type_id").toString();
            String pay_type_id = map.get("pay_type_id")==null?"":map.get("pay_type_id").toString();
            String beg_time = map.get("beg_time")==null?"":map.get("beg_time").toString();
            String end_time = map.get("end_time")==null?"":map.get("end_time").toString();
            if("".equals(pkUserId)){
                return WebUtils.getData(false,"非法访问！",null);
            }
            //查询条件
            Criteria criteria = new Criteria();
            criteria.clear();
            criteria.put("pkSysUser", pkUserId);
            if(StringUtils.isNotBlank(consume_type_id)){
                criteria.put("consume_type_id_fk", consume_type_id);
            }
            if(StringUtils.isNotBlank(pay_type_id) && !pay_type_id.equals("null")){
                criteria.put("pay_type_id_fk", pay_type_id);
            }
            if(StringUtils.isNotBlank(beg_time) && !beg_time.equals("null")){
                criteria.put("beg_time", beg_time);
            }
            if(StringUtils.isNotBlank(end_time) && !beg_time.equals("null")){
                criteria.put("end_time", end_time);
            }
            //分页
            if(StringUtils.isNotBlank(PAGE_SIZE)){
                pageSize = Integer.valueOf(PAGE_SIZE);
            }
            if(StringUtils.isNotBlank(CURRENT_PAGE)){
                currentPage = Integer.valueOf(CURRENT_PAGE);
            }
            Pager pager = new Pager(pageSize,currentPage);
            ComboPager comboPager = consumeRecordServiceImpl.queryListByPager(criteria,pager);
            return WebUtils.getData(true, "", comboPager);
        } catch (Exception e) {
            logger.error("NewsController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }

    @RequestMapping(value = "/add")
    public String addConsumeRecord(@RequestBody Map map,HttpSession session) {
        try {

            String pkUserId = map.get("token")==null?"":map.get("token").toString(); //用户主键
            String consumeTypeIdFk = map.get("consumeTypeIdFk")==null?"":map.get("consumeTypeIdFk").toString(); //消费类型主键
            String payTypeIdFk = map.get("payTypeIdFk")==null?"":map.get("payTypeIdFk").toString(); //支付类型主键
            // 消费记录时间
            String consumeTime = map.get("consumeTime")==null?"":map.get("consumeTime").toString(); //消费时间
            String consumeAmount = map.get("consumeAmount")==null?"":map.get("consumeAmount").toString(); //消费金额
            String consumeDesc = map.get("consumeDesc")==null?"":map.get("consumeDesc").toString(); //消费描述
            String consumeAddr = map.get("consumeAddr")==null?"":map.get("consumeAddr").toString(); //消费地址

            if("".equals(consumeTypeIdFk)){
                return WebUtils.getData(false,"消费类型不可为空！",null);
            }
            if("".equals(pkUserId)){
                return WebUtils.getData(false,"非法访问！",null);
            }
            if("".equals(payTypeIdFk)){
                return WebUtils.getData(false,"支付类型不可为空！",null);
            }
            if("".equals(consumeTime)){
                return WebUtils.getData(false,"消费时间不可为空！",null);
            }
            if("".equals(consumeAmount)){
                return WebUtils.getData(false,"消费金额不可为空！",null);
            }
            ConsumeRecord cr = new ConsumeRecord();
            cr.setTsysUserIdFk(new Long(pkUserId));
            cr.setConsumeTypeIdFk(new Long(consumeTypeIdFk));
            cr.setPayTypeIdFk(new Long(payTypeIdFk));
            cr.setConsumeTime(DateFormatUtil.getDate(consumeTime,"yyyy-MM-dd HH:mm:ss"));
            cr.setConsumeAmount(new BigDecimal(consumeAmount));
            cr.setConsumeDesc(consumeDesc);
            cr.setConsumeAddr(consumeAddr); //消费地址

            Integer rows = consumeRecordServiceImpl.addConsumeRecord(cr);
            if(rows > 0) {
                return WebUtils.getData(true, "消费记录添加成功！", "");
            }else {
                return WebUtils.getData(false, "消费记录添加失败！", "");
            }

        } catch (Exception e) {
            logger.error("ConsumeRecordController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }

    /**
     * 月份分析查询列表
     */
    @RequestMapping(value = "/getMonthAnalysisByConsumeType")
    public String getMonthAnalysisByConsumeType(
            HttpSession session,
            @RequestBody Map map) {
        try {
            String pkUserId = map.get("token")==null?"":map.get("token").toString(); //用户主键
            String beg_time = map.get("beg_time")==null?"":map.get("beg_time").toString();
            String end_time = map.get("end_time")==null?"":map.get("end_time").toString();
            if("".equals(pkUserId)){
                return WebUtils.getData(false,"非法访问！",null);
            }
            //查询条件
            Criteria criteria = new Criteria();
            criteria.clear();
            criteria.put("pkSysUser", pkUserId);

            if(StringUtils.isNotBlank(beg_time) && !beg_time.equals("null")){
                criteria.put("beg_time", beg_time);
            }
            if(StringUtils.isNotBlank(end_time) && !beg_time.equals("null")){
                criteria.put("end_time", end_time);
            }
            Map mp = consumeRecordServiceImpl.queryList(criteria);

            return WebUtils.getData(true, "", mp);
        } catch (Exception e) {
            logger.error("NewsController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }

    /**
     * 年度分析查询列表
     */
    @RequestMapping(value = "/getYearAnalysisByConsumeMonth")
    public String getYearAnalysisByConsumeMonth(
            HttpSession session,
            @RequestBody Map map) {
        try {
            String pkUserId = map.get("token")==null?"":map.get("token").toString(); //用户主键
            String beg_time = map.get("beg_time")==null?"":map.get("beg_time").toString(); // 开始时间为某年1月1日 00：00：00
            String end_time = map.get("end_time")==null?"":map.get("end_time").toString();
            if("".equals(pkUserId)){
                return WebUtils.getData(false,"非法访问！",null);
            }
            //查询条件
            Criteria criteria = new Criteria();
            criteria.clear();
            criteria.put("pkSysUser", pkUserId);

            if(StringUtils.isNotBlank(beg_time) && !beg_time.equals("null")){
                criteria.put("beg_time", beg_time);
            }
            if(StringUtils.isNotBlank(end_time) && !beg_time.equals("null")){
                criteria.put("end_time", end_time);
            }
            Map mp = consumeRecordServiceImpl.queryListAccordingConsumeMonth(criteria);

            return WebUtils.getData(true, "", mp);
        } catch (Exception e) {
            logger.error("NewsController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }
}
