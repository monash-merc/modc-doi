<#if (actionErrors?? && actionErrors?size > 0)>
<div class="actionError">
    <ul>
        <#list actionErrors as error>
            <#if error?if_exists !="">
                <li><span class="errorMessage">${error}</span></li>
            </#if>
        </#list>
    </ul>
</div>
</#if>

<#if (actionMessages?? && actionMessages?size > 0)>
<div class="actionMessage">
    <ul>
        <#list actionMessages as actMessage>
            <#if actMessage??>
                <li><span class="actMessage">${actMessage}</span></li>
            </#if>
        </#list>
    </ul>
</div>
</#if>