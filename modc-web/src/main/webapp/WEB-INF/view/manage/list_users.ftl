<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "user.all.users.action.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "user.all.users.action.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
            <#include "../template/action_message.ftl" />
                <div class="none_border_block">
                    <span class="name_title">Total <span class="span_number"> ${paginationUsers.totalSize} </span> Users</span>
                </div>
            <#if (paginationUsers?? && paginationUsers.pageResults?size > 0)>
                <div class="none_display_link_div">
                    <a href="${base}/${pageLink}?pageNo=${paginationUsers.pageNo?c}" class="page_url"></a>
                </div>
                <div class="content_none_border_div">
                    <div class="none_border_block">
                        <span class="page_order_inline_span">
                            <#assign sizePerPages = {"5":"5", "10":"10", "15":"15", "20":"20", "25":"25", "30":"30", "40":"40", "50":"50", "100":"100", "150":"150", "200":"200"}>
                            Page Size: <@sf.select path="sizePerPage" class="input_select_small" id="item_select_size">
                            <#list sizePerPages?keys as key>
                                <#if sizePerPage??&&sizePerPage?c == key>
                                    <@sf.option selected="selected" value="${key}">${sizePerPages[key]}</@sf.option>
                                <#else>
                                    <@sf.option value="${key}">${sizePerPages[key]}</@sf.option>
                                </#if>
                            </#list>
                        </@sf.select>
                            <#assign orderBys = {"displayName":"display name", "registeredDate":"registered date"}>
                            &nbsp;Sorted by: <@sf.select path="orderBy" cssClass="input_select_small" id="item_select_order" >
                            <#list orderBys?keys as key>
                                <#if orderBy??&&orderBy == key>
                                    <@sf.option selected="selected" value="${key}">${orderBys[key]}</@sf.option>
                                <#else>
                                    <@sf.option value="${key}">${orderBys[key]}</@sf.option>
                                </#if>
                            </#list>
                        </@sf.select>
                            <#assign orderByTypes = {"asc":"asc", "desc":"desc"}>
                            &nbsp;Ordered by: <@sf.select path="orderByType" class="input_select_small" id="item_select_otype">
                            <#list orderByTypes?keys as key>
                                <#if orderByType??&& orderByType == key>
                                    <@sf.option selected="selected" value="${key}">${orderByTypes[key]}</@sf.option>
                                <#else>
                                    <@sf.option value="${key}">${orderByTypes[key]}</@sf.option>
                                </#if>
                            </#list>
                        </@sf.select>
                        </span>
                    </div>
                </div>
                <div class="content_none_border_div">
                    <table class="display_data_tab">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Email</th>
                            <th width="25%">Organization</th>
                            <th width="10%">User Type</th>
                            <th width="10%">Active Status</th>
                            <th width="15%">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list paginationUsers.pageResults as userResult>
                            <tr>
                                <td>
                                ${userResult.displayName}
                                </td>
                                <td>
                                ${userResult.email}
                                </td>
                                <td>
                                ${userResult.profile.organization}
                                </td>
                                <td>
                                    <#if (userResult.userType == 1)>
                                        Super Admin
                                    <#elseif (userResult.userType == 2)>
                                        Admin
                                    <#else>
                                        User
                                    </#if>
                                </td>
                                <td>
                                ${userResult.activated?c}
                                </td>
                                <td>
                                    <div class="tab_link">
                                        <#if (authen_user_type??&&authen_user_type == 1 || authen_user_type??&&authen_user_type == 2)>
                                            <#if (authen_user_id != userResult.id) && (userResult.userType !=1)>
                                                <a href="${base}/admin/manage_user.htm?id=${userResult.id?c}">Manage</a>
                                            <#else>
                                                <a href="${base}/manage/view_user.htm?id=${userResult.id?c}">View</a>
                                            </#if>
                                        <#else>
                                            <a href="${base}/manage/view_user.htm?id=${userResult.id?c}">View</a>
                                        </#if>
                                    </div>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <div class="blank_separator"></div>
                <div class="pagination_div">
                    <#include "../pager/pag_style.ftl" />
                </div>
            </#if>
                <br/>
            </div>
        </div>
        <!-- right panel -->
        <div class="right_display_div">
        <#if authen_flag?? && authen_flag == 'authenticated'>
            <#include "../template/sub_nav.ftl" />
        </#if>
        </div>
    </div>
</div>
</div>
<div style="clear:both"></div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>