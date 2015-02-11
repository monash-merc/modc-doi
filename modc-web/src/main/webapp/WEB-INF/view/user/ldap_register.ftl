<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "user.registration.title" /></title>
    <script type="text/javascript">
        function refresh() {
            document.getElementById("captcha_img").src = '${base}/captcha/secode.htm?now=' + new Date();
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
    <div class="page_title_inline"><@s.message "user.registration.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
            <#include "../template/action_message.ftl" />
                <div class="reg_panel">
                <@sf.form action="ldap_register.htm" commandName="registration" method="post">
                    <div class="reg_middle_panel">
                        <div class="input_field_row">
                            <div class="input_field_title">
                                <@s.message "user.authcateId" />:
                            </div>
                            <div class="input_field_value_section">
                                <@sf.input path="user.uniqueId" />
                                <div class="comments"><@s.message "user.ldap.reg.authcateId.hint" /></div>
                            </div>
                        </div>
                    </div>
                    <div class="reg_middle_panel">
                        <div class="input_field_row">
                            <div class="input_field_title">
                                <@s.message "user.password" />:
                            </div>
                            <div class="input_field_value_section">
                                <@sf.password path="user.password" />
                                <div class="comments"><@s.message "user.ldap.reg.authcate.password.hint" /></div>
                            </div>
                        </div>
                    </div>
                    <div class="reg_middle_panel">
                        <div class="input_field_row">
                            <div class="input_field_title">
                                <@s.message "security.code" />:
                            </div>
                            <div class="input_field_value_section">
                                <@sf.input path="securityCode" />
                                <div class="comments"><@s.message "security.code.hint" /></div>
                            </div>
                        </div>
                    </div>
                    <div class="input_field_row">
                        <div class="input_field_title">
                            &nbsp;
                        </div>
                        <div class="input_field_value_section">
                            <div class="captch_div">
                                <img src="${base}/captcha/secode.htm" id="captcha_img" name="captcha_img"/>
                                <a href="#" onclick="refresh()"> &nbsp;<img src="${base}/images/refresh.png" class="img_position"/> <span class="inline_span">can't read this?</span></a>
                            </div>
                        </div>
                    </div>

                    <div class="blank_separator"></div>
                    <div class="blank_separator"></div>
                    <div class="input_field_row">
                        <div class="input_field_title">
                            &nbsp;
                        </div>
                        <div class="input_field_value_section">
                            <input type="submit" value="Register" class="input_button_style" /> &nbsp; <input type="reset" value="Reset" class="input_button_style" />
                            <span class="inline_span"> If you already have an account, please <a href="${base}/user/user_login.htm">Sign in now </a></span>
                        </div>
                    </div>
                </@sf.form>
                </div>
                <div style="clear:both"></div>
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