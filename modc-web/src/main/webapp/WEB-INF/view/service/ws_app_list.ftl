<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "webservice.list.apps.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "webservice.list.apps.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
            <#include "../template/action_message.ftl" />

                <div class="none_border_block_norm">
                    <div class="none_border_inner_left">
                        <span class="name_title">Total <span class="span_number"> ${paginationObjs.totalSize} </span> Web Service Apps</span>
                    </div>
                    <div class="none_border_inner_right">
                        <span class="page_order_inline_span">
                            <#assign serviceTypes = {"doi":"doi ws", "md":"metadata ws"}>
                                &nbsp;Filter by: <@sf.select path="serviceType" cssClass="input_select_small" id="item_select_service_type" >
                                <@sf.option value="none"> -- select -- </@sf.option>
                                <#list serviceTypes?keys as key>
                                    <#if serviceType??&&serviceType == key>
                                        <@sf.option selected="selected" value="${key}">${serviceTypes[key]}</@sf.option>
                                    <#else>
                                        <@sf.option value="${key}">${serviceTypes[key]}</@sf.option>
                                    </#if>
                                </#list>
                            </@sf.select>
                        </span>
                        <span class="add_new_span"> <a href="${base}/service/new_ws_app.htm"> <img src="${base}/images/add_a.png" /> Add New App</a></span>
                     </div>
                    <div style="clear:both"></div>
                </div>

                <div class="none_display_link_div">
                    <a href="${base}/${pageLink}?pageNo=${paginationObjs.pageNo?c}" class="page_url"></a>
                </div>
            <#if (paginationObjs?? && paginationObjs.pageResults?size > 0)>

                <div class="content_none_border_div">
                    <table class="display_data_tab">
                        <thead>
                        <tr>
                            <th width="15%">Name</th>
                            <th width="10%">Service Type</th>
                            <th width="25%">App ID</th>
                            <th width="20%">App Password</th>
                            <th width="20%">Description</th>
                            <th width="10%"></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list paginationObjs.pageResults as obj>
                            <tr>
                                <td>
                                ${obj.name}
                                </td>
                                <td>
                                ${obj.serviceType}
                                </td>
                                <td>
                                ${obj.uniqueId}
                                </td>
                                <td>
                                ${obj.authCode!}
                                </td>
                                <td>
                                ${obj.description}
                                </td>
                                <td>
                                    <div class="tab_link">
                                        <a href="${base}/service/ws_app.htm?id=${obj.id}"> View </a>
                                    </div>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <div class="blank_separator"></div>
                <div class="pagination_div">
                    <#include "../pager/pag_style2.ftl" />
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