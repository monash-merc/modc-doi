package edu.monash.merc.eddy.modc.common.captcha;

import java.awt.image.BufferedImage;

/**
 * Interface class BackgroundProducer
 *
 * @version 2.0, 07/2011
 * @version 1.0
 *
 */
public interface BackgroundProducer {

	public BufferedImage addBackground(BufferedImage image);

	public BufferedImage getBackground(int width, int height);
}
