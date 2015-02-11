package edu.monash.merc.eddy.modc.common.captcha;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * A builder for generating a CAPTCHA image/answer pair.
 * 
 * <p>
 * Example for generating a new CAPTCHA:
 * </p>
 * 
 * <pre>
 * Captcha captcha = new Captcha.Builder(200, 50).addText().addBackground().build();
 * </pre>
 * <p>
 * Note that the <code>build()</code> must always be called last. Other methods are optional, and can sometimes be
 * repeated. For example:
 * </p>
 * 
 * <pre>
 * Captcha captcha = new Captcha.Builder(200, 50).addText().addNoise().addNoise().addNoise().addBackground().build();
 * </pre>
 * <p>
 * Adding multiple backgrounds has no affect; the last background added will simply be the one that is eventually
 * rendered.
 * </p>
 * <p>
 * To validate that <code>answerStr</code> is a correct answer to the CAPTCHA:
 * </p>
 * 
 * <code>captcha.isCorrect(answerStr);</code>
 * 
 * 
 */
public class ImgCaptcha implements Serializable {

	public static final String NAME = "imageCaptcha";
	private Builder _builder;

	private ImgCaptcha(Builder builder) {
		_builder = builder;
	}

	public static class Builder implements Serializable {

		private String code = "";

		private BufferedImage _img;

		private BufferedImage _bg;
		private boolean _addBorder = false;

		public Builder(int width, int height) {
			_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}

		/**
		 * Add a background using the default BackgroundProducer.
		 * 
		 * @return a Builder object.
		 */
		public Builder addBackground() {
			return addBackground(new TransparentBackgroundProducer());
		}

		/**
		 * Add a background using the given BackgroundProducer.
		 * 
		 * @param bgProd
		 * @return a Builder object.
		 */
		public Builder addBackground(BackgroundProducer bgProd) {
			_bg = bgProd.getBackground(_img.getWidth(), _img.getHeight());

			return this;
		}

		/**
		 * Add text to the image using the default TextProducer
		 * 
		 * @return a Builder object.
		 */
		public Builder addText() {
			return addText(new DefaultTextProducer());
		}

		/**
		 * Add text to the image using the given TextProducer
		 * 
		 * @param txtProd
		 * @return a Builder object.
		 */ 
		public Builder addText(TextProducer txtProd) {
			return addText(txtProd, new DefaultWordRenderer());
		}

		/**
		 * Display the answer on the image using the given WordRenderer.
		 * 
		 * @param wRenderer
		 * @return a Builder object.
		 */
		public Builder addText(WordRenderer wRenderer) {
			return addText(new DefaultTextProducer(), wRenderer);
		}

		public Builder addText(TextProducer txtProd, WordRenderer wRenderer) {
			code += txtProd.getText();
			wRenderer.render(code, _img);

			return this;
		}

		/**
		 * Add noise using the default NoiseProducer.
		 * 
		 * @return a Builder object.
		 */
		public Builder addNoise() {
			return this.addNoise(new CurvedLineNoiseProducer());
		}

		/**
		 * Add noise using the given NoiseProducer.
		 * 
		 * @param nProd
		 * @return a Builder object.
		 */
		public Builder addNoise(NoiseProducer nProd) {
			nProd.makeNoise(_img);
			return this;
		}

		/**
		 * Gimp the image using the default GimpyRenderer.
		 *
		 * @return a Builder object.
		 */
		public Builder gimp() {
			return gimp(new RippleGimpyRenderer());
		}

		/**
		 * Gimp the image using the given GimpyRenderer.
		 *
		 * @param gimpy
		 * @return a Builder object.
		 */
		public Builder gimp(GimpyRenderer gimpy) {
			gimpy.gimp(_img);
			return this;
		}

		/**
		 * Draw a single-pixel wide black border around the image.
		 * 
		 * @return a Builder object.
		 */
		public Builder addBorder() {
			_addBorder = true;

			return this;
		}

		/**
		 * Build the CAPTCHA. This method should always be called, and should always be called last.
		 * 
		 * @return a ImgCaptcha object.
		 */
		public ImgCaptcha build() {
			if (_bg == null) {
				_bg = new TransparentBackgroundProducer().getBackground(_img.getWidth(), _img.getHeight());
			}

			// Paint the main image over the background
			Graphics2D g = _bg.createGraphics();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g.drawImage(_img, null, null);

			// Add the border, if necessary
			if (_addBorder) {
				int width = _img.getWidth();
				int height = _img.getHeight();

				g.setColor(new Color(179, 179, 179));
				g.drawLine(0, 0, 0, width);
				g.drawLine(0, 0, width, 0);
				g.drawLine(0, height - 1, width, height - 1);
				g.drawLine(width - 1, height - 1, width - 1, 0);
			}

			_img = _bg;

			return new ImgCaptcha(this);
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[Code: ");
			sb.append(code);
			sb.append("][Image: ");
			sb.append(_img);
			sb.append("]");

			return sb.toString();
		}

		private void writeObject(ObjectOutputStream out) throws IOException {
			out.writeObject(code);
			ImageIO.write(_img, "png", ImageIO.createImageOutputStream(out));
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
			code = (String) in.readObject();
			_img = ImageIO.read(ImageIO.createImageInputStream(in));
		}
	}

	public boolean isCorrect(String answer) {
		return _builder.code.equals(answer);
	}

	public String getCode() {
		return _builder.code;
	}

	public BufferedImage getImage() {
		return _builder._img;
	}

	@Override
	public String toString() {
		return _builder.toString();
	}

	public static void main(String[] args) throws Exception {

		ImgCaptcha captcha = new Builder(200, 45).addText().addBackground(new GradiatedBackgroundProducer()).gimp().addNoise().addBorder()
				.build();

		BufferedImage img = captcha.getImage();
		File file = new File("simon.png");
		ImageIO.write(img, "png", file);

		System.out.println("code: " + captcha.getCode());
	}
}