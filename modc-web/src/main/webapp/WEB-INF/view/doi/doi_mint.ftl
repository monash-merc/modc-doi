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

    <!-- @sf.form action="register.do" method="post" -->
<@sf.form action="mint.htm" commandName="doiResource" method="post">

    <div class="dinput_title_section">
        DOI Resource &nbsp; <span class="inline_span">(Fields marked with <span class="red_span"> * </span>  are mandatory)</span>
    </div>
    <div style="clear:both"></div>

    <div class="dinput_row_section">
        <div class="dfield_left_part">URL:</div>
        <div class="dfield_right_part">
            <@sf.input path="url" cssClass="input_big" /> <span class="red_span"> *</span>
        </div>
        <div style="clear:both"></div>
        <div class="dfield_comments">
            <@s.message "comment.doi.url" />
        </div>
    </div>
    <div style="clear:both"></div>

    <!-- creators -->
    <div class="dinput_row_section">
        <div class="dfield_left_part">Creators:</div>
        <div class="dfield_right_part">
            Add Creator <img class="dfield_img" src="${base}/images/add.png" border="0" id="add_creator"/>
        </div>
        <div style="clear:both"></div>
        <div class="dfield_comments">
            <@s.message "comment.doi.creators" />
        </div>
    </div>

    <div class="dinput_row_section">
        <div class="dfield_left_part">&nbsp;</div>
        <div class="dfield_right_part">
            <div class="dfield_inner">
                <table class="dfield_tab" id="add_creator_tab">
                    <tbody>
                        <#if (doiResource.doiCreators?? && doiResource.doiCreators?size > 0)>
                            <#list doiResource.doiCreators as doiCreator>
                            <tr>
                                <td>Creator:</td>
                                <td>
                                    <div class="dvalue_row_div">
                                        <span class="dlabel_fixed_span">Creator Name: </span> <@sf.input path="doiCreators[${doiCreator_index}].creatorName" id="creatorName" />
                                        <span class="red_span"> *</span>
                                        <span class="dcomment_span">Required. Format: Family, Given.</span>
                                    </div>
                                    <div class="dvalue_row_div">
                                        <span class="dlabel_fixed_span">Name Identifier: </span> <@sf.input path="doiCreators[${doiCreator_index}].nameIdentifier.identifier" id="identifier" />
                                        <span class="dcomment_span">Optional, eg: ISNI identifier or ORCID.</span>
                                    </div>
                                    <div class="dvalue_row_div">
                                        <span class="dlabel_fixed_span">Name Idenitifer Scheme</span> <@sf.input path="doiCreators[${doiCreator_index}].nameIdentifier.nameIdentifierScheme" type="text" class="dinput_small" id="nameIdentifierScheme"/>
                                        <span class="dcomment_span">Optional, eg: ISNI or ORCID.</span>
                                    </div>
                                </td>
                                <td width="50"><img class="dfield_img" src="${base}/images/delete.png" title='remove' id="remove_creator"/></td>
                            </tr>
                            </#list>
                        <#else>
                        <tr>
                            <td>Creator:</td>
                            <td>
                                <div class="dvalue_row_div"><span class="dlabel_fixed_span">Creator Name: </span> <@sf.input path="doiCreators[0].creatorName" id="creatorName" />
                                    <span class="red_span"> *</span>
                                    <span class="dcomment_span">Required. Format: Family, Given.</span>
                                </div>
                                <div class="dvalue_row_div">
                                    <span class="dlabel_fixed_span">Name Identifier: </span> <@sf.input path="doiCreators[0].nameIdentifier.identifier" id="identifier" />
                                    <span class="dcomment_span">Optional, eg: ISNI identifier or ORCID.</span>
                                </div>
                                <div class="dvalue_row_div">
                                    <span class="dlabel_fixed_span">Name Indenitifer Scheme</span> <@sf.input path="doiCreators[0].nameIdentifier.nameIdentifierScheme" type="text" class="dinput_small" id="nameIdentifierScheme"/>
                                    <span class="dcomment_span">Optional, eg: ISNI or ORCID.</span>
                                </div>
                            </td>
                            <td width="50"><img class="dfield_img" src="${base}/images/delete.png" title='remove' id="remove_creator"/></td>
                        </tr>
                        </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div style="clear:both"></div>

    <div class="dinput_row_section">
        <div class="dfield_left_part">Titles:</div>
        <div class="dfield_right_part">
            Add Title <img class="dfield_img" src="${base}/images/add.png" border="0" id="add_title"/>
        </div>
        <div style="clear:both"></div>
        <div class="dfield_comments">
            <@s.message "comment.doi.title" />
        </div>
    </div>

    <div class="dinput_row_section">
        <div class="dfield_left_part">&nbsp;</div>
        <div class="dfield_right_part">
            <div class="dfield_inner">
                <table class="dfield_tab" id="add_title_tab">
                    <tbody>
                        <#if (doiResource.titles?? && doiResource.titles?size >0)>
                            <#list doiResource.titles as title >
                            <tr>
                                <td>Title:</td>
                                <td>
                                    <div class="dvalue_row_div"><@sf.input path="titles[${title_index}].title" id="title" />
                                        <span class="red_span"> *</span>
                                        <span class="dcomment_span">Required</span>
                                    </div>
                                    <div class="dvalue_row_div">
                                        <@sf.select path="titles[${title_index}].titleType" id="titleType" cssClass="input_select_field">
                                            <@sf.option value="none"> --- Select --- </@sf.option>
                                            <@sf.option value="AlternativeTitle">Alternative Title</@sf.option>
                                            <@sf.option value="Subtitle">Subtitle</@sf.option>
                                            <@sf.option value="TranslatedTitle">Translated Title</@sf.option>
                                        </@sf.select>
                                        <span class="dcomment_span">Optional</span></div>
                                </td>
                                <td width="50"><img class="dfield_img" src="${base}/images/delete.png" id="remove_title"/></td>
                            </tr>
                            </#list>
                        <#else>
                        <tr>
                            <td>Title:</td>
                            <td>
                                <div class="dvalue_row_div"><@sf.input path="titles[0].title" id="title" />
                                    <span class="red_span"> *</span>
                                    <span class="dcomment_span">Required</span></div>
                                <div class="dvalue_row_div">
                                    <@sf.select path="titles[0].titleType" id="titleType"  cssClass="input_select_field">
                                        <@sf.option value="none"> --- Select --- </@sf.option>
                                        <@sf.option value="AlternativeTitle">Alternative Title</@sf.option>
                                        <@sf.option value="Subtitle">Subtitle</@sf.option>
                                        <@sf.option value="TranslatedTitle">Translated Title</@sf.option>
                                    </@sf.select>
                                    <span class="dcomment_span">Optional</span>
                                </div>
                            </td>
                            <td width="50"><img class="dfield_img" src="${base}/images/delete.png" id="remove_title"/></td>
                        </tr>
                        </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div style="clear:both"></div>

    <div class="dinput_row_section">
        <div class="dfield_left_part">Publisher:</div>
        <div class="dfield_right_part">
            <@sf.input path="publisher.publisher" />
            <span class="red_span"> *</span>
        </div>
        <div class="dfield_comments">
            <@s.message "comment.doi.publisher" />
        </div>
    </div>
    <div style="clear:both"></div>

    <div class="dinput_row_section">
        <div class="dfield_left_part">Publication Year:</div>
        <div class="dfield_right_part">
            <@s.bind "publicationYears" />
            <@s.formSingleSelect "doiResource.publicationYear", publicationYears "class='input_select_field'" /> <span class="red_span"> *</span>
        </div>
        <div class="dfield_comments">
            <@s.message "comment.doi.publication.year" />
        </div>
    </div>
    <div style="clear:both"></div>

    <div class="dinput_title_section">
        <!-- DOI resource fields (optional) --> &nbsp;
    </div>
    <br/>

    <div style="clear:both"></div>
    <div class="dinput_row_section">
        <div class="dfield_left_part">&nbsp;</div>
        <div class="dfield_right_part">
            <input type="submit" value="Mint DOI" class="input_button_style" id="wait_modal"/>
        </div>
        <div id='mask'></div>
        <div id='modal_window'>
            Calling DOI Minting Service, please wait ... <img src="${base}/images/wait_loader.gif" class="loading_image">
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