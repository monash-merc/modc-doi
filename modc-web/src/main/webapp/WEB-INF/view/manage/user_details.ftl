<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "user.show.details.action.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><a href="${base}/manage/list_users.htm"><@s.message "user.all.users.action.title" /></a></div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "user.show.details.action.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
            <#include "../template/action_message.ftl" />
                <div class="content_display_div">
                    <div class="user_details_left">
                        <br/>
                        <br/>
                        <img src="${base}/${userBean.avatar.fileName}">
                    </div>
                    <div class="user_details_right">
                        <div class="input_field_row">
                            <div class="input_field_title">
                                User name:
                            </div>
                            <div class="input_field_value_section">
                            ${userBean.user.displayName}
                            </div>
                        </div>
                        <div class="input_field_row">
                            <div class="input_field_title">
                                Gender:
                            </div>
                            <div class="input_field_value_section">
                            ${userBean.profile.gender}
                            </div>
                        </div>
                        <div class="input_field_row">
                            <div class="input_field_title">
                                Date since joined:
                            </div>
                            <div class="input_field_value_section">
                            ${userBean.user.registeredDate?string["yyyy-dd-MM"]}
                            </div>
                        </div>
                        <div class="input_field_row">
                            <div class="input_field_title">
                                Email:
                            </div>
                            <div class="input_field_value_section">
                            ${userBean.user.email}
                            </div>
                        </div>
                        <div class="input_field_row">
                            <div class="input_field_title">
                                Active status:
                            </div>
                            <div class="input_field_value_section">
                            ${userBean.user.activated?c}
                            </div>
                        </div>
                        <div class="input_field_row">
                            <div class="input_field_title">
                                User type:
                            </div>
                            <div class="input_field_value_section">
                            <#if (userBean.user.userType == 1)>
                                Super Admin
                            <#elseif (userBean.user.userType == 2)>
                                Admin
                            <#else>
                                User
                            </#if>
                            </div>
                        </div>
                        <div class="input_field_row">
                            <div class="input_field_title">
                                Organization:
                            </div>
                            <div class="input_field_value_section">
                            ${userBean.profile.organization}
                            </div>
                        </div>
                        <div style="clear:both"></div>
                    </div>
                </div>
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