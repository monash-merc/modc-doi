<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "admin.activate.account.action.title" /></title>
    <script>
        function reject() {
            targetForm = document.forms[0];
            targetForm.action = "reject.htm";
            targetForm.submit();
        }
    </script>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "admin.activate.account.action.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
            <#include "../template/action_message.ftl" />
            <@sf.form  action="activate.htm" commandName="registration" method="post">
                <@sf.hidden path="user.id" />
                <@sf.hidden path="user.displayName" />
                <@sf.hidden path="user.activateHashCode" />
                <@sf.hidden path="user.email" />
                <@sf.hidden path="profile.organization" />
                <div class="reg_middle_panel">
                    <div class="input_field_row">
                        <div class="input_field_title">
                            <@s.message "user.displayName" />:
                        </div>
                        <div class="input_field_value_section">
                            ${registration.user.displayName}
                        </div>
                    </div>
                </div>
                <div class="reg_middle_panel">
                    <div class="input_field_row">
                        <div class="input_field_title">
                            <@s.message "user.email" />:
                        </div>
                        <div class="input_field_value_section">
                        ${registration.user.email}
                        </div>
                    </div>
                </div>
                <div class="reg_middle_panel">
                    <div class="input_field_row">
                        <div class="input_field_title">
                            <@s.message "user.organization" />:
                        </div>
                        <div class="input_field_value_section">
                        ${registration.profile.organization}
                        </div>
                    </div>
                </div>
                <div class="reg_middle_panel">

                    <div class="input_field_row">
                        <div class="input_field_title">
                            &nbsp;
                        </div>
                        <div class="input_field_value_section">
                            <input type="submit" value="Activate" class="input_button_style"/>
                            &nbsp; <input type="reset" value="Reject" onclick="reject();" class="input_button_style"/>
                        </div>
                    </div>
                </div>
            </@sf.form>
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