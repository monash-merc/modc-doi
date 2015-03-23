<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "webservice.ws.app.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><a href="${base}/service/ws_app_list.htm"><@s.message "webservice.list.apps.title" /></a></div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "webservice.ws.app.title" /></div>
</div>
<div style="clear:both"></div>
<!-- End of Navigation Title -->

<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
            <#include "../template/action_message.ftl" />
                <div class="input_row_section">
                    <div class="input_row_left_part">App Name:</div>
                    <div class="input_row_right_part">
                    ${serviceApp.name}
                    </div>
                </div>
                <div style="clear:both"></div>

                <!-- app id -->
                <div class="input_row_section">
                    <div class="input_row_left_part">App Id:</div>
                    <div class="input_row_right_part">
                    ${serviceApp.uniqueId}
                    </div>
                </div>

                <!-- app id -->
                <div class="input_row_section">
                    <div class="input_row_left_part">App Password:</div>
                    <div class="input_row_right_part">
                    <#if serviceApp.authCode??>
                        ${serviceApp.authCode}
                    <#else>
                        &nbsp;
                    </#if>

                    </div>
                </div>

                <!-- type -->
                <div class="input_row_section">
                    <div class="input_row_left_part">Service Type:</div>
                    <div class="input_row_right_part">
                    ${serviceApp.serviceType}
                    </div>
                </div>

                <!-- doi mint prefix -->
                <div class="input_row_section">
                    <div class="input_row_left_part">DOI Minting Prefix:</div>
                    <div class="input_row_right_part">
                        ${serviceApp.authorizedApp.appName}
                    </div>
                </div>

                <div class="input_row_section">
                    <div class="input_row_left_part">App Description:</div>
                    <div class="input_row_right_part">
                    ${serviceApp.description}
                    </div>
                </div>

                <div style="clear:both"></div>
                <div class="input_row_section">
                    <div class="input_row_left_part">&nbsp;</div>
                    <div class="input_row_right_part">
                        <div class="data_action_link">
                            <a href="${base}/service/update_ws_app.htm?id=${serviceApp.id?c}"> Update </a>
                        </div>
                        <div class="data_action_link">
                            <a href="${base}/service/delete_ws_app.htm?id=${serviceApp.id?c}"> Delete </a>
                        </div>
                    </div>
                </div>
                <div style="clear:both"></div>
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