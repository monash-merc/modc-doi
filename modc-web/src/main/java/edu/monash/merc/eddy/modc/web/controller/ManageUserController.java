/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.web.controller;

import edu.monash.merc.eddy.modc.domain.User;
import edu.monash.merc.eddy.modc.service.UserService;
import edu.monash.merc.eddy.modc.sql.condition.OrderByType;
import edu.monash.merc.eddy.modc.sql.condition.SqlOrderBy;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import edu.monash.merc.eddy.modc.web.conts.MConts;
import edu.monash.merc.eddy.modc.web.form.ManagedUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 17/11/14
 */
@Controller
@RequestMapping("/manage")
public class ManageUserController extends BaseController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list_users")
    public String listUsers(Integer pageNo, Integer sizePerPage, String orderBy, String orderByType, HttpServletRequest request, Model model) {
        if (pageNo == null || pageNo.intValue() == 0) {
            pageNo = MConts.DEFAULT_START_PAGE_NO;
        }
        if (sizePerPage == null || sizePerPage.intValue() == 0) {
            sizePerPage = (Integer) getFromSession(request, MConts.SIZE_PER_PAGE);
            if (sizePerPage == null || sizePerPage == 0) {
                sizePerPage = MConts.DEFAULT_SIZE_PER_PAGE;
            }
        }

        //save sizePerPage into session
        storeInSession(request, MConts.SIZE_PER_PAGE, sizePerPage);
        if (orderBy == null) {
            orderBy = (String) getFromSession(request, MConts.ORDER_BY);
            if (orderBy == null) {
                orderBy = "displayName";
            }
        }
        //save orderBy into session
        storeInSession(request, MConts.ORDER_BY, orderBy);
        if (orderByType == null) {
            orderByType = (String) getFromSession(request, MConts.ORDER_BY_TYPE);
            if (orderByType == null) {
                orderByType = OrderByType.ASC.order();
            }
        }
        //save orderByType into session
        storeInSession(request, MConts.ORDER_BY_TYPE, orderByType);


        SqlOrderBy myOrders = genOrderBy(orderBy, orderByType);
        //set the pagination params and links
        model.addAttribute("sizePerPage", sizePerPage);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderByType", orderByType);
        model.addAttribute("pageLink", "manage/list_users.htm");
        try {
            Pager<User> paginationUsers = this.userService.getUsers(pageNo, sizePerPage, myOrders.orders());
            if (paginationUsers == null) {
                paginationUsers = new Pager<>();
            }
            //set the pagination users
            model.addAttribute("paginationUsers", paginationUsers);
        } catch (Exception ex) {
            logger.error(ex);
            actionSupport(request, model);
            addActionError("user.list.users.error");
            makeErrorAware();
            return "manage/list_users_error";
        }
        return "manage/list_users";
    }

    @RequestMapping(value = "/view_user", method = RequestMethod.GET)
    public String viewUser(@RequestParam("id") long id, HttpServletRequest request, Model model) {
        actionSupport(request, model);
        ManagedUserBean userBean = new ManagedUserBean();
        model.addAttribute("userBean", userBean);
        try {
            User user = this.userService.getUserById(id);
            if (user == null) {
                addActionError("user.show.details.account.not.found");
                makeErrorAware();
                return "manage/show_user_error";
            }
            userBean.setUser(user);
            userBean.setProfile(user.getProfile());
            userBean.setAvatar(user.getAvatar());
            return "manage/user_details";
        } catch (Exception ex) {
            logger.error(ex);
            addActionError("user.show.details.check.user.error");
            makeErrorAware();
            return "manage/show_user_error";
        }
    }

    private SqlOrderBy genOrderBy(String orderBy, String orderByType) {
        SqlOrderBy myOrderBy = new SqlOrderBy().asc("userType");
        if (orderByType.equalsIgnoreCase(OrderByType.ASC.order())) {
            myOrderBy.asc(orderBy);
        } else {
            myOrderBy.desc(orderBy);
        }
        return myOrderBy;
    }
}
