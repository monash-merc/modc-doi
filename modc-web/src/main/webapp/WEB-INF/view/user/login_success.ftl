<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "user.login.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "user.login.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
                <div class="redirect_pane">
                    <br/>
                    <b>${authen_user_name}</b> &nbsp;&nbsp; Logged in successfully.&nbsp;&nbsp; Welcome to <b>${applicationName}</b>!
                    <br/>
                    <br/>
                    <span class="redirect_span">After a few seconds, the page will redirect ...</span>
                    <br/>
                    <br/>
                    <span class="redirect_span">Problems with the redirect? Please use this <a href='${base}${requestedUrl}'>direct link</a>.</span>
                    <br/>
                    <br/>
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

<script>
    function jump() {
        location.href = '${base}${requestedUrl}';
    }
    setTimeout("jump()", 3000);
</script>
</body>
</html>