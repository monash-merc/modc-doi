<!DOCTYPE html>
<html>
<head>
<#include "../template/header.ftl"/>
    <title>File Upload</title>
    <script type="text/javascript">
        function doAfterImport(success) {
            if (success) {
               //do something here -- refresh current page etc.
            }
        }
    </script>
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
                <h3> Please choose a file to upload</h3>
                <!-- File importing message block -->
                <div class="content_none_border_div">
                    <div class="file_success_msg_div">
                        <p id="file_success_msg">Some text</p>
                    </div>

                    <div class="file_error_msg_div">
                        <div class="file_error_msg_item_div">
                        </div>
                    </div>
                </div>

                <!-- file uploading progress messages -->
                <div class="content_none_border_div">
                    <div class="file_uploading_div">
                        <div id="ajaxfileupload">
                            <div id="fileuploadProgress">
                                <div id="uploadFilename">Initialising, please wait.....</div>
                                <div id="progress-bar">
                                    <div id="progress-bgrd"></div>
                                    <div id="progress-text"></div>
                                </div>
                                <br/>
                            </div>
                            <span id="message"></span>
                        </div>
                    </div>
                </div>
                <div id="fileuploadForm">
                    <form method="post" action="upload.jspx" enctype="multipart/form-data" id="ajaxFileUploadForm" onsubmit="return false">
                        <input type="text" name="name" id="uploadName"/>
                        <input type="file" name="file" id="upload" />
                        <input type="submit" value="Upload" id="fileUpload" name="fileUpload" onclick="merc.FileUpload.initialize(doAfterImport);" class="input_button_style"/>
                    </form>
                </div>
                <br/>
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