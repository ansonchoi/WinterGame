import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BufferedImageLoader{

	private BufferedImage image;
	
	public BufferedImage loadImage(String path) throws IOException{
	try{
		image = ImageIO.read(getClass().getResource(path));
	}catch(Exception e){
		e.printStackTrace();
	}
		return image;
	}

}