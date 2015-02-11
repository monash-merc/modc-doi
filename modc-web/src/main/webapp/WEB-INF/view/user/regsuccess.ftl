<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title>User Register</title>
</head>
<body>
<!-- Navigation Section -->
<#include "../template/nav.ftl" />
<div style="clear:both"></div>
<div class="main_body_div">
    <div class="main_middle_div">

        <!-- left panel -->
        <div class="left_display_div">
            <div style="clear:both"></div>
            <div class="left_display_inner">
                <h3>User Registration</h3>

                <h1>Registration has been completed with following details :</h1><br/>
                Name: ${user.firstName} ${user.lastName}.<br/>
                Email : ${user.email}.

            </div>
        </div>
        <!-- right panel -->
        <div class="right_display_div">
            &nbsp;
        </div>
    </div>
</div>
</div>
<div style="clear:both"></div>
</div>
<#include "../template/footer.ftl"/>
</body>
</html>