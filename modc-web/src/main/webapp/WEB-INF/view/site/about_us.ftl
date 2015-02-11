<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title><@s.message "about.us.action.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "about.us.action.title" /></div>
</div>
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
                <div class="content_none_border_div">
                    <p>
                        The digital object identifier (DOI) Service was built by Monash eResearch Centre as part of the ANDS funded Monash University Major Open Data Collections Project.
                        The DOI service provides a persistent and unique identifier for datasets and research publications.
                    </p>

                    <p>
                        For any queries please contact:
                        <br/>
                        <br/>

                        Research Data Management Coordinator
                        <br/>
                        <a href="mailto:researchdata@monash.edu">researchdata@monash.edu</a>
                    </p>
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