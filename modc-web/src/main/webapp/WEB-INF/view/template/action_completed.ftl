<#if (actionMessages?? && actionMessages?size > 0)>
<div class="action_complete">
    <#list actionMessages as actMessage>
        <#if actMessage??>
            <span class="actMessage">${actMessage}</span>
            <br/>
        </#if>
    </#list>
</div>
</#if>