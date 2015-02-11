<div class="user_avatar_div">
    <img src="${base}/${authen_user_avatar}">
</div>
<div style="clear: both;"></div>
<div class="user_info_div">
<#if (authen_user_type??&&authen_user_type == 1)>
    Super Admin &nbsp;
<#elseif (authen_user_type??&&authen_user_type == 2)>
    Admin &nbsp;
</#if>
    ${authen_user_name}
</div>
<div style="clear: both;"></div>