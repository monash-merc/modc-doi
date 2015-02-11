<!DOCTYPE html>
<html>
<head>
<#include "template/header.ftl"/>
    <title><@s.message "home.action.title" /></title>
</head>
<body>
<!-- Navigation Section -->
<#include "template/nav.ftl" />
<!-- Navigation Title -->
<div class="page_title">
    <div class="page_title_inline">&nbsp;</div>
    <div class="page_title_inline"><img src="${base}/images/link_arrow.png" border="0"/></div>
    <div class="page_title_inline"><@s.message "home.action.title" /></div>
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
                        The digital object identifier (DOI) system provides a persistent and unique identifier for datasets and research publications. A DOI is permanently assigned to datasets and
                        publications to provide information about them, including where they or information about them can be found on the Internet. The DOI will not change even if information about
                        the
                        datasets changes over time. The system was originally established by a consortium of academic publishers to ensure the persistence of links to journal articles. Please follow
                        this
                        link to view a DOI record: <a href="http://dx.doi.org/10.2196/mhealth.3314" target="_blank">http://dx.doi.org/10.2196/mhealth.3314</a>
                    </p>

                    <p>
                        <a href="https://www.datacite.org/" target="_blank">DataCite</a> is an international organisation which aims to establish easier access to research data. <a
                            href="https://www.datacite.org/" target="_blank">DataCite</a>
                        does not allocate DOIs; this is done by its member agencies, in our
                        case
                        Monash University mints DOIs through DataCite member the <a href="http://www.ands.org.au/" target="_blank">Australian National Data Service</a>. Assigning a DOI to data
                        facilitates citation of
                        that data and is the gold standard of
                        identifiers in academic publishing. Its use demonstrates that the dataset will be well managed and accessible for the long-term. Staff at Monash University Library will ensure
                        that
                        all details pertaining to your research are kept up to date, thereby ensuring that your work can continue to be accurately cited and discovered.
                    </p>

                    <p>
                        DOIs also allow citations to be measured which quantifies the usage and impact of cited work.
                    </p>

                    <p>
                        Other benefits of implementing a DOI include:
                    </p>

                    <ul>
                        <li>Knowing what you have;</li>
                        <li>Finding what you want;</li>
                        <li>Knowing where it exists;</li>
                        <li>Being able to access it.</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- right panel -->
        <div class="right_display_div">
        <#if authen_flag?? && authen_flag == 'authenticated'>
            <#include "template/sub_nav.ftl" />
        </#if>
        </div>
    </div>
</div>
</div>
<div style="clear:both"></div>
</div>
<#include "template/footer.ftl"/>
</body>
</html>