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

package edu.monash.merc.eddy.modc.web.controller;

import edu.monash.merc.eddy.modc.common.captcha.GradiatedBackgroundProducer;
import edu.monash.merc.eddy.modc.common.captcha.ImageUtil;
import edu.monash.merc.eddy.modc.common.captcha.ImgCaptcha;
import edu.monash.merc.eddy.modc.web.conts.MConts;
import edu.monash.merc.eddy.modc.web.view.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 8/10/2014
 */
@Controller
@RequestMapping("/captcha")
public class ImageCaptchaController extends BaseController {

    @Autowired
    private ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @RequestMapping("/secode")
    public ModelAndView captchaImg(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        ImgCaptcha captcha = new ImgCaptcha.Builder(200, 45).addText().addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder().build();

        String code = captcha.getCode();
        storeInSession(request, MConts.CAPTCHA_CODE_KEY, code);
        BufferedImage img = captcha.getImage();
        //define a output stream to hold the image
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageUtil.writeImage(output, img);
        byte[] images = output.toByteArray();
        model.put("image", images);
        return new ModelAndView(imageView, model);
    }
}
