<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "user.registration.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "user.registration.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
                <div class="reg_panel">
                    <div class="hints_panel">
                        <img src="${base}/images/warn.png"/> &nbsp;<@s.message "user.reg.options.message" />
                    </div>
                    <div class="reg_options_middle">
                        <div class="blank_separator"></div>
                        <div class="reg_choices">
                            <img src="${base}/images/reg_mon.png" /> <a href="${base}/user/ldap_register.htm"><strong><@s.message "user.ldap.register.action.title" /></strong></a>
                        </div>
                        <div style="clear:both"></div>
                        <div class="blank_separator"></div>
                        <br/>
                        <br/>

                        <div class="blank_separator"></div>
                        <div class="reg_choices">
                            <img src="${base}/images/reg_self.png" /> <a href="${base}/user/self_register.htm"> <strong><@s.message "user.register.action.title" /></strong></a>
                        </div>
                        <div style="clear:both"></div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
        <!-- right panel -->
        <div class="right_display_div">
            &nbsp;
        </div>
    </div>
</div>
</div>
<div style="clear:both"></div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>