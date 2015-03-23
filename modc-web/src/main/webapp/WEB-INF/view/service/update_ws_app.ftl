<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "webservice.update.ws.app.title" /></title>
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
    <div class="page_title_inline"><@s.message "webservice.update.ws.app.title" /></div>
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
            <@sf.form action="update_ws_app.htm" commandName="serviceApp" method="post">
                <@sf.hidden path="id" />
                <@sf.hidden path="uniqueId" />
                <div class="input_row_section">
                    <div class="input_row_left_part">App Name:</div>
                    <div class="input_row_right_part">
                        <@sf.input path="name" /> <span class="red_span"> *</span>
                    </div>
                    <div style="clear:both"></div>
                    <div class="input_row_comments">
                        <@s.message "webservice.app.name.comment" />
                    </div>
                </div>
                <div style="clear:both"></div>

                <!-- creators -->
                <div class="input_row_section">
                    <div class="input_row_left_part">App Id:</div>
                    <div class="input_row_right_part">
                    ${serviceApp.uniqueId}
                    </div>
                </div>
                <!-- service app id -->
                <div class="input_row_section">
                    <div class="input_row_left_part">App Password:</div>
                    <div class="input_row_right_part">
                        <@sf.input path="authCode" id="auth_code"/> <span class="red_span"> *</span> <div class="input_button_style3" id="app_pwd"> Generate password </div>
                    </div>
                    <div style="clear:both"></div>
                    <div class="input_row_comments">
                        <@s.message "webservice.app.password.comment" />
                    </div>
                </div>

                <!-- creators -->
                <div class="input_row_section">
                    <div class="input_row_left_part">Service Type:</div>
                    <div class="input_row_right_part">
                        <@sf.select path="serviceType" class="input_select_small">
                            <@sf.option value="none"> --- Select --- </@sf.option>
                            <@sf.option value="doi">doi</@sf.option>
                            <@sf.option value="md">metadata</@sf.option>
                        </@sf.select> <span class="red_span"> *</span>
                    </div>
                    <div style="clear:both"></div>
                    <div class="input_row_comments">
                        <@s.message "webservice.app.service.type.comment" />
                    </div>
                </div>

                <!-- doi mint prefix  -->
                <div class="input_row_section">
                    <div class="input_row_left_part">DOI Minting Prefix</div>
                    <div class="input_row_right_part">
                        <@sf.select path="authorizedApp.id"   class="input_select_small">
                            <@sf.option value="-1"> --- Select --- </@sf.option>
                            <@sf.options items = authorizedApps itemValue = "id" itemLabel = "appName" />
                        </@sf.select> <span class="red_span"> *</span>
                    </div>
                    <div style="clear:both"></div>
                    <div class="input_row_comments">
                        <@s.message "webservice.app.doi.mint.prefix.comment" />
                    </div>
                </div>


                <div class="input_row_section">
                    <div class="input_row_left_part">App Description:</div>
                    <div class="input_row_right_part">
                        <@sf.textarea path="description" cssStyle="width: 400px; height: 80px;" cssClass="input_textarea" /> <span class="red_span"> *</span>
                    </div>
                    <div style="clear:both"></div>
                    <div class="input_row_comments">
                        <@s.message "webservice.app.desc.comment" />
                    </div>
                </div>

                <div style="clear:both"></div>
                <div class="input_row_section">
                    <div class="input_row_left_part">&nbsp;</div>
                    <div class="input_row_right_part">
                        <input type="submit" value="Update" class="input_button_style"/>
                    </div>
                </div>
                <div style="clear:both"></div>
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