<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title>DOI Miniting Service</title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />

<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "doi.mint.title" /></div>
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

                <div class="dinput_title_section2">
                    Minting DOI Details:
                </div>
                <div class="content_display_div">
                <#if doiResponse??>
                    <div class="dinput_row_section">
                        <div class="dfield_left_part">Status:</div>
                        <div class="dfield_right_part">
                            <div class="doi_response_norm_div">
                            ${doiResponse.type}
                            </div>
                        </div>
                    </div>
                    <div class="dinput_row_section">
                        <div class="dfield_left_part">Response Code:</div>
                        <div class="dfield_right_part">
                            <div class="doi_response_norm_div">
                            ${doiResponse.responseCode}
                            </div>
                        </div>
                    </div>
                    <div class="dinput_row_section">
                        <div class="dfield_left_part">DOI:</div>
                        <div class="dfield_right_part">
                            <div class="doi_response_blue_div">
                            ${doiResponse.doi}
                            </div>
                        </div>
                    </div>
                    <div class="dinput_row_section">
                        <div class="dfield_left_part">URL:</div>
                        <div class="dfield_right_part">
                            <div class="doi_response_norm_div">
                            ${doiResponse.url}
                            </div>
                        </div>
                    </div>
                    <div class="dinput_row_section">
                        <div class="dfield_left_part">Message:</div>
                        <div class="dfield_right_part">
                            <div class="doi_response_norm_div">
                            ${doiResponse.message}
                            </div>
                        </div>
                    </div>
                <#else>
                    <div class="dinput_row_section">
                        <div class="dfield_left_part">&nbsp;</div>
                        <div class="dfield_right_part">
                            None Response Found
                        </div>
                    </div>
                </#if>
                </div>
                <div style="clear:both"></div>
                <div class="dinput_title_section2">
                    &nbsp;
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