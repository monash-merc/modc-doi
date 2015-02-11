<#ftl strip_whitespace=true>

<#macro errors>
<div class="actionError">
    display error!
    <ul>
    <#if (status.errorMessages?? && status.errorMessages?size > 0)>
        <#list status.errorMessages as error>
            <#if error?if_exists !="">
                <li><span class="errorMessage">${error}</span></li>
            <#else>
                <li><span class="errorMessage">${error}</span></li>
            </#if>
        </#list>
    </#if>
    </ul>
</div>
</#macro>