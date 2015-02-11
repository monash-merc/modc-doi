/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//merc namespace
var merc = {};
merc.FileUpload = function (doafter) {

    // Global variable needed to monitor upload progress
    var uploadStartTime = new Date().getTime() / 1000; // seconds start time
    // //time it started
    var totalFileSize = 0; // size of file
    var updateRate = 1000;
    var lastProgressUpdate = uploadStartTime; // last time the data changed
    var lastUploadedSize = -1; // current upload size
    var barwidth = 450; // size of the progress bar
    var fileName;
    var initialisingTxt = "File uploading, please wait.....";
    var timeoutId;

    // GUI Elements
    var fileNameTxt = jQuery("#uploadFilename");
    var progressBarDiv = jQuery("#progress-bar");
    var progressBarTextDiv = jQuery("#progress-text");
    var progressAreaDiv = jQuery("#fileuploadProgress");
    var progressBarGraphic = jQuery("#progress-bgrd");

    var theSubmitButton = jQuery("#fileUpload input[type='submit']");


    // file importing message div block
    var fileSuccessMsgDiv = jQuery(".file_success_msg_div");
    var fileSuccessMsg = jQuery("#file_success_msg");
    fileSuccessMsg.empty();
    fileSuccessMsgDiv.hide();

    var fileErrorMsgDiv = jQuery(".file_error_msg_div");
    var fileErrorMsgItemsDiv = $('.file_error_msg_item_div');
    fileErrorMsgItemsDiv.empty();
    fileErrorMsgDiv.hide();


    // Initialise/Reset GUI elements
    theSubmitButton.attr("disabled", "disabled");

    fileNameTxt.html(initialisingTxt);
    progressBarGraphic.width(0);
    progressBarTextDiv.html("");


    fileImport();

    function fileImport() {
        var options = {
            // target:'#output2', // target element(s) to be updated with server response
            beforeSubmit: prepareFileImport, // pre-submit callback
            success: processFileImportResponse, // post-submit callback
            type: 'post', // 'get' or 'post', override for form's 'method' attribute
            dataType: 'json'      // 'xml', 'script', or 'json' (expected server response type)
            // other available options:
            //url:       url         // override for form's 'action' attribute
            //clearForm: true,        // clear all form fields after successful submit
            //resetForm: true        // reset the form after successful submit
            // $.ajax options can be used here too, for example:
            //timeout:   3000
        };

        var fileField = $("#upload");
        fileName = getFileName(fileField.val());

        // getProgress() will recurse until uploading is over
        timeoutId = setTimeout(getProgress, updateRate);
        progressAreaDiv.show();
        // bind form using 'ajaxSubmit'
        $('#ajaxFileUploadForm').ajaxSubmit(options);
        return false;
    }

    function prepareFileImport(formData, jqForm, options) {

        // formData is an array; here we use $.param to convert it to a string to display it
        // but the form plugin does this for you automatically when it submits the data
        //  var queryString = $.param(formData);
        //alert('About to submit: \n\n' + queryString + ", file name: " + fileName);
    }

    function processFileImportResponse(responseData, statusText, xhr, $form) {
        // for normal html responses, the first argument to the success callback
        // is the XMLHttpRequest object's responseText property

        // if the ajaxSubmit method was passed an Options Object with the dataType
        // property set to 'xml' then the first argument to the success callback
        // is the XMLHttpRequest object's responseXML property

        // if the ajaxSubmit method was passed an Options Object with the dataType
        // property set to 'json' then the first argument to the success callback
        // is the json data object returned by the server
        var succeed = responseData.succeed;
        if (succeed) {
            fileSuccessMessage(responseData.message);
            complete(true);
        } else {
            fileErrorMessage([responseData.message]);
            complete(false);
        }

    }


    /**
     * Gets the upload progress from the server and then recursively calls
     * itself until the upload is complete
     */
    function getProgress() {
        jQuery.ajax({
            type: "POST",
            url: "progress.jspx?rnd=" + new Date().getTime(),
            dataType: "json",
            success: function (jsonData) {
                var aborted = jsonData.aborted;
                if (aborted) {
                    alert("uploading is aborted - " + jsonData.a);
                    abort(jsonData.abortedMessage);
                } else {
                    var percent = jsonData.completePercent;
                    totalFileSize = jsonData.totalBytes;
                    var sent = jsonData.sentBytes;

                    if (fileNameTxt.html() == initialisingTxt) {
                        fileNameTxt.html('<b>Importing:</b> ' + fileName);
                    }

                    if (sent > 0) {
                        updateProgressBarGUI(sent);
                    }

                    if (percent == "100") {
                        // The end
                        clearTimeout(timeoutId);
                    } else {
                        setTimeout(getProgress, updateRate);
                    }
                }
            },
            error: function (request, error, exception) {
                // show the error
                fileErrorMessage(["Failed to connect to the server to get importing progress"]);
            }
        });
    }


    function fileErrorMessage(errorMsgs) {
        var errorHtml = "<ul>"
        $.each(errorMsgs, function (i, msg) {
            errorHtml += "<li>" + msg + " </li>"
        });
        errorHtml += "</ul>";
        fileErrorMsgItemsDiv.empty();
        fileErrorMsgItemsDiv.append(errorHtml);
        fileErrorMsgDiv.show();
    }

    function fileSuccessMessage(succeedMsg) {
        fileSuccessMsg.html(succeedMsg);
        fileSuccessMsgDiv.show();
    }


    function abort(abortedMsg) {
        fileErrorMessage(abortedMsg);
        complete(false);
    }

    function complete(success) {
        progressAreaDiv.hide();
        theSubmitButton.attr("disabled", "");
        clearTimeout(timeoutId);
        if (doafter && typeof (doafter) == 'function') {
            doafter(success);
        }
    }

    var previousPercentComplete = 0;

    /**
     * This is called every 2 seconds to update the progress bar
     */
    function updateProgressBarGUI(bytesTransferred) {
        // if (bytesTransferred > lastUploadedSize) {
        var now = new Date().getTime() / 1000.0; // seconds
        var uploadTransferRate = bytesTransferred / (now - uploadStartTime);
        var timeRemaining = (totalFileSize - lastUploadedSize)
            / uploadTransferRate;

        // if (totalFileSize > 5242880) //if greater than 5 megabytes - slow
        // down checks to every 5 seconds
        // currentRate = 500;

        lastUploadedSize = bytesTransferred;
        lastProgressUpdate = now;
        var progress = (0.0 + lastUploadedSize) / totalFileSize;
        var percentComplete = Math.round(100 * progress);

        var timeLeft = timeRemaining;

        /*
         * This modifies the server connection frequency based on the progress
         * rate of the upload So for large files it will slow down progress
         * update checks - or for network glitches which slow and speed up
         * transfer, it will adjust the rate accordingly.
         */
        var rateModifier = 3;
        if (previousPercentComplete > 0) {
            if ((percentComplete - previousPercentComplete) < rateModifier) {
                // If less than 3% change - slow down the checks
                updateRate += 500;
            } else if (((percentComplete - previousPercentComplete) > rateModifier)
                && (updateRate > 1000)) {
                updateRate -= 500;
            }
        }
        previousPercentComplete = percentComplete;

        var totalStr = Math.round(totalFileSize / 1024); // changed to kilobytes
        if (progress && progress > 0) {
            progressBarGraphic.width(barwidth * progress);
        }
        var ptext = percentComplete + '% completed (' + formatSize(lastUploadedSize) + ' of ' + formatSize(totalFileSize) + ')';
        ptext += '&nbsp; &nbsp; Time remaining: ' + formatTime(timeLeft);
        ptext += '&nbsp; &nbsp; Transfer rate: ' + formatSize(uploadTransferRate) + " / sec";
        progressBarTextDiv.html(ptext);
    }

    /**
     * If bigger than 1MB output MB, else output KB
     */
    function formatSize(byteCount) {
        var str = Math.round(byteCount / 1024);
        if (str > 1024) {
            str = Math.round(100 * str / 1024) / 100.0;
            str += ' MB';
        } else {
            str += ' KB';
        }
        return str;
    }

    /**
     * Format seconds into a string readable format
     */
    function formatTime(seconds) {
        var str;
        if (seconds > 3600) {
            var h = Math.floor(seconds / 3600);
            seconds -= h * 3600;
            var m = Math.round(seconds / 60);
            str = h + ' hr. ' + ((m < 10) ? '0' + m : m) + ' min.';
        } else if (seconds > 60) {
            var m = Math.floor(seconds / 60);
            seconds -= m * 60;
            seconds = Math.round(seconds);
            str = m + ' min. ' + ((seconds < 10) ? '0' + seconds : seconds)
                + ' sec.';
        } else {
            str = Math.round(seconds) + ' sec.';
        }
        return str;
    }


    function getFileName(path) {
        if (path) {
            var windows = path.indexOf("\\");
            if (windows && windows > -1) {
                var lastIndex = path.lastIndexOf("\\");
                var filename = path.substring(lastIndex + 1, path.length);
                return filename;
            } else {
                // linux
                var lastIndex = path.lastIndexOf("/");
                var filename = path.substring(lastIndex + 1, path.length);
                return filename;
            }
        } else {
            return "";
        }
    }
};


/**
 * Static factory - sort of.
 */
merc.FileUpload.initialize = function (doafter) {
    return new merc.FileUpload(doafter);
}
