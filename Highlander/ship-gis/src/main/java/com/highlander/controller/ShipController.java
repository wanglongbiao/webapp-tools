package com.highlander.controller;

import com.highlander.common.Global;
import com.highlander.entity.RealData;
import com.highlander.entity.Rail;
import com.highlander.entity.ShipInfo;
import com.highlander.entity.User;
import com.highlander.repository.PosTimeRepository;
import com.highlander.repository.RailRepository;
import com.highlander.repository.RealDataRepository;
import com.highlander.repository.ShipInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("ship")
public class ShipController extends BaseController {

    @Autowired
    private RailRepository railRepository;

    @Autowired
    private PosTimeRepository posTimeRepository;

    @Autowired
    private ShipInfoRepository shipInfoRepository;

    @Autowired
    private RealDataRepository realDataRepository;

    @RequestMapping("shipManagement")
    public String shipManagement(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        User loginedUser = (User) session.getAttribute(Global.USER_INFO);
        log.info(loginedUser.getUserName());
        model.put("user", loginedUser);
        return "ship/shipManagement";
    }
    @RequestMapping("shipView")
    public String shipView(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
//        User loginedUser = (User) session.getAttribute(Global.USER_INFO);
//        log.info(loginedUser.getUserName());
//        model.put("user", loginedUser);
        return "ship/shipView";
    }

    @RequestMapping("colorbox/shipSearch")
    public String shipSearch(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        String searcText = params.get("searchText");
        User loginedUser = (User) session.getAttribute(Global.USER_INFO);

        List<ShipInfo> list = shipInfoRepository.findAll();

        // log.info(loginedUser.getUserName());
        model.put("user", loginedUser);
        model.put("list", list);
        return "ship/shipSearch";
    }
    @RequestMapping("colorbox/getRealData")
    public String getRealData(@RequestParam Map<String, String> params, Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        RealData entity = realDataRepository.findOne(id);


        model.put("entity", entity);

//        ShipInfo shipInfo = shipInfoRepository.findOne(1L);
//        model.put("shipInfo", shipInfo);
        model.put("shipInfo", entity.getShipInfo());
        return "ship/realDataDialog";

        // log.info(loginedUser.getUserName());
    }

    @RequestMapping("getRails")
    @ResponseBody
    public Map<String, Object> getRails(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Sort sort = new Sort("railCode");

        List<Rail> list = railRepository.findAll(sort);
        result.put("list", list);
        return result;
    }

    // 获取实时数据，返回到前台，显示为圆圈
    @RequestMapping("getRealDataList")
    @ResponseBody
    public Map<String, Object> getRealDataList(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        List<RealData> realDataList = realDataRepository.findAll();

//        realDataList.remove(0);
//        log.info(realDataList.toString());
        result.put("list", realDataList);
        return result;
    }

}
