<!-- START OF Logo and Menu Section -->
<div class="site_top">
    <br/>

    <div class="site_top_left">
        <div class="menu_logo">
            <a href="http://monash.edu.au/library" target="_blank"><img src="${base}/images/logo/logo_monash_library.png"/></a>
        </div>
    </div>
    <div class="site_top_right">
        <div class="user_login">
        <#if authen_flag?? && authen_flag == 'authenticated'>
            <a href="${base}/user/user_logout.htm">Logout</a>
        <#else>
            <a href="${base}/user/user_login.htm">Login</a> <a href="${base}/user/registration_options.htm">Register</a>
        </#if>
        </div>
    </div>
</div>
<div style="clear: both;"/>
</div>

<!-- Navigations -->
<div class="nav_div">
    <div class="nav_menu">
        <ul>
            <li><a href="${base}/home.htm">Home</a></li>
            <!-- li><a href="#">Collections</a></li -->
        <#if authen_flag?? && authen_flag == 'authenticated'>
            <li><a href="${base}/doi/show_mint.htm">DOI</a></li>
            <#if (authen_user_type??&&(authen_user_type == 1 ||authen_user_type == 2))>
                <li><a href="${base}/service/ws_app_list.htm">Webservice App</a></li>
            </#if>
            <li><a href="${base}/manage/list_users.htm">Users</a></li>
        </#if>
            <li><a href="${base}/site/about_us.htm">About Us</a></li>
        </ul>
    </div>
</div>
<div style="clear:both"></div>
<!-- End of Nav -->