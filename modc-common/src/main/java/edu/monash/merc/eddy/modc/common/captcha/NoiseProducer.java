package edu.monash.merc.eddy.modc.common.captcha;

import java.awt.image.BufferedImage;

/**
 * Class NoiseProducer
 * 
 * @version 2.0, 07/2011
 * @version 1.0
 * 
 */
public interface NoiseProducer {
	public void makeNoise(BufferedImage image);
}
