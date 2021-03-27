package com.me.account.controller;

import com.me.account.common.utils.WebUtils;
import com.me.account.service.impl.ConsumeTypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/consumeType")
public class ConsumeTypeController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumeTypeController.class);
    @Resource
    private ConsumeTypeServiceImpl consumeTypeServiceImpl;

    @RequestMapping(value = "/list")
    public String queryListByPager(HttpSession session) {
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            list = consumeTypeServiceImpl.getList();
            return WebUtils.getData(true, "", list);
        } catch (Exception e) {
            logger.error("NewsController.java-queryListByPager-Exception: ", e);
            return WebUtils.getData(false, "系统错误！", null);
        }
    }

}
