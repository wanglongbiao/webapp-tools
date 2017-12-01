package com.highlander.controller;

import com.highlander.entity.Addr;
import com.highlander.repository.AddrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "addr")
public class AddrController {

    private Long topParentId = -1L;

    @Autowired
    private AddrRepository addrRepository;

    @RequestMapping("getAddrTreeList")
    @ResponseBody
    public Map<String, Object> getAddrTreeList(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
//        User loginedUser = (User) session.getAttribute(Global.USER_INFO);

        List<Addr> addrList = addrRepository.findAll();


//        getTreeNodesById(treeNodes, topParentId);

//        result.put("treeNodes", treeNodes);
        result.put("addrList", addrList);
//        result.put("message", "退出登录成功");
        return result;
    }



}
